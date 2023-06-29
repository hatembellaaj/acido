import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { AcidoformComponent } from './list/acidoform.component';
import { AcidoformDetailComponent } from './detail/acidoform-detail.component';
import { AcidoformUpdateComponent } from './update/acidoform-update.component';
import { AcidoformDeleteDialogComponent } from './delete/acidoform-delete-dialog.component';
import { AcidoformRoutingModule } from './route/acidoform-routing.module';

@NgModule({
  imports: [SharedModule, AcidoformRoutingModule],
  declarations: [AcidoformComponent, AcidoformDetailComponent, AcidoformUpdateComponent, AcidoformDeleteDialogComponent],
})
export class AcidoformModule {}
