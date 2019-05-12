import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { PersonListComponent } from './person-list/person-list.component';
import { PersonDetailComponent } from './person-detail/person-detail.component';


const routes: Routes = [
    {
      path: '',
      data: {
        title: 'person'
      },
      children: [
        {
            path: 'all',
            component: PersonListComponent
        },
        {
          path: 'detail/:id',
          component: PersonDetailComponent
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
export class PersonRoutingModule {}