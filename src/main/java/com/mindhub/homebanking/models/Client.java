package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;


@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String dni;
    private String firstName;
    private String lastName;

    private String email;

    @OneToMany(mappedBy="holder", fetch=FetchType.EAGER)
    private Set<Account> accounts = new HashSet<>();

    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    private Set<ClientLoan> loans = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch= FetchType.EAGER)
    private Set<Card> cards = new HashSet<>();


    public Client(){
    }

    public Long getId() {
        return id;
    }

    public Client(String dni, String firstName, String lastName, String email) {
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first) {
        this.firstName = first;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last) {
        this.lastName = last;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
    public Set<ClientLoan> getClientLoans(){
        return loans;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    public void setLoans(Set<ClientLoan> loans) {
        this.loans = loans;
    }

    public void addAccount(Account account){
        account.setHolder(this);
        accounts.add(account);
    }
    public void addClientLoan (ClientLoan clientLoan){
        clientLoan.setClient(this);
        loans.add(clientLoan);
    }
    public List<Loan>getLoans(){
        return loans.stream().map(ClientLoan::getLoan).collect(toList());
    }
    public void addCard(Card card){
        card.setClient(this);
        cards.add(card);
    }
}
