import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.HashMap;


public class NumToWords {

    HashMap<Integer,String> words = new HashMap<Integer,String>();

    public String numToWord(BigDecimal x) throws IOException
    {


        int up = 0;
        int intVal = 10;
        BigDecimal div = new BigDecimal("0");
        BigDecimal one = new BigDecimal("1");

        String s = intNumToWord(x.toBigInteger());
        x = cutLastNumber(x);
        System.out.println(x);


        return "";
    }
    public String intNumToWord(BigInteger x)
    {
        int up;
        String word;
        BigInteger div = new BigInteger("100");

        while(x.divide(div).compareTo(new BigInteger("0")) != -1 )//less to 1
        {
            up = getlast2Numbers(x);
            word = words.get(up);
            if(word == null)
            {
                //up
            }
            System.out.println(word);
            div = div.multiply(new BigInteger("10"));
        }
        return "xa";
    }

    public int getlast2Numbers(BigInteger x)
    {
        return x.remainder(new BigInteger("100")).intValue();
    }
    public BigDecimal cutLastNumber(BigDecimal x)
    {
        return x.divide(new BigDecimal("10"),3, RoundingMode.CEILING);
    }

    public char[] intToCharArray(BigInteger x)
    {
        char[] chars = ("" + x).toCharArray();
        return chars;
    }

    public String charArrayToWord(BigDecimal x) throws IOException {

        DataInserter fl = new DataInserter();
        fl.readFromFile(words);
        char[] chars;
        double multNum;
        int mul ;
        String word = new String();

        chars = intToCharArray(x.toBigInteger());

        for(int i=0; i<chars.length; i++)
        {
            multNum = Math.pow(10,chars.length-i-1);
            mul = (int) (multNum*Character.getNumericValue(chars[i]));
            word = words.get(mul);
            System.out.print(word + " ");
        }
        return word;
    }


}
