package com.mindhub.homebanking;

import java.time.LocalDate;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class HomebankingApplication {


	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}
   @Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository){
		return (args) -> {
			Client client1 = new Client("33222111", "Melba", "Morel", "melba@gmail.com");
			clientRepository.save(client1);

			Client client2 = new Client("32222222", "Marcos", "Maciel", "marcos@gmail.com");
			clientRepository.save(client2);

			Account account1 = new Account("VIN001",LocalDate.now(), 5000.00);
			client1.addAccount(account1);
			accountRepository.save(account1);

            Account account2 = new Account("VIN002", LocalDate.now().plusDays(1), 7500.00);
			client1.addAccount(account2);
			accountRepository.save(account2);

			Account account3 = new Account("VIN003", LocalDate.now(),7000.00);
			client2.addAccount(account2);
			accountRepository.save(account3);

			Transaction transaction1 = new Transaction(TransactionType.CREDIT, 1000.00,"Developer salary",LocalDate.now());
			account1.addTransaction(transaction1);
			transactionRepository.save(transaction1);

			Transaction transaction2 = new Transaction(TransactionType.DEBIT, -150.00,"Energy service",LocalDate.now());
			account1.addTransaction(transaction2);
			transactionRepository.save(transaction2);

			Transaction transaction3 = new Transaction(TransactionType.CREDIT, 200.00,"Uber salary",LocalDate.now());
			account2.addTransaction(transaction3);
			transactionRepository.save(transaction3);

			Transaction transaction4 = new Transaction(TransactionType.DEBIT, -50.00,"Phone service",LocalDate.now());
			account2.addTransaction(transaction4);
			transactionRepository.save(transaction4);

			Transaction transaction5 = new Transaction(TransactionType.CREDIT, 1200.00,"Developer salary",LocalDate.now());
			account3.addTransaction(transaction5);
			transactionRepository.save(transaction5);

			Transaction transaction6 = new Transaction(TransactionType.DEBIT, 300.00,"VISA card",LocalDate.now());
			account3.addTransaction(transaction6);
			transactionRepository.save(transaction6);
		};
   }

}

