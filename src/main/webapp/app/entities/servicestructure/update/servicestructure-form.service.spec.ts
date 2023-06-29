import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../servicestructure.test-samples';

import { ServicestructureFormService } from './servicestructure-form.service';

describe('Servicestructure Form Service', () => {
  let service: ServicestructureFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServicestructureFormService);
  });

  describe('Service methods', () => {
    describe('createServicestructureFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createServicestructureFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            nom: expect.any(Object),
            structure: expect.any(Object),
          })
        );
      });

      it('passing IServicestructure should create a new form with FormGroup', () => {
        const formGroup = service.createServicestructureFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            nom: expect.any(Object),
            structure: expect.any(Object),
          })
        );
      });
    });

    describe('getServicestructure', () => {
      it('should return NewServicestructure for default Servicestructure initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createServicestructureFormGroup(sampleWithNewData);

        const servicestructure = service.getServicestructure(formGroup) as any;

        expect(servicestructure).toMatchObject(sampleWithNewData);
      });

      it('should return NewServicestructure for empty Servicestructure initial value', () => {
        const formGroup = service.createServicestructureFormGroup();

        const servicestructure = service.getServicestructure(formGroup) as any;

        expect(servicestructure).toMatchObject({});
      });

      it('should return IServicestructure', () => {
        const formGroup = service.createServicestructureFormGroup(sampleWithRequiredData);

        const servicestructure = service.getServicestructure(formGroup) as any;

        expect(servicestructure).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IServicestructure should not enable id FormControl', () => {
        const formGroup = service.createServicestructureFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewServicestructure should disable id FormControl', () => {
        const formGroup = service.createServicestructureFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
