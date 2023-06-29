import { IServicestructure, NewServicestructure } from './servicestructure.model';

export const sampleWithRequiredData: IServicestructure = {
  id: 'fb20405d-5479-48b5-9f31-8cb1beb1037e',
  nom: 'Forward',
};

export const sampleWithPartialData: IServicestructure = {
  id: 'ac26f23e-7434-45b1-b9e8-d464e239aae6',
  nom: 'encompassing',
};

export const sampleWithFullData: IServicestructure = {
  id: '8a2210d1-d62c-4b7b-961e-e8b32af48b53',
  nom: 'Grenadines collaborative',
};

export const sampleWithNewData: NewServicestructure = {
  nom: 'grey Oregon orchid',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
