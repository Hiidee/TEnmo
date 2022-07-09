package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class AccountService {

    private final String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();
    private final AuthenticatedUser currentUser;

    public AccountService(String url, AuthenticatedUser currentUser) {
        this.currentUser = currentUser;
        this.baseUrl = url;
    }

    public BigDecimal getBalance(AuthenticatedUser currentUser) {
        BigDecimal balance = new BigDecimal(0);
        try {
            // The URL path below might not be right
            balance = restTemplate.exchange(baseUrl + "account/balance/" + currentUser.getUser().getId(), HttpMethod.GET, makeAuthEntity(), BigDecimal.class).getBody();
            System.out.println("Your current account balance is: $" + balance);
        } catch (RestClientException e) {
            System.out.println("There was an error getting the balance.");
        }
        return balance;
    }

    // Should addToBalance and subtractFromBalance go here?
    public BigDecimal addToBalance(AuthenticatedUser currentUser) {
        BigDecimal balance = new BigDecimal(0);
        try {
            balance = restTemplate.exchange(baseUrl + "balance/" + currentUser.getUser().getId(), HttpMethod.PUT, makeAuthEntity(), BigDecimal.class).getBody();
            System.out.println("Your current account balance is: $" + balance);
        } catch (RestClientException e) {
            System.out.println("There was an error getting the balance.");
        }
        return balance;
    }

    public BigDecimal subtractFromBalance(AuthenticatedUser currentUser) {
        BigDecimal balance = new BigDecimal(0);
        try {
            balance = restTemplate.exchange(baseUrl + "balance/" + currentUser.getUser().getId(), HttpMethod.PUT, makeAuthEntity(), BigDecimal.class).getBody();
            System.out.println("Your current account balance is: $" + balance);
        } catch (RestClientException e) {
            System.out.println("There was an error getting the balance.");
        }
        return balance;
    }

    private HttpEntity makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        HttpEntity entity = new HttpEntity<>(headers);
        return entity;
    }
}
