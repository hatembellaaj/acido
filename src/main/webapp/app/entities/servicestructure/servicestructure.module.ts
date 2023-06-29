import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { ServicestructureComponent } from './list/servicestructure.component';
import { ServicestructureDetailComponent } from './detail/servicestructure-detail.component';
import { ServicestructureUpdateComponent } from './update/servicestructure-update.component';
import { ServicestructureDeleteDialogComponent } from './delete/servicestructure-delete-dialog.component';
import { ServicestructureRoutingModule } from './route/servicestructure-routing.module';

@NgModule({
  imports: [SharedModule, ServicestructureRoutingModule],
  declarations: [
    ServicestructureComponent,
    ServicestructureDetailComponent,
    ServicestructureUpdateComponent,
    ServicestructureDeleteDialogComponent,
  ],
})
export class ServicestructureModule {}
