import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IServicestructure } from '../servicestructure.model';
import { ServicestructureService } from '../service/servicestructure.service';

@Injectable({ providedIn: 'root' })
export class ServicestructureRoutingResolveService implements Resolve<IServicestructure | null> {
  constructor(protected service: ServicestructureService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IServicestructure | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((servicestructure: HttpResponse<IServicestructure>) => {
          if (servicestructure.body) {
            return of(servicestructure.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(null);
  }
}
