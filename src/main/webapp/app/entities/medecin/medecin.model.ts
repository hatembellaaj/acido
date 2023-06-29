import { IStructure } from 'app/entities/structure/structure.model';
import { especialite } from 'app/entities/enumerations/especialite.model';

export interface IMedecin {
  id: string;
  nom?: string | null;
  prenom?: string | null;
  specialite?: especialite | null;
  autrespecialite?: string | null;
  structure?: Pick<IStructure, 'id'> | null;
}

export type NewMedecin = Omit<IMedecin, 'id'> & { id: null };
