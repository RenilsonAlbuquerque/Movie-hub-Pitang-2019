import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { MovieListComponent } from './movie-list/movie-list.component';


const routes: Routes = [
    {
      path: '',
      data: {
        title: 'movie'
      },
      children: [
        {
          path:'',
          redirectTo: 'all',
          pathMatch: 'full'
        },
        {
            path: 'all',
            component: MovieListComponent
        }
      ]
    }
]
@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class MovieRoutingModule {}