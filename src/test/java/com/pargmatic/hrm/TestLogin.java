package com.pargmatic.hrm;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestLogin {

    private WebDriver webDriver;

    @BeforeClass
    public void beforeClass(){
        //Setup the driver
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    private WebDriver beforeMethod() {

        //Create an instance of the web driver
        webDriver = new ChromeDriver();

        //Navigate to the URL
        webDriver.get("http://hrm.pragmatictestlabs.com/");
        return webDriver;
    }

    @AfterMethod
    public void afterMethod(){
        webDriver.close();
    }

    @Test
    public void testValidUserLogin(){

        //Type the username
        webDriver.findElement(By.id("txtUsername")).sendKeys("Admin");

        //Type the password
        webDriver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321");

        //Click the login button
        webDriver.findElement(By.id("btnLogin")).click();

        //Verify the welcome message
        Assert.assertEquals(webDriver.findElement(By.id("welcome")).getText(),"Welcome Admin");


    }


    @Test
    public void testValidUserLoginWithEnterKeyPress(){

        //Type the username
        webDriver.findElement(By.id("txtUsername")).sendKeys("Admin");

        //Type the password
        webDriver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321"+ Keys.ENTER);

        //Verify the welcome message
        Assert.assertEquals(webDriver.findElement(By.id("welcome")).getText(),"Welcome Admin");


    }


    @Test
    public void testValidUserLoginWithSubmitMethod(){

        //Type the username
        webDriver.findElement(By.id("txtUsername")).sendKeys("Admin");

        //Type the password
        webDriver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321");
        webDriver.findElement(By.id("txtPassword")).submit();

        //Verify the welcome message
        Assert.assertEquals(webDriver.findElement(By.id("welcome")).getText(),"Welcome Admin");


    }


    @Test
    public void testUserLoginWithBlankUsernameAndBlankPassword(){

        //Type the username
        webDriver.findElement(By.id("txtUsername")).clear();

        //Type the password
        webDriver.findElement(By.id("txtPassword")).click();
        webDriver.findElement(By.id("txtPassword")).submit();

        //Verify the error message
        Assert.assertEquals(webDriver.findElement(By.id("spanMessage")).getText(),"Username cannot be empty");

    }


    @Test
    public void testUserLoginWithBlankUsername(){

        //Type the username
        webDriver.findElement(By.id("txtUsername")).clear();

        //Type the password
        webDriver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321");
        webDriver.findElement(By.id("txtPassword")).submit();

        //Verify the error message
        Assert.assertEquals(webDriver.findElement(By.id("spanMessage")).getText(),"Username cannot be empty");

    }


    @Test
    public void testUserLoginWithBlankPassword(){

        //Type the username
        webDriver.findElement(By.id("txtUsername")).sendKeys("Admin");

        //Type the password
        webDriver.findElement(By.id("txtPassword")).clear();
        webDriver.findElement(By.id("txtPassword")).submit();

        //Verify the error message
        Assert.assertEquals(webDriver.findElement(By.id("spanMessage")).getText(),"Password cannot be empty");

    }






}
