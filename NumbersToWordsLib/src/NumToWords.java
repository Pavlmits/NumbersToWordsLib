import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.HashMap;


public class NumToWords {

    HashMap<Integer,String> words = new HashMap<Integer,String>();

    public String numToWord(String src) throws IOException {

        DataInserter fl = new DataInserter();
        fl.readFromFile(words);
        StringBuilder digits = new StringBuilder();
        BigDecimal a;

        for (int i = 0; i < src.length(); i++) {

            char c = src.charAt(i);
            if(Character.isDigit(c))
            {
                digits.append(c);
            }
        }
        a = new BigDecimal(digits.toString());
        return charArrayToWord(a);
    }

    public char[] intToCharArray(BigInteger x)
    {
        char[] chars = ("" + x).toCharArray();
        return chars;
    }

    public String charArrayToWord(BigDecimal x) throws IOException {


        char[] chars;
        int number;
        String word = new String();
        StringBuilder num = new StringBuilder();

        chars = intToCharArray(x.toBigInteger());

        for(int i=0; i<chars.length; i++)
        {
            if(chars.length-i == 2)
            {
                num.append(chars[i]);
                i++;
                num.append(chars[i]);
                number = Integer.parseInt(num.toString());

                if(words.get(number)!= null)
                     word += words.get(number) + " ";
                else
                {
                    i--;
                    word += words.get(calcNum(chars,i)) + " ";
                }
            }
            else {

                word += words.get(calcNum(chars,i)) + " ";

            }

        }
        System.out.print(word + " ");
        return word;
    }

    public int calcNum(char[] chars, int i)
    {
        double multNum;
        multNum = Math.pow(10, chars.length - i - 1);
        return (int) (multNum * Character.getNumericValue(chars[i]));
    }


}
