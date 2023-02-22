package cinematicketbooking.canceltickets;

public class CancelTicketsView implements CancelTicketsViewToController {
    private CancelTicketsControllerToView cancelticketsController;

    public CancelTicketsView() {
        cancelticketsController = new CancelTicketsController(this);
    }

    public void create(){

    }
}