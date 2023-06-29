import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAcidoform, NewAcidoform } from '../acidoform.model';

export type PartialUpdateAcidoform = Partial<IAcidoform> & Pick<IAcidoform, 'id'>;

type RestOf<T extends IAcidoform | NewAcidoform> = Omit<T, 'updatedate' | 'createddate' | 'datenaissance' | 'datedeces'> & {
  updatedate?: string | null;
  createddate?: string | null;
  datenaissance?: string | null;
  datedeces?: string | null;
};

export type RestAcidoform = RestOf<IAcidoform>;

export type NewRestAcidoform = RestOf<NewAcidoform>;

export type PartialUpdateRestAcidoform = RestOf<PartialUpdateAcidoform>;

export type EntityResponseType = HttpResponse<IAcidoform>;
export type EntityArrayResponseType = HttpResponse<IAcidoform[]>;

@Injectable({ providedIn: 'root' })
export class AcidoformService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/acidoforms');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(acidoform: NewAcidoform): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(acidoform);
    return this.http
      .post<RestAcidoform>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(acidoform: IAcidoform): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(acidoform);
    return this.http
      .put<RestAcidoform>(`${this.resourceUrl}/${this.getAcidoformIdentifier(acidoform)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(acidoform: PartialUpdateAcidoform): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(acidoform);
    return this.http
      .patch<RestAcidoform>(`${this.resourceUrl}/${this.getAcidoformIdentifier(acidoform)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestAcidoform>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestAcidoform[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getAcidoformIdentifier(acidoform: Pick<IAcidoform, 'id'>): string {
    return acidoform.id;
  }

  compareAcidoform(o1: Pick<IAcidoform, 'id'> | null, o2: Pick<IAcidoform, 'id'> | null): boolean {
    return o1 && o2 ? this.getAcidoformIdentifier(o1) === this.getAcidoformIdentifier(o2) : o1 === o2;
  }

  addAcidoformToCollectionIfMissing<Type extends Pick<IAcidoform, 'id'>>(
    acidoformCollection: Type[],
    ...acidoformsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const acidoforms: Type[] = acidoformsToCheck.filter(isPresent);
    if (acidoforms.length > 0) {
      const acidoformCollectionIdentifiers = acidoformCollection.map(acidoformItem => this.getAcidoformIdentifier(acidoformItem)!);
      const acidoformsToAdd = acidoforms.filter(acidoformItem => {
        const acidoformIdentifier = this.getAcidoformIdentifier(acidoformItem);
        if (acidoformCollectionIdentifiers.includes(acidoformIdentifier)) {
          return false;
        }
        acidoformCollectionIdentifiers.push(acidoformIdentifier);
        return true;
      });
      return [...acidoformsToAdd, ...acidoformCollection];
    }
    return acidoformCollection;
  }

  protected convertDateFromClient<T extends IAcidoform | NewAcidoform | PartialUpdateAcidoform>(acidoform: T): RestOf<T> {
    return {
      ...acidoform,
      updatedate: acidoform.updatedate?.format(DATE_FORMAT) ?? null,
      createddate: acidoform.createddate?.format(DATE_FORMAT) ?? null,
      datenaissance: acidoform.datenaissance?.format(DATE_FORMAT) ?? null,
      datedeces: acidoform.datedeces?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restAcidoform: RestAcidoform): IAcidoform {
    return {
      ...restAcidoform,
      updatedate: restAcidoform.updatedate ? dayjs(restAcidoform.updatedate) : undefined,
      createddate: restAcidoform.createddate ? dayjs(restAcidoform.createddate) : undefined,
      datenaissance: restAcidoform.datenaissance ? dayjs(restAcidoform.datenaissance) : undefined,
      datedeces: restAcidoform.datedeces ? dayjs(restAcidoform.datedeces) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestAcidoform>): HttpResponse<IAcidoform> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestAcidoform[]>): HttpResponse<IAcidoform[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
