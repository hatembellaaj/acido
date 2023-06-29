import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { ServicestructureFormService, ServicestructureFormGroup } from './servicestructure-form.service';
import { IServicestructure } from '../servicestructure.model';
import { ServicestructureService } from '../service/servicestructure.service';
import { IStructure } from 'app/entities/structure/structure.model';
import { StructureService } from 'app/entities/structure/service/structure.service';

@Component({
  selector: 'jhi-servicestructure-update',
  templateUrl: './servicestructure-update.component.html',
})
export class ServicestructureUpdateComponent implements OnInit {
  isSaving = false;
  servicestructure: IServicestructure | null = null;

  structuresSharedCollection: IStructure[] = [];

  editForm: ServicestructureFormGroup = this.servicestructureFormService.createServicestructureFormGroup();

  constructor(
    protected servicestructureService: ServicestructureService,
    protected servicestructureFormService: ServicestructureFormService,
    protected structureService: StructureService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareStructure = (o1: IStructure | null, o2: IStructure | null): boolean => this.structureService.compareStructure(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ servicestructure }) => {
      this.servicestructure = servicestructure;
      if (servicestructure) {
        this.updateForm(servicestructure);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const servicestructure = this.servicestructureFormService.getServicestructure(this.editForm);
    if (servicestructure.id !== null) {
      this.subscribeToSaveResponse(this.servicestructureService.update(servicestructure));
    } else {
      this.subscribeToSaveResponse(this.servicestructureService.create(servicestructure));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IServicestructure>>): void {
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

  protected updateForm(servicestructure: IServicestructure): void {
    this.servicestructure = servicestructure;
    this.servicestructureFormService.resetForm(this.editForm, servicestructure);

    this.structuresSharedCollection = this.structureService.addStructureToCollectionIfMissing<IStructure>(
      this.structuresSharedCollection,
      servicestructure.structure
    );
  }

  protected loadRelationshipsOptions(): void {
    this.structureService
      .query()
      .pipe(map((res: HttpResponse<IStructure[]>) => res.body ?? []))
      .pipe(
        map((structures: IStructure[]) =>
          this.structureService.addStructureToCollectionIfMissing<IStructure>(structures, this.servicestructure?.structure)
        )
      )
      .subscribe((structures: IStructure[]) => (this.structuresSharedCollection = structures));
  }
}
