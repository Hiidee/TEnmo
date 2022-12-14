package com.techelevator.tenmo;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.UserCredentials;
import com.techelevator.tenmo.services.AccountService;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.ConsoleService;
import com.techelevator.tenmo.services.TransferService;
import org.bouncycastle.asn1.cmp.RevDetails;

import java.math.BigDecimal;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";

    private final ConsoleService consoleService = new ConsoleService();
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);

    private AuthenticatedUser currentUser;
    private AccountService accountService;
    private TransferService transferService;

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            mainMenu();
        }
    }
    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                handleRegister();
            } else if (menuSelection == 2) {
                handleLogin();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
                consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        System.out.println("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();
        if (authenticationService.register(credentials)) {
            System.out.println("Registration successful. You can now login.");
        } else {
            consoleService.printErrorMessage();
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        accountService = new AccountService(API_BASE_URL, currentUser);
        transferService = new TransferService(API_BASE_URL, currentUser);
        if (currentUser == null) {
            consoleService.printErrorMessage();
        }
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                viewCurrentBalance();
            } else if (menuSelection == 2) {
                viewTransferHistory();
            } else if (menuSelection == 3) {
                viewPendingRequests();
            } else if (menuSelection == 4) {
                sendBucks();
            } else if (menuSelection == 5) {
                requestBucks();
            } else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            consoleService.pause();
        }
    }

	private void viewCurrentBalance() {
        try {
            accountService.getBalance(currentUser);
       } catch (NullPointerException e) {
            System.out.println("No balance was found.");
        }
    }

	private void viewTransferHistory() {
        System.out.println("--------------------------------");
        System.out.println("Transfers");
        System.out.println("ID         From/To        Amount");
        System.out.println("--------------------------------");
        transferService.viewTransferHistory(currentUser);
        System.out.println("--------------------------------");
        long transferID = consoleService.promptForInt("Please enter transfer ID to view details (0 to cancel): ");
        Transfer transferDetails = new Transfer();
        transferService.getTransferDetails(transferID);
        System.out.println(transferDetails);
    }

	private void viewPendingRequests() {
		// TODO Auto-generated method stub
		
	}

	private void sendBucks() {
        transferService.listAllUsers();
        int userID = consoleService.promptForInt("Enter the id of the user to send to: ");
        BigDecimal transferAmount = consoleService.promptForBigDecimal("Enter the amount to send: ");
        BigDecimal currentBalanceAmount = accountService.getBalance(currentUser);
        transferService.sendBucks(userID, transferAmount, currentBalanceAmount);
	}

	private void requestBucks() {
		// TODO Auto-generated method stub
		
	}

}
