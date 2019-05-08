import { Component, OnInit } from '@angular/core';
import { MovieOverview } from 'src/app/models/movie-overview';
import { MovieService } from '../movie.service';

@Component({
  selector: 'app-movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.css']
})
export class MovieListComponent implements OnInit {

  public movies: MovieOverview[];

  constructor(private movieService: MovieService) { }

  ngOnInit() {
    this.movieService.getOverview().subscribe(
        response => (this.movies = response['content'])  
    )
  }

  goEdit(movie){

  }

}
