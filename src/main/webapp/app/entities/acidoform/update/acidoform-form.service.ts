import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IAcidoform, NewAcidoform } from '../acidoform.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IAcidoform for edit and NewAcidoformFormGroupInput for create.
 */
type AcidoformFormGroupInput = IAcidoform | PartialWithRequiredKeyOf<NewAcidoform>;

type AcidoformFormDefaults = Pick<NewAcidoform, 'id' | 'homocystinurie' | 'leucinose' | 'phenylcetonurie' | 'tyrosinemie'>;

type AcidoformFormGroupContent = {
  id: FormControl<IAcidoform['id'] | NewAcidoform['id']>;
  homocystinurie: FormControl<IAcidoform['homocystinurie']>;
  leucinose: FormControl<IAcidoform['leucinose']>;
  phenylcetonurie: FormControl<IAcidoform['phenylcetonurie']>;
  tyrosinemie: FormControl<IAcidoform['tyrosinemie']>;
  updatedate: FormControl<IAcidoform['updatedate']>;
  typeobservation: FormControl<IAcidoform['typeobservation']>;
  createddate: FormControl<IAcidoform['createddate']>;
  sexe: FormControl<IAcidoform['sexe']>;
  datenaissance: FormControl<IAcidoform['datenaissance']>;
  statut: FormControl<IAcidoform['statut']>;
  ageactuelans: FormControl<IAcidoform['ageactuelans']>;
  ageactuelmois: FormControl<IAcidoform['ageactuelmois']>;
  ageactueljours: FormControl<IAcidoform['ageactueljours']>;
  datedeces: FormControl<IAcidoform['datedeces']>;
  agedecesan: FormControl<IAcidoform['agedecesan']>;
  agedecesmois: FormControl<IAcidoform['agedecesmois']>;
  agedecesjours: FormControl<IAcidoform['agedecesjours']>;
};

export type AcidoformFormGroup = FormGroup<AcidoformFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class AcidoformFormService {
  createAcidoformFormGroup(acidoform: AcidoformFormGroupInput = { id: null }): AcidoformFormGroup {
    const acidoformRawValue = {
      ...this.getFormDefaults(),
      ...acidoform,
    };
    return new FormGroup<AcidoformFormGroupContent>({
      id: new FormControl(
        { value: acidoformRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      homocystinurie: new FormControl(acidoformRawValue.homocystinurie),
      leucinose: new FormControl(acidoformRawValue.leucinose),
      phenylcetonurie: new FormControl(acidoformRawValue.phenylcetonurie),
      tyrosinemie: new FormControl(acidoformRawValue.tyrosinemie),
      updatedate: new FormControl(acidoformRawValue.updatedate),
      typeobservation: new FormControl(acidoformRawValue.typeobservation),
      createddate: new FormControl(acidoformRawValue.createddate),
      sexe: new FormControl(acidoformRawValue.sexe),
      datenaissance: new FormControl(acidoformRawValue.datenaissance),
      statut: new FormControl(acidoformRawValue.statut),
      ageactuelans: new FormControl(acidoformRawValue.ageactuelans),
      ageactuelmois: new FormControl(acidoformRawValue.ageactuelmois),
      ageactueljours: new FormControl(acidoformRawValue.ageactueljours),
      datedeces: new FormControl(acidoformRawValue.datedeces),
      agedecesan: new FormControl(acidoformRawValue.agedecesan),
      agedecesmois: new FormControl(acidoformRawValue.agedecesmois),
      agedecesjours: new FormControl(acidoformRawValue.agedecesjours),
    });
  }

  getAcidoform(form: AcidoformFormGroup): IAcidoform | NewAcidoform {
    return form.getRawValue() as IAcidoform | NewAcidoform;
  }

  resetForm(form: AcidoformFormGroup, acidoform: AcidoformFormGroupInput): void {
    const acidoformRawValue = { ...this.getFormDefaults(), ...acidoform };
    form.reset(
      {
        ...acidoformRawValue,
        id: { value: acidoformRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): AcidoformFormDefaults {
    return {
      id: null,
      homocystinurie: false,
      leucinose: false,
      phenylcetonurie: false,
      tyrosinemie: false,
    };
  }
}
