import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class ManageServiceService {
    private REST_API_SERVER = 'http://localhost:8080';

    constructor(private http: HttpClient) {}

    getAll(): Observable<any[]> {
        const url = `${this.REST_API_SERVER}/service`;
        return this.http.get<any[]>(url);
    }

    save(data): Observable<any> {
        const url = `${this.REST_API_SERVER}/service`;
        return this.http.post<any>(url, data);
    }

    delete(id: number) {
        const url = `${this.REST_API_SERVER}/service/${id}`;
        return this.http.delete<any>(url);
    }
}
