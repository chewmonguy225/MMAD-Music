package com.example;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import MMAD.AccountHandler;

public class LoginTest {
    
    AccountHandler accountHandler = null;

    @Before
    public void setUp(){
        accountHandler = AccountHandler.access();
    }

    @Test
    public void testSuccessfulLogin() {
        assertTrue(accountHandler.loginAttempt("spongebob", "password123"));
    }
}
