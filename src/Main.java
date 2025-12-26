import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<String> listOfSocks = List.of("Black", "Orange", "Black", "White", "Yellow", "White");
        Map<String, Integer> mapOfSocks = new HashMap<>();

        listOfSocks.forEach(s -> mapOfSocks.put(s, mapOfSocks.getOrDefault(s, 0)+1));
        List<String> result = mapOfSocks.entrySet().stream().filter(s-> s.getValue() % 2 == 0).map(e->e.getKey()).toList();

        System.out.println(result);
    }
}