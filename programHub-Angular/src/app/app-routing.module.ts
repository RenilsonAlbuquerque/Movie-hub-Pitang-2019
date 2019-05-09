import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LayoutComponent } from './layout/layout.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'home',
    component: LayoutComponent,
    children:[
      {
        path: 'movie',
        loadChildren: './movie/movie.module#MovieModule'
      },
      {
        path: 'serie',
        loadChildren: './serie/serie.module#SerieModule'
      },
      {
        path:'',
        redirectTo: 'movie',
        pathMatch: 'full'
      }
    ]
  }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
