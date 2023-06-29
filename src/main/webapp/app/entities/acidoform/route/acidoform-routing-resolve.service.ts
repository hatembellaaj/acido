import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAcidoform } from '../acidoform.model';
import { AcidoformService } from '../service/acidoform.service';

@Injectable({ providedIn: 'root' })
export class AcidoformRoutingResolveService implements Resolve<IAcidoform | null> {
  constructor(protected service: AcidoformService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAcidoform | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((acidoform: HttpResponse<IAcidoform>) => {
          if (acidoform.body) {
            return of(acidoform.body);
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
