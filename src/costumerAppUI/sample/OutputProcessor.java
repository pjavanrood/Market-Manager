package costumerAppUI.sample;

import model.Costumer;

public class OutputProcessor {
    public static AlertBox alertBox = new AlertBox();

    public static void unregisteredUsername(){
        alertBox.display("User not registered!");
    }

    public static void wrongPassword(){
        alertBox.display("Wrong Password!");
    }

    public static void existingUser(){
        alertBox.display("Username already exists!");
    }

    public static void notMatchingPasswords(){alertBox.display("Passwords not matching!");}

    public static void successfulRegister(){alertBox.display("Register Successful!\nPlease Login");}

    public static void successfulLogin(){alertBox.display("Login Successful!");}

    public static void showBalance(){alertBox.display("Your Balance is:\n" + Costumer.getBalance());}

    public static void invalidInput(){alertBox.display("Invalid input!");}

    public static void notEnoughGood(){alertBox.display("Not Enough Good in storage!");}

    public static void buySuccessful(String orderId){alertBox.display("Order Done!\n" + "Order iD: " + orderId);}

    public static void notEnoughBalance(){alertBox.display("Not Enough Balance!");}

    public static void noSelection(){alertBox.display("No Selection Made!");}

    public static void existingGood(){alertBox.display("Good Already Exists!");}

    public static void successfulAdd(){alertBox.display("Good added Successfully!");}

    public static void showMessage(String message){alertBox.display(message);}

}
