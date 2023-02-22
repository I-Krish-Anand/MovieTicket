package cinematicketbooking.login;

import cinematicketbooking.admincontrols.AdminControlsView;
import cinematicketbooking.booktickets.BookTicketsView;
import cinematicketbooking.canceltickets.CancelTicketsView;

import java.text.ParseException;
import java.util.Scanner;

public class LoginView implements LoginViewToController {
    private final LoginControllerToView loginController;
    private final Scanner input=new Scanner(System.in);

    LoginView() {
        loginController = new LoginController(this);
    }

    public static void main(String[] args)  {
        LoginView loginView=new LoginView();
        loginView.create();
    }

    public void create()  {
        getCredentials();
    }
    private void getCredentials()  {
       boolean flag;
       do {
           System.out.println("\t WELCOME TO PVR CINEMAS");
           System.out.println("Enter Username");
           String username = input.next();
           System.out.println("Enter Password");
           String password = input.next();
           loginController.checkCredentials(username, password);
           System.out.println("Switch user?True/False");
           flag=input.nextBoolean();
       } while (flag);
    }

    @Override
    public void loginSuccess(String username)  {
        System.out.println(username+""+": Logged In Successfully");
        boolean flag;
        do{
            System.out.println("Press 1 to Book Tickets");
            System.out.println("Press 2 to Cancel ticket");

            int choice = input.nextInt();
            switch (choice) {
                case 1 -> {
                    BookTicketsView bookTicketsView = new BookTicketsView();
                    bookTicketsView.create();
                }
                case 2 -> {
                    CancelTicketsView cancelTicketsView = new CancelTicketsView();
                    cancelTicketsView.create();
                }

            }
            System.out.println("Do you want to Continue: True/False");
            flag = input.nextBoolean();
        }while (flag);

    }

    @Override
    public void adminLoginSuccess(String username) {
        AdminControlsView adminControlsView=new AdminControlsView();
        adminControlsView.create();
    }
    public void loginFailure(){
        System.out.println("Invalid Credentials");
    }
}