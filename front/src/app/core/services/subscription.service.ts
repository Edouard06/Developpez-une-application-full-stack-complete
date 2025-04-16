import { Inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Subscription } from '../interfaces/subscription';
import { GenericResponse } from '../interfaces/generic-response';
import { SubscriptionRequest } from '../interfaces/subscription-request';
import { UnsubscriptionRequest } from '../interfaces/unsubscription-request';

// Service for handling subscription-related operations.

@Injectable({
  providedIn: 'root'
})
export class SubscriptionService {

  private pathService: string = "api/subscription";

  constructor(
    private httpClient: HttpClient
  ) {}
  public subscribe(request: SubscriptionRequest): Observable<GenericResponse> {
    return this.httpClient.post<GenericResponse>(`${this.pathService}/subscribe`, request);
  }

  public unSubscribe(request: UnsubscriptionRequest): Observable<GenericResponse> {
    return this.httpClient.post<GenericResponse>(`${this.pathService}/unsubscribe`, request);
  }

  public all(): Observable<Subscription[]> {
    return this.httpClient.get<Subscription[]>(`${this.pathService}/`);
  }
}