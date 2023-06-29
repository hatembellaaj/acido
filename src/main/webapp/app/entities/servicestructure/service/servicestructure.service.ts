import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IServicestructure, NewServicestructure } from '../servicestructure.model';

export type PartialUpdateServicestructure = Partial<IServicestructure> & Pick<IServicestructure, 'id'>;

export type EntityResponseType = HttpResponse<IServicestructure>;
export type EntityArrayResponseType = HttpResponse<IServicestructure[]>;

@Injectable({ providedIn: 'root' })
export class ServicestructureService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/servicestructures');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(servicestructure: NewServicestructure): Observable<EntityResponseType> {
    return this.http.post<IServicestructure>(this.resourceUrl, servicestructure, { observe: 'response' });
  }

  update(servicestructure: IServicestructure): Observable<EntityResponseType> {
    return this.http.put<IServicestructure>(
      `${this.resourceUrl}/${this.getServicestructureIdentifier(servicestructure)}`,
      servicestructure,
      { observe: 'response' }
    );
  }

  partialUpdate(servicestructure: PartialUpdateServicestructure): Observable<EntityResponseType> {
    return this.http.patch<IServicestructure>(
      `${this.resourceUrl}/${this.getServicestructureIdentifier(servicestructure)}`,
      servicestructure,
      { observe: 'response' }
    );
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IServicestructure>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IServicestructure[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getServicestructureIdentifier(servicestructure: Pick<IServicestructure, 'id'>): string {
    return servicestructure.id;
  }

  compareServicestructure(o1: Pick<IServicestructure, 'id'> | null, o2: Pick<IServicestructure, 'id'> | null): boolean {
    return o1 && o2 ? this.getServicestructureIdentifier(o1) === this.getServicestructureIdentifier(o2) : o1 === o2;
  }

  addServicestructureToCollectionIfMissing<Type extends Pick<IServicestructure, 'id'>>(
    servicestructureCollection: Type[],
    ...servicestructuresToCheck: (Type | null | undefined)[]
  ): Type[] {
    const servicestructures: Type[] = servicestructuresToCheck.filter(isPresent);
    if (servicestructures.length > 0) {
      const servicestructureCollectionIdentifiers = servicestructureCollection.map(
        servicestructureItem => this.getServicestructureIdentifier(servicestructureItem)!
      );
      const servicestructuresToAdd = servicestructures.filter(servicestructureItem => {
        const servicestructureIdentifier = this.getServicestructureIdentifier(servicestructureItem);
        if (servicestructureCollectionIdentifiers.includes(servicestructureIdentifier)) {
          return false;
        }
        servicestructureCollectionIdentifiers.push(servicestructureIdentifier);
        return true;
      });
      return [...servicestructuresToAdd, ...servicestructureCollection];
    }
    return servicestructureCollection;
  }
}
