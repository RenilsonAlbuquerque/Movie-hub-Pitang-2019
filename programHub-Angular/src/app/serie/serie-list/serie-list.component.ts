import { Component, OnInit } from '@angular/core';
import { SerieService } from '../serie.service';
import { ProgramOverview } from 'src/app/models/program-overview';

@Component({
  selector: 'app-serie-list',
  templateUrl: './serie-list.component.html',
  styleUrls: ['./serie-list.component.scss']
})
export class SerieListComponent implements OnInit {

  public series: ProgramOverview[];

  constructor(private movieService: SerieService) { }

  ngOnInit() {
    this.movieService.getOverview().subscribe(
        response => (this.series = response['content'], console.log(this.series))  
    )
  }

  goEdit(movie){

  }

}
