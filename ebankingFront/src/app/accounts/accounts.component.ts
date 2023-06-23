import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AccountsService} from "../services/accounts.service";
import {catchError, Observable, throwError} from "rxjs";
import {AccountDetails} from "../model/account.model";

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements  OnInit{

  accountFormGroup! : FormGroup;
  currentPage : number = 0;
  pageSize : number = 5;
  accountObservables! : Observable<AccountDetails>;
  oprerationFromGroup! : FormGroup;
  errorMessage! : string;

  constructor(private fb : FormBuilder, private accountService : AccountsService) {
  }

  ngOnInit() : void {
    this.accountFormGroup = this.fb.group({
      accountId : this.fb.control('')
    });
    this.oprerationFromGroup = this.fb.group({
      operationType : this.fb.control(null),
      amount : this.fb.control(0),
      description : this.fb.control(null),
      accountDestination : this.fb.control(null)
    })
  }

  handleSearchAccount() {
    let accountId : string = this.accountFormGroup.value.accountId;
    this.accountObservables = this.accountService.getAccount(accountId, this.currentPage, this.pageSize).pipe(
      catchError(err =>{
        this.errorMessage = err.message;
        return throwError(err);
      })
    );
  }

  gotoPage(page: number) {
    this.currentPage = page;
    this.handleSearchAccount();
  }

  handleAccountOperation() {
    let accountId : string = this.accountFormGroup.value.accountId;
    let operationType = this.oprerationFromGroup.value.operationType;
    let amount : number = this.oprerationFromGroup.value.amount;
    let description : string = this.oprerationFromGroup.value.description;
    let accountDestination : string = this.oprerationFromGroup.value.accountDestination;

    if(operationType=='DEBIT'){
      this.accountService.debit(accountId, amount, description).subscribe({
        next : (data)=>{
          alert("Success Debit !");
          this.oprerationFromGroup.reset();
          this.handleSearchAccount();
        },
        error : err => {
          console.log(err);
        }
      });
    }
    else if (operationType=='CREDIT'){
      this.accountService.credit(accountId, amount, description).subscribe({
        next : (data)=>{
          alert("Success Credit !");
          this.oprerationFromGroup.reset();
          this.handleSearchAccount();
        },
        error : err => {
          console.log(err);
        }
      });
    }
    else if (operationType=='TRANSFER'){
      this.accountService.transfer(accountId, accountDestination, amount, description).subscribe({
        next : (data)=>{
          alert("Success Transfer !");
          this.oprerationFromGroup.reset();
          this.handleSearchAccount();
        },
        error : err => {
          console.log(err);
        }
      })
    }
  }
}
