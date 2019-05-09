import { Component, OnInit } from '@angular/core';
import { ProgramOverview } from 'src/app/models/program-overview';
import { MovieService } from '../movie.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.scss']
})
export class MovieListComponent implements OnInit {

  public movies: ProgramOverview[];

  constructor(private movieService: MovieService, private router: Router) { }

  ngOnInit() {
    this.movieService.getOverview().subscribe(
        response => (this.movies = response['content'])  
    )
  }

  goEdit(movie){
    this.router.navigate(['home/movie/detail', movie.id])
  }

}
