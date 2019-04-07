import java.io.FileReader;
import java.io.FileWriter;
//import  java.io.BufferedWriter;
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
            }
            ipt.close();
        }
        catch (IOException e){
            System.out.println(e);
        }

        Map<Integer, Vector<Vector>> weight = new TreeMap<Integer, Vector<Vector>>();
        for (Character key : pre_weight.keySet()) {
            int i = pre_weight.get(key);
            if(!weight.containsKey(i)) {
                Vector<Character> tmp1 = new Vector<Character>();
                tmp1.add(key);
                Vector<Vector> tmp2 = new Vector<Vector>();
                tmp2.add(tmp1);
                weight.put(i,tmp2);
            }
            else {
                Vector<Vector> tmp2 = weight.get(i);
                Vector<Character> tmp1 = new Vector<Character>();
                tmp1.add(key);
                tmp2.add(tmp1);
                weight.put(i,tmp2);
            }
        }

        //System.out.println(weight);

        boolean hasSingle = false;
        Vector bridge_vector = null;
        Vector node_1 = null;
        Vector node_2 = null;
        Vector<Character> new_node = null;
        int new_weight = 0;
        int bridge_weight = 0;
        Map<Character,Stack<Integer>> table = new HashMap<Character,Stack<Integer>>();

        while (true) {
            Iterator entries = weight.entrySet().iterator();
            if (!entries.hasNext()) {break;}
            Map.Entry entry = (Map.Entry) entries.next();
            int Wgt = (Integer) entry.getKey();
            //System.out.println(Wgt);
            Vector<Vector> current_line = weight.get(Wgt);

            if(!hasSingle) {
                //System.out.println(current_line.size());
                //System.out.println(current_line.size() % 2);
                if(current_line.size() % 2 == 0) {
                    //System.out.println("Case 1");
                    //System.out.println(current_line.size());
                    //System.out.println(current_line);
                    for (int i = 0;i < current_line.size();++i) {
                        node_1 = current_line.get(i);
                        node_2 = current_line.get(i+1);

                        for (int j = 0;j < node_1.size();++j) {
                            char key = (Character) node_1.get(j);
                            if (!table.containsKey(key)) {
                                Stack<Integer> pattern = new Stack<Integer>();
                                pattern.push(0);
                                table.put(key,pattern);
                            }
                            else {
                                Stack<Integer> previous_pattern = table.get(key);
                                previous_pattern.push(0);
                                table.put(key,previous_pattern);
                            }
                        }

                        for (int j = 0;j < node_2.size();++j) {
                            char key = (Character) node_2.get(j);
                            if (!table.containsKey(key)) {
                                Stack<Integer> pattern = new Stack<Integer>();
                                pattern.push(1);
                                table.put(key,pattern);
                            }
                            else {
                                Stack<Integer> previous_pattern = table.get(key);
                                previous_pattern.push(1);
                                table.put(key,previous_pattern);
                            }
                        }

                        new_node = new Vector<Character>();
                        for (int j = 0;j < node_1.size();++j) {
                            new_node.add((Character) node_1.get(j));
                        }
                        for (int j = 0;j < node_2.size();++j) {
                            new_node.add((Character) node_2.get(j));
                        }
                        //System.out.println(new_node);

                        new_weight = Wgt * 2;
                        if (!weight.containsKey(new_weight)) {
                            Vector<Vector> new_line = new Vector<Vector>();
                            new_line.add(new_node);
                            weight.put(new_weight,new_line);
                        }
                        else {
                            Vector<Vector> new_line = weight.get(new_weight);
                            new_line.add(new_node);
                            weight.put(new_weight,new_line);
                        }

                        ++i;
                    }
                    weight.remove(Wgt);
                    //System.out.println(weight);
                    //System.out.println(table);
                }
                else { //
                    //System.out.println("Case 2");
                    hasSingle = true;

                    for (int i = 0;i < current_line.size() - 1;++i) {
                        node_1 = current_line.get(i);
                        node_2 = current_line.get(i+1);

                        for (int j = 0;j < node_1.size();++j) {
                            char key = (Character) node_1.get(j);
                            if (!table.containsKey(key)) {
                                Stack<Integer> pattern = new Stack<Integer>();
                                pattern.push(0);
                                table.put(key,pattern);
                            }
                            else {
                                Stack<Integer> previous_pattern = table.get(key);
                                previous_pattern.push(0);
                                table.put(key,previous_pattern);
                            }
                        }

                        for (int j = 0;j < node_2.size();++j) {
                            char key = (Character) node_2.get(j);
                            if (!table.containsKey(key)) {
                                Stack<Integer> pattern = new Stack<Integer>();
                                pattern.push(1);
                                table.put(key,pattern);
                            }
                            else {
                                Stack<Integer> previous_pattern = table.get(key);
                                previous_pattern.push(1);
                                table.put(key,previous_pattern);
                            }
                        }

                        new_node = new Vector<Character>();
                        for (int j = 0;j < node_1.size();++j) {
                            new_node.add((Character) node_1.get(j));
                        }
                        for (int j = 0;j < node_2.size();++j) {
                            new_node.add((Character) node_2.get(j));
                        }
                        //System.out.println(new_node);

                        new_weight = Wgt * 2;
                        if (!weight.containsKey(new_weight)) {
                            Vector<Vector> new_line = new Vector<Vector>();
                            new_line.add(new_node);
                            weight.put(new_weight,new_line);
                        }
                        else {
                            Vector<Vector> new_line = weight.get(new_weight);
                            new_line.add(new_node);
                            weight.put(new_weight,new_line);
                        }

                        ++i;
                    }

                    int position = current_line.size() - 1; //
                    if (position == 0) { //
                        //System.out.println("Only One Element");
                        if (!entries.hasNext()) { //
                            //System.out.println("The Last One");
                            break;
                        }
                    }

                    //
                    bridge_vector = current_line.get(position);
                    bridge_weight = Wgt;

                    for (int j = 0;j < bridge_vector.size();++j) {
                        char key = (Character) bridge_vector.get(j);
                        if (!table.containsKey(key)) {
                            Stack<Integer> pattern = new Stack<Integer>();
                            pattern.push(0);
                            table.put(key,pattern);
                        }
                        else {
                            Stack<Integer> previous_pattern = table.get(key);
                            previous_pattern.push(0);
                            table.put(key,previous_pattern);
                        }
                    }

                    weight.remove(Wgt);
                    //System.out.println(weight);
                    //System.out.println(table);

                }
            }
            else { //
                if (current_line.size() % 2 == 0) {//
                    //System.out.println("Case 3");
                    hasSingle = true;

                    //
                    node_1 = current_line.get(0);

                    //
                    for (int j = 0;j < node_1.size();++j) {
                        char key = (Character) node_1.get(j);
                        if (!table.containsKey(key)) {
                            Stack<Integer> pattern = new Stack<Integer>();
                            pattern.push(1);
                            table.put(key,pattern);
                        }
                        else {
                            Stack<Integer> previous_pattern = table.get(key);
                            previous_pattern.push(1);
                            table.put(key,previous_pattern);
                        }
                    }

                    //
                    new_node = new Vector<Character>();
                    for (int j = 0;j < node_1.size();++j) {
                        new_node.add((Character) node_1.get(j));
                    }
                    for (int j = 0;j < bridge_vector.size();++j) {
                        new_node.add((Character) bridge_vector.get(j));
                    }

                    new_weight = Wgt + bridge_weight;
                    if (!weight.containsKey(new_weight)) {
                        Vector<Vector> new_line = new Vector<Vector>();
                        new_line.add(new_node);
                        weight.put(new_weight,new_line);
                    }
                    else {
                        Vector<Vector> new_line = weight.get(new_weight);
                        new_line.add(new_node);
                        weight.put(new_weight,new_line);
                    }

                    //
                    for (int i = 1;i < current_line.size() - 1;++i) {
                        node_1 = current_line.get(i);
                        node_2 = current_line.get(i+1);

                        for (int j = 0;j < node_1.size();++j) {
                            char key = (Character) node_1.get(j);
                            if (!table.containsKey(key)) {
                                Stack<Integer> pattern = new Stack<Integer>();
                                pattern.push(0);
                                table.put(key,pattern);
                            }
                            else {
                                Stack<Integer> previous_pattern = table.get(key);
                                previous_pattern.push(0);
                                table.put(key,previous_pattern);
                            }
                        }

                        for (int j = 0;j < node_2.size();++j) {
                            char key = (Character) node_2.get(j);
                            if (!table.containsKey(key)) {
                                Stack<Integer> pattern = new Stack<Integer>();
                                pattern.push(1);
                                table.put(key,pattern);
                            }
                            else {
                                Stack<Integer> previous_pattern = table.get(key);
                                previous_pattern.push(1);
                                table.put(key,previous_pattern);
                            }
                        }

                        new_node = new Vector<Character>();
                        for (int j = 0;j < node_1.size();++j) {
                            new_node.add((Character) node_1.get(j));
                        }
                        for (int j = 0;j < node_2.size();++j) {
                            new_node.add((Character) node_2.get(j));
                        }
                        //System.out.println(new_node);

                        new_weight = Wgt * 2;
                        if (!weight.containsKey(new_weight)) {
                            Vector<Vector> new_line = new Vector<Vector>();
                            new_line.add(new_node);
                            weight.put(new_weight,new_line);
                        }
                        else {
                            Vector<Vector> new_line = weight.get(new_weight);
                            new_line.add(new_node);
                            weight.put(new_weight,new_line);
                        }

                        ++i;
                    }

                    //
                    int position = current_line.size() - 1; //
                    bridge_vector = current_line.get(position);
                    bridge_weight = Wgt;

                    for (int j = 0;j < bridge_vector.size();++j) {
                        char key = (Character) bridge_vector.get(j);
                        if (!table.containsKey(key)) {
                            Stack<Integer> pattern = new Stack<Integer>();
                            pattern.push(0);
                            table.put(key,pattern);
                        }
                        else {
                            Stack<Integer> previous_pattern = table.get(key);
                            previous_pattern.push(0);
                            table.put(key,previous_pattern);
                        }
                    }

                    //System.out.println(table);
                    //System.out.println(weight);
                    weight.remove(Wgt);
                }
                else {//
                    //System.out.println("Case 4");
                    hasSingle = false;

                    //
                    node_1 = current_line.get(0);

                    //
                    for (int j = 0;j < node_1.size();++j) {
                        char key = (Character) node_1.get(j);
                        if (!table.containsKey(key)) {
                            Stack<Integer> pattern = new Stack<Integer>();
                            pattern.push(1);
                            table.put(key,pattern);
                        }
                        else {
                            Stack<Integer> previous_pattern = table.get(key);
                            previous_pattern.push(1);
                            table.put(key,previous_pattern);
                        }
                    }

                    //
                    new_node = new Vector<Character>();
                    for (int j = 0;j < node_1.size();++j) {
                        new_node.add((Character) node_1.get(j));
                    }
                    for (int j = 0;j < bridge_vector.size();++j) {
                        new_node.add((Character) bridge_vector.get(j));
                    }

                    new_weight = Wgt + bridge_weight;
                    if (!weight.containsKey(new_weight)) {
                        Vector<Vector> new_line = new Vector<Vector>();
                        new_line.add(new_node);
                        weight.put(new_weight,new_line);
                    }
                    else {
                        Vector<Vector> new_line = weight.get(new_weight);
                        new_line.add(new_node);
                        weight.put(new_weight,new_line);
                    }

                    for (int i = 1;i < current_line.size();++i) {
                        node_1 = current_line.get(i);
                        node_2 = current_line.get(i+1);

                        for (int j = 0;j < node_1.size();++j) {
                            char key = (Character) node_1.get(j);
                            if (!table.containsKey(key)) {
                                Stack<Integer> pattern = new Stack<Integer>();
                                pattern.push(0);
                                table.put(key,pattern);
                            }
                            else {
                                Stack<Integer> previous_pattern = table.get(key);
                                previous_pattern.push(0);
                                table.put(key,previous_pattern);
                            }
                        }

                        for (int j = 0;j < node_2.size();++j) {
                            char key = (Character) node_2.get(j);
                            if (!table.containsKey(key)) {
                                Stack<Integer> pattern = new Stack<Integer>();
                                pattern.push(1);
                                table.put(key,pattern);
                            }
                            else {
                                Stack<Integer> previous_pattern = table.get(key);
                                previous_pattern.push(1);
                                table.put(key,previous_pattern);
                            }
                        }

                        new_node = new Vector<Character>();
                        for (int j = 0;j < node_1.size();++j) {
                            new_node.add((Character) node_1.get(j));
                        }
                        for (int j = 0;j < node_2.size();++j) {
                            new_node.add((Character) node_2.get(j));
                        }
                        //System.out.println(new_node);

                        new_weight = Wgt * 2;
                        if (!weight.containsKey(new_weight)) {
                            Vector<Vector> new_line = new Vector<Vector>();
                            new_line.add(new_node);
                            weight.put(new_weight,new_line);
                        }
                        else {
                            Vector<Vector> new_line = weight.get(new_weight);
                            new_line.add(new_node);
                            weight.put(new_weight,new_line);
                        }

                        ++i;
                    }

                    //System.out.println(table);
                    //System.out.println(weight);
                    weight.remove(Wgt);
                }
            }
        }
        //System.out.println(table);
        //System.out.println(weight);

        Map<Character,Vector<Integer>> Encodingtable = new HashMap<Character, Vector<Integer>>();
        for (Character key : table.keySet()) {
            Vector<Integer> code = new Vector<Integer>();
            while (!table.get(key).empty()) {
                code.add(table.get(key).peek());
                table.get(key).pop();
            }
            Encodingtable.put(key,code);
        }

        //System.out.println(Encodingtable);

        Vector<Character> output = new Vector<Character>();
        int cnt = 0;
        try {
            ipt = new FileReader(InputFileName);
            char[] buf = new char[1];
            while(ipt.read(buf) != -1) {
                char c = buf[0];
                cnt++;
                for (int i = 0;i < Encodingtable.get(c).size();++i) {
                    if (Encodingtable.get(c).get(i) == 1) {
                        output.add('1');
                    }
                    else {
                        output.add('0');
                    }
                }
            }
            ipt.close();
        }
        catch (IOException e){
            System.out.println(e);
        }

        try {
            FileWriter fw = new FileWriter(OutputFileName);
            fw.write(String.valueOf(cnt));
            fw.write(' ');
            fw.write(String.valueOf(Encodingtable.size()));
            fw.write(' ');
            for (Character key : Encodingtable.keySet()) {
                fw.write(key);
                fw.write(' ');
                for (int i = 0;i < Encodingtable.get(key).size();++i) {
                    fw.write(String.valueOf(Encodingtable.get(key).get(i)));
                }
                fw.write(' ');
            }
            for (int i = 0;i < output.size();++i)
            {
                fw.write(output.get(i));
            }
            fw.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}
