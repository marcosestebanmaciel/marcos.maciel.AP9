package com.mindhub.homebanking;

import java.time.LocalDate;
import java.util.List;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
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
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository, CardRepository cardRepository){
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
			client2.addAccount(account3);
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

			Loan loan1 = new Loan("Hipotecario", 500000.00, List.of(12, 24, 36, 48,60));
			loanRepository.save(loan1);

			Loan loan2 = new Loan("Personal", 100000.00, List.of(6, 12, 24));
			loanRepository.save(loan2);

			Loan loan3 = new Loan("Automotriz", 300000.00, List.of(6, 12, 24, 36));
			loanRepository.save(loan3);

			ClientLoan clientLoan1 = new ClientLoan (60, 400000.0, client1,loan1);
			clientLoanRepository.save(clientLoan1);

			ClientLoan clientLoan2 = new ClientLoan (12, 50000.0, client1, loan2);
			clientLoanRepository.save(clientLoan2);

			ClientLoan clientLoan3 = new ClientLoan (24, 100000.0, client2, loan2);
			clientLoanRepository.save(clientLoan3);

			ClientLoan clientLoan4 = new ClientLoan (36, 200000.0, client2, loan3);
			clientLoanRepository.save(clientLoan4);

			Card card1 = new Card(client1.getFirstName()+" "+client1.getLastName(), CardType.DEBIT, CardColor.GOLD, "3325-6745-7876-4445",990, LocalDate.now(), LocalDate.now().plusYears(5));
			client1.addCard(card1);
			cardRepository.save(card1);

			Card card2 = new Card(client1.getFirstName()+" "+client1.getLastName(), CardType.CREDIT, CardColor.TITANIUM, "2234-6745-552-7888",750, LocalDate.now(), LocalDate.now().plusYears(5));
			client1.addCard(card2);
			cardRepository.save(card2);

			Card card3 = new Card(client2.getFirstName()+" "+client2.getLastName(), CardType.DEBIT, CardColor.SILVER, "3325-6745-7876-4445",990, LocalDate.now(), LocalDate.now().plusYears(5));
			client2.addCard(card2);
			cardRepository.save(card3);
		};
   }

}

