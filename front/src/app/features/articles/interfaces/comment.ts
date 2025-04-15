export interface Comment {
    id: number;
    content: string;
    author: string;
    article_id: string;
    createdAt?: string;
    updatedAt?: string;
}