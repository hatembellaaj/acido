import { estructure } from 'app/entities/enumerations/estructure.model';

import { IStructure, NewStructure } from './structure.model';

export const sampleWithRequiredData: IStructure = {
  id: '879efe5d-3ba4-482c-a7ba-68cdab42d932',
  nom: 'primary hack',
};

export const sampleWithPartialData: IStructure = {
  id: 'ded2b9b7-7c49-4395-9e7d-752805dae1ae',
  nom: 'calculating Cape',
};

export const sampleWithFullData: IStructure = {
  id: '163aee43-fd60-42fb-9e72-a33d74753209',
  nom: 'Generic Avon Multi-tiered',
  typestructure: estructure['STRUCTURE_DE_SANTE_PUBLIQUE'],
};

export const sampleWithNewData: NewStructure = {
  nom: 'National Bacon fresh-thinking',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
