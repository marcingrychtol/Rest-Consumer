package com.gryfny.bcsimulator.client.rest;

import com.gryfny.bcsimulator.client.dto.TransactionDto;
import com.gryfny.bcsimulator.client.dto.WalletDto;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
@NoArgsConstructor
public class RESTConsumerService {

    @Autowired
    private RESTConsumer restConsumer;
    private final Map<Integer, WalletDto> walletMap = new HashMap<>();

    public void run() {
        TransactionDto transactionDto;
        Set<TransactionDto> transactionHistory = new HashSet<>();

        while(true) {
            instantiateWalletMap(); // each time, because balance changes every time
            transactionDto = generateRandomTransaction();
            if (transactionDto == null){  // if there is not enough wallets
                restConsumer.postWallets();
                continue;
            }
            restConsumer.postTransaction(transactionDto);
            transactionHistory.add(transactionDto);

            if (Math.random()*10 >= 9){  // approximately 10% of luck
                restConsumer.postWallets();
            }

            try {
               Thread.sleep(3000);
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
        int senderIteration = 0;
        float value = 0;
        int nullBallanceCounter = 0;
        do {
            nullBallanceCounter++;
            if (nullBallanceCounter == mapSize){ return null; }
            senderIteration = (int) (Math.random() * mapSize);
            value = walletMap.get(senderIteration).getBalance();
        }while (value == 0);
        WalletDto senderWallet = walletMap.get(senderIteration);

        // walczy aż znajdzie różne portfele
        int receiverIteration = 0;
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
        Set<WalletDto> walletSet = restConsumer.getAllWallets();
        for (WalletDto wallet:
                walletSet
        ) {
            walletMap.put(i,wallet);
            i++;
        }
    }


}
