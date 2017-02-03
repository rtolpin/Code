package com.tests.babygodzilla;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChromeFullBabyGodzillaTest {
	private WebDriver driver;
	private String baseUrl;
	private String filepath = "/Users/rtolpin/Desktop/";
	private String filename = "Testing in fuseprospector (1).xlsx";
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	/*private String adminUserName = "rt1384@nyu.edu";*/
	/*private String adminUserName = "admin@fusetest.com";
	private String adminPassword = "test123";*/
	private String adminUserName = "rt1384@nyu.edu";
	private String adminPassword = "fusemachines";
	/*private String adminUserName = "rebecca.tolpin@fusemachines.com";
	private String adminPassword = "fusemachines";
	private String adminUserName = "admin@fusetest.com";
	private String adminPassword = "test123";*/
	private String sheetName = "excel";
	private String adminEmail = "bob@gmail.com";
	private String email1 = "ram@dell.com";
	private String email2 = "sirouhi@yahoo.com";
	private String email3 = "anup@gmail.com";
	private String email4 = "sirouhi@gmail.com";
	private String email5 = "bob@gmail.com";
	private String email6 = "bob@aol.com";
	private String email7 = "jinaconven@bonanza.com";
	private String email8 = "adobeacrobatquestions@gmail.com";
	private String firstName1 = "anish";
	private String firstName2 = "Mary";
	private String firstName3 = "Samuel";
	private String firstName4 = "Amelia";
	private String firstName5 = "Rebecca";
	private String lastName1 = "mulligan";
	private String lastName2 = "Goldman";
	private String lastName3 = "Levey";
	//private String lastName3 = "Poisson";
	private String position1 = "Sr. Software Developer";
	private String position2 = "Sr. Financial Analyst";
	private String position3 = "cow";
	/*private String firstName1 = "sirouhi";
	private String firstName2 = "Jonathan";
	private String lastName1 = "Bonanza";
	private String lastName2 = "Thompson";
	private String lastName3 = "mulligan";
	private String position1 = "Sr. Citizen";
	private String position2 = "Sr. Client Manager";
	private String position3 = "duck";*/
	private String company1 = "Apple";
	private String company2 = "dunkin donuts";
	private String company3 = "J";
	private String company4 = "someCompany" + (int)Math.random()*999;
	private String companyWebsite1 = "http://apple.com";
	private String companyWebsite2 = "http://microsoft.com";
	private String companyWebsite3 = "http://busypeople.net";
	private static String companyWebsite4 = "https://bobthebuilder.com";
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
	private String tag1 = "development";
	private String tag2 = "business";
	private String tag3 = "client";
	
	
  @Before
  public void setUp() throws Exception {
	  //ChromeOptions options = new ChromeOptions();
	  //options.addArguments("start-maximized");
	  System.setProperty("webdriver.chrome.driver", "/Applications/Google Chrome.app/Contents/MacOS/chromedriver");
	  driver = new ChromeDriver();
	  baseUrl = "https://fusemachines.com";
	  //Dimension dimension = new Dimension(1850,980);
	  Dimension dimension = new Dimension(1250,980);
	  //Dimension dimension = new Dimension(1250,100); 
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  driver.manage().window().setSize(dimension);
  }
  
  @Test
  public void sampleTest() throws Exception{
	  driver.get(baseUrl + "/sales.ai");
	  Thread.sleep(2000);
	  driver.findElement(By.linkText("Sign up")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("input[@placeholder='Your Name']")).click();
	  driver.findElement(By.xpath("input[@placeholder='Your Name']")).sendKeys(firstName5);
	  driver.findElement(By.name("username")).clear();
	  driver.findElement(By.name("username")).sendKeys(adminUserName);
	  driver.findElement(By.name("password")).clear();
	  driver.findElement(By.name("password")).sendKeys(adminPassword);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//section[@class='login-wrapper']/div/div/form/div[4]/span/button")).click();
	  Thread.sleep(2000);
	  //driver.findElement(By.xpath("//div[@id='options-sidebar']/aside/ul/li/a")).click();
	  //Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@id='options-sidebar']/aside/ul/li/ul/li[2]/a[@href='/sales.ai/sourcing/upload']")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.cssSelector("input.search")).click();
	  /*driver.findElement(By.xpath("//div[@id='options-sidebar']/aside/ul/li/ul/li[2]/a[@href='/sales.ai/sourcing/upload']")).click();
	  Thread.sleep(2000);
	  //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='data-section-filters']/span/div[@class='ui search dropdown selection']/input")));
	  driver.findElement(By.xpath("//div[@class='data-section-filters']/span/div[@class='ui search dropdown selection']/input")).click();
	  Thread.sleep(2000);*/
	  /*driver.findElement(By.xpath("//div[@class='data-section-filters']/span/div[@class='ui search dropdown selection active visible']/input[@class='search']")).sendKeys(sheetName);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='data-section-filters']/span/div[@class='ui search dropdown selection active visible']/input[@class='search']")).sendKeys(Keys.RETURN);*/
  }
  
  
   @Test
   public void testingtesting0() throws Exception{
	  driver.get(baseUrl + "/sales.ai");
	  driver.findElement(By.linkText("Sign up")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.name("username")).clear();
	  driver.findElement(By.name("username")).sendKeys(firstName5);
	  driver.findElement(By.name("Company Name")).clear();
	  driver.findElement(By.name("Company Name")).sendKeys(company4);
	  driver.findElement(By.name("email")).clear();
	  driver.findElement(By.name("email")).sendKeys(adminEmail);
	  driver.findElement(By.name("password")).clear();
	  driver.findElement(By.name("password")).sendKeys(adminPassword);
	  driver.findElement(By.name("confirmpassword")).clear();
	  driver.findElement(By.name("confirmpassword")).sendKeys(adminPassword);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//section[@class='login-wrapper']/div/div/form[@class='text-left']/div[@class='row']/span/button")).click();
	  Thread.sleep(2000);
  }
  
  @Test
  public void testingtesting1() throws Exception{
	  driver.get(baseUrl + "/sales.ai");
	  WebDriverWait wait = new WebDriverWait(driver, 5);
	  Thread.sleep(1000);
	  driver.findElement(By.name("username")).clear();
	  driver.findElement(By.name("username")).sendKeys(adminEmail);
	  driver.findElement(By.name("password")).clear();
	  driver.findElement(By.name("password")).sendKeys(adminPassword);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//section[@class='login-wrapper']/div/div/form/div[4]/span/button")).click();
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
	  //Thread.sleep(2000);
	  //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='navbar-left']/ul/li/a")));
	  //driver.findElement(By.xpath("//div[@class='navbar-left']/ul/li/a")).click();
	  Thread.sleep(2000);
	  //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='options-sidebar']/aside/ul/li[2]/a")));
	  //driver.findElement(By.xpath("//div[@id='options-sidebar']/aside/ul/li/a")).click();
	  //Thread.sleep(2000);
	  //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='options-sidebar']/aside/ul/li[2]/ul/li[2]/a[@href='/sales.ai/sourcing/upload']")));
	  driver.findElement(By.xpath("//div[@id='options-sidebar']/aside/ul/li/ul/li[2]/a[@href='/sales.ai/sourcing/upload']")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.cssSelector("input.search")).click();
	  //driver.findElement(By.xpath("//div[@class='ui search dropdown selection']")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.cssSelector("input.search")).sendKeys(sheetName);
	  //driver.findElement(By.xpath("//div[@class='data-section-filters']/span/div[@class='ui search dropdown selection active visible']/input[@class='search']")).sendKeys(sheetName);
	  Thread.sleep(1000);
	  driver.findElement(By.cssSelector("input.search")).sendKeys(Keys.RETURN);
	  //driver.findElement(By.xpath("//div[@class='data-section-filters']/span/div[@class='ui search dropdown selection active visible']/input[@class='search']")).sendKeys(Keys.RETURN);
	  //driver.findElement(By.xpath("//div[@class='ui search dropdown selection']/div[@class='menu transition visible']/div[@data-value='<fuseprospector-web@model:lead-generation/md-source::ember927:55cc9e52e4b036022fbd8ade>']")).click();
	  //driver.findElement(By.xpath("//div[@id='data-section']/div/div/div/div[@class='data-section-filters']/span/div/div[@class='menu transition visible']/div[2]")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.id("file-field-withformat")).sendKeys(filepath + filename);
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
	  driver.findElement(By.xpath("//div[@id='options-sidebar']/aside/ul/li/a")).click();
	  /*Alert alert = driver.switchTo().alert();
	  alert.accept();*/
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@id='options-sidebar']/aside/ul/li/ul/li/a[@href='/sales.ai/sourcing/clean']")).click();
	  Thread.sleep(15000);
  }
  
  @Test
  public void testingtesting2() throws Exception{
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
	  driver.findElement(By.cssSelector("input.search")).sendKeys("excel");
	  Thread.sleep(1000);
	  driver.findElement(By.cssSelector("input.search")).sendKeys(Keys.RETURN);
	  Thread.sleep(6000);
	  int lenText0 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[1]/td[3]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[1]/td[3]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for(int i = 0; i < lenText0; i++){
	    action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for(int i = 0; i < lenText0; i++){
	    action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  //Thread.sleep(2000);
	  //action.build().perform();
	  action.sendKeys(email1);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
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
	  int lenText1 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[3]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[3]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for(int i = 0; i < lenText1; i++){
		action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for(int i = 0; i < lenText1; i++){
		action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  action.sendKeys(email2);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
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
	  int lenText3 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[3]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[3]")));
	  Thread.sleep(1000);
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for(int i = 0; i < lenText3; i++){
	    action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for(int i = 0; i < lenText3; i++){
		action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  action.sendKeys(email3);
	  action.build().perform();
	  Thread.sleep(2000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
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
	  int lenText4 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[4]/td[3]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[4]/td[3]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  for(int i = 0; i < lenText4; i++){
	    action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for(int i = 0; i < lenText4; i++){
		action.sendKeys(Keys.DELETE);
	  }
	  Thread.sleep(1000);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(email3);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
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
	  int lenText5 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[3]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[3]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for(int i = 0; i < lenText5; i++){
	    action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for(int i = 0; i < lenText5; i++){
		action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(email4);
	  Thread.sleep(1000);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
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
	  int lenText6 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[3]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[3]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for(int i = 0; i < lenText6; i++){
	    action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for(int i = 0; i < lenText6; i++){
		action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(email4);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
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
	  int lenText7 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[3]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[3]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for(int i = 0; i < lenText7; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for(int i = 0; i < lenText7; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(email4);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
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
	  int lenText75 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[1]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[1]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for(int i = 0; i < lenText75; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for(int i = 0; i < lenText75; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(firstName1);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
	  Thread.sleep(2000);
	  int lenText8 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[8]/td[3]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[8]/td[3]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for(int i = 0; i < lenText8; i++){
	    action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for(int i = 0; i < lenText8; i++){
		action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(email5);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
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
	  int lenText9 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[1]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[1]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for(int i = 0; i < lenText9; i++){
	    action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for(int i = 0; i < lenText9; i++){
		action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(firstName2);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
	  Thread.sleep(2000);
	  int lenText10 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[2]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[2]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for(int i = 0; i < lenText10; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for(int i = 0; i < lenText10; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(lastName1);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
	  Thread.sleep(2000);
	  int lenText11= driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[2]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[2]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for(int i = 0; i < lenText11; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for(int i = 0; i < lenText11; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(lastName2);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
	  Thread.sleep(2000);
	  int lenText12 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[4]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[4]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for(int i = 0; i < lenText12; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for (int i = 0; i < lenText12; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(position1);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
	  Thread.sleep(2000);
	  int lenText13 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[4]/td[4]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[4]/td[4]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for(int i = 0; i < lenText13; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for(int i = 0; i < lenText13; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(position2);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
	  Thread.sleep(2000);
	  int lenText14 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[4]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[4]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for(int i = 0; i < lenText14; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for(int i = 0; i < lenText14; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(position3);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
	  Thread.sleep(2000);
	  int lenText15 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[5]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[5]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for (int i = 0; i < lenText15; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for (int i = 0; i < lenText15; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(company1);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
	  Thread.sleep(2000);
	  int lenText16 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[5]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[5]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for (int i = 0; i < lenText16; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for (int i = 0; i < lenText16; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(company2);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
	  Thread.sleep(2000);
	  int lenText17 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[5]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[5]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for (int i = 0; i < lenText17; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for (int i = 0; i < lenText17; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(company3);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
	  Thread.sleep(2000);
	  int lenText18 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[3]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[3]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for (int i = 0; i < lenText18; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for (int i = 0; i < lenText18; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(email6);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
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
	  int lenText19 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[1]/td[3]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[1]/td[3]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for (int i = 0; i < lenText19; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for (int i = 0; i < lenText19; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(email4);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
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
	  int lenText20 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[4]/td[6]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[4]/td[6]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for (int i = 0; i < lenText20; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for (int i = 0; i < lenText20; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(companyWebsite1);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
	  Thread.sleep(2000);
	  int lenText21 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[6]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[6]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for (int i = 0; i < lenText21; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for (int i = 0; i < lenText21; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(companyWebsite2);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
	  Thread.sleep(2000);
	  int lenText22 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[6]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[6]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for (int i = 0; i < lenText22; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for (int i = 0; i < lenText22; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(companyWebsite3);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
	  Thread.sleep(2000);
	  int lenText23 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[7]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[7]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for (int i = 0; i < lenText23; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for (int i = 0; i < lenText23; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(companySize1);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
	  Thread.sleep(2000);
	  int lenText24 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[7]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[7]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for (int i = 0; i < lenText24; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for (int i = 0; i < lenText24; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(companySize2);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
	  Thread.sleep(2000);
	  int lenText25 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[7]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[7]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for (int i = 0; i < lenText25; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for (int i = 0; i < lenText25; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(companySize3);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
	  Thread.sleep(2000);
	  int lenText26 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[8]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[8]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for (int i = 0; i < lenText26; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for (int i = 0; i < lenText26; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(phoneNumber1);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
	  Thread.sleep(2000);
	  int lenText27 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[8]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[8]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for (int i = 0; i < lenText27; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for (int i = 0; i < lenText27; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(phoneNumber2);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
	  Thread.sleep(2000);
	  int lenText28 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[8]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[8]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for (int i = 0; i < lenText28; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for (int i = 0; i < lenText28; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(phoneNumber3);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
	  Thread.sleep(2000);
	  int lenText29 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[8]/td[9]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[8]/td[9]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for (int i = 0; i < lenText29; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for (int i = 0; i < lenText29; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(profileUrl1);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
	  Thread.sleep(2000);
	  int lenText30 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[9]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[9]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for (int i = 0; i < lenText30; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for (int i = 0; i < lenText30; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(profileUrl2);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
	  Thread.sleep(2000);
	  int lenText31 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[9]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[9]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for (int i = 0; i < lenText31; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for (int i = 0; i < lenText31; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(profileUrl3);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
	  Thread.sleep(2000);
	  int lenText32 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[10]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[7]/td[10]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for (int i = 0; i < lenText32; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for (int i = 0; i < lenText32; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(funding1);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
	  Thread.sleep(2000);
	  int lenText33 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[10]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[10]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for (int i = 0; i < lenText33; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for (int i = 0; i < lenText33; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(funding2);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
	  Thread.sleep(2000);
	  int lenText34 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[10]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[10]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for (int i = 0; i < lenText34; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for (int i = 0; i < lenText34; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(funding3);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
	  Thread.sleep(2000);
	  int lenText35 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[11]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[11]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for (int i = 0; i < lenText35; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for (int i = 0; i < lenText35; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(revenue1);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
	  Thread.sleep(2000);
	  int lenText36 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[11]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[11]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for (int i = 0; i < lenText36; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for (int i = 0; i < lenText36; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(revenue2);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
	  Thread.sleep(2000);
	  int lenText37 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[11]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[11]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for (int i = 0; i < lenText37; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for (int i = 0; i < lenText37; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(revenue3);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
	  Thread.sleep(2000);
	  int lenText38 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[12]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[5]/td[12]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for (int i = 0; i < lenText38; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for (int i = 0; i < lenText38; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(department1);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
	  Thread.sleep(2000);
	  int lenText39 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[12]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[12]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for (int i = 0; i < lenText39; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for (int i = 0; i < lenText39; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(department2);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
	  Thread.sleep(2000);
	  int lenText40 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[12]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[6]/td[12]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for (int i = 0; i < lenText40; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for (int i = 0; i < lenText40; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(department3);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
	  Thread.sleep(5000);
	  //Switch to page 2
	  driver.findElement(By.xpath("//div[@class='label-checker ui']/ul/li[3]/a")).click();
	  Thread.sleep(1000);
	  int lenText405 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[1]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[1]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for (int i = 0; i < lenText405; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for (int i = 0; i < lenText405; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(firstName1);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
	  Thread.sleep(2000);
	  int lenText406 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[4]/td[1]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[4]/td[1]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for (int i = 0; i < lenText406; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for (int i = 0; i < lenText406; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(firstName2);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
	  Thread.sleep(2000);
	  int lenText41 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[1]/td[3]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[1]/td[3]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for (int i = 0; i < lenText41; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for (int i = 0; i < lenText41; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(email4);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
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
	  int lenText42 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[3]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[3]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for (int i = 0; i < lenText42; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for (int i = 0; i < lenText42; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(email4);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
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
	  int lenText43 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[3]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[3]/td[3]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for (int i = 0; i < lenText43; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for (int i = 0; i < lenText43; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(email7);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
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
	  int lenText44 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[4]/td[3]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[4]/td[3]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for (int i = 0; i < lenText44; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for (int i = 0; i < lenText44; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(email3);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
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
	  int lenText45 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[1]/td[4]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[1]/td[4]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for (int i = 0; i < lenText45; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for (int i = 0; i < lenText45; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(position1);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
	  Thread.sleep(2000);
	  int lenText46 = driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[4]")).getText().length();
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='ds-table table-responsive lead-table']/table/tbody/tr[2]/td[4]")));
	  action.doubleClick();
	  action.click();
	  action.build().perform();
	  Thread.sleep(1000);
	  for (int i = 0; i < lenText46; i++){
		  action.sendKeys(Keys.ARROW_LEFT);
	  }
	  action.build().perform();
	  for (int i = 0; i < lenText46; i++){
		  action.sendKeys(Keys.DELETE);
	  }
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(position2);
	  action.build().perform();
	  Thread.sleep(1000);
	  action.sendKeys(Keys.RETURN);
	  action.build().perform();
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
  public void testingtesting3() throws Exception{
	  driver.get(baseUrl + "/sales.ai");
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
	  Thread.sleep(2000);
	  //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='options-sidebar']/aside/ul/li[2]/a")));
	  //driver.findElement(By.xpath("//div[@id='options-sidebar']/aside/ul/li/a")).click();
	  //Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@id='options-sidebar']/aside/ul/li/ul/li[3]/a[@href='/sales.ai/sourcing/add']")).click();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//div[@class='table-data-wrap col-md-12']/div/form[@class='form-search ds-table-2 table-responsive']/table/tbody/tr/td")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='table-data-wrap col-md-12']/div/form[@class='form-search ds-table-2 table-responsive']/table/tbody/tr/td/input")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='table-data-wrap col-md-12']/div/form[@class='form-search ds-table-2 table-responsive']/table/tbody/tr/td/input")).sendKeys(firstName3);
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@class='table-data-wrap col-md-12']/div/form[@class='form-search ds-table-2 table-responsive']/table/tbody/tr/td[2]")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='table-data-wrap col-md-12']/div/form[@class='form-search ds-table-2 table-responsive']/table/tbody/tr/td[2]/input")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='table-data-wrap col-md-12']/div/form[@class='form-search ds-table-2 table-responsive']/table/tbody/tr/td[2]/input")).sendKeys(lastName3);
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@class='table-data-wrap col-md-12']/div/form[@class='form-search ds-table-2 table-responsive']/table/tbody/tr/td[3]")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='table-data-wrap col-md-12']/div/form[@class='form-search ds-table-2 table-responsive']/table/tbody/tr/td[3]/input")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='table-data-wrap col-md-12']/div/form[@class='form-search ds-table-2 table-responsive']/table/tbody/tr/td[3]/input")).sendKeys(email4);
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
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@class='data-section-container']")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@id='options-sidebar']/aside/ul/li/ul/li/a[@href='/sales.ai/sourcing/clean']")).click();
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
	  //driver.findElement(By.xpath("//div[@class='navbar-left']/ul/li/a")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@id='options-sidebar']/aside/ul/li/ul/li[4]/a[@href='/sales.ai/sourcing/browse']")).click();
	  Thread.sleep(5000);
  }
  
  @Test
  public void testingtesting5() throws Exception{
	  driver.get(baseUrl + "/sales.ai");
	  driver.findElement(By.name("username")).clear();
	  driver.findElement(By.name("username")).sendKeys(adminUserName);
	  driver.findElement(By.name("password")).clear();
	  driver.findElement(By.name("password")).sendKeys(adminPassword);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//section[@class='login-wrapper']/div/div/form/div[4]/span/button")).click();
	  Thread.sleep(1000);
	  //driver.findElement(By.xpath("//div[@class='navbar-left']/ul/li/a")).click();
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
  public void testingtesting6() throws Exception{
	  driver.get(baseUrl + "/sales.ai");
	  WebDriverWait wait = new WebDriverWait(driver, 5);
	  driver.findElement(By.name("username")).clear();
	  driver.findElement(By.name("username")).sendKeys(adminUserName);
	  driver.findElement(By.name("password")).clear();
	  driver.findElement(By.name("password")).sendKeys(adminPassword);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//section[@class='login-wrapper']/div/div/form/div[4]/span/button")).click();
	  Thread.sleep(1000);
	  //driver.findElement(By.xpath("//div[@class='navbar-left']/ul/li/a")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@id='options-sidebar']/aside/ul/li[2]/a")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@id='options-sidebar']/aside/ul/li[2]/ul/li/a")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.linkText("Personal")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//a[@class='align-right add-btn']")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='ds-table-2 table-responsive']/table/tbody/tr/td/input")).click();
	  driver.findElement(By.xpath("//div[@class='ds-table-2 table-responsive']/table/tbody/tr/td/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table-2 table-responsive']/table/tbody/tr/td/input")).sendKeys(email3);
	  driver.findElement(By.xpath("//div[@class='ds-table-2 table-responsive']/table/tbody/tr/td/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//a[@class='align-right add-btn']")).click();
	  Thread.sleep (1000);
	  driver.findElement(By.xpath("//div[@class='ds-table-2 table-responsive']/table/tbody/tr[2]/td/input")).click();
	  driver.findElement(By.xpath("//div[@class='ds-table-2 table-responsive']/table/tbody/tr[2]/td/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table-2 table-responsive']/table/tbody/tr[2]/td/input")).sendKeys(email5);
	  driver.findElement(By.xpath("//div[@class='ds-table-2 table-responsive']/table/tbody/tr[2]/td/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//a[@class='align-right add-btn']")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@class='ds-table-2 table-responsive']/table/tbody/tr[3]/td/input")).click();
	  driver.findElement(By.xpath("//div[@class='ds-table-2 table-responsive']/table/tbody/tr[3]/td/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table-2 table-responsive']/table/tbody/tr[3]/td/input")).sendKeys(email2);
	  driver.findElement(By.xpath("//div[@class='ds-table-2 table-responsive']/table/tbody/tr[3]/td/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  driver.findElement(By.linkText("Company")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//a[@class='align-right add-btn']")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@class='ds-table-2 table-responsive']/table/tbody/tr/td/input")).click();
	  driver.findElement(By.xpath("//div[@class='ds-table-2 table-responsive']/table/tbody/tr/td/input")).clear();
	  driver.findElement(By.xpath("//div[@class='ds-table-2 table-responsive']/table/tbody/tr/td/input")).sendKeys(email3);
	  driver.findElement(By.xpath("//div[@class='ds-table-2 table-responsive']/table/tbody/tr/td/input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
  }
  
  @Test
  public void testingtesting7() throws Exception{
	  driver.get(baseUrl + "/sales.ai");
	  WebDriverWait wait = new WebDriverWait(driver, 5);
	  driver.findElement(By.name("username")).clear();
	  driver.findElement(By.name("username")).sendKeys(adminUserName);
	  driver.findElement(By.name("password")).clear();
	  driver.findElement(By.name("password")).sendKeys(adminPassword);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//section[@class='login-wrapper']/div/div/form/div[4]/span/button")).click();
	  Thread.sleep(1000);
	  //driver.findElement(By.xpath("//div[@class='navbar-left']/ul/li/a")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@id='options-sidebar']/aside/ul/li[2]/a")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@id='options-sidebar']/aside/ul/li[2]/ul/li[2]/a")).click();
	  Thread.sleep(2000);
	  
  }
  
  @Test
  public void testingtesting8() throws Exception{
	  driver.get(baseUrl + "/sales.ai");
	  driver.findElement(By.name("username")).clear();
	  driver.findElement(By.name("username")).sendKeys(adminUserName);
	  driver.findElement(By.name("password")).clear();
	  driver.findElement(By.name("password")).sendKeys("incorrectpassword");
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//section[@class='login-wrapper']/div/div/form/div[4]/span/button")).click();
	  Thread.sleep(3000);
	  driver.get(baseUrl + "/sales.ai");
	  driver.findElement(By.name("username")).clear();
	  driver.findElement(By.name("username")).sendKeys("reb@gmail.com");
	  driver.findElement(By.name("password")).clear();
	  driver.findElement(By.name("password")).sendKeys(adminPassword);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//section[@class='login-wrapper']/div/div/form/div[4]/span/button")).click();
	  Thread.sleep(3000);
	  driver.get(baseUrl + "/sales.ai");
	  driver.findElement(By.name("username")).clear();
	  driver.findElement(By.name("username")).sendKeys(adminUserName);
	  driver.findElement(By.name("password")).clear();
	  driver.findElement(By.name("password")).sendKeys(adminPassword);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//section[@class='login-wrapper']/div/div/form/div[4]/span/button")).click();
	  Thread.sleep(1000);
  }
  
  @Test
  public void testingtesting9() throws Exception{
	  driver.get(baseUrl + "/sales.ai");
	  WebDriverWait wait = new WebDriverWait(driver, 5);
	  driver.findElement(By.name("username")).clear();
	  driver.findElement(By.name("username")).sendKeys(adminUserName);
	  driver.findElement(By.name("password")).clear();
	  driver.findElement(By.name("password")).sendKeys(adminPassword);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//section[@class='login-wrapper']/div/div/form/div[4]/span/button")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='navbar-left']/ul/li[3]/a")).click();
	  driver.findElement(By.linkText("Templates")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.linkText("Create")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//div[@class='data-section-container']/div/form/input")).click();
	  driver.findElement(By.xpath("//div[@class='data-section-container']/div/form/input")).clear();
	  driver.findElement(By.xpath("//div[@class='data-section-container']/div/form/input")).sendKeys(firstName2 + " " + lastName2);
	  driver.findElement(By.xpath("//div[@class='data-section-container']/div/form/input[2]")).click();
	  driver.findElement(By.xpath("//div[@class='data-section-container']/div/form/input[2]")).clear();
	  driver.findElement(By.xpath("//div[@class='data-section-container']/div/form/input[2]")).sendKeys(position1);
	  //driver.findElement(By.xpath("//div[@class='data-section-container']/div/form/div/div/div[4]/textarea")).click();
	  driver.findElement(By.cssSelector("div.note-editable.panel-body")).click();
	  driver.findElement(By.cssSelector("div.note-editable.panel-body")).sendKeys("Hello {{Person}},\nMy name is " + firstName2 + " " + lastName2 + " and I am here to talk to you about my product, AutomateTesting.\nWith Regards,\n" + firstName2 + " " + lastName2 + "\nSr. Developer\nFusemachines\n110 East 16th St, New York, NY");
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@class='ds-footer']/div/button[@type='submit']")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.linkText("Create")).click();
	  driver.findElement(By.xpath("//div[@class='data-section-container']/div/form/input")).click();
	  driver.findElement(By.xpath("//div[@class='data-section-container']/div/form/input")).clear();
	  driver.findElement(By.xpath("//div[@class='data-section-container']/div/form/input")).sendKeys(firstName1 + " " + lastName3);
	  driver.findElement(By.xpath("//div[@class='data-section-container']/div/form/input[2]")).click();
	  driver.findElement(By.xpath("//div[@class='data-section-container']/div/form/input[2]")).clear();
	  driver.findElement(By.xpath("//div[@class='data-section-container']/div/form/input[2]")).sendKeys(position1);
	  //driver.findElement(By.xpath("//div[@class='data-section-container']/div/form/div/div/div[4]/textarea")).click();
	  driver.findElement(By.cssSelector("div.note-editable.panel-body")).click();
	  driver.findElement(By.cssSelector("div.note-editable.panel-body")).sendKeys("Hello {{Person}},\nMy name is " + firstName1 + " " + lastName3 + " and I am here to talk to you about my product, StudyHelper.\nWith Regards,\n" + firstName1 + " " + lastName3 + "\nSr. Developer\nFusemachines\n110 East 16th St, New York, NY");
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@class='ds-footer']/div/button[@type='submit']")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.linkText("Browse")).click();
	  Thread.sleep(2000);
	  /*driver.findElement(By.linkText("Search")).click();
	  driver.findElement(By.xpath("//input[@placeholder='Search']")).click();
	  driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys(firstName1);
	  driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);*/
	  driver.findElement(By.linkText("Browse")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@class='ds-footer']/div/button[@type='submit']")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@class='temp-bx-wrapper']/button")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@class='ds-footer']/div/button")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.linkText("Archive")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.linkText("Delete")).click();
	  Thread.sleep(1000);
	  driver.switchTo().alert().accept();
	  Thread.sleep(1000);
	  driver.switchTo().alert().accept();
	  driver.findElement(By.linkText("Delete")).click();
	  Thread.sleep(1000);
	  driver.switchTo().alert().accept();
	  Thread.sleep(1000);
	  driver.switchTo().alert().accept();
	  driver.findElement(By.linkText("Create")).click();
	  driver.findElement(By.xpath("//div[@class='align-right']/button[2]")).click();
	 Thread.sleep(1000);
	 driver.findElement(By.linkText("Tags")).click();
	 driver.findElement(By.xpath("//a[@class='align-right add-btn']")).click();
	 Thread.sleep(2000);
	 driver.findElement(By.xpath("//div[@class='modal-body']/form/div[@class='form-group']/input")).click();
	 driver.findElement(By.xpath("//div[@class='modal-body']/form/div[@class='form-group']/input")).clear();
	 driver.findElement(By.xpath("//div[@class='modal-body']/form/div[@class='form-group']/input")).sendKeys(tag1);
	 Thread.sleep(1000);
	 driver.findElement(By.xpath("//div[@class='modal-body']/form/button")).click();
	 Thread.sleep(2000);
	 driver.findElement(By.xpath("//a[@class='align-right add-btn']")).click();
	 Thread.sleep(2000);
	 driver.findElement(By.xpath("//div[@class='modal-body']/form/div[@class='form-group']/input")).click();
	 driver.findElement(By.xpath("//div[@class='modal-body']/form/div[@class='form-group']/input")).clear();
	 driver.findElement(By.xpath("//div[@class='modal-body']/form/div[@class='form-group']/input")).sendKeys(tag2);
	 Thread.sleep(1000);
	 driver.findElement(By.xpath("//div[@class='modal-body']/form/button")).click();
	 Thread.sleep(2000);
	 driver.findElement(By.xpath("//a[@class='align-right add-btn']")).click();
	 Thread.sleep(2000);
	 driver.findElement(By.xpath("//div[@class='modal-body']/form/div[@class='form-group']/input")).click();
	 driver.findElement(By.xpath("//div[@class='modal-body']/form/div[@class='form-group']/input")).clear();
	 driver.findElement(By.xpath("//div[@class='modal-body']/form/div[@class='form-group']/input")).sendKeys(tag3);
	 Thread.sleep(1000);
	 driver.findElement(By.xpath("//div[@class='modal-body']/form/button")).click();
	 Thread.sleep(2000);
	 driver.findElement(By.xpath("//div[@class='table table-hover']/tbody/tr/td[2]/a")).click();
	 Thread.sleep(1000);
	 driver.switchTo().alert().accept();
  }
  
  @Test
  public void testingtesting10() throws Exception{
	  
  }
  
  
  @After
  public void tearDown() throws Exception {
	    //driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

}
