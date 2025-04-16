import { Component, Input } from '@angular/core';
import { Observable } from 'rxjs';
import { FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CommentService } from '../../services/comment.service';
import { Comment } from '../../interfaces/comment';
import { CommentRequest } from '../../interfaces/comment-request';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.scss']
})
export class CommentComponent {
  @Input() public articleId!: number;
  public comments$!: Observable<Comment[]>;
  public form = this.builder.group({
    content: ['', [Validators.required]]
  });

  constructor(
    private commentService: CommentService,
    private builder: FormBuilder,
    private matSnackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.comments$ = this.commentService.all(this.articleId);
  }

  public submit(): void {
    const content = this.form.value.content as string;
    const request: CommentRequest = {
      article_id: this.articleId,
      content: content
    };

    this.commentService.create(request).subscribe({
      next: () => {
        this.form.reset();
        this.comments$ = this.commentService.all(this.articleId);
        this.matSnackBar.open("Commentaire ajouté", "Close", { duration: 3000 });
      },
      error: (error) => {
        console.error('Error', error);
      }
    });
  }
}