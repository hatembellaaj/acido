import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IAcidoform } from '../acidoform.model';
import { AcidoformService } from '../service/acidoform.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './acidoform-delete-dialog.component.html',
})
export class AcidoformDeleteDialogComponent {
  acidoform?: IAcidoform;

  constructor(protected acidoformService: AcidoformService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.acidoformService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
