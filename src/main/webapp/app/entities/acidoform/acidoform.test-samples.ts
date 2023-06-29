import dayjs from 'dayjs/esm';

import { etypeobservation } from 'app/entities/enumerations/etypeobservation.model';
import { esexe } from 'app/entities/enumerations/esexe.model';
import { estatut } from 'app/entities/enumerations/estatut.model';

import { IAcidoform, NewAcidoform } from './acidoform.model';

export const sampleWithRequiredData: IAcidoform = {
  id: '7d2b273b-0973-4f0f-a8e3-6dbbcbbc9d18',
};

export const sampleWithPartialData: IAcidoform = {
  id: '46126821-20aa-4bb3-b0fb-db6d58288312',
  leucinose: false,
  tyrosinemie: true,
  updatedate: dayjs('2023-06-29'),
  sexe: esexe['F'],
  ageactuelmois: 94335,
  datedeces: dayjs('2023-06-28'),
  agedecesmois: 75648,
  agedecesjours: 69199,
};

export const sampleWithFullData: IAcidoform = {
  id: 'eb801eb7-3ca5-40e3-ac6e-db78958294e3',
  homocystinurie: true,
  leucinose: false,
  phenylcetonurie: true,
  tyrosinemie: true,
  updatedate: dayjs('2023-06-29'),
  typeobservation: etypeobservation['P'],
  createddate: dayjs('2023-06-28'),
  sexe: esexe['F'],
  datenaissance: dayjs('2023-06-28'),
  statut: estatut['ENCORE_SUIVI'],
  ageactuelans: 77666,
  ageactuelmois: 66167,
  ageactueljours: 95804,
  datedeces: dayjs('2023-06-29'),
  agedecesan: 91069,
  agedecesmois: 29706,
  agedecesjours: 53661,
};

export const sampleWithNewData: NewAcidoform = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
