import { Component, OnInit } from '@angular/core';
import { Client } from '../model/Client';
import { MatTableDataSource } from '@angular/material/table';
import { ClientService } from '../client.service';
import { MatDialog } from '@angular/material/dialog';
import { ClientEditComponent } from '../client-edit/client-edit.component';
import { DialogConfirmationComponent } from 'src/app/core/dialog-confirmation/dialog-confirmation.component';

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.scss']
})
export class ClientListComponent implements OnInit {
  dataSource = new MatTableDataSource<Client>();
  displayedColumns: string[] = ['id', 'name', 'action'];

  constructor(private clientService: ClientService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.clientService.getClients().subscribe(
      (clients) => {
        this.dataSource.data = clients;
      }
    )
  }

  createClient() {
    const dialogRef = this.dialog.open(ClientEditComponent, {
      data: {}
    });

    dialogRef.afterClosed().subscribe(result => {
      this.ngOnInit();
    });
  }

  editClient(client: Client) {
    const dialogRef = this.dialog.open(ClientEditComponent, {
      data: { client: client }
    });

    dialogRef.afterClosed().subscribe(result => {
      this.ngOnInit();
    });
  }

  deleteClient(client: Client) {
    const dialogRef = this.dialog.open(DialogConfirmationComponent, {
      data: { message: 'Eliminar cliente', description: 'Atención si borra el cliente se perderán sus datos.<br> ¿Desea eliminar el cliente?' }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.clientService.deleteCategory(client.id).subscribe(result => {
          this.ngOnInit();
        })
      }
    });
  }
}
