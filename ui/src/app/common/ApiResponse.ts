export interface ApiResponse<T> {
  status: string;
  errorMessage: string;
  body: T;
}


