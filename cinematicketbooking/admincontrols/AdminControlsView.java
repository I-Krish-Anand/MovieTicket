package cinematicketbooking.admincontrols;

import cinematicketbooking.dto.ShowSpecificDetails;
import cinematicketbooking.dto.TheatreDetails;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AdminControlsView implements AdminControlsViewToController {
    private final AdminControlsControllerToView admincontrolsController;

    private final Scanner input=new Scanner(System.in);

    public AdminControlsView() {
        admincontrolsController = new AdminControlsController(this);
    }

    public void create(){
        System.out.println("You are an admin now!");
        adminControls();
    }
    private void adminControls(){
       boolean flag;
       do
       {
           System.out.println("1) Press 1 to view schedule\n2) Press 2 to add a show\n3) Press 3 to cancel a show");
           int choice = input.nextInt();
           switch (choice) {
            case 1 -> viewSchedule();

            case 2 -> addSchedule();

            case 3 -> cancelSchedule();

            case 4 -> alterSchedule();
           }
         System.out.println("Do you want to continue? True/False");
         flag=input.nextBoolean();
       }while(flag);

    }

    private void viewSchedule() {
        admincontrolsController.getSchedule();
    }
    private void addSchedule()
    {
        System.out.println("Enter a movie name to schedule booking");
        String movieName=input.next();
        System.out.println("Enter required screens:");
        int  noOfScreens=input.nextInt();
        boolean[]shows=new boolean[4];
        System.out.println("Morning show 9:00 ? True/false");
        shows[0]=input.nextBoolean();
        System.out.println("Matinee show 13:00 ? True/false");
        shows[1]=input.nextBoolean();
        System.out.println("Evening show 17:00? True/false");
        shows[2]=input.nextBoolean();
        System.out.println("Night show 21:00? True/false");
        shows[3]=input.nextBoolean();
        admincontrolsController.addSchedule(movieName,noOfScreens,shows);

    }
    private void cancelSchedule(){

    }
    private void alterSchedule(){

    }
    public void scheduleAddedSuccessfully(String movieName){
        System.out.println("Schedule created for "+movieName+" successfully");
    }
    public void scheduleAdditionFailed(){
        System.out.println("Schedules clash in-between, kindly re-verify your choice");
    }

    @Override
    public void printSchedule(HashMap<LocalDate, ShowSpecificDetails[][]> map) {
        TheatreDetails theatreDetails =new TheatreDetails();
        System.out.println("Date\t\t\t\t\tSCREEN1\t\t\t\t\t\tSCREEN2");
        for(Map.Entry<LocalDate, ShowSpecificDetails[][]> entry:map.entrySet())
        {
            System.out.print(entry.getKey()+"\t");
            ShowSpecificDetails[][] showSpecificDetails= entry.getValue();
            for(int i=0;i<theatreDetails.getNoOfScreens();i++){
                for (int j=0;j<theatreDetails.getShows();j++){
                    if(showSpecificDetails[i][j]!=null)
                     System.out.print(showSpecificDetails[i][j]+"---");
                    else
                        System.out.print("FREE---");
                }
                System.out.print("\t");
            }
            System.out.println("\n");
        }
    }
}