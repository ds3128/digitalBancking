import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Customer} from "../model/customer.model";


@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  backEndHost = "http://localhost:8090";
  constructor(private http : HttpClient) { }

  public getCustomer() : Observable<Customer[]>{
    return this.http.get<Customer[]>(this.backEndHost+"/customers");
  }

  public searchCustomer(keyword : string) : Observable<Customer[]>{
    return this.http.get<Customer[]>(this.backEndHost+"/customers/search?keyword="+keyword);
  }

  public saveCustomer(customer : Customer) : Observable<Customer>{
    return this.http.post<Customer>(this.backEndHost+"/customers", customer);
  }

  public deleteCustomer(id : number) {
    return this.http.delete(this.backEndHost+"/customers/"+id);
  }
}
