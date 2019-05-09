import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { ProgramOverview } from '../models/program-overview';

@Injectable()
export class SerieService {

  constructor(private httpClient: HttpClient) {
  }

  getOverview(): Observable<ProgramOverview[]>{
    return this.httpClient.post<ProgramOverview[]>(`${environment.BASE_URL}serie`,{page:1, size:10});
  }
}