package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TransferService {

    private final String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();
    private final AuthenticatedUser currentUser;
    private Transfer transfer;

    public TransferService(String url, AuthenticatedUser currentUser) {
        this.baseUrl = url;
        this.currentUser = currentUser;
    }

    public void sendBucks(int userID, double transferAmount, BigDecimal currentBalanceAmount) {
        double curBalAmt = currentBalanceAmount.doubleValue();
        if (transferAmount > curBalAmt) {
            return;
        }
        Transfer transfer = new Transfer();
        transfer.setTransferTypeID(2);
        transfer.setTransferStatusID(2);
        transfer.setAccountTo(userID);
        transfer.setAccountFrom(userID);
        transfer.setAmount(transferAmount);
    }

    public void listAllUsers() {
        User[] users = null;
        try {
            users = restTemplate.exchange(baseUrl + "users", HttpMethod.GET, makeAuthEntity(), User[].class).getBody();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        for (User user : users) {
            if (currentUser.getUser().getId() != user.getId()) {
                System.out.println(user);
            }
        }
    }

    public List<Transfer> viewTransferHistory(AuthenticatedUser currentUser){
        //List<Transfer> transferHistory = new ArrayList<>();
       // Transfer transferHistory = new Transfer();
        List<Transfer> transferHistory = new ArrayList<>();
        try {
            transfer = restTemplate.exchange(baseUrl + "/transfer", HttpMethod.GET, makeTransferEntity(transfer), Transfer.class).getBody();
            System.out.println("Your transfer history: " + transferHistory);
        } catch (RestClientResponseException e) {
            System.out.println(e.getRawStatusCode() + e.getStatusText());
        }
        return transferHistory;
    }

    public Transfer getTransferDetails (Long transferID) {
        Transfer transferDetails = new Transfer();
        try {
            transferDetails = restTemplate.exchange(baseUrl + "/transfer{id}", HttpMethod.GET, makeTransferEntity(transfer), Transfer.class).getBody();
            System.out.println("Your transfer details: " + transferDetails);
        } catch (RestClientResponseException e) {
            System.out.println(e.getRawStatusCode() + e.getStatusText());
        }
        return transferDetails;
    }

    private HttpEntity makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        HttpEntity entity = new HttpEntity<>(headers);
        return entity;
    }

    private HttpEntity<Transfer> makeTransferEntity(Transfer transfer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(currentUser.getToken());
        return new HttpEntity<>(transfer, headers);
    }

    private int userIDToAccountID(int userID) {
        HttpHeaders headers = new HttpHeaders();
        return 0;
    }

}

/*
Get the user_id switched to an account_id to plug into send bucks to get correct account from and account to
Sprint boot request mapping in account controller from account/{accid} @Pathvariable accid
JdbcAccountDao needs a getAccount, will take the user id and plug into sql string for SELECT * FROM account WHERE user_id
use this to get the account number and set it as a account from / account to variables
make sure account object has an account getter for tracking purposes and setting up

transfer service finish transfer object
take the transfer and send it through the api to the database, SEND should be done

transfer history and details
 */