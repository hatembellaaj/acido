import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { MedecinFormService } from './medecin-form.service';
import { MedecinService } from '../service/medecin.service';
import { IMedecin } from '../medecin.model';
import { IStructure } from 'app/entities/structure/structure.model';
import { StructureService } from 'app/entities/structure/service/structure.service';

import { MedecinUpdateComponent } from './medecin-update.component';

describe('Medecin Management Update Component', () => {
  let comp: MedecinUpdateComponent;
  let fixture: ComponentFixture<MedecinUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let medecinFormService: MedecinFormService;
  let medecinService: MedecinService;
  let structureService: StructureService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [MedecinUpdateComponent],
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
      .overrideTemplate(MedecinUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(MedecinUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    medecinFormService = TestBed.inject(MedecinFormService);
    medecinService = TestBed.inject(MedecinService);
    structureService = TestBed.inject(StructureService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Structure query and add missing value', () => {
      const medecin: IMedecin = { id: 'CBA' };
      const structure: IStructure = { id: '0cd84c73-f387-4ed6-b2db-982777fd5d78' };
      medecin.structure = structure;

      const structureCollection: IStructure[] = [{ id: '7e42046c-0f50-4461-8da7-e15994ead9d2' }];
      jest.spyOn(structureService, 'query').mockReturnValue(of(new HttpResponse({ body: structureCollection })));
      const additionalStructures = [structure];
      const expectedCollection: IStructure[] = [...additionalStructures, ...structureCollection];
      jest.spyOn(structureService, 'addStructureToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ medecin });
      comp.ngOnInit();

      expect(structureService.query).toHaveBeenCalled();
      expect(structureService.addStructureToCollectionIfMissing).toHaveBeenCalledWith(
        structureCollection,
        ...additionalStructures.map(expect.objectContaining)
      );
      expect(comp.structuresSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const medecin: IMedecin = { id: 'CBA' };
      const structure: IStructure = { id: '868a5b1e-c788-4780-945f-8a374c7ed4bf' };
      medecin.structure = structure;

      activatedRoute.data = of({ medecin });
      comp.ngOnInit();

      expect(comp.structuresSharedCollection).toContain(structure);
      expect(comp.medecin).toEqual(medecin);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMedecin>>();
      const medecin = { id: 'ABC' };
      jest.spyOn(medecinFormService, 'getMedecin').mockReturnValue(medecin);
      jest.spyOn(medecinService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ medecin });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: medecin }));
      saveSubject.complete();

      // THEN
      expect(medecinFormService.getMedecin).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(medecinService.update).toHaveBeenCalledWith(expect.objectContaining(medecin));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMedecin>>();
      const medecin = { id: 'ABC' };
      jest.spyOn(medecinFormService, 'getMedecin').mockReturnValue({ id: null });
      jest.spyOn(medecinService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ medecin: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: medecin }));
      saveSubject.complete();

      // THEN
      expect(medecinFormService.getMedecin).toHaveBeenCalled();
      expect(medecinService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMedecin>>();
      const medecin = { id: 'ABC' };
      jest.spyOn(medecinService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ medecin });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(medecinService.update).toHaveBeenCalled();
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
