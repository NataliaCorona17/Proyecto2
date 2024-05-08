package proyecto4;

import java.util.Scanner;

public class ConsoleReader {

    private final Scanner scanner;

    public ConsoleReader() {
        this.scanner = new Scanner(System.in);
    }

    public String readString(String prompt, StringValidator validator) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine();
            if (!validator.validate(input)) {
                System.out.println("Error: La entrada no es válida. Por favor, inténtelo de nuevo.");
            }
        } while (!validator.validate(input));
        return input;
    }

    
    public int readInt(String prompt, IntegerValidator validator) {
        int input = 0;
        do {
            System.out.print(prompt);
            try {
                input = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: La entrada no es un número válido. Por favor, inténtelo de nuevo.");
                continue;
            }
            if (!validator.validate(input)) {
                System.out.println("Error: La entrada no cumple con los requisitos. Por favor, inténtelo de nuevo.");
            }
        } while (!validator.validate(input));
        return input;
    }

    
    
  public String readPassword(String prompt, StringValidator validator) {
        String password;
        do {
            System.out.print(prompt);
            password = scanner.nextLine();
            if (!validator.validate(password)) {
                System.out.println("Error: La contraseña no cumple con los requisitos. Por favor, inténtelo de nuevo.");
            }
        } while (!validator.validate(password));
        return password;
    }
}




