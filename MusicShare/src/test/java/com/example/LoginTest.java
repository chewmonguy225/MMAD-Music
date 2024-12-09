package com.example;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import MMAD.AccountHandler;

public class LoginTest {
    
    AccountHandler accountHandler = null;
    String testUsername = null;
    String testPassword = null;

    @Before
    public void setUp(){
        accountHandler = AccountHandler.access();
        testUsername = "spongebob";
        testPassword = "password123";
    }

    @Test
    public void testSuccessfulLogin() {
        assertTrue(accountHandler.loginAttempt(testUsername, testPassword));
    }
}
