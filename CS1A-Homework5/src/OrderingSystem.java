//==================================================================================================
// File Name: OrderingSystem.java
// Author: Matthew Zehnder
// Copyright: Copyright 2017 Matthew Zehnder
// Description: This program is an ordering system for an online fruit market.
// Revision History:
// Date              Version     Change ID          Author             Comment
// 08-10-17           1.0           1           Matthew Zehnder       Initial creation
// =================================================================================================

import java.util.*;

public class OrderingSystem
{
   public static void main (String[] args) {
      OnlineSuperMarket zehnderMart;
      zehnderMart = new OnlineSuperMarket("Zehnder-Mart", "www.zehndermart.com");
      zehnderMart.init();      
      zehnderMart.sort();
      zehnderMart.showFruits();
      zehnderMart.run();
   }
}
   
class OnlineSuperMarket
{
   private Fruit [] fruitList = null;
   private String name;
   private String website;
   private static final double TAX_RATE = 0.085;
   private static final int ARRAY_SIZE = 10;
   private static final String DEFAULT_NAME = "undefined";
   private static final String DEFAULT_WEBSITE = "undefined";
   private static final int MIN_LENGTH = 2;
   private static final int MAX_LENGTH = 200;
   
   public OnlineSuperMarket(){
      name = DEFAULT_NAME;
      website = DEFAULT_WEBSITE;
      fruitList = new Fruit [ARRAY_SIZE];
      
   }
   
   public OnlineSuperMarket(String name, String website){
      if (!setName(name))
         this.name = DEFAULT_NAME;
      if (!setWebsite(website))
         this.website = DEFAULT_WEBSITE;
      fruitList = new Fruit [ARRAY_SIZE];
   }
   
   public String getName() {
      return name;
   }
   
   public String getWebsite() {
      return website;
   }
   
   public boolean setName(String name) {
      if(name.length() < MIN_LENGTH || name.length() > MAX_LENGTH)
         return false;
      else this.name = name;
      return true;
   }
   
   public boolean setWebsite(String website) {
      if(website.length() < MIN_LENGTH || website.length() > MAX_LENGTH)
         return false;
      else this.website = website;
      return true;
   }
   
   public void init() {
      
      double WEIGHT_LOW = 1.00;
      double WEIGHT_HIGH = 50;
      double PRICE_LOW = 0.01;
      double PRICE_HIGH = 10;
      
      for (int i = 0; i < ARRAY_SIZE; i++) {
         fruitList[i] = new Fruit();
      }
      
      String[] fruitNames = {"apple", "mango", "watermelon", "blueberry", "banana", "cherry",
            "grapes", "peach", "pear", "cantalope"};
      
      for (int i = 0; i < ARRAY_SIZE; i++) {
         fruitList[i].setName(fruitNames[i]);
      }
      
      Random generator = new Random();
      
      for (int i = 0; i < ARRAY_SIZE; i++) {
         fruitList[i].setUnitPrice((generator.nextDouble() * (PRICE_HIGH - PRICE_LOW))+PRICE_LOW);
      }
      
      for (int i = 0; i < ARRAY_SIZE; i++) {
        fruitList[i].setWeight((generator.nextDouble() * (WEIGHT_HIGH - WEIGHT_LOW))+WEIGHT_LOW);
      }
   }
   
   public void sort() {
      int i, j, minIndex;
      Fruit tmp;
      tmp = new Fruit();
      int n = fruitList.length;
      for (i = 0; i < n - 1; i ++) {
         minIndex = i;
         for (j = i + 1; j < n; j++)
            if (fruitList[j].getName().compareTo(fruitList[minIndex].getName()) <0 )
               minIndex = j;
         if (minIndex != i) {
            tmp = fruitList[i];
            fruitList[i] = fruitList[minIndex];
            fruitList[minIndex] = tmp;
         }
      }
   }
   
