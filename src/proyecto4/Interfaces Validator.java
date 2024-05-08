
package proyecto4;

@FunctionalInterface
interface StringValidator {
    boolean validate(String input);
}

@FunctionalInterface
interface IntegerValidator {
    boolean validate(int input);
}


