package cinematicketbooking.booktickets;

import cinematicketbooking.dto.MovieDetails;
import cinematicketbooking.dto.ShowSpecificDetails;
import cinematicketbooking.dto.User;

import java.time.LocalDate;
import java.util.ArrayList;

public class BookTicketsController implements BookTicketsControllerToView, BookTicketsControllerToModel {
    private final BookTicketsViewToController bookticketsView;
    private final BookTicketsModelToController bookticketsModel;

    BookTicketsController(BookTicketsView bookticketsView) {
        this.bookticketsView = bookticketsView;
        bookticketsModel = new BookTicketsModel(this);
    }

    @Override
    public void getMoviesNames()  {
        bookticketsModel.getMoviesNames();
    }

    @Override
    public void checkAvaliablity(String movieName, LocalDate date) {
        if(date.isBefore(LocalDate.now()))
            bookticketsView.dateIsInPast();
        else
         bookticketsModel.checkAvaliablity(movieName,date);
    }

    @Override
    public void printMoviesName(ArrayList<MovieDetails> movieDetailsArrayList)  {
        bookticketsView.printMoviesName(movieDetailsArrayList);
    }

    @Override
    public void getMoviesNameQueryFailed() {
        bookticketsView.getMoviesNameQueryFailed();
    }

    @Override
    public void getAvaliableShows(ArrayList<ShowSpecificDetails> tempQuery) {
        bookticketsView.getAvaliableShows(tempQuery);
    }

    @Override
    public void showsUserQueryFailed() {
        bookticketsView.showsUserQueryFailed();
    }

    @Override
    public void printVaccancy(User user, ShowSpecificDetails showSpecificDetails) {
        bookticketsView.printVaccancy(user,showSpecificDetails);
    }

    @Override
    public void invalidShowID() {
        bookticketsView.invalidShowID();
    }

    @Override
    public void validChoice(ShowSpecificDetails showSpecificDetails, String[] seatsChosen, User user) {
        bookticketsView.validChoice(showSpecificDetails,seatsChosen,user);
    }

    @Override
    public void inValidChoice() {
        bookticketsView.inValidChoice();
    }

    @Override
    public void bookingSuccess(String[] seatsChosen) {
        bookticketsView.bookingSuccess(seatsChosen);
    }

    @Override
    public void checkIfSeatsAreValid(ShowSpecificDetails showSpecificDetails, String[] seatsChosen, User user) {
        bookticketsModel.checkIfSeatsAreValid(showSpecificDetails,seatsChosen,user);
    }

    @Override
    public void getShowVaccancyDetails(String bookingName,String userShowChoice, int noOfSeatsRequested) {
        bookticketsModel.getShowVaccancyDetails( bookingName,userShowChoice,noOfSeatsRequested);
    }

    @Override
    public void bookingConfirmation(boolean confirmTickets, ShowSpecificDetails showSpecificDetails, String[] seatsChosen, User user) {
        if(confirmTickets)
            bookticketsModel.bookingConfirmation(showSpecificDetails,seatsChosen,user);
        else
            declineBooking();
    }
    private void declineBooking(){
        bookticketsView.declineBooking();
    }
}