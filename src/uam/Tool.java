package uam;

import java.util.Scanner;

public class Tool {
    
    public static Integer convertStringToInt(Scanner input) {
        boolean continueLoop = true;
        String optionAsString;
        Integer optionAsInt = 0;
        do {
            optionAsString = input.nextLine();
            try {
                optionAsInt = Integer.parseInt(optionAsString);
                continueLoop = false;
            } catch (Exception e) {
                System.out.println("Você não digitou um número! Digite novamente.");
                continueLoop = true;
            }
        } while (continueLoop);
        return optionAsInt;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static Double convertStringToDouble(Scanner input) {
        boolean continueLoop = true;
        String optionAsString;
        Double optionAsDouble = 0.0;
        do {
            optionAsString = input.nextLine();
            try {
                optionAsDouble = Double.parseDouble(optionAsString);
                continueLoop = false;
            } catch (Exception e) {
                System.out.println("Você não digitou um número! Digite novamente.");
                continueLoop = true;
            }
        } while (continueLoop);
        return optionAsDouble;
    }
}

