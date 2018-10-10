import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class DataInserter {

    public void readFromFile(HashMap aHashMap,String filePath)  {

        String line;
        try{
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        while ((line = reader.readLine()) != null)
        {
            String[] parts = line.split(":", 2);
            if (parts.length >= 2)
            {
                String key = parts[0];
                String value = parts[1];
                aHashMap.put(key, value);

            }
            else {
                System.out.println("ignoring line: " + line);
            }
        }}
        catch (IOException e)
        {
            System.out.println("File with numbers does not found");
            System.exit(0);
        }


    }
    public  void printMap(HashMap mp) {
        Iterator it = mp.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
    }

}
