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
		
		// Run successful test cases
		testSuccessfulCase1(txtInput, btnRun, txtResult);
		txtInput.clear();
		testSuccessfulCase2(txtInput, btnRun, txtResult);
		txtInput.clear();
		testSuccessfulCase3(txtInput, btnRun, txtResult);
		txtInput.clear();
		testSuccessfulCase4(txtInput, btnRun, txtResult);
		txtInput.clear();
		testSuccessfulCase5(txtInput, btnRun, txtResult);
		txtInput.clear();
		testSuccessfulCase6(txtInput, btnRun, txtResult);
		txtInput.clear();
		testSuccessfulCase7(txtInput, btnRun, txtResult);
		txtInput.clear();
		testSuccessfulCase8(txtInput, btnRun, txtResult);
		txtInput.clear();
		testSuccessfulCase9(txtInput, btnRun, txtResult);
		txtInput.clear();
		
		// Run tests that should capture user input errors
		testFailedTypo(txtInput, btnRun, txtResult);	
		txtInput.clear();
		testFailedNotACommand(txtInput, btnRun, txtResult);
		txtInput.clear();
		testFailedTooManyWords(txtInput, btnRun, txtResult);
		txtInput.clear();
		testFailedPlaceDirection(txtInput, btnRun, txtResult);
		txtInput.clear();
		testFailedPlaceCoordinate(txtInput, btnRun, txtResult);
		txtInput.clear();
		testFailedNonNumericCoordinate(txtInput, btnRun, txtResult);
	}
	
	// Test for successful case from the README.md provided
	public static void testSuccessfulCase1(WebElement txtInput, WebElement btnRun, WebElement txtResult) {
		txtInput.sendKeys("PLACE 0,0,NORTH\n\nMOVE\n\nREPORT");
		btnRun.click();
		System.out.println("Test Successful Case 1 Result:");
		System.out.println(txtResult.getAttribute("value"));
	}
	
	// Test for successful case from the README.md provided
	public static void testSuccessfulCase2(WebElement txtInput, WebElement btnRun, WebElement txtResult) {
		txtInput.sendKeys("PLACE 0,0,NORTH\n\nLEFT\n\nREPORT");
		btnRun.click();
		System.out.println("Test Successful Case 2 Result:");
		System.out.println(txtResult.getAttribute("value"));
	}
	
	// Test for successful case from the README.md provided
	public static void testSuccessfulCase3(WebElement txtInput, WebElement btnRun, WebElement txtResult) {
		txtInput.sendKeys("PLACE 1,2,EAST\n\nMOVE\n\nMOVE\n\nLEFT\n\nMOVE\n\nREPORT");
		btnRun.click();
		System.out.println("Test Successful Case 3 Result:");
		System.out.println(txtResult.getAttribute("value"));
	}
	
	// Test for successful case with multiple REPORT commands
	public static void testSuccessfulCase4(WebElement txtInput, WebElement btnRun, WebElement txtResult) {
		txtInput.sendKeys("PLACE 1,2,EAST\n\nMOVE\n\nMOVE\n\nREPORT\n\nLEFT"
							+ "\n\nMOVE\n\nREPORT\n\nLEFT\n\nREPORT");
		btnRun.click();
		System.out.println("Test Successful Case 4 Result:");
		System.out.println(txtResult.getAttribute("value"));
	}
	
	// Test for successful case with leading and trailing spaces before MOVE.
	// The program should treat those lines as a normal MOVE command because the leading
	// and trailing spaces have been removed using trim() in pacman.js
	public static void testSuccessfulCase5(WebElement txtInput, WebElement btnRun, WebElement txtResult) {
		txtInput.sendKeys("PLACE 1,2,NORTH\n\n    MOVE\n\nMOVE      \n\nREPORT\n\nRIGHT"
							+ "\n\nMOVE\n\nREPORT\n\nRIGHT\n\nREPORT");
		btnRun.click();
		System.out.println("Test Successful Case 5 Result:");
		System.out.println(txtResult.getAttribute("value"));
	}
	
	// Test for successful case with multiple PLACE commands
	public static void testSuccessfulCase6(WebElement txtInput, WebElement btnRun, WebElement txtResult) {
		txtInput.sendKeys("PLACE 1,2,EAST\n\nMOVE\n\nMOVE\n\nREPORT\n\nPLACE 1,2,EAST\n\nLEFT"
							+ "\n\nMOVE\n\nREPORT\n\nLEFT\n\nREPORT");
		btnRun.click();
		System.out.println("Test Successful Case 6 Result:");
		System.out.println(txtResult.getAttribute("value"));
	}
	
	// Test for successful case with commands before first PLACE command and multiple PLACE commands.
	// Commands that are written before the first PLACE command will be ignored.
	public static void testSuccessfulCase7(WebElement txtInput, WebElement btnRun, WebElement txtResult) {
		txtInput.sendKeys("MOVE\n\nRIGHT\n\nPLACE 1,2,EAST\n\nMOVE\n\nMOVE\n\nREPORT\n\nPLACE 1,2,EAST\n\nLEFT"
							+ "\n\nMOVE\n\nREPORT\n\nLEFT\n\nREPORT");
		btnRun.click();
		System.out.println("Test Successful Case 7 Result:");
		System.out.println(txtResult.getAttribute("value"));
	}
	
	// Test for successful case with MOVE command that moves Pacman outside of the boundaries.
	// The output will display warning about the invalid move, but the program will still read the rest
	// of the commands and it doesn't move Pacman based on the invalid move
	public static void testSuccessfulCase8(WebElement txtInput, WebElement btnRun, WebElement txtResult) {
		txtInput.sendKeys("PLACE 2,2,EAST\n\nMOVE\n\nMOVE\n\nREPORT\n\nMOVE"
							+ "\n\nREPORT\n\nLEFT\n\nMOVE\n\nREPORT");
		btnRun.click();
		System.out.println("Test Successful Case 8 Result:");
		System.out.println(txtResult.getAttribute("value"));
	}
	
	// Test for successful case with lowercase letters and mix between lowercase and uppercase letters.
	// The program will convert all letters to uppercase, so this is not a problem.
	public static void testSuccessfulCase9(WebElement txtInput, WebElement btnRun, WebElement txtResult) {
		txtInput.sendKeys("PlaCE 2,2,east\n\nMoVe\n\nMovE\n\nREpoRt\n\nMOVE"
							+ "\n\nrEPoRT\n\nLEFT\n\nMOVE\n\nREPORT");
		btnRun.click();
		System.out.println("Test Successful Case 9 Result:");
		System.out.println(txtResult.getAttribute("value"));
	}
	
	// Test for failed case with "MOVED" instead of "MOVE"
	public static void testFailedTypo(WebElement txtInput, WebElement btnRun, WebElement txtResult) {
		txtInput.sendKeys("place 1,1,east\n\nMoved\nReport");
		btnRun.click();
		System.out.println("Test Failed Typo Result:");
		System.out.println(txtResult.getAttribute("value"));
	}
	
	// Test for failed case with a word that's not part of the command
		public static void testFailedNotACommand(WebElement txtInput, WebElement btnRun, WebElement txtResult) {
			txtInput.sendKeys("place 1,1,east\n\nMoVE\n\nRUN\nReport");
			btnRun.click();
			System.out.println("Test Failed Not A Command Result:");
			System.out.println(txtResult.getAttribute("value"));
		}
	
	// Test for failed case with "MOVE" repeated 3 times instead of just once
	public static void testFailedTooManyWords(WebElement txtInput, WebElement btnRun, WebElement txtResult) {
		txtInput.sendKeys("place 1,1,west\nMove Move Move\nReport");
		btnRun.click();
		System.out.println("Test Failed Too Many Words Result:");
		System.out.println(txtResult.getAttribute("value"));
	}
	
	// Test for failed case with invalid facing direction
	public static void testFailedPlaceDirection(WebElement txtInput, WebElement btnRun, WebElement txtResult) {
		txtInput.sendKeys("place 1,1,eastern\nMove\nReport");
		btnRun.click();
		System.out.println("Test Failed Place Direction Result:");
		System.out.println(txtResult.getAttribute("value"));
	}
	
	// Test for failed case with invalid initial coordinate
	public static void testFailedPlaceCoordinate(WebElement txtInput, WebElement btnRun, WebElement txtResult) {
		txtInput.sendKeys("place 5,1,south\nMove\nReport");
		btnRun.click();
		System.out.println("Test Failed Place Coordinate Result:");
		System.out.println(txtResult.getAttribute("value"));
	}
	
	// Test for failed case with non-numeric initial coordinate
	public static void testFailedNonNumericCoordinate(WebElement txtInput, WebElement btnRun, WebElement txtResult) {
		txtInput.sendKeys("place 5,a,south\nMove\nReport");
		btnRun.click();
		System.out.println("Test Failed Non-Numeric Coordinate Result:");
		System.out.println(txtResult.getAttribute("value"));
	}
}
