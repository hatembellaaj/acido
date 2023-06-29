import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAcidoform } from '../acidoform.model';

@Component({
  selector: 'jhi-acidoform-detail',
  templateUrl: './acidoform-detail.component.html',
})
export class AcidoformDetailComponent implements OnInit {
  acidoform: IAcidoform | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ acidoform }) => {
      this.acidoform = acidoform;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
