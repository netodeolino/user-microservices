import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './core/guard/auth.guard';
import { JwtInterceptor } from './core/interceptor/jwt.interceptor';

import { AppComponent } from './app.component';

const loginModule = () => import('./login/login.module').then(x => x.LoginModule);
const homeModule = () => import('./home/home.module').then(x => x.HomeModule);
const userModule = () => import('./user/user.module').then(x => x.UserModule);
const emailModule = () => import('./email/email.module').then(x => x.EmailModule);

const routes: Routes = [
  { path: '', loadChildren: homeModule, canActivate: [AuthGuard] },
  { path: 'login', loadChildren: loginModule },
  { path: 'user', loadChildren: userModule },
  { path: 'email', loadChildren: emailModule },
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
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
