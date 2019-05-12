import { Component, OnInit } from '@angular/core';
import { ProgramOverview } from 'src/app/models/program-overview';
import { MovieService } from '../movie.service';
import { Router } from '@angular/router';
import { Page } from 'src/app/models/page';

@Component({
  selector: 'app-movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.scss']
})
export class MovieListComponent implements OnInit {

  public page: Page<ProgramOverview>;
  public search: String;

  constructor(private movieService: MovieService, private router: Router) { }

  ngOnInit() {
    
    this.search = "";
    this.movieService.getOverview(1).subscribe(
        response => (this.page = response,console.log(response))  
    )
  }

  goEdit(movie){
    this.router.navigate(['home/movie/detail', movie.id])
  }
  pageChange(){
    this.movieService.getOverview(this.page.currentPageNumber).subscribe(
      response => (this.page = response)  
    )
  }
  onSearch(){
    this.movieService.getSearchResult(this.search,1).subscribe(
      response => (this.page = response)  
    )
  }

}
