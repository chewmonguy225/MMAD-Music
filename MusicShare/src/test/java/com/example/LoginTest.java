package com.example;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import MMAD.AccountHandler;
import MMAD.Login;

public class LoginTest {
    
    AccountHandler accountHandler = null;
    Login invalidUser;
    Login validUser;

    @Before
    public void createSongAndUserObjects(){
        accountHandler = AccountHandler.access();

        invalidUser = new Login("Not a user", "123");

        validUser = new Login("TestUser123", "TestUser123");

        accountHandler.createAccount(validUser.getUsername(), validUser.getPassword());
    }

    @After
    public void cleanUp() {
        accountHandler.deleteAccount();
    }


    @Test
    public void testSuccessfulLogin() {
        //accountHandler.createAccount(validUser.getUsername(), validUser.getPassword());
        assertTrue(accountHandler.loginAttempt(validUser.getUsername(), validUser.getPassword()));
    }

    @Test
    public void testFailedLogin() {
        assertFalse(accountHandler.loginAttempt(invalidUser.getUsername(), invalidUser.getPassword()));
    }
}
