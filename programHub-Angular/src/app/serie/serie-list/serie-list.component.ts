import { Component, OnInit } from '@angular/core';
import { SerieService } from '../serie.service';
import { ProgramOverview } from 'src/app/models/program-overview';
import { Page } from 'src/app/models/page';
import { Router } from '@angular/router';

@Component({
  selector: 'app-serie-list',
  templateUrl: './serie-list.component.html',
  styleUrls: ['./serie-list.component.scss']
})
export class SerieListComponent implements OnInit {

  public page: Page<ProgramOverview>;
  public search: String;

  constructor(private serieService: SerieService, private router: Router) { }

  ngOnInit() {
    
    this.search = "";
    this.serieService.getOverview(1).subscribe(
        response => (this.page = response)  
    )
  }

  goEdit(movie){
    this.router.navigate(['home/serie/detail', movie.id])
  }
  pageChange(){
    this.serieService.getOverview(this.page.currentPageNumber).subscribe(
      response => (this.page = response)  
    )
  }
  onSearch(){
    this.serieService.getSearchResult(this.search,1).subscribe(
      response => (this.page = response)  
    )
  }

}
