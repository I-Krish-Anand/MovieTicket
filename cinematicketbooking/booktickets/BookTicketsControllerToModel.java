package cinematicketbooking.booktickets;

import cinematicketbooking.dto.MovieDetails;
import cinematicketbooking.dto.ShowSpecificDetails;
import cinematicketbooking.dto.User;

import java.util.ArrayList;

public interface BookTicketsControllerToModel {

    void printMoviesName(ArrayList<MovieDetails> movieDetails) ;

    void getMoviesNameQueryFailed();

    void getAvaliableShows(ArrayList<ShowSpecificDetails> tempQuery);

    void showsUserQueryFailed();

    void printVaccancy(User user, ShowSpecificDetails showSpecificDetails);

    void invalidShowID();

    void validChoice(ShowSpecificDetails showSpecificDetails, String[] seatsChosen, User user);

    void inValidChoice();

    void bookingSuccess(String[] seatsChosen);

}