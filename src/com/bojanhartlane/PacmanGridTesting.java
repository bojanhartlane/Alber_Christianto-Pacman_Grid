package com.bojanhartlane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PacmanGridTesting {
	public static void main(String[] args) {  
		// Google Chrome version 72
		System.setProperty("webdriver.chrome.driver","resources/selenium-java-3.6.0/chromedriver.exe");
		WebDriver cd = new ChromeDriver();
		
		cd.get("http://titan.csit.rmit.edu.au/~e38980/pacman_grid/");
		WebElement txtInput = cd.findElement(By.id("commands"));
		WebElement btnRun = cd.findElement(By.id("run"));
		WebElement txtResult = cd.findElement(By.id("result"));
		
		// Run successful tests
		testSuccessfulScenario1(txtInput, btnRun, txtResult);
		txtInput.clear();
		testSuccessfulScenario2(txtInput, btnRun, txtResult);
		txtInput.clear();
		testSuccessfulScenario3(txtInput, btnRun, txtResult);
		txtInput.clear();
		testSuccessfulScenario4(txtInput, btnRun, txtResult);
		txtInput.clear();
		testSuccessfulScenario5(txtInput, btnRun, txtResult);
		txtInput.clear();
		
		// Run tests that should capture user input errors
		testFailedTypo(txtInput, btnRun, txtResult);	
		txtInput.clear();
		testFailedTooManyWords(txtInput, btnRun, txtResult);
		txtInput.clear();
		testFailedPlaceDirection(txtInput, btnRun, txtResult);
		txtInput.clear();
		testFailedPlaceCoordinate(txtInput, btnRun, txtResult);
	}
	
	// Test for successful scenario from the README.md provided
	public static void testSuccessfulScenario1(WebElement txtInput, WebElement btnRun, WebElement txtResult) {
		txtInput.sendKeys("PLACE 0,0,NORTH\n\nMOVE\n\nREPORT");
		btnRun.click();
		System.out.println("Test Successful Scenario 1 Result:");
		System.out.println(txtResult.getAttribute("value"));
	}
	
	// Test for successful scenario from the README.md provided
	public static void testSuccessfulScenario2(WebElement txtInput, WebElement btnRun, WebElement txtResult) {
		txtInput.sendKeys("PLACE 0,0,NORTH\n\nLEFT\n\nREPORT");
		btnRun.click();
		System.out.println("Test Successful Scenario 2 Result:");
		System.out.println(txtResult.getAttribute("value"));
	}
	
	// Test for successful scenario from the README.md provided
	public static void testSuccessfulScenario3(WebElement txtInput, WebElement btnRun, WebElement txtResult) {
		txtInput.sendKeys("PLACE 1,2,EAST\n\nMOVE\n\nMOVE\n\nLEFT\n\nMOVE\n\nREPORT");
		btnRun.click();
		System.out.println("Test Successful Scenario 3 Result:");
		System.out.println(txtResult.getAttribute("value"));
	}
	
	// Test for successful scenario with multiple REPORT commands
	public static void testSuccessfulScenario4(WebElement txtInput, WebElement btnRun, WebElement txtResult) {
		txtInput.sendKeys("PLACE 1,2,EAST\n\nMOVE\n\nMOVE\n\nREPORT\n\nLEFT"
							+ "\n\nMOVE\n\nREPORT\n\nLEFT\n\nREPORT");
		btnRun.click();
		System.out.println("Test Successful Scenario 4 Result:");
		System.out.println(txtResult.getAttribute("value"));
	}
	
	// Test for successful scenario with leading and trailing spaces before MOVE.
	// The program should treat those lines as a normal MOVE command because the leading
	// and trailing spaces have been removed using trim() in pacman.js
	public static void testSuccessfulScenario5(WebElement txtInput, WebElement btnRun, WebElement txtResult) {
		txtInput.sendKeys("PLACE 1,2,NORTH\n\n    MOVE\n\nMOVE      \n\nREPORT\n\nRIGHT"
							+ "\n\nMOVE\n\nREPORT\n\nRIGHT\n\nREPORT");
		btnRun.click();
		System.out.println("Test Successful Scenario 5 Result:");
		System.out.println(txtResult.getAttribute("value"));
	}
	
	// Test for failed scenario with "MOVED" instead of "MOVE"
	public static void testFailedTypo(WebElement txtInput, WebElement btnRun, WebElement txtResult) {
		txtInput.sendKeys("place 1,1,east\nMoved\nReport");
		btnRun.click();
		System.out.println("Test Failed Typo Result:");
		System.out.println(txtResult.getAttribute("value"));
	}
	
	// Test for failed scenario with "MOVE" repeated 3 times instead of just once
	public static void testFailedTooManyWords(WebElement txtInput, WebElement btnRun, WebElement txtResult) {
		txtInput.sendKeys("place 1,1,west\nMove Move Move\nReport");
		btnRun.click();
		System.out.println("Test Failed Too Many Words Result:");
		System.out.println(txtResult.getAttribute("value"));
	}
	
	// Test for failed scenario with invalid facing direction
	public static void testFailedPlaceDirection(WebElement txtInput, WebElement btnRun, WebElement txtResult) {
		txtInput.sendKeys("place 1,1,eastern\nMove\nReport");
		btnRun.click();
		System.out.println("Test Failed Place Direction Result:");
		System.out.println(txtResult.getAttribute("value"));
	}
	
	// Test for failed scenario with invalid initial coordinate
	public static void testFailedPlaceCoordinate(WebElement txtInput, WebElement btnRun, WebElement txtResult) {
		txtInput.sendKeys("place 5,1,south\nMove\nReport");
		btnRun.click();
		System.out.println("Test Failed Place Direction Result:");
		System.out.println(txtResult.getAttribute("value"));
	}
}
