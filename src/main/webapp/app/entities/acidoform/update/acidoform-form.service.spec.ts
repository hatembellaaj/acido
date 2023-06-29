import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../acidoform.test-samples';

import { AcidoformFormService } from './acidoform-form.service';

describe('Acidoform Form Service', () => {
  let service: AcidoformFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AcidoformFormService);
  });

  describe('Service methods', () => {
    describe('createAcidoformFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createAcidoformFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            homocystinurie: expect.any(Object),
            leucinose: expect.any(Object),
            phenylcetonurie: expect.any(Object),
            tyrosinemie: expect.any(Object),
            updatedate: expect.any(Object),
            typeobservation: expect.any(Object),
            createddate: expect.any(Object),
            sexe: expect.any(Object),
            datenaissance: expect.any(Object),
            statut: expect.any(Object),
            ageactuelans: expect.any(Object),
            ageactuelmois: expect.any(Object),
            ageactueljours: expect.any(Object),
            datedeces: expect.any(Object),
            agedecesan: expect.any(Object),
            agedecesmois: expect.any(Object),
            agedecesjours: expect.any(Object),
          })
        );
      });

      it('passing IAcidoform should create a new form with FormGroup', () => {
        const formGroup = service.createAcidoformFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            homocystinurie: expect.any(Object),
            leucinose: expect.any(Object),
            phenylcetonurie: expect.any(Object),
            tyrosinemie: expect.any(Object),
            updatedate: expect.any(Object),
            typeobservation: expect.any(Object),
            createddate: expect.any(Object),
            sexe: expect.any(Object),
            datenaissance: expect.any(Object),
            statut: expect.any(Object),
            ageactuelans: expect.any(Object),
            ageactuelmois: expect.any(Object),
            ageactueljours: expect.any(Object),
            datedeces: expect.any(Object),
            agedecesan: expect.any(Object),
            agedecesmois: expect.any(Object),
            agedecesjours: expect.any(Object),
          })
        );
      });
    });

    describe('getAcidoform', () => {
      it('should return NewAcidoform for default Acidoform initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createAcidoformFormGroup(sampleWithNewData);

        const acidoform = service.getAcidoform(formGroup) as any;

        expect(acidoform).toMatchObject(sampleWithNewData);
      });

      it('should return NewAcidoform for empty Acidoform initial value', () => {
        const formGroup = service.createAcidoformFormGroup();

        const acidoform = service.getAcidoform(formGroup) as any;

        expect(acidoform).toMatchObject({});
      });

      it('should return IAcidoform', () => {
        const formGroup = service.createAcidoformFormGroup(sampleWithRequiredData);

        const acidoform = service.getAcidoform(formGroup) as any;

        expect(acidoform).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IAcidoform should not enable id FormControl', () => {
        const formGroup = service.createAcidoformFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewAcidoform should disable id FormControl', () => {
        const formGroup = service.createAcidoformFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
