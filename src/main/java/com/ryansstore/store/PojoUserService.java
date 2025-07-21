package com.ryansstore.store;

// import org.springframework.scheduling.annotation.Scheduled;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

// No longer needed with AppConfig class approach
/*import org.springframework.stereotype.Service;

@Service*/
public class PojoUserService {
    private final PojoUserRepository userRepo;
    private final NotificationManager notifManager;

    public PojoUserService(PojoUserRepository userRepo, NotificationManager notifManager) {
        this.userRepo = userRepo;
        this.notifManager = notifManager;
    }

    public void registerUser(PojoUser user) {
        if(!userRepo.userExists(user)) {
            // optional: add user registration text message
            // generate random code
            Random random = new Random();
            int regCode = (random.nextInt(900000) + 100000);

            // send regCode to user over sms
            notifManager.sendNotification("sms", "Registration code: " + regCode, user.getPhoneNumber());

            // ask user to input the code they received through sms
            Scanner scanner = new Scanner(System.in);
            int inputCode = -1;
            for(int i = 0; i < 3; i++) {
                if(i > 0)
                    System.out.println("Oops! The code you provided didn't match the registration code!\nLet's try again! Attempt #" + (i+1) + "!");

                System.out.print("Enter your registration code: ");

                try {
                    inputCode = scanner.nextInt();
                }
                catch(InputMismatchException e) {
                    // System.err.println("ERROR: Input didn't contain only integers");
                    System.out.println("Oops! We noticed a wrong input, please only input numbers!");
                    scanner.nextLine();
                    i--;
                }

                if(inputCode == regCode) { // Success: save user and send confirmation email
                    userRepo.saveUser(user);
                    notifManager.sendNotification("email", "user " + user.getName() + " has been successfully registered!", user.getEmail());
                    return;
                }
            }
            // Failure: unable to due to mismatching registration codes
            // System.err.println("ERROR: Input didn't match registration code after three attempts");
            System.out.println("Oops! Your input didn't match the registration code, please try again later!");
        }
        else // Optional: handle duplicate user
        {
            // System.err.println("ERROR: User with email " + user.getEmail() + " already exists! Duplicate users are not supported.");
            System.out.println("Oops! A user with email " + user.getEmail() + " already exists! Duplicate users are not supported.");
        }

        // WRONG APPROACH BELOW: NEED TO USE CONSTRUCTOR INJECTION!!!
        /*InMemoryUserRepository userRepo = new InMemoryUserRepository();
        userRepo.saveUser(user);

        NotificationService notifService = new NotificationService();
        notifService.sendNotification("user " + user.getName() + " has been successfully register!", user.getEmail());
        */
    }
}
