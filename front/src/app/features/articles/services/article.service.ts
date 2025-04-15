import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Article } from '../interfaces/article';
import { ArticleRequest } from '../interfaces/article-request';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {
  private pathService = 'api/article';

  constructor(private httpClient: HttpClient) { }

  public findById(id: string): Observable<Article> {
    return this.httpClient.get<Article>(`${this.pathService}/${id}`);
  }

  public all(sortOrder: string = 'desc'): Observable<Article[]> {
    return this.httpClient.get<Article[]>(`${this.pathService}?sort=${sortOrder}`);
  }

  public create(request: ArticleRequest): Observable<Article> {
    return this.httpClient.post<Article>(`${this.pathService}/create`, request);
  }
}