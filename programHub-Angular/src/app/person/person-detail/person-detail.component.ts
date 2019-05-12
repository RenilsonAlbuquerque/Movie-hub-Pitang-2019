import { Component, OnInit } from '@angular/core';
import { Person } from 'src/app/models/person';
import { ActivatedRoute } from '@angular/router';
import { PersonService } from '../person.service';

@Component({
  selector: 'app-person-detail',
  templateUrl: './person-detail.component.html',
  styleUrls: ['./person-detail.component.scss']
})
export class PersonDetailComponent implements OnInit {

  public person: Person;
  constructor(private _activatedRoute: ActivatedRoute, private movieService: PersonService) { }

  ngOnInit() {
    this._activatedRoute.params.subscribe(params => {
      let id = params['id'];
      this.movieService.getPersonById(id).subscribe(
        response => (this.person = response)  
      )
    });
   
  }

}
