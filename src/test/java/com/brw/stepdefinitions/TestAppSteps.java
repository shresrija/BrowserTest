package com.brw.stepdefinitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;


import java.io.File;
import java.util.concurrent.TimeUnit;


/**
 * Created by Owner on 28/06/2015.
 */
public class TestAppSteps {



    public enum Browsers		{ Firefox,  InternetExplorer  };

    private WebDriver driver;
     Browsers browser;

    @Before("@WithFF")
    public void setupScenario_Firefox() {
        browser = Browsers.Firefox;
    }

    @Before("@WithIE")
    public void setupScenario_InternetExplorer() {
        browser = Browsers.InternetExplorer;
    }

    public WebDriver getDriver() {
       // if (driver == null) {
            switch (browser) {
                case Firefox: {
                    driver = new FirefoxDriver();
                    break;
                }

                case InternetExplorer: {
                    System.setProperty("webdriver.ie.driver", "src/test/browser/IEDriverServer.exe");
                    driver = new InternetExplorerDriver();
                    break;
                }
            }

        driver.manage().window().maximize();
        return driver;

    }


    @After
    public void cleanUp() {

        driver.quit();
    }



    @Given("^I navigate to Gmail site$")
    public void i_navigate_to_Gmail_site() throws Throwable {
        getDriver().get("https://www.gmail.com");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Then("^I should see the message 'Sign in to continue to Gmail'$")
    public void i_should_see_the_message_Sign_in_to_continue_to_Gmail() throws Throwable {
        Assert.assertEquals("Sign in to continue to Gmail", driver.findElement(By.className("hidden-small")).getText());
    }


    @When("^I enter username as 'shresrija'$")
    public void i_enter_username_as_shresrija() throws Throwable {
        driver.findElement(By.id("Email")).sendKeys("shresrija");
    }

    @When("^I click the Next button$")
    public void i_click_the_Next_button() throws Throwable {
        driver.findElement(By.id("next")).click();
    }


    @When("^I enter password as 'cukes(\\d+)'$")
    public void i_enter_password_as_cukes(int arg1) throws Throwable {
        driver.findElement(By.id("Passwd")).sendKeys("cukes1245");
    }

    @When("^I click on Sign In button$")
    public void i_click_on_Sign_In_button() throws Throwable {
        driver.findElement(By.id("signIn")).click();
    }




    @Then("^I should be see 'shresrija@gmail\\.com'$")
    public void i_should_be_see_shresrija_gmail_com() throws Throwable {
        Assert.assertEquals("shresrija@gmail.com",
                driver.findElement(By.xpath("//a[contains(@class,'gb_ga gb_l gb_r gb_h')]")).getText());

    }

    @Then("^I should be able to SignOut$")
    public void i_should_be_able_to_SignOut() throws Throwable {
        //Select select = new Select (driver.findElement(By.xpath("//a[contains(@class,'gb_ga gb_l gb_r gb_h')]")));
        //select.selectByVisibleText("Sign out");

        driver.findElement(By.xpath("//a[contains(@class,'gb_ga gb_l gb_r gb_h')]")).click();
        driver.findElement(By.id("gb_71")).click();
        }

    @Then("^I should see an error message 'Please enter your email\\.'$")
    public void i_should_see_an_error_message_Please_enter_your_email() throws Throwable {
        Assert.assertEquals("Please enter your email.", driver.findElement(By.id("errormsg_0_Email")).getText());

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("target/loginFailScreenShot.png"));
    }
}

