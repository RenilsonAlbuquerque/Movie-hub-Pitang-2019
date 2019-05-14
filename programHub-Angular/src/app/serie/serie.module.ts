import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SerieListComponent } from './serie-list/serie-list.component';
import { SerieRoutingModule } from './serie.module.routing';
import { SerieService } from './serie.service';
import { NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [SerieListComponent],
  imports: [
    CommonModule,
    SerieRoutingModule,
    NgbPaginationModule
  ],
  providers:[
    SerieService
  ]
})
export class SerieModule { }
