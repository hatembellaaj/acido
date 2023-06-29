import dayjs from 'dayjs/esm';
import { etypeobservation } from 'app/entities/enumerations/etypeobservation.model';
import { esexe } from 'app/entities/enumerations/esexe.model';
import { estatut } from 'app/entities/enumerations/estatut.model';

export interface IAcidoform {
  id: string;
  homocystinurie?: boolean | null;
  leucinose?: boolean | null;
  phenylcetonurie?: boolean | null;
  tyrosinemie?: boolean | null;
  updatedate?: dayjs.Dayjs | null;
  typeobservation?: etypeobservation | null;
  createddate?: dayjs.Dayjs | null;
  sexe?: esexe | null;
  datenaissance?: dayjs.Dayjs | null;
  statut?: estatut | null;
  ageactuelans?: number | null;
  ageactuelmois?: number | null;
  ageactueljours?: number | null;
  datedeces?: dayjs.Dayjs | null;
  agedecesan?: number | null;
  agedecesmois?: number | null;
  agedecesjours?: number | null;
}

export type NewAcidoform = Omit<IAcidoform, 'id'> & { id: null };
