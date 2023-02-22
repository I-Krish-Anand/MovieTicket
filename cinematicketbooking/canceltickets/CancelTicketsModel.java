package cinematicketbooking.canceltickets;

public class CancelTicketsModel implements CancelTicketsModelToController {
    CancelTicketsControllerToModel cancelticketsController;

    CancelTicketsModel(CancelTicketsController cancelticketsController) {
        this.cancelticketsController = cancelticketsController;
    }
}