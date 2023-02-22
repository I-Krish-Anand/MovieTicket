package cinematicketbooking.booktickets;

import cinematicketbooking.dto.ShowSpecificDetails;
import cinematicketbooking.dto.User;

import java.time.LocalDate;

public interface BookTicketsControllerToView {

    void getMoviesNames();

    void checkAvaliablity(String movieName, LocalDate date);

    void checkIfSeatsAreValid(ShowSpecificDetails showSpecificDetails, String[] seatsChosen, User user);

    void getShowVaccancyDetails(String bookingName,String userShowChoice, int noOfSeatsRequested);

    void bookingConfirmation(boolean confirmTickets, ShowSpecificDetails showSpecificDetails, String[] seatsChosen, User user);

}