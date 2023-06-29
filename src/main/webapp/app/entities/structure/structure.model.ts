import { estructure } from 'app/entities/enumerations/estructure.model';

export interface IStructure {
  id: string;
  nom?: string | null;
  typestructure?: estructure | null;
}

export type NewStructure = Omit<IStructure, 'id'> & { id: null };
