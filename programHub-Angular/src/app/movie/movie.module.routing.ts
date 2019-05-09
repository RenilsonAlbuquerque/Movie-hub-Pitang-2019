import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { MovieListComponent } from './movie-list/movie-list.component';
import { MovieDetailComponent } from './movie-detail/movie-detail.component';


const routes: Routes = [
    {
      path: '',
      data: {
        title: 'movie'
      },
      children: [
        {
            path: 'all',
            component: MovieListComponent
        },
        {
          path: 'detail/:id',
          component: MovieDetailComponent
        },
         {
          path:'',
          redirectTo: 'all',
          pathMatch: 'full'
        }
      ]
    }
]
@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class MovieRoutingModule {}