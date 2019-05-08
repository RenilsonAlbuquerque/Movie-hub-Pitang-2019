import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LayoutComponent } from './layout/layout.component';

const routes: Routes = [

  {
    path:'',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'home',
    component: LayoutComponent,
    children:[
      {
        path:'',
        redirectTo: 'movie',
        pathMatch: 'full'
      },
      {
        path: 'movie',
        loadChildren: './movie/movie.module#MovieModule'
      }
    ]
  }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
