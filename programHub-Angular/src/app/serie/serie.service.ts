import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { ProgramOverview } from '../models/program-overview';
import { Page } from '../models/page';
import { Serie } from '../models/serie';

@Injectable()
export class SerieService {

  constructor(private httpClient: HttpClient) {
  }

  getOverview(pageNumber): Observable<Page<ProgramOverview>>{
    return this.httpClient.post<Page<ProgramOverview>>(`${environment.BASE_URL}serie`,{page:pageNumber, size:9});
  }
  getMovieById(id): Observable<Serie>{
    return this.httpClient.get<Serie>(`${environment.BASE_URL}serie/${id}`);
  }
  getSearchResult(searchTitle: String, pageNumber): Observable<Page<ProgramOverview>>{
    return this.httpClient.post<Page<ProgramOverview>>(`${environment.BASE_URL}serie/filter?title=${searchTitle}`,{page:pageNumber, size:9});
  }
}