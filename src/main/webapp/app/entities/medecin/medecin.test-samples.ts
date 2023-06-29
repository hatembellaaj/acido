import { especialite } from 'app/entities/enumerations/especialite.model';

import { IMedecin, NewMedecin } from './medecin.model';

export const sampleWithRequiredData: IMedecin = {
  id: '64b48989-4282-411a-9bd7-b2016cb6c5a3',
  nom: 'Wisconsin CSS quantify',
};

export const sampleWithPartialData: IMedecin = {
  id: '04b41e4f-d92a-4a48-b440-7d98a67e22f0',
  nom: 'Principal',
  specialite: especialite['OPHTALMOLOGIE'],
  autrespecialite: 'Robust Usability Administrator',
};

export const sampleWithFullData: IMedecin = {
  id: '1c59a287-21a4-42d1-a4de-68ed648c2233',
  nom: 'brand strategize withdrawal',
  prenom: 'help-desk Analyst',
  specialite: especialite['PEDIATRIE'],
  autrespecialite: 'Tenge Salad evolve',
};

export const sampleWithNewData: NewMedecin = {
  nom: 'Island',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
