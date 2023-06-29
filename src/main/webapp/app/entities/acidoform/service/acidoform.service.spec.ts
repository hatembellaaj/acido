import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IAcidoform } from '../acidoform.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../acidoform.test-samples';

import { AcidoformService, RestAcidoform } from './acidoform.service';

const requireRestSample: RestAcidoform = {
  ...sampleWithRequiredData,
  updatedate: sampleWithRequiredData.updatedate?.format(DATE_FORMAT),
  createddate: sampleWithRequiredData.createddate?.format(DATE_FORMAT),
  datenaissance: sampleWithRequiredData.datenaissance?.format(DATE_FORMAT),
  datedeces: sampleWithRequiredData.datedeces?.format(DATE_FORMAT),
};

describe('Acidoform Service', () => {
  let service: AcidoformService;
  let httpMock: HttpTestingController;
  let expectedResult: IAcidoform | IAcidoform[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AcidoformService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find('ABC').subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a Acidoform', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const acidoform = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(acidoform).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Acidoform', () => {
      const acidoform = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(acidoform).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Acidoform', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Acidoform', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Acidoform', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addAcidoformToCollectionIfMissing', () => {
      it('should add a Acidoform to an empty array', () => {
        const acidoform: IAcidoform = sampleWithRequiredData;
        expectedResult = service.addAcidoformToCollectionIfMissing([], acidoform);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(acidoform);
      });

      it('should not add a Acidoform to an array that contains it', () => {
        const acidoform: IAcidoform = sampleWithRequiredData;
        const acidoformCollection: IAcidoform[] = [
          {
            ...acidoform,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addAcidoformToCollectionIfMissing(acidoformCollection, acidoform);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Acidoform to an array that doesn't contain it", () => {
        const acidoform: IAcidoform = sampleWithRequiredData;
        const acidoformCollection: IAcidoform[] = [sampleWithPartialData];
        expectedResult = service.addAcidoformToCollectionIfMissing(acidoformCollection, acidoform);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(acidoform);
      });

      it('should add only unique Acidoform to an array', () => {
        const acidoformArray: IAcidoform[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const acidoformCollection: IAcidoform[] = [sampleWithRequiredData];
        expectedResult = service.addAcidoformToCollectionIfMissing(acidoformCollection, ...acidoformArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const acidoform: IAcidoform = sampleWithRequiredData;
        const acidoform2: IAcidoform = sampleWithPartialData;
        expectedResult = service.addAcidoformToCollectionIfMissing([], acidoform, acidoform2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(acidoform);
        expect(expectedResult).toContain(acidoform2);
      });

      it('should accept null and undefined values', () => {
        const acidoform: IAcidoform = sampleWithRequiredData;
        expectedResult = service.addAcidoformToCollectionIfMissing([], null, acidoform, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(acidoform);
      });

      it('should return initial array if no Acidoform is added', () => {
        const acidoformCollection: IAcidoform[] = [sampleWithRequiredData];
        expectedResult = service.addAcidoformToCollectionIfMissing(acidoformCollection, undefined, null);
        expect(expectedResult).toEqual(acidoformCollection);
      });
    });

    describe('compareAcidoform', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareAcidoform(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareAcidoform(entity1, entity2);
        const compareResult2 = service.compareAcidoform(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareAcidoform(entity1, entity2);
        const compareResult2 = service.compareAcidoform(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareAcidoform(entity1, entity2);
        const compareResult2 = service.compareAcidoform(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
