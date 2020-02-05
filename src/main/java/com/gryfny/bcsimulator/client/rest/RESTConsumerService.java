package com.gryfny.bcsimulator.client.rest;

import com.gryfny.bcsimulator.client.dto.TransactionDto;
import com.gryfny.bcsimulator.client.dto.WalletDto;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@NoArgsConstructor
public class RESTConsumerService {

    @Autowired
    private RESTConsumer restConsumer;
    private final Map<Integer, WalletDto> walletMap = new HashMap<>();
    private boolean walletAdded = true;

    public RESTConsumerService(RESTConsumer restConsumer) {
        this.restConsumer = restConsumer;
    }

    public void run() {
        TransactionDto transactionDto;

        while(true) {
            if (walletAdded) {
                instantiateWalletMap(); // sets walletAdded to false every time wallet map is instantiated
            }

            transactionDto = generateRandomTransaction();
            if (transactionDto == null){  // if there is not enough wallets
                addWallet();  // sets walletAdded to true every time wallet was added
                continue;
            }

            restConsumer.postTransaction(transactionDto);

            if (Math.random()*10 >= 9){  // approximately 10% of luck
                addWallet();
            }

            try {
                wait(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private TransactionDto generateRandomTransaction() {
        int mapSize = walletMap.size();
        if (mapSize <= 1){ // only if there is not enough wallets
            return null;
        }

        // walczy aż znajdzie portfel o niezerowej sumie
        int senderIteration;
        float value;
        do {
            senderIteration = (int) (Math.random() * mapSize);
            value = walletMap.get(senderIteration).getBalance();
        }while (value == 0);
        WalletDto senderWallet = walletMap.get(senderIteration);

        // walczy aż znajdzie różne portfele
        int receiverIteration;
        do{
            receiverIteration = (int) (Math.random()*mapSize);
        }while(senderIteration==receiverIteration);
        WalletDto receiverWallet = walletMap.get(receiverIteration);

        value = (float) (Math.random()*value); // wybiera losową kwotę płatności z zakresu portfela

        return new TransactionDto(
                senderWallet.getPublicKey(),
                receiverWallet.getPublicKey(),
                value
        );
    }

    private void instantiateWalletMap() {
        int i = 0;
        walletMap.clear();
        for (WalletDto wallet:
                restConsumer.getAllWallets()
        ) {
            walletMap.put(i,wallet);
            i++;
        }
        walletAdded = false;
    }

    private void addWallet(){
        restConsumer.postWallets();
        walletAdded=true;
    }
}
