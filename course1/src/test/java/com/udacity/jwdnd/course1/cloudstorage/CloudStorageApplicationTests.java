 package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@ActiveProfiles("test")
class CloudStorageApplicationTests {
	@Autowired
	EncryptionService encryptionService;
	@Autowired
	CredentialService credentialsService;
	@Autowired
	UserService userService;

	@LocalServerPort
	private int port;
    private WebDriver driver;
    public String baseURL;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
		baseURL = "http://localhost:" + this.port;
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get(baseURL + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}
	
	@Test
	public void testUnauthorizedUserAccess(){
		driver.get(baseURL +"/home");
		Assertions.assertEquals("Login",driver.getTitle());
		driver.get(baseURL+"/uploadFile");
		Assertions.assertEquals("Login",driver.getTitle());
		driver.get(baseURL +"/credential");
		Assertions.assertEquals("Login",driver.getTitle());
		driver.get(baseURL +"/note");
		Assertions.assertEquals("Login",driver.getTitle());
	}
	@Test
	public void testUserSignupAndLogin() throws InterruptedException {
		String username = "mm";
		String password = "mm123";
		String firstname = "kosuri";
		String lastname = "matha";
		driver.get(baseURL+"/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstname,lastname,username,password);
		signupPage.getsignupSuccessText();
		Assertions.assertEquals("You successfully signed up! Please continue to the login page.",signupPage.getsignupSuccessText());
		driver.get(baseURL+"/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username,password);
		Assertions.assertEquals("Home",driver.getTitle());
		driver.findElement(By.id("logoutbutton")).submit();
		Assertions.assertNotEquals("Home",driver.getTitle());
		driver.get(baseURL+"/home");
		Assertions.assertNotEquals("Home",driver.getTitle());
		//Thread.sleep(3000);
	}
	

	
	@Test
	  public void testAddNote() {
		userSignUpLoginProcess();
        driver.get(baseURL + "/home");
        HomePage homePage = new HomePage(driver);
      //  homePage.clickNoteTab();
        homePage.createNote();
        ResultPage resultPage = new ResultPage(driver);
		resultPage.clickHereBtn();
        homePage.clickNoteTab();
        Assertions.assertEquals("test", homePage.getNoteTitleText());
        Assertions.assertEquals("testdescription", homePage.getNoteDescriptionText());
        homePage.deleteNote();
        driver.findElement(By.id("logoutbutton")).submit();
     
	   
        
        

     //   Assertions.assertEquals(true, homePage.isNoteCreated());
    }
	
	
	@Test
	public void testDeleteNote() {
		    userSignUpLoginProcess(); 
		    driver.get(baseURL + "/home");
	        HomePage homePage = new HomePage(driver);
	        homePage.createNote();
	        driver.get(baseURL + "/home");
	        homePage.clickNoteTab();
	        homePage.deleteNote();
	        Assertions.assertThrows(NoSuchElementException.class, ()->{
	        	homePage.getNoteTitleText();
	        });
	   
		
	}
	
	@Test
	public void testEditNote() {
		    userSignUpLoginProcess();

	        driver.get(baseURL + "/home");
	        HomePage homePage = new HomePage(driver);
	        homePage.createNote();
	        driver.get(baseURL + "/home");
	        homePage.clickNoteTab();
	        homePage.editNote();
	        ResultPage resultPage = new ResultPage(driver);
	        Assertions.assertEquals("Success", resultPage.resultStatus());
			resultPage.clickHereBtn(); // Redirects to home page
	        
	        homePage.clickNoteTab();
	        Assertions.assertEquals("edittest", homePage.getNoteTitleText());
	        Assertions.assertEquals("edittestdescription", homePage.getNoteDescriptionText());
	        homePage.deleteNote();
	        driver.findElement(By.id("logoutbutton")).submit();
	        
	       // homePage.submitLogout();
	       // driver.get(baseURL+"/login");
	        
	       
	}
	
	
	@Test
	public void testAddCredential() throws Exception {
		    userSignUpLoginProcess();

	        driver.get(baseURL + "/home");
	        HomePage homePage = new HomePage(driver);
	        homePage.createCredential();
	        ResultPage resultPage = new ResultPage(driver);
			resultPage.clickHereBtn();
	        homePage.clickCredentialTab();
	      
	       
	        List<String> details = homePage.getCredentialtableDetails();
	         Assertions.assertEquals("example.gmail", details.get(0));
    	    Assertions.assertEquals("user1", details.get(1));
    	    Assertions.assertEquals("user123", getDecryptedText(details.get(2)));
		}
	
	
	
	@Test
	public void testDeleteCredential() {
		  userSignUpLoginProcess();

		  driver.get(baseURL + "/home");
	        HomePage homePage = new HomePage(driver);
	        homePage.createCredential();
	        ResultPage resultPage = new ResultPage(driver);
			resultPage.clickHereBtn();
	        homePage.clickCredentialTab();
	        homePage.deleteCredential();
	        Assertions.assertThrows(Exception.class, ()->{
	        	homePage.getCredentialUrl();
	        	
	        });
	   
		
	}
	
	private String getDecryptedText(String encryptedPassword) throws Exception {
		User user = userService.getUser("matha");
		Credential credential = credentialsService.getUserCredentials(user.getUserId()).get(0);
		return encryptionService.decryptValue(encryptedPassword, credential.getKey());
	}
	
	private void userSignUpLoginProcess() {
		
		 driver.get(baseURL + "/signup");
	        SignupPage signUpPage = new SignupPage(driver);
	        signUpPage.signup("mm","mm123","matha","kosuri"); 


	        driver.get(baseURL + "/login");
	        LoginPage loginPage = new LoginPage(driver);
	        loginPage.login("matha", "kosuri");
	}
	
}
	
	


