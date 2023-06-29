import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'structure',
        data: { pageTitle: 'Structures' },
        loadChildren: () => import('./structure/structure.module').then(m => m.StructureModule),
      },
      {
        path: 'servicestructure',
        data: { pageTitle: 'Servicestructures' },
        loadChildren: () => import('./servicestructure/servicestructure.module').then(m => m.ServicestructureModule),
      },
      {
        path: 'medecin',
        data: { pageTitle: 'Medecins' },
        loadChildren: () => import('./medecin/medecin.module').then(m => m.MedecinModule),
      },
      {
        path: 'acidoform',
        data: { pageTitle: 'Acidoforms' },
        loadChildren: () => import('./acidoform/acidoform.module').then(m => m.AcidoformModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
