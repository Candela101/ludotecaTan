import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { Loan } from '../model/Loan';
import { LoanService } from '../loan.service';
import { Client } from 'src/app/client/model/Client';
import { ClientService } from 'src/app/client/client.service';
import { Game } from 'src/app/game/model/Game';
import { GameService } from 'src/app/game/game.service';


@Component({
  selector: 'app-loan-edit',
  templateUrl: './loan-edit.component.html',
  styleUrls: ['./loan-edit.component.scss']
})
export class LoanEditComponent implements OnInit {

  loan: Loan;
  clients: Client[];
  games: Game[];

  constructor(
    public dialogRef: MatDialogRef<LoanEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialog: MatDialog,
    private loanService: LoanService,
    private gameService: GameService,
    private clientService: ClientService
  ) { }

  ngOnInit(): void {
    this.loan = new Loan();

    this.gameService.getGames(null, null).subscribe(
      games => { this.games = games; }

    );

    this.clientService.getClients().subscribe(
      clients => { this.clients = clients; }
    );
  }


  onSave() {
    console.log(this.loan);
    this.loanService.saveLoan(this.loan).subscribe(result => {
      this.dialogRef.close();
    });
  }

  onClose() {
    this.dialogRef.close();
  }
}
