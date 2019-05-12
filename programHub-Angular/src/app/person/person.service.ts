import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

import { Movie } from '../models/movie';
import { Page } from '../models/page';
import { PersonOverview } from '../models/person-overview';
import { Person } from '../models/person';

@Injectable()
export class PersonService {

  constructor(private httpClient: HttpClient) {
  }

  getOverview(pageNumber): Observable<Page<PersonOverview>>{
    return this.httpClient.post<Page<PersonOverview>>(`${environment.BASE_URL}person`,{page:pageNumber, size:9});
  }
  getPersonById(id): Observable<Person>{
    return this.httpClient.get<Person>(`${environment.BASE_URL}person/${id}`);
  }
  getSearchResult(searchTitle: String, pageNumber): Observable<Page<PersonOverview>>{
    return this.httpClient.post<Page<PersonOverview>>(`${environment.BASE_URL}person/filter?name=${searchTitle}`,{page:pageNumber, size:9});
  }
}