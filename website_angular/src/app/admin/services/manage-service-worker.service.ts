import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

export class ServiceWorker {
    id: number;
    fullname: string;
    phoneNumber: string;
    avatar: string;
    address: string;
    description: string;
    createTime: string;
}

@Injectable({
    providedIn: 'root'
})

export class ManageServiceWorkerService {
    private REST_API_SERVER = 'http://localhost:8080/admin';

    constructor(private http: HttpClient) {}

    getAll(): Observable<ServiceWorker[]> {
        const url = `${this.REST_API_SERVER}/worker`;
        return this.http.get<ServiceWorker[]>(url);
    }

    delete(id: number) {
        const url = `${this.REST_API_SERVER}/worker/${id}`;
        return this.http.delete<any>(url);
    }
}
