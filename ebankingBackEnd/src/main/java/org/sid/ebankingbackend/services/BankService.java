package org.sid.ebankingbackend.services;

import jakarta.transaction.Transactional;
import org.sid.ebankingbackend.entities.BankAccount;
import org.sid.ebankingbackend.entities.CurrentAccount;
import org.sid.ebankingbackend.entities.SavingAccount;
import org.sid.ebankingbackend.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BankService {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    public void consulter(){
        BankAccount bankAccount = bankAccountRepository.findById("f8e29c8a-0381-47ec-bb0b-2d08c4ae4697").orElse(null);
        if(bankAccount != null) {
            System.out.println("******************************************");
            System.out.println(bankAccount.getId());
            System.out.println(bankAccount.getBalance());
            System.out.println(bankAccount.getStatus());
            System.out.println(bankAccount.getCreateDat());
            System.out.println(bankAccount.getCustomer().getName());
            System.out.println(bankAccount.getClass().getSimpleName());
            if (bankAccount instanceof CurrentAccount) {
                System.out.println("Over Draft => " + ((CurrentAccount) bankAccount).getOverDraft());
            } else if (bankAccount instanceof SavingAccount) {
                System.out.println("Rate => " + ((SavingAccount) bankAccount).getInterestRate());

            }
//                Affichage de l'historique de compte
            bankAccount.getAccountOperations().forEach(op -> {
                System.out.println("=======================================");
                System.out.println(op.getOperationType());
                System.out.println(op.getOperationDate());
                System.out.println(op.getAmount());
            });
        }
    }
}
