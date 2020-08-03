import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";
import { environment } from "../../../environments/environment";
import { UtilService } from '../util/util.service';

@Injectable({ providedIn: "root" })
export class ApiService {
  private API_URL: string = environment.API_URL;

  constructor(private http: HttpClient, private utilService: UtilService) {}

  public get(url: string): Observable<any> {
    return this.http.get(`${this.API_URL}${url}`).pipe(map(response => response));
  }

  public post(url: string, body?: { [index: string]: any }): Observable<any> {
    let headers: HttpHeaders = new HttpHeaders();
    headers = headers.append('Authorization', this.utilService.getToken());

    body.headers = headers;

    return this.http.post(`${this.API_URL}${url}`, body).pipe(map(response => response));
  }

  public put(url: string, body?: { [index: string]: any } ): Observable<any> {
    return this.http.put(`${this.API_URL}${url}`, body).pipe(map(response => response));
  }

  public delete(url: string): Observable<any> {
    return this.http.delete(`${this.API_URL}${url}`).pipe(map(response => response));
  }
}