import { HttpClient, HttpParams } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Company } from '../models/company';
import { API_BASE_URL } from './api-token';
import { catchError, Observable, throwError } from 'rxjs';

function toHttpParams(obj?: Record<string, any>): HttpParams | undefined {
  if (!obj) return undefined;
  let p = new HttpParams();
  Object.keys(obj).forEach(k => {
    const v = obj[k];
    if (v === undefined || v === null) return;
    p = p.set(k, String(v));
  });
  return p;
}

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  private baseUrl = inject(API_BASE_URL, {optional: false})
  private http = inject(HttpClient)

  private handleError(err: any) {
    // adapte selon ton format d'erreur serveur
    const message = err?.message || err?.error?.message || 'Erreur réseau';
    return throwError(() => new Error(message));
  }

  get<T>(uri: string, params?: Record<string, any>, options?: { headers?: Record<string,string> }): Observable<T> {
    const httpParams = toHttpParams(params);
    return this.http.get<T>(`${this.baseUrl}${uri}`, { params: httpParams, ...(options ?? {}) })
      .pipe(catchError(err => this.handleError(err)));
  }

  post<T>(uri: string, body: any, params?: Record<string, any>): Observable<T> {
    const httpParams = toHttpParams(params);
    return this.http.post<T>(`${this.baseUrl}${uri}`, body, { params: httpParams })
      .pipe(catchError(err => this.handleError(err)));
  }
}