   public void run() {
      Scanner input = new Scanner(System.in);
      String welcomeBanner = new String("Your most convenient and time saving way to order fruit from"+
      "\n\n"+this.getName().toUpperCase()+"\n\n"+this.getWebsite()+"\n\nFRUIT ORDERING");
      String promptName = new String("\nEnter a fruit name or XXX to end: ");
      String promptWeight = new String("Enter weight in lbs: ");
      String query = new String ("");
      String quitString = new String("xxx");
      String nameErrorString = new String("Sorry that fruit was not found.");
      String weightErrorString = new String("Sorry, not enough fruit to complete your order.");
      int queryResult = 0;
      double inputWeight = 0.0;
      double orderCost = 0.0;
      double totalCost = 0.0;
      String orderCostRounded = new String("");
      String totalCostRounded = new String("");
      String weightRounded = new String("");
      
      System.out.println(welcomeBanner);   
      
      while(query.compareTo(quitString) != 0) {
         System.out.println(promptName);
         query = input.nextLine().toLowerCase();
         queryResult = find(fruitList, query, 0, fruitList.length-1);            
         
         if(queryResult != -1) {
            System.out.println(promptWeight);
            inputWeight = Double.parseDouble(input.nextLine());         
            orderCost = fruitList[queryResult].order(inputWeight);
            
            if(orderCost != -1) {
               totalCost = orderCost*(1+TAX_RATE);
               weightRounded = String.format("%.2f", inputWeight);
               orderCostRounded = String.format("%.2f", orderCost);
               totalCostRounded = String.format("%.2f", totalCost);

               System.out.println("You Ordered:\n\nFruit: "+ query + "\n\nWeight: "+weightRounded+"\n\nPrice: $"
               +orderCostRounded+"\n\nTotal(plus tax): $"+totalCostRounded);
               continue;
            }else {
               System.out.println(weightErrorString);
               continue;
            }
         }
         else {
            System.out.println(nameErrorString);
            continue;
         }
      }
      quit();
   }
   
   public static int find(Fruit[] array, String name, int firstIndex, int lastIndex) {
       int middleIndex, result;
       
       if (firstIndex > lastIndex)
          return -1;
       middleIndex = (firstIndex + lastIndex) / 2;
       result = name.compareToIgnoreCase(array[middleIndex].getName());
       
       if (result==0)
          return middleIndex;
       else if (result < 0)
          return find(array, name, firstIndex, middleIndex - 1);
       else
          return find( array, name, middleIndex +1, lastIndex);
    }
    
    public void quit() {   
       for (Fruit fruit : fruitList) {
          System.out.println(fruit.toString());
       }
       System.out.println("\nThanks for your visit and please come again");
       System.exit(0);
    }
    
    public void showFruits() {
       for(Fruit fruit : fruitList) {
          System.out.println(fruit);
       }
       System.out.println("*************");
    }
}

class Fruit
{
   private String name;
   private double weight;
   private double unitPrice;
   
   public static final String DEFAULT_NAME = "?";
   public static final double DEFAULT_WEIGHT = 0.0;
   public static final double DEFAULT_UNIT_PRICE = 0.0;
   private static final int MIN_LENGTH = 2;
   private static final int MAX_LENGTH = 200;
   private static final double MIN_PRICE = .01;
   private static final double MIN_WEIGHT = 0;

   
   public Fruit() {
      name = DEFAULT_NAME;
      weight = DEFAULT_WEIGHT;
      unitPrice = DEFAULT_UNIT_PRICE;
   }
   
   public Fruit(String name, double weight, double unitPrice) {
      
   }
   
   public String getName() {
      return name;
   }
   
   public double getWeight() {
      return weight;
   }
   public double getUnitPrice() {
      return unitPrice;
   }
   
   public boolean setName(String name) {
      if(name.length() < MIN_LENGTH || name.length() > MAX_LENGTH)
         return false;
      else this.name = name;
      return true;
   }
   
   public boolean setWeight(double weight) {
      if(weight < MIN_WEIGHT)
         return false;
   else this.weight = weight;
   return true;
   }
   
   public boolean setUnitPrice(double unitPrice) {
      if(unitPrice < MIN_PRICE)
         return false;
   else this.unitPrice = unitPrice;
   return true;
   }
   
   public double order(double request) {
      if (this.weight >= request) {
         setWeight(this.weight - request);
         return(request*this.unitPrice);
      }else {
         return(-1);
      }
   }
   
   public String toString(){
      String weightRounded = new String("");
      String unitPriceRounded = new String("");
      weightRounded = String.format("%.2f", weight);
      unitPriceRounded = String.format("%.2f", unitPrice);
      
      return new String("Fruit: " + name +"\nAvailable (lbs): " + weightRounded + "\nPrice per lbs: $" + unitPriceRounded);
   }
   
}