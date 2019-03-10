import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Compression {

    public void compress(String InputFileName, String OutputFileName) {
        FileReader ipt = null;
        Map<Character,Integer> pre_weight = null;
        try {
            ipt = new FileReader(InputFileName);
            pre_weight = new HashMap<Character,Integer>();
            char[] buf = new char[1];
            while(ipt.read(buf) != -1) {
                char c = buf[0];
                if(!pre_weight.containsKey(c)) {
                    pre_weight.put(c,1);
                }
                else {
                    pre_weight.put(c,pre_weight.get(c) + 1);
                }
            };
        }
        catch (IOException e){
            System.out.println(e);
        }
        //System.out.println(pre_weight);

        Map<Integer,Vector> weight = new TreeMap<Integer, Vector>();
        for (Character key : pre_weight.keySet()) {
            int i = pre_weight.get(key);
            if(!weight.containsKey(i)) {
                Vector temp = new Vector();
                temp.add(key);
                weight.put(i,temp);
            }
            else {
                Vector temp = weight.get(i);
                temp.add(key);
                weight.put(i,temp);
            }
        }
        System.out.println(weight);
    }

}
