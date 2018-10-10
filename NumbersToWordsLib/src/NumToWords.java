import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.HashMap;


public class NumToWords {

    HashMap<String,String> words = new HashMap<String,String>();
    HashMap<String,String> symbols = new HashMap<>();

    public String numToWord(String src) throws IOException {

        loadFiles();
        StringBuilder digits = new StringBuilder();
        StringBuilder symbol = new StringBuilder();
        BigDecimal a;

        for (int i = 0; i < src.length(); i++) {

            char c = src.charAt(i);

            if(Character.isDigit(c))
                digits.append(c);
            else if(symbols.get(c) != null)
            {
                symbol.append(symbols.get(c));
            }
            else
            {
                return "Invalid characters";
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
                     word += words.get(num) + " ";
                else
                {
                    i--;
                    word += words.get(Integer.toString(calcNum(chars,i)))  + " ";
                }
            }
            else {
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

}
