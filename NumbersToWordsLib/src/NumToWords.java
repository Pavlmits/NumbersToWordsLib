import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.HashMap;


public class NumToWords {

    HashMap<String,String> words = new HashMap<String,String>();
    HashMap<String,String> symbols = new HashMap<>();

    public String numToWord(String src)  {

        loadFiles();
        String word;
        String[] seperateNum;

        seperateNum = src.split("[, /.]");
        word = symbolSemerator(seperateNum[0]);
        if(seperateNum.length != 1) {
            word += " και ";
            word += symbolSemerator(seperateNum[1]);
        }

        return word;
    }
    public String symbolSemerator(String src)
    {
        BigDecimal a;
        StringBuilder digits = new StringBuilder();
        StringBuilder symbol = new StringBuilder();
        for (int i = 0; i < src.length(); i++) {

            char c = src.charAt(i);

            if(Character.isDigit(c))
                digits.append(c);
            else if(symbols.containsKey(c))
            {
                symbol.append(symbols.get(c));
            }
            else
            {
                return "Invalid characters";
            }
        }
        a = new BigDecimal(digits.toString());
        return charArrayToWord(a) + symbol.toString();

    }

    public char[] intToCharArray(BigInteger x)
    {
        char[] chars = ("" + x).toCharArray();
        return chars;
    }

    public String charArrayToWord(BigDecimal x)  {


        char[] chars;
        String word = new String();
        StringBuilder number = new StringBuilder();

        chars = intToCharArray(x.toBigInteger());

        for(int i=0; i<chars.length; i++)
        {
            if(chars.length-i == 2)
            {
                number.append(chars[i]);
                i++;
                number.append(chars[i]);

                if(words.containsKey(number.toString()))
                     word += words.get(number.toString()) + " ";
                else
                {
                    i--;
                    i += isZero(chars[i],chars.length);
                    word += words.get(Integer.toString(calcNum(chars,i)))  + " ";
                }
            }
            else {
                i += isZero(chars[i],chars.length);
                word += words.get(Integer.toString(calcNum(chars,i))) + " ";

            }

        }
        return word;
    }

    public int calcNum(char[] chars, int i)
    {
        double multNum;
        int res;
        multNum = Math.pow(10, chars.length - i - 1);
        res = (int) (multNum * Character.getNumericValue(chars[i]));
        return  res;
    }

    public void loadFiles()
    {
        DataInserter fl = new DataInserter();
        String filePath = "numbersWords.txt";
        fl.readFromFile(words,filePath);
        String fileSymbols = "symbols.txt";
        fl.readFromFile(symbols,fileSymbols);
    }
    public int isZero(char c, int length)
    {
        if(c =='0' && length != 1)
            return 1;
        else
            return 0;
    }

}
