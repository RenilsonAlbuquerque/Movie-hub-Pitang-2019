import { Component, OnInit } from '@angular/core';
import { Page } from 'src/app/models/page';
import { PersonOverview } from 'src/app/models/person-overview';
import { PersonService } from '../person.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-person-list',
  templateUrl: './person-list.component.html',
  styleUrls: ['./person-list.component.scss']
})
export class PersonListComponent implements OnInit {

  public page: Page<PersonOverview>;
  public search: String;

  constructor(private personService: PersonService, private router: Router) { }

  ngOnInit() {
    
    this.search = "";
    this.personService.getOverview(1).subscribe(
        response => (this.page = response,console.log(response))  
    )
  }

  goEdit(movie){
    this.router.navigate(['home/person/detail', movie.id])
  }
  pageChange(){
    this.personService.getOverview(this.page.currentPageNumber ).subscribe(
      response => (this.page = response)  
    )
  }
  onSearch(){
    this.personService.getSearchResult(this.search,1).subscribe(
      response => (this.page = response)  
    )
  }

}
