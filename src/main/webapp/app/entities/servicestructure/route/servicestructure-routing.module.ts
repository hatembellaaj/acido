import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ServicestructureComponent } from '../list/servicestructure.component';
import { ServicestructureDetailComponent } from '../detail/servicestructure-detail.component';
import { ServicestructureUpdateComponent } from '../update/servicestructure-update.component';
import { ServicestructureRoutingResolveService } from './servicestructure-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const servicestructureRoute: Routes = [
  {
    path: '',
    component: ServicestructureComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ServicestructureDetailComponent,
    resolve: {
      servicestructure: ServicestructureRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ServicestructureUpdateComponent,
    resolve: {
      servicestructure: ServicestructureRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ServicestructureUpdateComponent,
    resolve: {
      servicestructure: ServicestructureRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(servicestructureRoute)],
  exports: [RouterModule],
})
export class ServicestructureRoutingModule {}
