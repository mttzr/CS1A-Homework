//Program Title: Movie Ticket
//Purpose: Print out a movie stub for Adult Admission at AMC Universal CityWalk.
//Programmer: Matthew Zehnder

public class MovieTicket
{
   public static void main (String [] args) {
      String movieTitle;
      int timeHour = 0;
      int timeMinute = 0;
      String timeMeridiem = null;
      int dateMonth = 0;
      int dateDay = 0;
      int dateYear = 0;
      int theaterNumber = 0;
      String movieRating = null;
      double admissionPrice = 0.0;
      
      movieTitle = new String ("MISSION IMPOSSIBLE - ROGUE NATION");
      timeHour = 3;
      timeMinute = 20;
      timeMeridiem = new String ("PM");
      dateMonth = 01;
      dateDay = 17;
      dateYear = 2017;
      theaterNumber = 10;
      movieRating = new String ("PG-13");
      admissionPrice = 12.75;
      
      System.out.println("\t\t" + "AMC Universal CityWalk");
      System.out.println("\n\t\t\t" + "Presenting");
      System.out.println("\n\t" + movieTitle);
      System.out.println("\n\tShow time: "+ timeHour + ":" + timeMinute + timeMeridiem + " Date: " + dateMonth + "/" + dateDay + "/" + dateYear);
      System.out.println("\n\tTheater: " + theaterNumber + "\t\t" + movieRating);
      System.out.println("\n\tAdult Admission $" + admissionPrice );
      System.out.println("\n\tDon't forget to get your free small popcorn with 4 tickets or more!!!");
      
   }
}
