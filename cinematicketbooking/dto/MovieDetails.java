package cinematicketbooking.dto;

public class MovieDetails extends TheatreDetails {


    private String movieName;

    private int screensRequested;

    private boolean[]shows=new boolean[getNoOfScreens()];

    public MovieDetails(String movieName, int screensRequested,boolean[]shows) {
        this.movieName = movieName;
        this.screensRequested = screensRequested;
        this.shows=shows;

    }
    MovieDetails(){

    }
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getScreensRequested() {
        return screensRequested;
    }
    public String getMovieName() {
        return movieName;
    }
}
