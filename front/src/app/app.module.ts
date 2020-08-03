import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './core/guard/auth.guard';

import { AppComponent } from './app.component';

const loginModule = () => import('./login/login.module').then(x => x.LoginModule);
const homeModule = () => import('./home/home.module').then(x => x.HomeModule);
const userModule = () => import('./user/user.module').then(x => x.UserModule);

const routes: Routes = [
  { path: '', loadChildren: homeModule, canActivate: [AuthGuard] },
  { path: 'login', loadChildren: loginModule },
  { path: 'user', loadChildren: userModule },
  { path: '**', redirectTo: '' }
];

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
