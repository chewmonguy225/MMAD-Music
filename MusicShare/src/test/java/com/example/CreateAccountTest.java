package com.example;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import MMAD.AccountHandler;
import MMAD.Login;

public class CreateAccountTest {
    
    AccountHandler accountHandler = null;
    Login user1;
    Login user2;

    @Before
    public void createSongAndUserObjects(){
        accountHandler = AccountHandler.access();

        user1 = new Login("jsxd12", "123");

        user2 = new Login("ksln35", "123");
    }

    @After
    public void cleanUp() {
        accountHandler.deleteAccount();
    }


    @Test
    public void testSuccessfulCreation() {
        assertTrue(accountHandler.createAccount(user1.getUsername(), user1.getPassword()));
    }

    @Test
    public void testFailedLogin() {
        accountHandler.createAccount(user1.getUsername(), user1.getPassword());
        assertFalse(accountHandler.createAccount(user1.getUsername(), user1.getPassword()));
    }
}
