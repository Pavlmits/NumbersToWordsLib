import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) throws IOException {

        NumToWords n = new NumToWords();
        BigDecimal a ;
        String num;

        System.out.println("Give a number: ");
        Scanner scanIn = new Scanner(System.in);
        num = scanIn.nextLine();
        a = new BigDecimal(num);
        n.charArrayToWord(a);
        //n.numToWord(a);
    }
}
