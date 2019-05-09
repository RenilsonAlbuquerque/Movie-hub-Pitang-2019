import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SerieListComponent } from './serie-list/serie-list.component';
import { SerieRoutingModule } from './serie.module.routing';
import { SerieService } from './serie.service';

@NgModule({
  declarations: [SerieListComponent],
  imports: [
    CommonModule,
    SerieRoutingModule
  ],
  providers:[
    SerieService
  ]
})
export class SerieModule { }
