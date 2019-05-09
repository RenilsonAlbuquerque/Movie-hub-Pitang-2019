import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { ProgramOverview } from '../models/program-overview';
import { Movie } from '../models/movie';

@Injectable()
export class MovieService {

  constructor(private httpClient: HttpClient) {
  }

  getOverview(): Observable<ProgramOverview[]>{
    return this.httpClient.post<ProgramOverview[]>(`${environment.BASE_URL}movie`,{page:1, size:10});
  }
  getMovieById(id): Observable<Movie>{
    return this.httpClient.get<Movie>(`${environment.BASE_URL}movie/${id}`);
  }
}