package cinematicketbooking.booktickets;

import cinematicketbooking.dto.MovieDetails;
import cinematicketbooking.dto.ShowSpecificDetails;
import cinematicketbooking.dto.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class BookTicketsView implements BookTicketsViewToController {
    private final BookTicketsControllerToView bookticketsController;

    private final Scanner input=new Scanner(System.in);

    private final Scanner dateInput=new Scanner(System.in);

    public BookTicketsView() {
        bookticketsController = new BookTicketsController(this);
    }

    public void create()  {
      getMovieDetails();
    }
    private void getMovieDetails()  {
        System.out.println("\t --->WELCOME TO PVR CINEMAS<---");
        getMoviesNames();
    }
    private void getMoviesNames()  {
        bookticketsController.getMoviesNames();
    }

    @Override
    public void getMoviesNameQueryFailed() {
        System.out.println("We aren't running shows currently, Sorry");
    }

    @Override
    public void printMoviesName(ArrayList<MovieDetails> movieDetailsArrayList)  {

        System.out.print("NOW SHOWING:\t");
        for (MovieDetails movieDetails:movieDetailsArrayList){
            System.out.print(movieDetails.getMovieName()+"    ");
        }
        System.out.println("\nEnter movie of your Choice");
        String userMovieChoice=input.next();
        System.out.println("Enter Date of your Choice dd/mm/yyyy");
        String dateOfChoice=dateInput.nextLine();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (null != dateOfChoice && dateOfChoice.trim().length() > 0) {
            LocalDate date = LocalDate.parse(dateOfChoice,format);
            bookticketsController.checkAvaliablity(userMovieChoice,date);
        }

    }

    @Override
    public void getAvaliableShows(ArrayList<ShowSpecificDetails> tempQuery)
    {
        for (ShowSpecificDetails showSpecificDetails:tempQuery) {
            System.out.println("SHOW ID: "+showSpecificDetails.getShowId()+" "+showSpecificDetails+" Seats Left: "+showSpecificDetails.getCapacity());
        }
        System.out.println("Enter Booking Name");
        String bookingName=input.next();
        System.out.println("Enter your preferred SHOW-ID");
        String userShowChoice=input.next();
        System.out.println("Enter no of Tickets");
        int noOfSeatsRequested=input.nextInt();
        getShowVaccancyDetails(bookingName,userShowChoice,noOfSeatsRequested);
    }
    private void getShowVaccancyDetails(String bookingName,String userShowChoice,int noOfSeatsRequested){
        bookticketsController.getShowVaccancyDetails(bookingName,userShowChoice,noOfSeatsRequested);
    }

    @Override
    public void showsUserQueryFailed() {
        System.out.println("Shows aren't running at the moment");
    }

    @Override
    public void printVaccancy(User user, ShowSpecificDetails showSpecificDetails) {

        String[][]seatingArrangement=showSpecificDetails.getSeatingArrangement();
        for (int i=-1;i<seatingArrangement.length;i++)
        {
            if(i==-1)
                System.out.print("  ");
            else
             System.out.print(((char)(i+65))+" ");

            for(int j=0;j<seatingArrangement[0].length;j++)
            {
                if(i==-1)
                    System.out.print(j+" ");
                else
                    System.out.print(seatingArrangement[i][j]);
            }
            System.out.println(" ");
        }
        System.out.format("\n%15s"," SCREEN\n");
        System.out.println("Choose your Seats");
        String[] seatsChosen=new String[user.getNoOfTickets()];
        for(int i=0;i< user.getNoOfTickets();i++)
            seatsChosen[i]=input.next();
        checkIfSeatsAreValid(showSpecificDetails,seatsChosen,user);

    }


    @Override
    public void invalidShowID() {
        System.out.println("Criteria not met or you might have probably entered a wrong ID,Try again!");
    }

    @Override
    public void validChoice(ShowSpecificDetails showSpecificDetails, String[] seatsChosen, User user) {
        System.out.println("Choice Confirmed!");
        System.out.println("Continue Booking? True/false ");
        boolean confirmTickets=input.nextBoolean();
        bookingConfirmation(confirmTickets,showSpecificDetails,seatsChosen,user);
    }
    private void bookingConfirmation(boolean confirmTickets, ShowSpecificDetails showSpecificDetails, String[] seatsChosen, User user){
        bookticketsController.bookingConfirmation(confirmTickets,showSpecificDetails,seatsChosen,user);
    }

    @Override
    public void inValidChoice() {
        System.out.println("Invalid Choice, try again!");
    }

    private  void checkIfSeatsAreValid(ShowSpecificDetails showSpecificDetails, String[] seatsChosen, User user){
        bookticketsController.checkIfSeatsAreValid(showSpecificDetails,seatsChosen,user);
    }
    public void declineBooking(){
        System.out.println("Session Ended");
    }

    @Override
    public void bookingSuccess(String[] seatsChosen) {
        System.out.println("Booking Confirmed: Your Seats are");
        for(String tickets:seatsChosen)
            System.out.print(tickets+" ");
    }
    public void dateIsInPast(){
        System.out.println("Date cannot point to past!");
    }


}