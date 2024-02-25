package task1Package;

import formatPrinterPacjage.FormatPrinter;

public class Calculator {


    public static double sum(double i, double i1) {
        double result =i + i1;
        FormatPrinter.print(result);
        return  result;
    }

    public static double sub(double i, double i1) {

        double result=  i - i1;
        FormatPrinter.print(result);

        return  result;

    }

    public static double devide(double i, double i1) {
        double result = i/i1;
        FormatPrinter.print(result);

        return result;
    }

    public static double multiplication(double i, double i1) {
        double result = i*i1;
        FormatPrinter.print(result);

        return  result;
    }
}
