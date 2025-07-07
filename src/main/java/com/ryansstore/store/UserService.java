package com.ryansstore.store;

// import org.springframework.scheduling.annotation.Scheduled;
import java.util.Random;
import java.util.Scanner;

// No longer needed with AppConfig class approach
/*import org.springframework.stereotype.Service;

@Service*/
public class UserService {
    private final UserRepository userRepo;
    private final NotificationManager notifManager;

    public UserService(UserRepository userRepo, NotificationManager notifManager) {
        this.userRepo = userRepo;
        this.notifManager = notifManager;
    }

    public void registerUser(User user) {
        if(!userRepo.userExists(user)) {
            // optional: add user registration text message
            // generate random code
            Random random = new Random();
            int regCode = (random.nextInt(900000) + 100000);

            // send regCode to user over sms
            notifManager.sendNotification("sms", "Registration code: " + regCode, user.getPhoneNumber());

            // ask user to input the code they received through sms
            // TO-DO: handle non-numerical inputs better!
            Scanner scanner = new Scanner(System.in);
            int inputCode;
            for(int i = 0; i < 3; i++) {
                if(i > 0)
                    System.out.println("Oops! The code you provided didn't match the registration code!\nLet's try again! Attempt #" + (i+1) + "!");

                System.out.print("Enter your registration code: ");
                inputCode = scanner.nextInt();

                if(inputCode == regCode) { // Success: save user and send confirmation email
                    userRepo.saveUser(user);
                    notifManager.sendNotification("email", "user " + user.getName() + " has been successfully registered!", user.getEmail());
                    return;
                }
            }
            // Failure: unable to due to mismatching registration codes
            System.out.println("ERROR: input did not match registration code!");
        }
        else // Optional: handle duplicate user
            System.out.println("ERROR: user with email " + user.getEmail() + " already exists! Duplicate users are not supported.");

        // WRONG APPROACH BELOW: NEED TO USE CONSTRUCTOR INJECTION!!!
        /*InMemoryUserRepository userRepo = new InMemoryUserRepository();
        userRepo.saveUser(user);

        NotificationService notifService = new NotificationService();
        notifService.sendNotification("user " + user.getName() + " has been successfully register!", user.getEmail());
        */
    }

    // this was just for trying out the @SCHEDULED annotation
    /*@Scheduled(fixedRate = 5000)
    public void ScheduledTest() {
        System.out.println("what up it's the user");
    }*/
}
