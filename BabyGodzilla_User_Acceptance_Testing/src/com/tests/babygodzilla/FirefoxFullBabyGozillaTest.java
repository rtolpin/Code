package com.tests.babygodzilla;

import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class FirefoxFullBabyGozillaTest {
	private WebDriver driver;
	private String baseUrl;
	private String filepath = "/Users/rtolpin/Desktop/";
	private String filename = "Testing in fuseprospector (1).xlsx";
	private StringBuffer verificationErrors = new StringBuffer();
	/*private String adminUserName = "rt1384@nyu.edu";*/
	private String adminUserName = "rebecca.tolpin@gmail.com";
	private String adminPassword = "fusemachines";
	/*private String adminUserName = "admin@fusetest.com";
	private String adminPassword = "test123";*/
	private String sheetName = "excel";
	private String email1 = "ram@dell.com";
	private String email2 = "sirouhi@yahoo.com";
	private String email3 = "anup@gmail.com";
	private String email4 = "sirouhi@gmail.com";
	private String email5 = "bob@gmail.com";
	private String email6 = "rebecca.tolpin@fusemachines.com";
	private String email7 = "jeanjacques1@gmail.com";
	private String firstName1 = "anish";
	private String firstName2 = "Mary";
	private String firstName3 = "Samuel";
	private String lastName1 = "mulligan";
	private String lastName2 = "Goldman";
	private String lastName3 = "Levey";
	//private String lastName3 = "mulligan";
	private String position1 = "Sr. Software Developer";
	private String position2 = "Sr. Financial Analyst";
	private String position3 = "cow";
	private String company1 = "Apple";
	private String company2 = "dunkin donuts";
	private String company3 = "JR Stein";
	private String companyWebsite1 = "http://apple.com";
	private String companyWebsite2 = "http://microsoft.com";
	private String companyWebsite3 = "http://busypeople.net";
	private String companyWebsite4 = "https://bobthebuilder.com";
	private String companySize1 = "2";
	private String companySize2 = "73";
	private String companySize3 = "500,000";
	private String phoneNumber1 = "(201)256-7771";
	private String phoneNumber2 = "45";
	private String phoneNumber3 = "9732252898";
	private String profileUrl1 = "mary.bonanza@ralphlauren.com";
	private String profileUrl2 = "sirouhi.goldman@taj.com";
	private String profileUrl3 = "pineapple";
	private String numConnections1 = "200";
	private String numConnections2 = "2000";
	private String numConnections3 = "3";
	private String funding1 = "$5,000";
	private String funding2 = "10,000";
	private String funding3 = "$35";
	private String revenue1 = "$2,000";
	private String revenue2 = "10,000";
	private String revenue3 = "10000";
	private String department1 = "Software Engineering";
	private String department2 = "Business";
	private String department3 = "fitness"; 
	
  
  @Before
  public void setUp() throws Exception {
	ProfilesIni allProfiles = new ProfilesIni(); 
	FirefoxProfile profile = allProfiles.getProfile("default");
	driver = new FirefoxDriver(profile);
	baseUrl = "https://fusemachines.com";
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	driver.manage().window().maximize();
  }
  
  @Test
  public void testingtesting0() throws Exception{
	  driver.get(baseUrl + "/sales.ai");
	  WebDriverWait wait = new WebDriverWait(driver, 5);
	  driver.findElement(By.name("username")).clear();
	  driver.findElement(By.name("username")).sendKeys(adminUserName);
	  driver.findElement(By.name("password")).clear();
	  driver.findElement(By.name("password")).sendKeys(adminPassword);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//section[@class='login-wrapper']/div/div/form/div[4]/span/button")).click();
	  Thread.sleep(2000);
	  //driver.findElement(By.xpath("//div[@class='navbar-left']/ul/li/a")).click();
	  Thread.sleep(1000);
	  //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='options-sidebar']/aside/ul/li[2]/a")));
	  //driver.findElement(By.xpath("//div[@id='options-sidebar']/aside/ul/li/a")).click();
	  //Thread.sleep(2000);
	  //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='options-sidebar']/aside/ul/li[2]/ul/li[2]/a[@href='/sales.ai/sourcing/upload']")));
	  driver.findElement(By.xpath("//div[@id='options-sidebar']/aside/ul/li/ul/li[2]/a[@href='/sales.ai/sourcing/upload']")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.cssSelector("input.search")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.cssSelector("input.search")).clear();
	  driver.findElement(By.cssSelector("input.search")).sendKeys(sheetName);
	  Thread.sleep(1000);
	  driver.findElement(By.cssSelector("input.search")).sendKeys(Keys.RETURN);
	  //driver.findElement(By.xpath("//div[@class='ui search dropdown selection']/div[@class='menu transition visible']/div[@data-value='<fuseprospector-web@model:lead-generation/md-source::ember927:55cc9e52e4b036022fbd8ade>']")).click();
	  //driver.findElement(By.xpath("//div[@id='data-section']/div/div/div/div[@class='data-section-filters']/span/div/div[@class='menu transition visible']/div[2]")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.id("file-field-withformat")).sendKeys(filepath + filename);
	  //driver.findElement(By.id("file-field-withformat")).sendKeys("/Users/rtolpin/Downloads/Additional Leads for Colin 05-15-2015.xlsx");
	  Thread.sleep(1000);
	  driver.findElement(By.className("upload-text")).click();
	  try{
		  wait.until(ExpectedConditions.alertIsPresent());
		  Thread.sleep(4000);
		  Alert alert = driver.switchTo().alert();
		  alert.accept();
		  System.out.println("alert was present");
	  }
	  catch(Exception e){
		  System.out.println("alert was not present");
	  }
	  Thread.sleep(2000);
	  new Select(driver.findElement(By.xpath("//div[@class='step2']/form/div[@class='row']/div/div/div[2]/select[@id='firstName']"))).selectByValue("First  Name");
	  Thread.sleep(1000);
	  new Select(driver.findElement(By.xpath("//div[@class='step2']/form/div[@class='row']/div[2]/div/div[2]/select[@id='lastName']"))).selectByValue("Last Name");
	  Thread.sleep(1000);
	  new Select(driver.findElement(By.xpath("//div[@class='step2']/form/div[@class='row']/div[3]/div/div[2]/select[@id='companyName']"))).selectByValue("Company Name");
	  Thread.sleep(1000);
	  new Select(driver.findElement(By.xpath("//div[@class='step2']/form/div[@class='row']/div[4]/div/div[2]/select[@id='position']"))).selectByValue("Position");
	  Thread.sleep(1000);
	  new Select(driver.findElement(By.xpath("//div[@class='step2']/form/div[@class='row']/div[5]/div/div[2]/select[@id='companyWebsite']"))).selectByValue("Company Website");
	  Thread.sleep(1000);
	  new Select(driver.findElement(By.xpath("//div[@class='step2']/form/div[@class='row']/div[6]/div/div[2]/select[@id='email']"))).selectByValue("email");
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='col-lg-11 text-right']/button")).click();
	  Thread.sleep(2000);
	  try{
		  wait.until(ExpectedConditions.alertIsPresent());
		  Thread.sleep(4000);
		  Alert alert = driver.switchTo().alert();
		  alert.accept();
		  System.out.println("alert was present");
	  }
	  catch(Exception e){
		  System.out.println("alert was not present");
	  }
	  Thread.sleep(2000);
	  /*if (wait.until(ExpectedConditions.alertIsPresent()) ==null){
		  System.out.println("alert was not present");
	  }
	  else{
		  System.out.println("alert was present");
		  Thread.sleep(2000);
		  Alert alert = driver.switchTo().alert();
		  alert.accept();
	  }*/
	  driver.findElement(By.xpath("//div[@id='options-sidebar']/aside/ul/li/a")).click();
	  /*Alert alert = driver.switchTo().alert();
	  alert.accept();*/
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@id='options-sidebar']/aside/ul/li/ul/li/a[@href='/sales.ai/sourcing/clean']")).click();
	  Thread.sleep(15000);
  }
  
  
  @Test
  public void testingtesting1() throws Exception{
	  driver.get(baseUrl + "/sales.ai");
	  WebDriverWait wait = new WebDriverWait(driver, 20);
	  Actions action = new Actions(driver);
	  driver.findElement(By.name("username")).clear();
	  driver.findElement(By.name("username")).sendKeys(adminUserName);
	  driver.findElement(By.name("password")).clear();
	  driver.findElement(By.name("password")).sendKeys(adminPassword);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//section[@class='login-wrapper']/div/div/form/div[4]/span/button")).click();
	  Thread.sleep(1000);
	  //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='navbar-left']/ul/li/a")));
	  //driver.findElement(By.xpath("//div[@class='navbar-left']/ul/li/a")).click();
	  Thread.sleep(1000);
	  //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='options-sidebar']/aside/ul/li[2]/a")));
	  //driver.findElement(By.xpath("//div[@id='options-sidebar']/aside/ul/li/a")).click();
	  //Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@id='options-sidebar']/aside/ul/li/ul/li/a[@href='/sales.ai/sourcing/clean']")).click();
	  Thread.sleep(1000);
	  //driver.findElement(By.xpath("//div[@class='data-section-container']/div/i")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.cssSelector("input.search")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.cssSelector("input.search")).clear();
	  driver.findElement(By.cssSelector("input.search")).sendKeys("excel");
	  Thread.sleep(1000);
	  driver.findElement(By.cssSelector("input.search")).sendKeys(Keys.RETURN);
	  Thread.sleep(6000);
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[1]/td[3]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[1]/td[3]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[1]/td[3]/input")).sendKeys(email1);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[1]/td[3]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  try{
		  wait.until(ExpectedConditions.alertIsPresent());
		  Thread.sleep(4000);
		  Alert alert = driver.switchTo().alert();
		  alert.accept();
		  System.out.println("alert was present");
	  }
	  catch(Exception e){
		  System.out.println("alert was not present");
	  }
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[1]/td[3]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[1]/td[3]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[1]/td[3]/input")).sendKeys(email5);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[1]/td[3]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  try{
		  wait.until(ExpectedConditions.alertIsPresent());
		  Thread.sleep(4000);
		  Alert alert = driver.switchTo().alert();
		  alert.accept();
		  System.out.println("alert was present");
	  }
	  catch(Exception e){
		  System.out.println("alert was not present");
	  }
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[3]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[3]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[3]/input")).sendKeys(email2);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[3]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  try{
		  wait.until(ExpectedConditions.alertIsPresent());
		  Thread.sleep(4000);
		  Alert alert = driver.switchTo().alert();
		  alert.accept();
		  System.out.println("alert was present");
	  }
	  catch(Exception e){
		  System.out.println("alert was not present");
	  }
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[3]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[3]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[3]/input")).sendKeys(email6);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[3]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  try{
		  wait.until(ExpectedConditions.alertIsPresent());
		  Thread.sleep(4000);
		  Alert alert = driver.switchTo().alert();
		  alert.accept();
		  System.out.println("alert was present");
	  }
	  catch(Exception e){
		  System.out.println("alert was not present");
	  }
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[3]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[3]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[3]/input")).sendKeys(email3);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[3]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  try{
		  wait.until(ExpectedConditions.alertIsPresent());
		  Thread.sleep(4000);
		  Alert alert = driver.switchTo().alert();
		  alert.accept();
		  System.out.println("alert was present");
	  }
	  catch(Exception e){
		  System.out.println("alert was not present");
	  }
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[4]/td[3]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[4]/td[3]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[4]/td[3]/input")).sendKeys(email4);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[4]/td[3]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  try{
		  wait.until(ExpectedConditions.alertIsPresent());
		  Thread.sleep(4000);
		  Alert alert = driver.switchTo().alert();
		  alert.accept();
		  System.out.println("alert was present");
	  }
	  catch(Exception e){
		  System.out.println("alert was not present");
	  }
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[3]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[3]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[3]/input")).sendKeys(email4);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[3]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  try{
		  wait.until(ExpectedConditions.alertIsPresent());
		  Thread.sleep(4000);
		  Alert alert = driver.switchTo().alert();
		  alert.accept();
		  System.out.println("alert was present");
	  }
	  catch(Exception e){
		  System.out.println("alert was not present");
	  }
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[3]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[3]/input")).sendKeys(email4);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[3]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  try{
		  wait.until(ExpectedConditions.alertIsPresent());
		  Alert alert = driver.switchTo().alert();
		  Thread.sleep(4000);
		  alert.accept();
		  System.out.println("alert was present");
	  }
	  catch(Exception e){
		  System.out.println("alert was not present");
	  }
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[3]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[3]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[3]/input")).sendKeys(email4);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[3]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  try{
		  wait.until(ExpectedConditions.alertIsPresent());
		  Thread.sleep(4000);
		  Alert alert = driver.switchTo().alert();
		  alert.accept();
		  System.out.println("alert was present");
	  }
	  catch(Exception e){
		  System.out.println("alert was not present");
	  }
	  Thread.sleep(2000);
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[1]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[1]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[1]/input")).sendKeys(firstName1);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[1]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  try{
		  wait.until(ExpectedConditions.alertIsPresent());
		  Thread.sleep(4000);
		  Alert alert = driver.switchTo().alert();
		  alert.accept();
		  System.out.println("alert was present");
	  }
	  catch(Exception e){
		  System.out.println("alert was not present");
	  }
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[8]/td[3]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[8]/td[3]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[8]/td[3]/input")).sendKeys(email5);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[8]/td[3]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  try{
		  wait.until(ExpectedConditions.alertIsPresent());
		  Thread.sleep(4000);
		  Alert alert = driver.switchTo().alert();
		  alert.accept();
		  System.out.println("alert was present");
	  }
	  catch(Exception e){
		  System.out.println("alert was not present");
	  }
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[1]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[1]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[1]/input")).sendKeys(firstName2);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[1]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[2]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[2]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[2]/input")).sendKeys(lastName1);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[2]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[2]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[2]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[2]/input")).sendKeys(lastName2);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[2]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[4]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[4]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[4]/input")).sendKeys(position1);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[4]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[4]/td[4]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[4]/td[4]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[4]/td[4]/input")).sendKeys(position2);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[4]/td[4]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[4]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[4]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[4]/input")).sendKeys(position3);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[4]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[5]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[5]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[5]/input")).sendKeys(company1);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[5]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[5]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[5]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[5]/input")).sendKeys(company2);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[5]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[5]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[5]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[5]/input")).sendKeys(company3);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[5]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[3]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[3]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[3]/input")).sendKeys(email6);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[3]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  try{
		  wait.until(ExpectedConditions.alertIsPresent());
		  Thread.sleep(4000);
		  Alert alert = driver.switchTo().alert();
		  alert.accept();
		  System.out.println("alert was present");
	  }
	  catch(Exception e){
		  System.out.println("alert was not present");
	  }
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[1]/td[3]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[1]/td[3]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[1]/td[3]/input")).sendKeys(email4);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[1]/td[3]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  try{
		  wait.until(ExpectedConditions.alertIsPresent());
		  Thread.sleep(4000);
		  Alert alert = driver.switchTo().alert();
		  alert.accept();
		  System.out.println("alert was present");
	  }
	  catch(Exception e){
		  System.out.println("alert was not present");
	  }
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[4]/td[6]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[4]/td[6]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[4]/td[6]/input")).sendKeys(companyWebsite1);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[4]/td[6]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[6]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[6]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[6]/input")).sendKeys(companyWebsite2);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[6]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[6]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[6]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[6]/input")).sendKeys(companyWebsite3);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[6]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[7]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[7]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[7]/input")).sendKeys(companySize1);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[7]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[7]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[7]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[7]/input")).sendKeys(companySize2);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[7]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[7]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[7]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[7]/input")).sendKeys(companySize3);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[7]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[8]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[8]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[8]/input")).sendKeys(phoneNumber1);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[8]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[8]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[8]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[8]/input")).sendKeys(phoneNumber2);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[8]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[8]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[8]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[8]/input")).sendKeys(phoneNumber3);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[8]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[8]/td[9]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[8]/td[9]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[8]/td[9]/input")).sendKeys(profileUrl1);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[8]/td[9]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[9]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[9]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[9]/input")).sendKeys(profileUrl2);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[9]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[9]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[9]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[9]/input")).sendKeys(profileUrl3);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[9]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[10]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[10]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[10]/input")).sendKeys(funding1);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[10]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[10]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[10]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[10]/input")).sendKeys(funding2);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[10]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[10]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[10]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[10]/input")).sendKeys(funding3);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[10]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[11]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[11]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[11]/input")).sendKeys(revenue1);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[11]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[11]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[11]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[11]/input")).sendKeys(revenue2);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[11]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[11]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[11]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[11]/input")).sendKeys(revenue3);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[11]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[12]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[12]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[12]/input")).sendKeys(department1);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[12]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[12]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[12]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[12]/input")).sendKeys(department2);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[12]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[12]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[12]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[12]/input")).sendKeys(department3);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[12]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(5000);
	  //Switch to page 2
	  driver.findElement(By.xpath("//div[@class='label-checker ui']/ul/li[3]/a")).click();
	  Thread.sleep(1000);
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[1]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[1]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[1]/input")).sendKeys(firstName1);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[1]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[4]/td[1]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[4]/td[1]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[4]/td[1]/input")).sendKeys(firstName2);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[4]/td[1]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[1]/td[3]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[1]/td[3]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[1]/td[3]/input")).sendKeys(email4);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[1]/td[3]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  try{
		  wait.until(ExpectedConditions.alertIsPresent());
		  Thread.sleep(4000);
		  Alert alert = driver.switchTo().alert();
		  alert.accept();
		  System.out.println("alert was present");
	  }
	  catch(Exception e){
		  System.out.println("alert was not present");
	  }
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[3]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[3]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[3]/input")).sendKeys(email4);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[3]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  try{
		  wait.until(ExpectedConditions.alertIsPresent());
		  Thread.sleep(4000);
		  Alert alert = driver.switchTo().alert();
		  alert.accept();
		  System.out.println("alert was present");
	  }
	  catch(Exception e){
		  System.out.println("alert was not present");
	  }
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[3]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[3]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[3]/input")).sendKeys(email7);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[3]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  try{
		  wait.until(ExpectedConditions.alertIsPresent());
		  Thread.sleep(4000);
		  Alert alert = driver.switchTo().alert();
		  alert.accept();
		  System.out.println("alert was present");
	  }
	  catch(Exception e){
		  System.out.println("alert was not present");
	  }
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[4]/td[3]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[4]/td[3]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[4]/td[3]/input")).sendKeys(email3);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[4]/td[3]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  try{
		  wait.until(ExpectedConditions.alertIsPresent());
		  Thread.sleep(4000);
		  Alert alert = driver.switchTo().alert();
		  alert.accept();
		  System.out.println("alert was present");
	  }
	  catch(Exception e){
		  System.out.println("alert was not present");
	  }
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[1]/td[4]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[1]/td[4]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[1]/td[4]/input")).sendKeys(position1);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[1]/td[4]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[4]")));
	  action.doubleClick();
	  //action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[4]/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[4]/input")).sendKeys(position2);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[4]/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@class='label-checker ui']/ul/li[2]/a")).click();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//div[@class='ui checkbox']")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@class='data-section-head']/div[@class='data-section-link-bar']/ul/li[2]/a")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@class='data-section-head']/div[@class='data-section-link-bar']/ul/li[3]/a")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@class='navbar-right']/ul/li[2]/a")).click();
	  Thread.sleep(2000);
  }
  
  @Test
  public void testingtesting2() throws Exception{
	  driver.get(baseUrl + "/sales.ai");
	  Actions action = new Actions(driver);
	  WebDriverWait wait = new WebDriverWait(driver, 15);
	  driver.findElement(By.name("username")).clear();
	  driver.findElement(By.name("username")).sendKeys(adminUserName);
	  driver.findElement(By.name("password")).clear();
	  driver.findElement(By.name("password")).sendKeys(adminPassword);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//section[@class='login-wrapper']/div/div/form/div[4]/span/button")).click();
	  //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='navbar-left']/ul/li/a")));
	  //driver.findElement(By.xpath("//div[@class='navbar-left']/ul/li/a")).click();
	  Thread.sleep(2000);
	  //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='options-sidebar']/aside/ul/li[2]/a")));
	  //driver.findElement(By.xpath("//div[@id='options-sidebar']/aside/ul/li/a")).click();
	  //Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@id='options-sidebar']/aside/ul/li/ul/li[3]/a[@href='/sales.ai/sourcing/add']")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='table-data-wrap col-md-12']/div/form[@class='form-search ds-table-2 table-responsive']/table/tbody/tr/td")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='table-data-wrap col-md-12']/div/form[@class='form-search ds-table-2 table-responsive']/table/tbody/tr/td/input")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='table-data-wrap col-md-12']/div/form[@class='form-search ds-table-2 table-responsive']/table/tbody/tr/td/input")).sendKeys(firstName2);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='table-data-wrap col-md-12']/div/form[@class='form-search ds-table-2 table-responsive']/table/tbody/tr/td[2]")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='table-data-wrap col-md-12']/div/form[@class='form-search ds-table-2 table-responsive']/table/tbody/tr/td[2]/input")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='table-data-wrap col-md-12']/div/form[@class='form-search ds-table-2 table-responsive']/table/tbody/tr/td[2]/input")).sendKeys(lastName3);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='table-data-wrap col-md-12']/div/form[@class='form-search ds-table-2 table-responsive']/table/tbody/tr/td[3]")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='table-data-wrap col-md-12']/div/form[@class='form-search ds-table-2 table-responsive']/table/tbody/tr/td[3]/input")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='table-data-wrap col-md-12']/div/form[@class='form-search ds-table-2 table-responsive']/table/tbody/tr/td[3]/input")).sendKeys(email4);
	  /*try{
		  wait.until(ExpectedConditions.alertIsPresent());
		  Thread.sleep(4000);
		  Alert alert = driver.switchTo().alert();
		  alert.accept();
		  System.out.println("alert was present");
	  }
	  catch(Exception e){
		  System.out.println("alert was not present");
	  }*/
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@class='table-data-wrap col-md-12']/div/form[@class='form-search ds-table-2 table-responsive']/table/tbody/tr/td[4]")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='table-data-wrap col-md-12']/div/form[@class='form-search ds-table-2 table-responsive']/table/tbody/tr/td[4]/input")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='table-data-wrap col-md-12']/div/form[@class='form-search ds-table-2 table-responsive']/table/tbody/tr/td[4]/input")).sendKeys(companyWebsite1);
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@class='table-data-wrap col-md-12']/div/form[@class='form-search ds-table-2 table-responsive']/table/tbody/tr/td[5]")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='table-data-wrap col-md-12']/div/form[@class='form-search ds-table-2 table-responsive']/table/tbody/tr/td[5]/input")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='table-data-wrap col-md-12']/div/form[@class='form-search ds-table-2 table-responsive']/table/tbody/tr/td[5]/input")).sendKeys(company1);
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@class='table-data-wrap col-md-12']/div/form[@class='form-search ds-table-2 table-responsive']/table/tbody/tr/td[6]")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='table-data-wrap col-md-12']/div/form[@class='form-search ds-table-2 table-responsive']/table/tbody/tr/td[6]/input")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='table-data-wrap col-md-12']/div/form[@class='form-search ds-table-2 table-responsive']/table/tbody/tr/td[6]/input")).sendKeys(companySize2);
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@class='table-data-wrap col-md-12']/div/form[@class='form-search ds-table-2 table-responsive']/table/tbody/tr/td[7]")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='table-data-wrap col-md-12']/div/form[@class='form-search ds-table-2 table-responsive']/table/tbody/tr/td[7]/input")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='table-data-wrap col-md-12']/div/form[@class='form-search ds-table-2 table-responsive']/table/tbody/tr/td[7]/input")).sendKeys(phoneNumber1);
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@class='table-data-wrap col-md-12']/div/form[@class='form-search ds-table-2 table-responsive']/table/tbody/tr/td[8]")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='table-data-wrap col-md-12']/div/form[@class='form-search ds-table-2 table-responsive']/table/tbody/tr/td[8]/input")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='table-data-wrap col-md-12']/div/form[@class='form-search ds-table-2 table-responsive']/table/tbody/tr/td[8]/input")).sendKeys(position1);
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@class='table-data-wrap col-md-12']/div/form[@class='form-search ds-table-2 table-responsive']/table"));
	  Thread.sleep(2000);
	  //driver.findElement(By.xpath("//span[@class='align-right add-btn']")).click();
	  driver.findElement(By.xpath("//div[@class='data-section-container']")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@id='options-sidebar']/aside/ul/li/ul/li/a[@href='/sales.ai/sourcing/clean']")).click();
	  Thread.sleep(5000);
  }
  
  @Test
  public void testingtesting3() throws Exception{
	  driver.get(baseUrl + "/sales.ai");
	  driver.findElement(By.name("username")).clear();
	  driver.findElement(By.name("username")).sendKeys(adminUserName);
	  driver.findElement(By.name("password")).clear();
	  driver.findElement(By.name("password")).sendKeys(adminPassword);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//section[@class='login-wrapper']/div/div/form/div[4]/span/button")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='navbar-left']/ul/li/a")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@id='options-sidebar']/aside/ul/li/ul/li[4]/a[@href='/sales.ai/sourcing/browse']")).click();
	  Thread.sleep(5000);
  }
  
  @Test
  public void testingtesting4() throws Exception{
	  driver.get(baseUrl + "/sales.ai");
	  driver.findElement(By.name("username")).clear();
	  driver.findElement(By.name("username")).sendKeys(adminUserName);
	  driver.findElement(By.name("password")).clear();
	  driver.findElement(By.name("password")).sendKeys(adminPassword);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//section[@class='login-wrapper']/div/div/form/div[4]/span/button")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='navbar-left']/ul/li/a")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@id='options-sidebar']/aside/ul/li/ul/li[5]/a[@href='/sales.ai/sourcing/search']")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.id("search-text")).click();
	  driver.findElement(By.id("search-text")).clear();
	  driver.findElement(By.id("search-text")).sendKeys(email4);
	  driver.findElement(By.id("search-text")).sendKeys(Keys.RETURN);
	  Thread.sleep(3000);
	  driver.findElement(By.id("search-text")).click();
	  driver.findElement(By.id("search-text")).clear();
	  driver.findElement(By.id("search-text")).sendKeys(firstName1);
	  driver.findElement(By.id("search-text")).sendKeys(Keys.RETURN);
	  Thread.sleep(3000);
	  driver.findElement(By.id("search-text")).click();
	  driver.findElement(By.id("search-text")).clear();
	  driver.findElement(By.id("search-text")).sendKeys(lastName1);
	  driver.findElement(By.id("search-text")).sendKeys(Keys.RETURN);
	  Thread.sleep(3000);
	  driver.findElement(By.id("search-text")).click();
	  driver.findElement(By.id("search-text")).clear();
	  driver.findElement(By.id("search-text")).sendKeys(company1);
	  driver.findElement(By.id("search-text")).sendKeys(Keys.RETURN);
	  Thread.sleep(3000);
  }
  
  @Test
  public void testingtesting5() throws Exception{
	  driver.get(baseUrl + "/sales.ai");
	  WebDriverWait wait = new WebDriverWait(driver, 5);
	  driver.findElement(By.name("username")).clear();
	  driver.findElement(By.name("username")).sendKeys(adminUserName);
	  driver.findElement(By.name("password")).clear();
	  driver.findElement(By.name("password")).sendKeys(adminPassword);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//section[@class='login-wrapper']/div/div/form/div[4]/span/button")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='navbar-left']/ul/li/a")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@id='options-sidebar']/aside/ul/li[2]/a")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@id='options-sidebar']/aside/ul/li[2]/ul/li/a")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.linkText("Personal")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//a[@class='align-right add-btn']")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='modal-body']/input")).click();
	  driver.findElement(By.xpath("//div[@class='modal-body']/input")).clear();
	  driver.findElement(By.xpath("//div[@class='modal-body']/input")).sendKeys(email3);
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@class='modal-footer']/button[2]")).click();
	  Thread.sleep(2000);
	  try{
		  wait.until(ExpectedConditions.alertIsPresent());
		  Alert alert = driver.switchTo().alert();
		  alert.accept();
		  System.out.println("alert was present");
	  }
	  catch(Exception e){
		  System.out.println("alert was not present");
	  }
	  Thread.sleep(2000);
	  driver.findElement(By.linkText("Company")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//a[@class='align-right add-btn']")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='modal-body']/input")).click();
	  driver.findElement(By.xpath("//div[@class='modal-body']/input")).clear();
	  driver.findElement(By.xpath("//div[@class='modal-body']/input")).sendKeys(email4);
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@class='modal-footer']/button[2]")).click();
	  Thread.sleep(2000);
	  try{
		  wait.until(ExpectedConditions.alertIsPresent());
		  Alert alert = driver.switchTo().alert();
		  alert.accept();
		  System.out.println("alert was present");
	  }
	  catch(Exception e){
		  System.out.println("alert was not present");
	  }
	  Thread.sleep(2000);
  }
  
  
  @Test
  public void testingtesting6() throws Exception{
	  driver.get(baseUrl + "/sales.ai");
	  driver.findElement(By.name("username")).clear();
	  driver.findElement(By.name("username")).sendKeys(adminUserName);
	  driver.findElement(By.name("password")).clear();
	  driver.findElement(By.name("password")).sendKeys("incorrectpassword");
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//section[@class='login-wrapper']/div/div/form/div[4]/span/button")).click();
	  Thread.sleep(7000);
	  driver.get(baseUrl + "/sales.ai");
	  driver.findElement(By.name("username")).clear();
	  driver.findElement(By.name("username")).sendKeys("reb@gmail.com");
	  driver.findElement(By.name("password")).clear();
	  driver.findElement(By.name("password")).sendKeys(adminPassword);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//section[@class='login-wrapper']/div/form/div[2]/span[2]/button")).click();
	  Thread.sleep(7000);
	  driver.get(baseUrl + "/sales.ai");
	  driver.findElement(By.name("username")).clear();
	  driver.findElement(By.name("username")).sendKeys(adminUserName);
	  driver.findElement(By.name("password")).clear();
	  driver.findElement(By.name("password")).sendKeys(adminPassword);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//section[@class='login-wrapper']/div/form/div[2]/span[2]/button")).click();
	  Thread.sleep(7000);
  }
  
  
  
  
  
  
  
  
  
  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  
  
}
