//==================================================================================================
// File Name: MenuProgram.java
// Author: Matthew Zehnder
// Copyright: Copyright 2017 Matthew Zehnder
// Description: This program is an example of a menu program. It sums natural integers, verifies leap years,
// and counts vowels.
// Revision History:
// Date              Version     Change ID          Author             Comment
// 07-26-17           1.0           1           Matthew Zehnder       Initial creation
// =================================================================================================

import java.util.*;

public class MenuProgram {
   
   public static void main (String[] args) {
      String navValue;
      navValue = new String("");
      
      do
      {
         printMenu();
         navValue = getNavInput();
         switch (navValue) {
            case "s":
               sumNaturalIntegers();
               break;
            case "v":
               verifyLeapYear();
               break;
            case "c":
               countVowels();
               break;
            case "q":
               quitMessage();
               break;
         }
         if (!navValue.equals("s") && !navValue.equals("v") && !navValue.equals("c")
               && !navValue.equals("q")) {
            invalidSelection(navValue);
         }
      }
      while (!navValue.equals("q"));
   }

   public static void printMenu() {
      System.out.println ("\n\t\t**************************");
      System.out.println ("\t\t*\t FUN MENU \t*");
      System.out.println ("\t\t**************************");
      System.out.println ("\t\t<S>um of natural integers");      
      System.out.println ("\t\t<V>erify leap year");      
      System.out.println ("\t\t<C>ount vowels");      
      System.out.println ("\t\t<Q>uit");      
      System.out.println ("\n\t\tEnter a choice ('S', 's', 'V', 'v', 'C', 'c', 'Q', 'q'):\n");
      
   }
   
   public static String getNavInput() {
      String navInput;
      navInput = new String("");
      Scanner inputStream;
      inputStream = new Scanner(System.in);
      
      navInput = inputStream.nextLine().toLowerCase();

      return navInput;
   }

   public static void invalidSelection(String invalidInput) {
      System.out.println("Error: invalid choice entered: '"+ invalidInput + 
            "'. Enter S,s or V,v or C,c or Q,q only please.");
   }
   
   public static void sumNaturalIntegers() {
      int numberToSum;
      numberToSum = 0;
      int sum;
      sum = 0;
      Scanner inputStreamToSum;
      inputStreamToSum = new Scanner(System.in);
      
      System.out.println("\nEnter an integer:\n");
      numberToSum = inputStreamToSum.nextInt();
      
      for (int i = numberToSum; i > 0; i--) {
         sum = sum + i;
      }
      System.out.print("\nThe sum of " + numberToSum + " integers is: "+ sum + "\n");
      
   }

   public static void verifyLeapYear() {
      int yearToCheck;
      yearToCheck = 0;
      boolean leapBool;
      Scanner inputStream;
      inputStream = new Scanner(System.in);
      
      System.out.println("\nEnter a year\n");
      yearToCheck = inputStream.nextInt();
      
      leapBool = isLeapYear(yearToCheck);
      
      if (leapBool == false) {
         System.out.println(yearToCheck + " is not a leap year.");
      }else {
         System.out.println(yearToCheck + " is a leap year.");
      }
      
   }
   
   public static boolean isLeapYear(int year) {
      if (year % 4 == 0){
         if (year % 100 > 0) {
            return true;
         }
         else {
            if (year % 400 == 0) {
               return true;
            }
            else {
               return false;
            }
         }
      }
      else {
         return false;
      }
   }
   
   public static void countVowels() {
      String inputString;
      inputString = new String("");
      int vowelCount;
      vowelCount = 0;
      Scanner inputStreamForVowelCount;
      inputStreamForVowelCount = new Scanner(System.in);
      
      System.out.println("\nEnter a string: \n");
      inputString = inputStreamForVowelCount.nextLine().toLowerCase();
      
      for (int i = 0; i < inputString.length(); i++) {
         if (inputString.charAt(i) == 'a') {
            vowelCount++;
         }else if (inputString.charAt(i) == 'e') {
            vowelCount++;
         }else if (inputString.charAt(i) == 'i') {
            vowelCount++;
         }else if (inputString.charAt(i) == 'o') {
            vowelCount++;
         }else if (inputString.charAt(i) == 'u') {
            vowelCount++;
         }
      }
      if(vowelCount > 1) {
         System.out.print("\nYour string has " + vowelCount + " vowels.\n" );
      }
      else {
         System.out.print("\nYour string has " + vowelCount + " vowel.\n" );
      }
   }
   
   public static void quitMessage() {
      System.out.println("\nThe fun is over. Have a nice day!!!");
      System.exit(0);
   }
}
   
