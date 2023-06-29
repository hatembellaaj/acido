import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { AcidoformComponent } from '../list/acidoform.component';
import { AcidoformDetailComponent } from '../detail/acidoform-detail.component';
import { AcidoformUpdateComponent } from '../update/acidoform-update.component';
import { AcidoformRoutingResolveService } from './acidoform-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const acidoformRoute: Routes = [
  {
    path: '',
    component: AcidoformComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AcidoformDetailComponent,
    resolve: {
      acidoform: AcidoformRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AcidoformUpdateComponent,
    resolve: {
      acidoform: AcidoformRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AcidoformUpdateComponent,
    resolve: {
      acidoform: AcidoformRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(acidoformRoute)],
  exports: [RouterModule],
})
export class AcidoformRoutingModule {}
