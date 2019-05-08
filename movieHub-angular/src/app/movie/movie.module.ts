import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MovieListComponent } from './movie-list/movie-list.component';
import { MovieRoutingModule } from './movie.module.routing';
import { MovieService } from './movie.service';

@NgModule({
  declarations: [MovieListComponent],
  imports: [
    CommonModule,
    MovieRoutingModule
  ],
  providers:[
    MovieService
  ]
})
export class MovieModule { }
