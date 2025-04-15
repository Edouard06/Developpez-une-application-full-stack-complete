export interface Article {
    id: number;
    title: string;
    content: string;
    authorId: number;
    themeId: number;
    createdAt?: string;
    updatedAt?: string;
}