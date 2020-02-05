package com.gryfny.bcsimulator.client.rest;

import com.gryfny.bcsimulator.client.dto.TransactionDto;
import com.gryfny.bcsimulator.client.dto.WalletDto;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Component
@AllArgsConstructor
public class RESTConsumer {

    RestTemplate restTemplate;

    public Set<WalletDto> getAllWallets() {
        ResponseEntity<Set<WalletDto>> response =
                restTemplate.exchange(
                        AdressContainer.GET_WALLETS,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {
                        }
                );

        return response.getBody();
    }

    public void postTransaction(TransactionDto transaction){
        restTemplate.postForLocation(AdressContainer.POST_TRANSACTION, transaction);
    }

    public void postWallets(){
        restTemplate.postForLocation(AdressContainer.POST_WALLET,null);
    }

    public void postWallets(int i){
        restTemplate.postForLocation(AdressContainer.POST_WALLETS+i,null);
    }



}
