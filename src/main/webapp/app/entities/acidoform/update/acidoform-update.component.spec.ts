import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { AcidoformFormService } from './acidoform-form.service';
import { AcidoformService } from '../service/acidoform.service';
import { IAcidoform } from '../acidoform.model';

import { AcidoformUpdateComponent } from './acidoform-update.component';

describe('Acidoform Management Update Component', () => {
  let comp: AcidoformUpdateComponent;
  let fixture: ComponentFixture<AcidoformUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let acidoformFormService: AcidoformFormService;
  let acidoformService: AcidoformService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [AcidoformUpdateComponent],
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
      .overrideTemplate(AcidoformUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AcidoformUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    acidoformFormService = TestBed.inject(AcidoformFormService);
    acidoformService = TestBed.inject(AcidoformService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const acidoform: IAcidoform = { id: 'CBA' };

      activatedRoute.data = of({ acidoform });
      comp.ngOnInit();

      expect(comp.acidoform).toEqual(acidoform);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAcidoform>>();
      const acidoform = { id: 'ABC' };
      jest.spyOn(acidoformFormService, 'getAcidoform').mockReturnValue(acidoform);
      jest.spyOn(acidoformService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ acidoform });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: acidoform }));
      saveSubject.complete();

      // THEN
      expect(acidoformFormService.getAcidoform).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(acidoformService.update).toHaveBeenCalledWith(expect.objectContaining(acidoform));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAcidoform>>();
      const acidoform = { id: 'ABC' };
      jest.spyOn(acidoformFormService, 'getAcidoform').mockReturnValue({ id: null });
      jest.spyOn(acidoformService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ acidoform: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: acidoform }));
      saveSubject.complete();

      // THEN
      expect(acidoformFormService.getAcidoform).toHaveBeenCalled();
      expect(acidoformService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAcidoform>>();
      const acidoform = { id: 'ABC' };
      jest.spyOn(acidoformService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ acidoform });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(acidoformService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
