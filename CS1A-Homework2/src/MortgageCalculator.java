//==================================================================================================
// File Name: MortgageCalculator.java
// Author: Matthew Zehnder
// Copyright: Copyright 2017 Matthew Zehnder
// Description: This program is a mortgage calculator that helps borrowers calculate monthly mortgage payments 
//                and property tax.
// Revision History:
// Date              Version     Change ID          Author             Comment
// 07-16-17           1.0           1           Matthew Zehnder       Initial creation
// =================================================================================================
import java.util.*;

public class MortgageCalculator
{
      
      public static final double PROPERTY_TAX_RATE = 1.5;
      public static int zipCode = 0, yearsToPayOff = 0;
      public static double annualInterestRate = 0.0, principal = 0.0, downPaymentPercentage = 0.0,
            downPayment = 0.0, loanAmount = 0.0, mortgageMonthlyPayment = 0.0, totalMonthlyPayment = 0.0, 
            totalPayment = 0.0;
      public static String address = null;
      
      public static void main (String[] args)
      {         
         getUserInput();
         calculateMortgageDetails();
         displayResults();
      }
      
      public static void getUserInput()
      {
         Scanner inputStream = new Scanner(System.in);
         String zipCodeStr = null;
         String yearsToPayOffStr = null;
         String annualInterestRateStr = null;
         String principalStr = null;
         String downPaymentPercentageStr = null;
         
         System.out.print("Enter property zip code: ");
         zipCodeStr = inputStream.nextLine();
         zipCode = Integer.parseInt(zipCodeStr);
         
         System.out.print ("Enter property address: ");
         address = inputStream.nextLine();
         
         System.out.print("Enter annual interest rate (in percentage %): ");
         annualInterestRateStr = inputStream.nextLine();
         annualInterestRate = Double.parseDouble(annualInterestRateStr);
         
         System.out.print("Enter number of years: ");
         yearsToPayOffStr = inputStream.nextLine();
         yearsToPayOff = Integer.parseInt(yearsToPayOffStr);
         
         System.out.print("Enter Principal: ");
         principalStr = inputStream.nextLine();
         principal = Double.parseDouble(principalStr);
         
         System.out.print("Enter down payment (in percentage %): ");
         downPaymentPercentageStr = inputStream.nextLine();
         downPaymentPercentage = Double.parseDouble(downPaymentPercentageStr);
         
         inputStream.close();
         
         System.out.print("\nMortgage calculator is processing your data ... Please wait ...");
         try {
            Thread.sleep(4000);
         }
         catch (Exception e) {
         }
      }
      
      public static void calculateMortgageDetails()
      {
         double monthlyInterestRate = 0.0;
         double propertyTaxMonthlyPayment = 0.0;
               
         downPayment = principal * (.01*downPaymentPercentage);
         loanAmount = principal - downPayment;
         monthlyInterestRate = annualInterestRate/1200;
         mortgageMonthlyPayment = (loanAmount*monthlyInterestRate) / (1 - 
                (1 / Math.pow((1 + monthlyInterestRate), (yearsToPayOff * 12))));
         propertyTaxMonthlyPayment = principal * PROPERTY_TAX_RATE/100/12;
         totalMonthlyPayment = mortgageMonthlyPayment + propertyTaxMonthlyPayment;
         totalPayment = mortgageMonthlyPayment*12*yearsToPayOff;
      }
      
      public static void displayResults()
      {
         System.out.print("\n\n\t ****************************");
         System.out.print("\n\n\t MORTGAGE CALCULATOR RESULTS");
         System.out.print("\n\n\t ****************************");

         System.out.print("\n\nProperty address: " + address);
         System.out.format("\n\nProperty value: \t\t\t\t $ %.2f%n%n", principal);
         System.out.format("Down Payment: \t\t\t\t $ %.2f%n%n", downPayment);
         System.out.format("Loan Amount: \t\t\t\t $ %.2f%n%n", loanAmount);
         System.out.format("Mortgage monthly payment: \t\t $ %.2f%n%n", mortgageMonthlyPayment);
         System.out.format("Monthly payment (property tax included): $ %.2f%n%n", totalMonthlyPayment);
         System.out.format("Total payment: \t\t\t\t $ %.2f%n%n",totalPayment);

      }
}
