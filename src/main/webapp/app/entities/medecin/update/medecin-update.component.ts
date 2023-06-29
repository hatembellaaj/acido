import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { MedecinFormService, MedecinFormGroup } from './medecin-form.service';
import { IMedecin } from '../medecin.model';
import { MedecinService } from '../service/medecin.service';
import { IStructure } from 'app/entities/structure/structure.model';
import { StructureService } from 'app/entities/structure/service/structure.service';
import { especialite } from 'app/entities/enumerations/especialite.model';

@Component({
  selector: 'jhi-medecin-update',
  templateUrl: './medecin-update.component.html',
})
export class MedecinUpdateComponent implements OnInit {
  isSaving = false;
  medecin: IMedecin | null = null;
  especialiteValues = Object.keys(especialite);

  structuresSharedCollection: IStructure[] = [];

  editForm: MedecinFormGroup = this.medecinFormService.createMedecinFormGroup();

  constructor(
    protected medecinService: MedecinService,
    protected medecinFormService: MedecinFormService,
    protected structureService: StructureService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareStructure = (o1: IStructure | null, o2: IStructure | null): boolean => this.structureService.compareStructure(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ medecin }) => {
      this.medecin = medecin;
      if (medecin) {
        this.updateForm(medecin);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const medecin = this.medecinFormService.getMedecin(this.editForm);
    if (medecin.id !== null) {
      this.subscribeToSaveResponse(this.medecinService.update(medecin));
    } else {
      this.subscribeToSaveResponse(this.medecinService.create(medecin));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMedecin>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(medecin: IMedecin): void {
    this.medecin = medecin;
    this.medecinFormService.resetForm(this.editForm, medecin);

    this.structuresSharedCollection = this.structureService.addStructureToCollectionIfMissing<IStructure>(
      this.structuresSharedCollection,
      medecin.structure
    );
  }

  protected loadRelationshipsOptions(): void {
    this.structureService
      .query()
      .pipe(map((res: HttpResponse<IStructure[]>) => res.body ?? []))
      .pipe(
        map((structures: IStructure[]) =>
          this.structureService.addStructureToCollectionIfMissing<IStructure>(structures, this.medecin?.structure)
        )
      )
      .subscribe((structures: IStructure[]) => (this.structuresSharedCollection = structures));
  }
}
