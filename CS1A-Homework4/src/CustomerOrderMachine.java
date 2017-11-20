import java.util.*;

public class CustomerOrderMachine
{  
   public static void main (String[] args) {
      
      Scanner inputStream = new Scanner(System.in);
      
      OrderProcessor mattsBurritos;
      mattsBurritos = new OrderProcessor("Matt's Burrito Shop", "www.best-burritos.com");      
      mattsBurritos.init();
      mattsBurritos.reportOrderDetails();
      addThreeOrders(mattsBurritos);
      mattsBurritos.reportOrderDetails();
   }
   
   public static void addThreeOrders(OrderProcessor input) {
      for(int i = 3; i > 0; i-- ) {
         input.addOrder();
      }
   }
}

class OrderProcessor {
   
   public static final int MIN_NAME_LENGTH = 1;
   public static final int MAX_NAME_LENGTH = 100;
   public static final int MIN_WEBSITE_LENGTH = 1;
   public static final int MAX_WEBSITE_LENGTH = 1000;
   public static final int ORDER_ARRAY_SIZE = 32;
   public static final int DEFAULT_ORDER_COUNT = 0;
   public static final String DEFAULT_COMPANY_NAME = "unknown";
   public static final String DEFAULT_COMPANY_WEBSITE = "unknown";
   
   private String companyName;
   private String companyWebsite;
   private int orderCount;
   private Order [] orderList = null;
   
   public OrderProcessor(){
      companyName = DEFAULT_COMPANY_NAME;
      companyWebsite = DEFAULT_COMPANY_WEBSITE;
      orderCount = DEFAULT_ORDER_COUNT;
      orderList = new Order [ORDER_ARRAY_SIZE];
   }  
   
   public OrderProcessor(String companyName, String companyWebsite){
      if (!setCompanyName(companyName))
         this.companyName = DEFAULT_COMPANY_NAME;
      if (!setCompanyWebsite(companyWebsite))
         this.companyWebsite = DEFAULT_COMPANY_WEBSITE;
      orderCount = DEFAULT_ORDER_COUNT;
      orderList = new Order [ORDER_ARRAY_SIZE];
   }
   
   public String getCompanyName() {
      return companyName;
   }
   
   public String getCompanyWebsite() {
      return companyWebsite;
   }
   
   public int getOrderCount() {
      return orderCount;
   }

   public boolean setCompanyName(String companyName){
      if(companyName.length() < MIN_NAME_LENGTH || companyName.length() > MAX_NAME_LENGTH)
         return false;
      else this.companyName = companyName;
      return true;
   }
 
   public boolean setCompanyWebsite(String companyWebsite){
      if(companyWebsite.length() < MIN_WEBSITE_LENGTH || companyWebsite.length() > MAX_WEBSITE_LENGTH)
         return false;
      else this.companyWebsite = companyWebsite;
      return true;
   }
   public boolean setOrderCount() {
      this.orderCount = orderCount(orderList);
      return true;
   }
   
   public int orderCount(Order[] orderList) {
      int count = 0;
      for(int i = 0; i < orderList.length; i++) {
         if (orderList[i] != null)
            ++count;
      }
      return count;
   }
   
   public String toString() {
      return  new String ("\n****************************"+
   "\n\tCompany Details"+"\n****************************" + "\nCompany: "+ companyName
         + "\nWebsite:"+companyWebsite+"\nOrder Count: "+orderCount);
      }
   
   public void init() {
      double LOW = 100.0;
      double  HIGH = 1000.0;
      int quantity = 0;
      int date = 0;
      double price = 0;
      int orderCount = 8;
      
      for (int i = 0; i < orderCount; i++) {
         orderList[i] = new Order();
      }
      
      String[] customerNames = {"Michael Jackson", "Queen Latifah", "Justin Timberlake", "Beyonce", "Tina Fey",
            "Gwen Stefani", "Tiger Woods", "Barack Obama"};
      
      for (int i = 0; i < customerNames.length; i++) {
         orderList[i].setCustomerName(customerNames[i]);
      }   
      
      String[] productNames = {"Chicken Burrito", "Steak Burrito", "Veggie Burrito", "Shrimp Burrito",
            "Chicken Burrito", "Steak Burrito", "Veggie Burrito", "Bean and Cheese Burrito"};
      
      for (int i = 0; i < productNames.length; i++) {
         orderList[i].setProductName(productNames[i]);
      }
      
      Random generator = new Random();
      
      for (int i=0; i < orderCount; i++) { 
         orderList[i].setQuantity(generator.nextInt(10) + 1);
      }
      
      for (int i=0; i < orderCount; i++) { 
         orderList[i].setOrderDateDay(generator.nextInt(30)+1);
      }
      
      for (int i=0; i < orderCount; i++) {
         orderList[i].setUnitPrice((Math.round((LOW + generator.nextDouble() * (HIGH - LOW))*100))*.01);
      }
      
      setOrderCount();
   }
      
   public void addOrder() {
      String atCapacity, startOrder;
      Order newOrder;
      newOrder = new Order();
      
      atCapacity = new String("Sorry, "+companyName+" is not currently accepting new orders.");
      startOrder = new String("\n****************************"
      +"\n\tNew Order"+"\n****************************");
      
      if(orderCount + 1 >= orderList.length) {
         System.out.println(atCapacity);
      }else {
         System.out.println(startOrder);
         newOrder = new Order(newOrder.getCustomerName(), newOrder.getProductName(), newOrder.getUnitPrice()
               , newOrder.getQuantity(), newOrder.getOrderDateDay());
         orderList[orderCount+1] = newOrder;
         setOrderCount();
         }
   }
   
