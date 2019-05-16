import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { ProgramOverview } from '../models/program-overview';
import { Movie } from '../models/movie';
import { Page } from '../models/page';
import { Cast } from '../models/cast';

@Injectable()
export class MovieService {

  constructor(private httpClient: HttpClient) {
  }

  getOverview(pageNumber): Observable<Page<ProgramOverview>>{
    return this.httpClient.post<Page<ProgramOverview>>(`${environment.BASE_URL}movie`,{page:pageNumber, size:9});
  }
  getMovieById(id): Observable<Movie>{
    return this.httpClient.get<Movie>(`${environment.BASE_URL}movie/${id}`);
  }
  getSearchResult(searchTitle: String, pageNumber): Observable<Page<ProgramOverview>>{
    return this.httpClient.post<Page<ProgramOverview>>(`${environment.BASE_URL}movie/filter?title=${searchTitle}`,{page:pageNumber, size:9});
  }
  getCast(movieId: String): Observable<Cast[]>{
    return this.httpClient.get<Cast[]>(`${environment.BASE_URL}movie/cast/${movieId}`);
  }
}