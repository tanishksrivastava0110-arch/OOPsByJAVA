import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Mapdemo {
    public static void main(String[] args) {
        HashMap<Integer,Integer> hp=new  LinkedHashMap<>();
        hp.put(10,100);
        hp.put(12,98);
        hp.put(10,45);
        for (Map.Entry<Integer,Integer> i: hp.entrySet()){
            System.out.println(i.getKey() + " " + i.getValue());

        }

        hp.remove(12);
        if(hp.containsKey(10)){
            System.out.println("Marks of Roll no. " + hp.get(10));
        }
        for(Map.Entry<Integer,Integer>i: hp.entrySet()){
            System.out.println(i.getKey()+ " "+ i.getValue());
        }
    }
}
