import { Injectable } from '@angular/core';
import { Pageable } from '../core/model/page/Pageable';
import { Observable, of } from 'rxjs';
import { LoanPage } from './model/LoanPage';
import { LOANS } from "./model/mocks-loans";
import { Loan } from './model/Loan';
import { Client } from '../client/model/Client';
import { HttpClient } from '@angular/common/http';
import { Result } from '../core/model/Result';

@Injectable({
  providedIn: 'root'
})
export class LoanService {

  constructor(private http: HttpClient) { }

  getLoans(pageable: Pageable, game?: number, client?: number, date?: Date): Observable <LoanPage>{
    return this.http.post<LoanPage>('http://localhost:8080/loans', {pageable:pageable, game: game, client:client, date:date});
  }
  

  saveLoan(loan: Loan): Observable <Result>{
    return this.http.put<Result>('http://localhost:8080/loans', loan);
  }

  deleteLoan(idLoan: number): Observable <any>{
    return this.http.delete<any>('http://localhost:8080/loans/'+idLoan);
  }

  
}
