import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { AcidoformFormService, AcidoformFormGroup } from './acidoform-form.service';
import { IAcidoform } from '../acidoform.model';
import { AcidoformService } from '../service/acidoform.service';
import { etypeobservation } from 'app/entities/enumerations/etypeobservation.model';
import { esexe } from 'app/entities/enumerations/esexe.model';
import { estatut } from 'app/entities/enumerations/estatut.model';

@Component({
  selector: 'jhi-acidoform-update',
  templateUrl: './acidoform-update.component.html',
})
export class AcidoformUpdateComponent implements OnInit {
  isSaving = false;
  acidoform: IAcidoform | null = null;
  etypeobservationValues = Object.keys(etypeobservation);
  esexeValues = Object.keys(esexe);
  estatutValues = Object.keys(estatut);

  editForm: AcidoformFormGroup = this.acidoformFormService.createAcidoformFormGroup();

  constructor(
    protected acidoformService: AcidoformService,
    protected acidoformFormService: AcidoformFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ acidoform }) => {
      this.acidoform = acidoform;
      if (acidoform) {
        this.updateForm(acidoform);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const acidoform = this.acidoformFormService.getAcidoform(this.editForm);
    if (acidoform.id !== null) {
      this.subscribeToSaveResponse(this.acidoformService.update(acidoform));
    } else {
      this.subscribeToSaveResponse(this.acidoformService.create(acidoform));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAcidoform>>): void {
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

  protected updateForm(acidoform: IAcidoform): void {
    this.acidoform = acidoform;
    this.acidoformFormService.resetForm(this.editForm, acidoform);
  }
}
