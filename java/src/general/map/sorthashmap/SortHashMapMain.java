package general.map.sorthashmap;

import java.util.*;

public class SortHashMapMain {

    public static void main(String[] args) {

        Map<String, Integer> map = new HashMap<>();
        map.put("a", 3);
        map.put("b", 2);
        map.put("c", 1);
        map.put("d", 5);
        map.put("e", 6);
        map.put("f", 9);

        List list = sortMap(map);
        System.out.println(list.toString());

        List list1 = customSortMapUsingAnonymousObj(map);
        System.out.println(list1);

        List list2 = customSortMapUsingLambda(map);
        System.out.println(list2);

    }


    private static List sortMap(Map map){
        List<Map.Entry<String, Integer>> list = new LinkedList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        return list;
    }

    private static List customSortMapUsingAnonymousObj(Map map){
        List<Map.Entry<String, Integer>> list = new LinkedList<>(map.entrySet());
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        return list;
    }

    private static List customSortMapUsingLambda(Map map){
        List<Map.Entry<String, Integer>> list = new LinkedList<>(map.entrySet());
        list.sort(
                (o1, o2) -> o2.getValue() - o1.getValue()
        );
        return list;
    }

}
