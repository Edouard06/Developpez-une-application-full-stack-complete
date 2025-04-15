import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Theme } from '../../features/articles/interfaces/theme';

@Injectable({
  providedIn: 'root'
})
export class ThemeService {
  private pathService = 'api/theme';

  constructor(private httpClient: HttpClient) {}

  public findById(id: string): Observable<Theme> {
    return this.httpClient.get<Theme>(`${this.pathService}/${id}`);
  }

  public all(): Observable<Theme[]> {
    return this.httpClient.get<Theme[]>(this.pathService);
  }
}