import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import utils.Utils;

import java.time.Duration;

public class RegistrationTest extends BaseTest {


    By registrationButton = By.xpath("//a[@class='ico-register']");
    By selectGenderMale = By.xpath("//input[@id='gender-male']");
    By usernameField = By.cssSelector(".inputs label[for='FirstName'] + input[id='FirstName']");
    By lastnameField = By.xpath("//label[text()='Last name:']/following-sibling::input");
    By emailField = By.xpath("//input[@id='Email']");
    By passwordField = By.xpath("//input[@id='Password']");
    By confirmPassword = By.xpath("//input[@id='ConfirmPassword']");
    By register = By.xpath("//input[@id='register-button']");
    By continueButton = By.cssSelector("input[class='button-1 register-continue-button']");
    By registrationCompletedText = By.cssSelector("div[class='result']");
    By poljeBooks = By.cssSelector(".inactive a[href='/books']");
    By dodajPrvuKnjigu = By.xpath("//input[contains(@onclick, '/catalog/13/1/1')]");
    //By dodajDruguKnjigu = By.xpath("//input[contains(@onclick, '/catalog/45/1/1')]");
    //By trecaKnjiga = By.xpath("//input[contains(@onclick, '/addproducttocart/catalog/22/1/1')]");
    By klikNaDruguKnjigu = By.xpath("//img[contains(@src, '/0000133_fiction_125.jpeg')]");
    By dodajDruguKnjigu = By.cssSelector("input[id='add-to-cart-button-45']");
    By shoppingCart = By.xpath("//span[text()='Shopping cart']");
    By cenaPrveKnjige = By.xpath("//span[@class='product-unit-price'][text()='10.00']");
    By cenaDrugeKnjige = By.xpath("//span[@class='product-unit-price'][text()='24.00']");
    By konacniIznos = By.xpath("//span[text()='34.00']");


    @Test
    public void registerUserTest() {

        String email = Utils.generateRandomEmail();
        System.out.println("Email: " + email);
        clickOnElement(registrationButton);
        clickOnElement(selectGenderMale);
        //Unos podataka
        typeIn(usernameField, "Tom");
        typeIn(lastnameField, "Smith");
        typeIn(emailField, email);
        typeIn(passwordField, "SuperSecretPassword!");
        typeIn(confirmPassword, "SuperSecretPassword!");

        clickOnElement(register);

        //Verifikacija
        /*
        boolean isElementPresent = getElement(continueButton).isDisplayed();
        softAssert.assertTrue(isElementPresent);
        System.out.println("Continue dugme je prisutno.");

        String actualText = getTextFromElement(registrationCompletedText);
        String expectedText = "Your registration completed";
        softAssert.assertEquals(actualText, actualText);
        System.out.println("Očekivani tekst je prisutan.");
        softAssert.assertAll();
        */

        //Domaći. Posle registracije staviti u korpu dva elementa, i sabrati njihovu cenovnu vrednost
        clickOnElement(continueButton);
        clickOnElement(poljeBooks);
        clickOnElement(dodajPrvuKnjigu);
        //wait.until(ExpectedConditions.elementToBeClickable(By.id("//input[contains(@onclick, '/catalog/45/1/1')]")));
        //clickOnElement(dodajDruguKnjigu);
        //clickOnElement(trecaKnjiga);
        clickOnElement(klikNaDruguKnjigu);
        clickOnElement(dodajDruguKnjigu);
        clickOnElement(shoppingCart);

        //Poredjenje cena
        String prvaCena = "//span[@class='product-unit-price'][text()='10.00']";
        String drugaCena = "//span[@class='product-unit-price'][text()='24.00']";
        String konacnaCena = "//span[text()='34.00']";

        double prvaCena1 = Double.parseDouble(prvaCena.replaceAll("\\D", ""));
        double drugaCena1 = Double.parseDouble(drugaCena.replaceAll("\\D", ""));
        double konacanCena1 = Double.parseDouble(konacnaCena.replaceAll("\\D", ""));
        double konacanCena2 = prvaCena1 + drugaCena1;

        System.out.println(prvaCena1);
        System.out.println(drugaCena1);
        System.out.println(konacanCena1);
        System.out.println(konacanCena2);

        //Verifikacija
        boolean ceneSePoklapaju = konacanCena1 == konacanCena2;
        softAssert.assertTrue(ceneSePoklapaju);
        System.out.println("Cene se poklapaju");

    }

}
