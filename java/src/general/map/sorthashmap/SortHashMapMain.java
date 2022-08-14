package general.map.sorthashmap;

import java.util.*;

public class SortHashMapMain {

    public static void main(String[] args) {

        Map<String, Integer> map = new HashMap<>();
        map.put("a", 3);
        map.put("b", 2);
        map.put("c", 1);

        List list = sortMap(map);

        System.out.println(list.toString());

    }


    private static List sortMap(Map map){
        List<Map.Entry<String, Integer>> list = new LinkedList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        return list;
    }

    private static List customSortMap(Map map){
        List<Map.Entry<String, Integer>> list = new LinkedList<>(map.entrySet());
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        return list;
    }

    private static List cleanCustomEntryMethod(Map map){
        List<Map.Entry<String, Integer>> list = new LinkedList<>(map.entrySet());
        list.sort(
                (o1, o2) -> o1.getValue() - o2.getValue()
        );
        return list;
    }

}
