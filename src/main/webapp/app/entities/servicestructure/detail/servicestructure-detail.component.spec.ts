import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ServicestructureDetailComponent } from './servicestructure-detail.component';

describe('Servicestructure Management Detail Component', () => {
  let comp: ServicestructureDetailComponent;
  let fixture: ComponentFixture<ServicestructureDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ServicestructureDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ servicestructure: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(ServicestructureDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(ServicestructureDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load servicestructure on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.servicestructure).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
