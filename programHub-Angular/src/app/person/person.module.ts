import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PersonDetailComponent } from './person-detail/person-detail.component';
import { PersonListComponent } from './person-list/person-list.component';
import { PersonRoutingModule } from './person.module.routing';
import { PersonService } from './person.service';
import { NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [PersonDetailComponent, PersonListComponent],
  imports: [
    CommonModule,
    PersonRoutingModule,
    NgbPaginationModule
  ],
  providers:[PersonService]
})
export class PersonModule { }
