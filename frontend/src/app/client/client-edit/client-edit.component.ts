import { Component, OnInit, Inject } from '@angular/core';
import { Client } from '../model/Client';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ClientService } from '../client.service';

@Component({
  selector: 'app-client-edit',
  templateUrl: './client-edit.component.html',
  styleUrls: ['./client-edit.component.scss']
})
export class ClientEditComponent implements OnInit {
  client: Client;
  clients: Client[];
  error: string = '';

  constructor(
    public dialogRef: MatDialogRef<ClientEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private clientService: ClientService,
  ) { }

  ngOnInit(): void {
    if (this.data.client != null) {
      this.client = Object.assign({}, this.data.client);
    } else {
      this.client = new Client();
    }
    this.clientService.getClients().subscribe(clients => {
      this.clients = clients;
    });
  }

  onSave() {
    if (this.checkRepeatedName()) {
      this.error = 'El nombre ya existe';
      return;
    } else {
      this.error = '';
    }
    this.clientService.saveClients(this.client).subscribe(result => {
      this.dialogRef.close();
    });
  }

  onClose() {
    this.dialogRef.close();
  }

  checkRepeatedName(): boolean {
    return this.clients.some(client => client.name === this.client.name && client.id !== this.client.id);
  }
}