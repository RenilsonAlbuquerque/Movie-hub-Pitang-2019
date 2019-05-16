import { Component, OnInit } from '@angular/core';
import { MovieService } from '../movie.service';
import { ActivatedRoute } from '@angular/router';
import { Movie } from 'src/app/models/movie';
import { Cast } from 'src/app/models/cast';

@Component({
  selector: 'app-movie-detail',
  templateUrl: './movie-detail.component.html',
  styleUrls: ['./movie-detail.component.scss']
})
export class MovieDetailComponent implements OnInit {

  public movie: Movie;
  public cast: Cast[];
  constructor(private _activatedRoute: ActivatedRoute, private movieService: MovieService) { }

  ngOnInit() {
    this._activatedRoute.params.subscribe(params => {
      let id = params['id'];
      this.movieService.getMovieById(id).subscribe(
        response => (this.movie = response)  
      )
      this.movieService.getCast(id).subscribe(
        response => (this.cast = response)
      )
    });
   
  }

}
