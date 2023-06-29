import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IServicestructure } from '../servicestructure.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../servicestructure.test-samples';

import { ServicestructureService } from './servicestructure.service';

const requireRestSample: IServicestructure = {
  ...sampleWithRequiredData,
};

describe('Servicestructure Service', () => {
  let service: ServicestructureService;
  let httpMock: HttpTestingController;
  let expectedResult: IServicestructure | IServicestructure[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ServicestructureService);
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

    it('should create a Servicestructure', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const servicestructure = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(servicestructure).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Servicestructure', () => {
      const servicestructure = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(servicestructure).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Servicestructure', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Servicestructure', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Servicestructure', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addServicestructureToCollectionIfMissing', () => {
      it('should add a Servicestructure to an empty array', () => {
        const servicestructure: IServicestructure = sampleWithRequiredData;
        expectedResult = service.addServicestructureToCollectionIfMissing([], servicestructure);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(servicestructure);
      });

      it('should not add a Servicestructure to an array that contains it', () => {
        const servicestructure: IServicestructure = sampleWithRequiredData;
        const servicestructureCollection: IServicestructure[] = [
          {
            ...servicestructure,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addServicestructureToCollectionIfMissing(servicestructureCollection, servicestructure);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Servicestructure to an array that doesn't contain it", () => {
        const servicestructure: IServicestructure = sampleWithRequiredData;
        const servicestructureCollection: IServicestructure[] = [sampleWithPartialData];
        expectedResult = service.addServicestructureToCollectionIfMissing(servicestructureCollection, servicestructure);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(servicestructure);
      });

      it('should add only unique Servicestructure to an array', () => {
        const servicestructureArray: IServicestructure[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const servicestructureCollection: IServicestructure[] = [sampleWithRequiredData];
        expectedResult = service.addServicestructureToCollectionIfMissing(servicestructureCollection, ...servicestructureArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const servicestructure: IServicestructure = sampleWithRequiredData;
        const servicestructure2: IServicestructure = sampleWithPartialData;
        expectedResult = service.addServicestructureToCollectionIfMissing([], servicestructure, servicestructure2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(servicestructure);
        expect(expectedResult).toContain(servicestructure2);
      });

      it('should accept null and undefined values', () => {
        const servicestructure: IServicestructure = sampleWithRequiredData;
        expectedResult = service.addServicestructureToCollectionIfMissing([], null, servicestructure, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(servicestructure);
      });

      it('should return initial array if no Servicestructure is added', () => {
        const servicestructureCollection: IServicestructure[] = [sampleWithRequiredData];
        expectedResult = service.addServicestructureToCollectionIfMissing(servicestructureCollection, undefined, null);
        expect(expectedResult).toEqual(servicestructureCollection);
      });
    });

    describe('compareServicestructure', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareServicestructure(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareServicestructure(entity1, entity2);
        const compareResult2 = service.compareServicestructure(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareServicestructure(entity1, entity2);
        const compareResult2 = service.compareServicestructure(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareServicestructure(entity1, entity2);
        const compareResult2 = service.compareServicestructure(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
