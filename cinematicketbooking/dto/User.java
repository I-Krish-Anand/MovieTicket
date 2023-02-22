package cinematicketbooking.dto;

public class User {

    private String username;

    private final String bookingName;

    private final String showChoice;

    private final int noOfTickets;

    private String[] ticketsChosen;

    private String bookingID="BKID";

    private static int bookingCount;

    public User(String bookingName, String showChoice, int noOfTickets) {
        this.bookingName = bookingName;
        this.showChoice = showChoice;
        this.noOfTickets = noOfTickets;
        bookingCount++;
        bookingID+=bookingCount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBookingName() {
        return bookingName;
    }

    public int getNoOfTickets() {
        return noOfTickets;
    }

    public void setTicketsChosen(String[] ticketsChosen) {
        this.ticketsChosen = ticketsChosen;
    }

    public String[] getTicketsChosen() {
        return ticketsChosen;
    }

}
