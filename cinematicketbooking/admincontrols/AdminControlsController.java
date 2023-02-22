package cinematicketbooking.admincontrols;

import cinematicketbooking.dto.ShowSpecificDetails;

import java.time.LocalDate;
import java.util.HashMap;

public class AdminControlsController implements AdminControlsControllerToView, AdminControlsControllerToModel {
    private final AdminControlsViewToController admincontrolsView;
    private final AdminControlsModelToController admincontrolsModel;

    AdminControlsController(AdminControlsView admincontrolsView) {
        this.admincontrolsView = admincontrolsView;
        admincontrolsModel = new AdminControlsModel(this);
    }

    @Override
    public void addSchedule(String movieName, int noOfScreens, boolean[] shows) {
        admincontrolsModel.addSchedule(movieName,noOfScreens,shows);
    }
    public void scheduleAddedSuccessfully(String movieName){
       admincontrolsView.scheduleAddedSuccessfully(movieName);
    }
    public void scheduleAdditionFailed(){
       admincontrolsView.scheduleAdditionFailed();
    }

    @Override
    public void getSchedule() {
       admincontrolsModel.getSchedule();
    }

    @Override
    public void printSchedule(HashMap<LocalDate, ShowSpecificDetails[][]> map) {
        admincontrolsView.printSchedule(map);
    }
}