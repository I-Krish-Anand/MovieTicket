package cinematicketbooking.admincontrols;

import java.time.LocalDate;

public interface AdminControlsControllerToView {
    void addSchedule(String movieName, int noOfScreens, boolean[] shows);

    void getSchedule();
}