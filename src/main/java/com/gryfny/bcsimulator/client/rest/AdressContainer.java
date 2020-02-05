package com.gryfny.bcsimulator.client.rest;

import org.springframework.stereotype.Component;

@Component
public interface AdressContainer {

    String GET_WALLETS =  "localhost:8080/sim/wallets";  // returns Set<WalletDto>

    String POST_TRANSACTION =  "localhost:8080/sim/transaction"; // consumes Json of TransactionDto

    String POST_WALLET =  "localhost:8080/sim/wallets"; // creates one wallet

    String POST_WALLETS =  "localhost:8080/sim/wallets/"/*{int}*/; // specify path variable for adding number of wallets

}
