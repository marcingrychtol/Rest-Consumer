package com.gryfny.bcsimulator.client.rest;

import com.gryfny.bcsimulator.client.dto.TransactionDto;
import com.gryfny.bcsimulator.client.dto.WalletDto;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RESTConsumerService {

    private RESTConsumer restConsumer;
    private final Map<Integer, WalletDto> walletMap = new HashMap<>();

    public RESTConsumerService(RESTConsumer restConsumer) {
        this.restConsumer = restConsumer;
    }

    public void run() {
        instantiateWalletMap();
        while(true) {
            TransactionDto transactionDto = generateRandomTransaction();
            if (transactionDto == null){
                return;
            }

        }
    }

    private TransactionDto generateRandomTransaction() {
        int mapSize = walletMap.size();
        if (mapSize == 0){
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
        for (WalletDto wallet:
                restConsumer.getAllWallets()
        ) {
            walletMap.put(i,wallet);
            i++;
        }
    }
}
