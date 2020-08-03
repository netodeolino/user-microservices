import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class UtilService {

  public setToken(token: string): void {
    localStorage.setItem('jwt-token', token);
  }

  public getToken(): string {
    return localStorage.getItem('jwt-token');
  }
}
