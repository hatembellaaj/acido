import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IServicestructure } from '../servicestructure.model';
import { ServicestructureService } from '../service/servicestructure.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './servicestructure-delete-dialog.component.html',
})
export class ServicestructureDeleteDialogComponent {
  servicestructure?: IServicestructure;

  constructor(protected servicestructureService: ServicestructureService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.servicestructureService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
