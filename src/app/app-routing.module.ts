import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TypeaheadComponent } from './typeahead/typeahead.component';
import { AppComponent } from './app.component';

const routes: Routes = [
  {
    path: 'search/:word',
    component: TypeaheadComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
  ],
  declarations: [],
  exports: [RouterModule]
})
export class AppRoutingModule { }
