package cinematicketbooking.repository;

import cinematicketbooking.dto.MovieDetails;
import cinematicketbooking.dto.ShowSpecificDetails;
import cinematicketbooking.dto.TheatreDetails;
import cinematicketbooking.dto.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TicketRepository {

    private static TicketRepository ticketRepository;

    LinkedHashMap<LocalDate, ShowSpecificDetails[][]>map=new LinkedHashMap<>();
    HashMap<String,String> userCredentialsMap=new HashMap<>();
    HashMap<String,String>adminDetailsMap=new HashMap<>();


    ArrayList<MovieDetails> movieDetailsArrayList=new ArrayList<>();
    ArrayList<ShowSpecificDetails> tempQuery=new ArrayList<>();

    ArrayList<User> userArrayList=new ArrayList<>();

    private String[][]temp;

    private int showCapacity;

    private TicketRepository(){

    }

    public static TicketRepository getInstance() {
        if(ticketRepository==null) {
            ticketRepository = new TicketRepository();
            ticketRepository.initialTheatreSetup();
            ticketRepository.setUserCredentialsMap();
            ticketRepository.setAdminDetailsMap();
        }
        return ticketRepository;
    }


    private void setUserCredentialsMap(){
        userCredentialsMap.put("krish","user");
    }

    private void setAdminDetailsMap(){
        adminDetailsMap.put("admin","admin");
    }
    public int checkCredentials(String username,String password){

        if(adminDetailsMap.containsKey(username)) {
            if(password.equals(adminDetailsMap.get(username)))
                return 1;
        }
        else if(userCredentialsMap.containsKey(username)) {
            if(password.equals(userCredentialsMap.get(username)))
                return 2;
        }
        return 3;
    }
    private void initialTheatreSetup(){
        TheatreDetails theatreDetails =new TheatreDetails();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.now();
        format.format(date);
        for(int i=0;i<theatreDetails.getScheduledDays();i++) {
            ShowSpecificDetails[][] showSpecificDetails = new ShowSpecificDetails[theatreDetails.getNoOfScreens()][theatreDetails.getShows()];
           map.put(date.plusDays(i),showSpecificDetails);
        }
    }
    public boolean addSchedule(String movieName, int noOfScreens,boolean[]shows)
    {
       MovieDetails movieDetails=new MovieDetails(movieName,noOfScreens,shows);
       for(Map.Entry<LocalDate, ShowSpecificDetails[][]> entry:map.entrySet())
//       {
//           ShowSpecificDetails[][] temp= entry.getValue();
//           for(int i=0;i<noOfScreens;i++){
//               for (int j=0;j<movieDetails.getShows();j++){
//                   if(temp[i][j]==null && shows[j]) {
//                       temp[i][j]=new ShowSpecificDetails(movieName,i+1);
//                       temp[i][j].setShowTimings(9+(j*4)+":00");
//                   }
//                   else  if(temp[i][j]!=null && shows[i])
//                       return false;
//               }
//           }
       }
       movieDetailsArrayList.add(movieDetails);
       return true;

    }
    public HashMap<LocalDate,ShowSpecificDetails[][]> getSchedule(){
        return map;
    }

    public boolean getMoviesNames(){ return !movieDetailsArrayList.isEmpty(); }
    public ArrayList<MovieDetails> printMoviesName(){
        return movieDetailsArrayList;
    }

    public boolean checkAvaliablity(String movieName, LocalDate date) {
        tempQuery.clear();
        for(Map.Entry<LocalDate,ShowSpecificDetails[][]>entry:map.entrySet())
        {
            if(date.isEqual(entry.getKey()))
            {
                TheatreDetails theatreDetails=new TheatreDetails();
                ShowSpecificDetails[][]temp=entry.getValue();
                for(int i=0;i<theatreDetails.getNoOfScreens();i++){
                    for (int j=0;j<theatreDetails.getShows();j++){
                        if(temp[i][j]!=null && temp[i][j].getMovieName().equals(movieName) && temp[i][j].getCapacity()>0)
                            tempQuery.add(temp[i][j]);
                    }
                }

            }
        }
        return !tempQuery.isEmpty();

    }
    public ArrayList<ShowSpecificDetails> getAvaliableShows(){
        return tempQuery;
    }
    public Object[] getShowVaccancyDetails(String bookingName,String userShowChoice, int noOfSeatsRequested) {
        for(ShowSpecificDetails showSpecificDetails:tempQuery){
            if(showSpecificDetails.getShowId().equals(userShowChoice) && showSpecificDetails.getCapacity()>=noOfSeatsRequested) {
                User visitor=new User(bookingName,userShowChoice,noOfSeatsRequested);
                return new Object[]{visitor, showSpecificDetails};
            }
        }
        return null;
    }
    public ShowSpecificDetails checkIfSeatsAreValid(ShowSpecificDetails showSpecificDetails, String[] seatsChosen, User user)
    {
          String[][]seatingArrangement=showSpecificDetails.getSeatingArrangement();
          temp=new String[seatingArrangement.length][seatingArrangement[0].length];
          showCapacity=showSpecificDetails.getCapacity();

          for(int i=0;i<seatingArrangement.length;i++)
              temp[i]=seatingArrangement[i].clone();

          for(int i=0;i<seatsChosen.length;i++){
              int row=(seatsChosen[i].charAt(0)-65);
              int col=seatsChosen[i].charAt(1)-'0';
              if(!temp[row][col].equals("💺"))
                  return null;
              else {
                  temp[row][col] = "🟩";
                  showCapacity--;
              }

          }
          return showSpecificDetails;
    }
    public void bookingConfirmation(ShowSpecificDetails showSpecificDetails,String[]seats,User user) {
        showSpecificDetails.setSeatingArrangement(temp);
        showSpecificDetails.setCapacity(showCapacity);
        user.setTicketsChosen(seats);
        userArrayList.add(user);
    }

}

