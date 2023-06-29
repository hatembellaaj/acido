import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IServicestructure, NewServicestructure } from '../servicestructure.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IServicestructure for edit and NewServicestructureFormGroupInput for create.
 */
type ServicestructureFormGroupInput = IServicestructure | PartialWithRequiredKeyOf<NewServicestructure>;

type ServicestructureFormDefaults = Pick<NewServicestructure, 'id'>;

type ServicestructureFormGroupContent = {
  id: FormControl<IServicestructure['id'] | NewServicestructure['id']>;
  nom: FormControl<IServicestructure['nom']>;
  structure: FormControl<IServicestructure['structure']>;
};

export type ServicestructureFormGroup = FormGroup<ServicestructureFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ServicestructureFormService {
  createServicestructureFormGroup(servicestructure: ServicestructureFormGroupInput = { id: null }): ServicestructureFormGroup {
    const servicestructureRawValue = {
      ...this.getFormDefaults(),
      ...servicestructure,
    };
    return new FormGroup<ServicestructureFormGroupContent>({
      id: new FormControl(
        { value: servicestructureRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      nom: new FormControl(servicestructureRawValue.nom, {
        validators: [Validators.required],
      }),
      structure: new FormControl(servicestructureRawValue.structure),
    });
  }

  getServicestructure(form: ServicestructureFormGroup): IServicestructure | NewServicestructure {
    return form.getRawValue() as IServicestructure | NewServicestructure;
  }

  resetForm(form: ServicestructureFormGroup, servicestructure: ServicestructureFormGroupInput): void {
    const servicestructureRawValue = { ...this.getFormDefaults(), ...servicestructure };
    form.reset(
      {
        ...servicestructureRawValue,
        id: { value: servicestructureRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): ServicestructureFormDefaults {
    return {
      id: null,
    };
  }
}
