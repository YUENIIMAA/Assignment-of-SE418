import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Decompression {
    public void decompress(String InputFileName, String OutputFileName) {
        FileReader ipt = null;
        try {
            ipt = new FileReader(InputFileName);
            char[] buf = new char[1];
            ipt.read(buf);
            int cnt = 0;
            while (Character.isDigit(buf[0]))
            {
                cnt = cnt * 10 + (int)buf[0] - (int)('0');
                ipt.read(buf);
            }

            ipt.read(buf);
            int numOFkey = 0;
            while (Character.isDigit(buf[0]))
            {
                numOFkey = numOFkey * 10 + (int)buf[0] - (int)('0');
                ipt.read(buf);
            }

            Map<String,Character> Decodingtable = new HashMap<String, Character>();
            for (int i = 0;i < numOFkey; ++i)
            {
                ipt.read(buf);
                char value = buf[0];
                String key = "";
                ipt.read(buf);
                ipt.read(buf);
                while (Character.isDigit(buf[0]))
                {
                    key += String.valueOf(buf[0]);
                    ipt.read(buf);
                }
                Decodingtable.put(key,value);
            }


            String key = "";
            FileWriter fw = new FileWriter(OutputFileName);
            while(ipt.read(buf) != -1) {
                key += String.valueOf(buf[0]);
                //System.out.println(key);
                if (Decodingtable.containsKey(key))
                {
                    fw.write(Decodingtable.get(key));
                    key = "";
                }
            }
            ipt.close();
            fw.close();
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
}
