import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { SerieListComponent } from './serie-list/serie-list.component';

const routes: Routes = [
    {
      path: '',
      data: {
        title: 'serie'
      },
      children: [
        {
          path:'',
          redirectTo: 'all',
          pathMatch: 'full'
        },
        {
            path: 'all',
            component: SerieListComponent
        }
      ]
    }
]
@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class SerieRoutingModule {}