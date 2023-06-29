import { IStructure } from 'app/entities/structure/structure.model';

export interface IServicestructure {
  id: string;
  nom?: string | null;
  structure?: Pick<IStructure, 'id'> | null;
}

export type NewServicestructure = Omit<IServicestructure, 'id'> & { id: null };
