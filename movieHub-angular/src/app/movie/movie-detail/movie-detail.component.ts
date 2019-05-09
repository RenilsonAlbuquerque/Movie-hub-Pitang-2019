import { Component, OnInit } from '@angular/core';
import { MovieService } from '../movie.service';
import { ActivatedRoute } from '@angular/router';
import { Movie } from 'src/app/models/movie';

@Component({
  selector: 'app-movie-detail',
  templateUrl: './movie-detail.component.html',
  styleUrls: ['./movie-detail.component.css']
})
export class MovieDetailComponent implements OnInit {

  public movie: Movie;
  constructor(private _activatedRoute: ActivatedRoute, private movieService: MovieService) { }

  ngOnInit() {
    this._activatedRoute.params.subscribe(params => {
      let id = params['id'];
      console.log("entgrou aqui")
      this.movieService.getMovieById(id).subscribe(
        response => (this.movie = response)  
      )
    });
   
  }

}
