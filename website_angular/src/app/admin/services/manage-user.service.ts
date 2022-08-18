import { catchError, retry } from 'rxjs/operators';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ManageUserService {

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };
  private REST_API_SERVER = 'http://localhost:8080';

  constructor(
    private http: HttpClient
  ) {}

  // Lấy toàn bộ danh sách user
  getAllUsers(page: number, pageSize: number): Observable<any> {
    const url = `${this.REST_API_SERVER}/admin/user?page=${page}&limit=${pageSize}`;
    return this.http.get<any>(url, this.httpOptions)
    .pipe(catchError(this.handleError));
  }

  // Lấy tổng slg user
  countUsers(): Observable<any> {
    const url = `${this.REST_API_SERVER}/admin/user-count`;
    return this.http.get<any>(url, this.httpOptions)
        .pipe(retry(1), catchError(this.handleError));
  }

  // Xóa user
  deleteUser(id: any): Observable<any> {
    const url= `${this.REST_API_SERVER}/admin/user?id=${id}`;
    return this.http.delete<any>(url, this.httpOptions)
    .pipe(catchError(this.handleError));
  }

  // Sửa user
  updateUser(user:any): Observable<any> {
    const url= `${this.REST_API_SERVER}/admin/user`;
    return this.http.put<any>(url, user, this.httpOptions)
    .pipe(catchError(this.handleError));
  }

  // Thêm user
  addUser(user: any): Observable<any> {
    const url= `${this.REST_API_SERVER}/admin/user`;
    return this.http.post<any>(url, user, this.httpOptions)
    .pipe(catchError(this.handleError));
  }

  // Handles error when send data to server
  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      console.log('An error :', error.error.message);
    } else {
      console.log(`Backend return code ${error.status},` + `body was: ${error.error} `);
    }
    return throwError(error);
  }
}
