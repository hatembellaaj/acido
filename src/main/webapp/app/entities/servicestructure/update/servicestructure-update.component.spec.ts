import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ServicestructureFormService } from './servicestructure-form.service';
import { ServicestructureService } from '../service/servicestructure.service';
import { IServicestructure } from '../servicestructure.model';
import { IStructure } from 'app/entities/structure/structure.model';
import { StructureService } from 'app/entities/structure/service/structure.service';

import { ServicestructureUpdateComponent } from './servicestructure-update.component';

describe('Servicestructure Management Update Component', () => {
  let comp: ServicestructureUpdateComponent;
  let fixture: ComponentFixture<ServicestructureUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let servicestructureFormService: ServicestructureFormService;
  let servicestructureService: ServicestructureService;
  let structureService: StructureService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [ServicestructureUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(ServicestructureUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ServicestructureUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    servicestructureFormService = TestBed.inject(ServicestructureFormService);
    servicestructureService = TestBed.inject(ServicestructureService);
    structureService = TestBed.inject(StructureService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Structure query and add missing value', () => {
      const servicestructure: IServicestructure = { id: 'CBA' };
      const structure: IStructure = { id: '2443f4f9-8093-4be3-876c-18ab6cd792a3' };
      servicestructure.structure = structure;

      const structureCollection: IStructure[] = [{ id: '537cd3d6-87ba-4439-8de0-91a3666a84ad' }];
      jest.spyOn(structureService, 'query').mockReturnValue(of(new HttpResponse({ body: structureCollection })));
      const additionalStructures = [structure];
      const expectedCollection: IStructure[] = [...additionalStructures, ...structureCollection];
      jest.spyOn(structureService, 'addStructureToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ servicestructure });
      comp.ngOnInit();

      expect(structureService.query).toHaveBeenCalled();
      expect(structureService.addStructureToCollectionIfMissing).toHaveBeenCalledWith(
        structureCollection,
        ...additionalStructures.map(expect.objectContaining)
      );
      expect(comp.structuresSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const servicestructure: IServicestructure = { id: 'CBA' };
      const structure: IStructure = { id: 'd8e34981-24b9-4476-94b9-c6203bb318dd' };
      servicestructure.structure = structure;

      activatedRoute.data = of({ servicestructure });
      comp.ngOnInit();

      expect(comp.structuresSharedCollection).toContain(structure);
      expect(comp.servicestructure).toEqual(servicestructure);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IServicestructure>>();
      const servicestructure = { id: 'ABC' };
      jest.spyOn(servicestructureFormService, 'getServicestructure').mockReturnValue(servicestructure);
      jest.spyOn(servicestructureService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ servicestructure });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: servicestructure }));
      saveSubject.complete();

      // THEN
      expect(servicestructureFormService.getServicestructure).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(servicestructureService.update).toHaveBeenCalledWith(expect.objectContaining(servicestructure));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IServicestructure>>();
      const servicestructure = { id: 'ABC' };
      jest.spyOn(servicestructureFormService, 'getServicestructure').mockReturnValue({ id: null });
      jest.spyOn(servicestructureService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ servicestructure: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: servicestructure }));
      saveSubject.complete();

      // THEN
      expect(servicestructureFormService.getServicestructure).toHaveBeenCalled();
      expect(servicestructureService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IServicestructure>>();
      const servicestructure = { id: 'ABC' };
      jest.spyOn(servicestructureService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ servicestructure });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(servicestructureService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareStructure', () => {
      it('Should forward to structureService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(structureService, 'compareStructure');
        comp.compareStructure(entity, entity2);
        expect(structureService.compareStructure).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
