import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../interfaces/user';
import { UserRequest } from '../interfaces/user-request';
import { GenericResponse } from '../interfaces/generic-response';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private pathService = 'api/user';

  constructor(private httpClient: HttpClient) { }

  public update(userRequest: UserRequest): Observable<GenericResponse> {
    return this.httpClient.put<GenericResponse>(`${this.pathService}/`, userRequest);
  }

  public getUserById(id: string): Observable<User> {
    return this.httpClient.get<User>(`${this.pathService}/${id}`);
  }
}