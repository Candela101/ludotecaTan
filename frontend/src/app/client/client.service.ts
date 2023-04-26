import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Client } from './model/Client';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(private http: HttpClient) { }

  getClients(): Observable<Client[]> {
    return this.http.get<Client[]>('http://localhost:8080/clients');
  }

  saveClients(client: Client): Observable<Client> {
    let url = 'http://localhost:8080/clients';
    if (client.id != null) {
      url += '/' + client.id;
    }

    return this.http.put<Client>(url, client);
  }

  deleteCategory(idClient: number): Observable<any> {
    return this.http.delete('http://localhost:8080/clients/' + idClient);
  }
}
