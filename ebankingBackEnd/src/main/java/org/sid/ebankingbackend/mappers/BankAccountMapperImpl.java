package org.sid.ebankingbackend.mappers;

import ch.qos.logback.core.model.Model;
import org.modelmapper.ModelMapper;
import org.sid.ebankingbackend.dtos.AccountOperationDTO;
import org.sid.ebankingbackend.dtos.CurrentBankAccountDTO;
import org.sid.ebankingbackend.dtos.CustomerDTO;
import org.sid.ebankingbackend.dtos.SavingBankAccountDTO;
import org.sid.ebankingbackend.entities.AccountOperation;
import org.sid.ebankingbackend.entities.CurrentAccount;
import org.sid.ebankingbackend.entities.Customer;
import org.sid.ebankingbackend.entities.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
public class BankAccountMapperImpl {

    private ModelMapper modelMapper = new ModelMapper();

//    //methode qui retourne DTO. on donne un objet de type customer et il retourne customer DTO
//    public CustomerDTO fromCustomer(Customer customer){
//        CustomerDTO customerDTO = new CustomerDTO();
//        BeanUtils.copyProperties(customer, customerDTO); //remplace les trois lignes en commentaire
////        customerDTO.setId(customer.getId());
////        customerDTO.setName(customer.getName());
////        customerDTO.setEmail(customer.getEmail());
//        return customerDTO;
//    }

    public CustomerDTO fromCustomer(Customer customer){

        return modelMapper.map(customer, CustomerDTO.class);
    }

    public Customer fromCustomerDTO(CustomerDTO customerDTO){

        return modelMapper.map(customerDTO, Customer.class);
    }

//    public Customer fromCustomerDTO(CustomerDTO customerDTO){
//        Customer customer = new Customer();
//        BeanUtils.copyProperties(customerDTO, customer);
//        return customer;
//    }

    public SavingBankAccountDTO fromSavingBankAccount(SavingAccount savingAccount){
        SavingBankAccountDTO savingBankAccountDTO =  new SavingBankAccountDTO();
        BeanUtils.copyProperties(savingAccount, savingBankAccountDTO);
        savingBankAccountDTO.setCustomerDTO(fromCustomer(savingAccount.getCustomer()));
        savingBankAccountDTO.setType(savingAccount.getClass().getSimpleName());
        return savingBankAccountDTO;
    }

    public SavingAccount fromSavingBankAccountDTO(SavingBankAccountDTO savingBankAccountDTO){
        SavingAccount savingAccount = new SavingAccount();
        BeanUtils.copyProperties(savingBankAccountDTO, savingAccount);
        savingAccount.setCustomer(fromCustomerDTO(savingBankAccountDTO.getCustomerDTO()));
        return savingAccount;
    }

    public CurrentBankAccountDTO fromCurrentBankAccount(CurrentAccount currentAccount){
        CurrentBankAccountDTO currentBankAccountDTO = new CurrentBankAccountDTO();
        BeanUtils.copyProperties(currentAccount, currentBankAccountDTO);
        currentBankAccountDTO.setCustomerDTO(fromCustomer(currentAccount.getCustomer()));
        currentBankAccountDTO.setType(currentAccount.getClass().getSimpleName());
        return currentBankAccountDTO;
    }

    public CurrentAccount fromCurrentBankAccountDTO(CurrentBankAccountDTO currentBankAccountDTO){
        CurrentAccount currentAccount = new CurrentAccount();
        BeanUtils.copyProperties(currentBankAccountDTO, currentAccount);
        currentAccount.setCustomer(fromCustomerDTO(currentBankAccountDTO.getCustomerDTO()));
        return currentAccount;
    }

    public AccountOperationDTO fromAccountOperation(AccountOperation accountOperation){
        AccountOperationDTO accountOperationDTO = new AccountOperationDTO();
        BeanUtils.copyProperties(accountOperation, accountOperationDTO);
        return  accountOperationDTO;
    }


}
