package cinematicketbooking.admincontrols;

import cinematicketbooking.dto.MovieDetails;
import cinematicketbooking.dto.ShowSpecificDetails;
import cinematicketbooking.repository.TicketRepository;

import java.time.LocalDate;
import java.util.HashMap;

public class AdminControlsModel implements AdminControlsModelToController {
    AdminControlsControllerToModel admincontrolsController;

    AdminControlsModel(AdminControlsController admincontrolsController) {
        this.admincontrolsController = admincontrolsController;
    }

    @Override
    public void addSchedule(String movieName,int noOfScreens, boolean[] shows) {
       boolean flag= TicketRepository.getInstance().addSchedule(movieName,noOfScreens,shows);
       if(flag)
           scheduleAddedSuccessfully(movieName);
       else
           scheduleAdditionFailed();

    }
    private void scheduleAddedSuccessfully(String movieName){
        admincontrolsController.scheduleAddedSuccessfully(movieName);
    }
    private void scheduleAdditionFailed(){
        admincontrolsController.scheduleAdditionFailed();
    }

    @Override
    public void getSchedule() {
        HashMap<LocalDate, ShowSpecificDetails[][]> map=TicketRepository.getInstance().getSchedule();
        printSchedule(map);

    }
    public void printSchedule(HashMap<LocalDate, ShowSpecificDetails[][]> map){
        admincontrolsController.printSchedule(map);
    }
}