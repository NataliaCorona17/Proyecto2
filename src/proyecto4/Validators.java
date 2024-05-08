
package proyecto4;


public class Validators {

    static StringValidator usernameValidator = input -> input.matches("[a-zA-Z0-9]+");
    static StringValidator passwordValidator = input -> input.length() >= 4; 

    static StringValidator isbnValidator = input -> {
        try {
            long isbn = Long.parseLong(input);
            return isbn > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    };
}

