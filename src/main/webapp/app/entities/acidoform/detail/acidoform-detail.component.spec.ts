import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AcidoformDetailComponent } from './acidoform-detail.component';

describe('Acidoform Management Detail Component', () => {
  let comp: AcidoformDetailComponent;
  let fixture: ComponentFixture<AcidoformDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AcidoformDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ acidoform: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(AcidoformDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(AcidoformDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load acidoform on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.acidoform).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
