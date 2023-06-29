import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IServicestructure } from '../servicestructure.model';

@Component({
  selector: 'jhi-servicestructure-detail',
  templateUrl: './servicestructure-detail.component.html',
})
export class ServicestructureDetailComponent implements OnInit {
  servicestructure: IServicestructure | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ servicestructure }) => {
      this.servicestructure = servicestructure;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
