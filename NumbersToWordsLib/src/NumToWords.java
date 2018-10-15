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
        word = symbolSeperator(seperateNum[0]);
        if(seperateNum.length != 1) {
            word += " και ";
            word += symbolSeperator(seperateNum[1]);
        }

        return word;
    }
    public String symbolSeperator(String src)
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

                if(words.containsKey(number.toString())) //2 digits
                     word += words.get(number.toString()) + " ";
                else
                {
                    i--;
                  //  i += isZeroSeq(Arrays.copyOfRange(chars, i, chars.length),chars.length);
                    word += words.get(Integer.toString(calcNum(chars,i)))  + " ";
                }
            }
            else {
               // i += isZeroSeq(Arrays.copyOfRange(chars, i, chars.length),chars.length);
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
    public int isZeroSeq(char[] c, int length)
    {
        int count = 0;
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '0' && length != 1)
                 count++;
            else
                return count;
        }
        return count;
    }



}
