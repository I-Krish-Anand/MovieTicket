package cinematicketbooking.canceltickets;

public class CancelTicketsController implements CancelTicketsControllerToView, CancelTicketsControllerToModel {
    private CancelTicketsViewToController cancelticketsView;
    private CancelTicketsModelToController cancelticketsModel;

    CancelTicketsController(CancelTicketsView cancelticketsView) {
        this.cancelticketsView = cancelticketsView;
        cancelticketsModel = new CancelTicketsModel(this);
    }
}