import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { MovieOverview } from '../models/movie-overview';

@Injectable()
export class MovieService {

  constructor(private httpClient: HttpClient) {
  }

  getOverview(): Observable<MovieOverview[]>{
    return this.httpClient.post<MovieOverview[]>(`${environment.BASE_URL}movie`,{page:1, size:10});
  }
}