   public void reportOrderDetails() {
      System.out.println("\n**********************************************************************"+
   "\n\t\t\tOrder Report"+ "\n**********************************************************************"+
   "\nCompany: "+companyName+"\t\tWebsite: "+companyWebsite);
      for(Order order : orderList) {
         if (order != null) {
         System.out.println(order);
         }
      }
   }
   
}

class Order {
   
   public static final int MIN_NAME_LENGTH = 2;
   public static final int MAX_NAME_LENGTH = 100;
   public static final double MIN_UNIT_PRICE = 100.00;
   public static final double MAX_UNIT_PRICE = 1000.00;
   public static final int MIN_QUANTITY = 1;
   public static final int MAX_QUANTITY = 10;
   public static final int MIN_DAY_VALUE = 1;
   public static final int MAX_DAY_VALUE = 30;
   public static final String DEFAULT_CUSTOMER_NAME = "John Doe";
   public static final String DEFAULT_PRODUCT_NAME = "unknown";
   public static final double DEFAULT_UNIT_PRICE = 0.0;
   public static final int DEFAULT_QUANTITY = 0;
   public static final int DEFAULT_ORDER_DATE_MONTH = 3;
   public static final int DEFAULT_ORDER_DATE_DAY = 01;
   public static final int DEFAULT_ORDER_DATE_YEAR = 2017;
   
   private String customerName;
   private String productName;
   private double unitPrice;
   private int quantity;
   private int orderDateMonth;
   private int orderDateDay;
   private int orderDateYear;
   
   public Order() {
      customerName = DEFAULT_CUSTOMER_NAME;
      productName = DEFAULT_PRODUCT_NAME;
      unitPrice = DEFAULT_UNIT_PRICE;
      quantity = DEFAULT_QUANTITY;
      orderDateMonth = DEFAULT_ORDER_DATE_MONTH;
      orderDateDay = DEFAULT_ORDER_DATE_DAY;
      orderDateYear = DEFAULT_ORDER_DATE_YEAR;
      }
   
   public Order(String customerName, String productName, double unitPrice,
         int quantity, int orderDateDay) {
      if (!setCustomerName(customerName))
            this.customerName = DEFAULT_CUSTOMER_NAME;
      if (!setProductName(productName))
            this.productName = DEFAULT_PRODUCT_NAME;
      if (!setUnitPrice(unitPrice))
            this.unitPrice = DEFAULT_UNIT_PRICE;
      if (!setQuantity(quantity))
            this.quantity = DEFAULT_QUANTITY;
      if (!setOrderDateDay(orderDateDay))
            this.orderDateDay = DEFAULT_ORDER_DATE_DAY;
      orderDateMonth = DEFAULT_ORDER_DATE_MONTH;
      orderDateYear = DEFAULT_ORDER_DATE_YEAR;
   }
   
   public String getCustomerName() {
      Scanner input = new Scanner(System.in);
      String prompt, strUserResponse;
      prompt = "\nEnter customer name: ";
         System.out.print(prompt);
         return strUserResponse = input.nextLine();
   }
   
   public String getProductName() {
      Scanner input = new Scanner(System.in);
      String prompt, strUserResponse;
      prompt = "\nEnter product name: ";
         System.out.print(prompt);
         return strUserResponse = input.nextLine();
   }
   
   public double getUnitPrice() {
      Scanner input = new Scanner(System.in);
      String prompt, strUserResponse;
      double dblResponse;
      prompt = "\nEnter unit price: ";
         System.out.print(prompt);
         strUserResponse = input.nextLine();
         return dblResponse = Double.parseDouble(strUserResponse);
   }
   
   public int getQuantity() {
      Scanner input = new Scanner(System.in);
      String prompt, strUserResponse;
      int intResponse;
      prompt = "\nEnter quantity: ";
         System.out.print(prompt);
         strUserResponse = input.nextLine();
         return intResponse = Integer.parseInt(strUserResponse);
   }
   
   public int getOrderDateDay() {
      Scanner input = new Scanner(System.in);
      String prompt, strUserResponse;
      int intResponse;
      prompt = "\nEnter order date: ";
         System.out.print(prompt);
         strUserResponse = input.nextLine();
         return intResponse = Integer.parseInt(strUserResponse);   }

   public boolean setCustomerName(String customerName) {
      if(customerName.length() < MIN_NAME_LENGTH || customerName.length() > MAX_NAME_LENGTH)
         return false;
      else this.customerName = customerName;
      return true;
   }
   
   public boolean setProductName(String productName) {
      if(productName.length() < MIN_NAME_LENGTH || productName.length() > MAX_NAME_LENGTH)
         return false;
      else this.productName = productName;
      return true;
   }
   
   public boolean setUnitPrice(double unitPrice) {
      if(unitPrice < MIN_UNIT_PRICE)
         return false;
      else this.unitPrice = unitPrice;
      return true;
   }
   
   public boolean setQuantity(int quantity) {
      if(quantity < MIN_QUANTITY)
         return false;
      else this.quantity = quantity;
      return true;
   }
      
   public boolean setOrderDateDay(int orderDateDay) {
      if(orderDateDay < MIN_DAY_VALUE || orderDateDay > MAX_DAY_VALUE)
         return false;
      else this.orderDateDay = orderDateDay;
      return true;
   }
      
   public double orderCost(double unitPrice, int quantity) {
      return Math.round((unitPrice * quantity)*100)/100;
   }
   
   public String toString() {
      return  new String ("\n****************************"+"\n\tOrder Details"+"\n****************************"+
   "\nCustomer: "+customerName+"\nProduct: "+productName+"\nUnit Price: $"+unitPrice+
         "\nQuantity: "+quantity+"\nOrder Date: 3/"+orderDateDay+"/2017"+"\nTotal cost: $"+
   this.orderCost(unitPrice, quantity));
   }            
}