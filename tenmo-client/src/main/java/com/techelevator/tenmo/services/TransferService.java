package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class TransferService {

    private final String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();
    private final AuthenticatedUser currentUser;
    private long accountTo;
    private BigDecimal amount;

    public TransferService(String url, AuthenticatedUser currentUser, Long accountTo, BigDecimal amount) {
        this.baseUrl = url;
        this.currentUser = currentUser;
        this.accountTo = accountTo;
        this.amount = amount;
    }

    public BigDecimal sendBucks(long accountTo,BigDecimal amount){
        return null;
    }

}
