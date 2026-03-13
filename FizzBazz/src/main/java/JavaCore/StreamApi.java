package JavaCore;

import java.util.*;
import java.util.stream.Collectors;

public class StreamApi {
    record Product(String name, String category, double price, int quantity) {}

    public static List<Product> products = List.of(
            new Product("Laptop",     "Electronics",  1200.0, 3),
            new Product("Phone",      "Electronics",   800.0, 5),
            new Product("Headphones", "Electronics",   150.0, 10),
            new Product("T-Shirt",    "Clothing",       25.0, 50),
            new Product("Jeans",      "Clothing",       60.0, 30),
            new Product("Jacket",     "Clothing",      120.0, 15),
            new Product("Blender",    "Appliances",     90.0, 8),
            new Product("Microwave",  "Appliances",    250.0, 4),
            new Product("Kettle",     "Appliances",     40.0, 20)
    );

    private static List<String> getProductNameExpensive100(List<Product> productList){
        return productList.stream()
                .filter(a -> a.price() > 100)
                .sorted(Comparator.comparingDouble(Product::price).reversed())
                .map(Product::name)
                .collect(Collectors.toList());
    }

    public static void findMostExpensive(List<Product> products){
        Map<String, Optional<Product>> results = products.stream()
                .collect(Collectors.groupingBy(
                        Product::category,
                        Collectors.maxBy(Comparator.comparingDouble(Product::price))
                ));

        results.forEach((category, product) ->
                System.out.println(category + " -> " + product.map(Product::name).orElse("none")));
    }

    public static List<String> summaryForCategory(List<Product> products) {
        Map<String, Double> resultsInMap = products.stream()
                .collect(Collectors.groupingBy(
                        Product::category,
                        Collectors.summingDouble(a -> a.price() * a.quantity())
                ));
        return resultsInMap.keySet().stream().map(a -> a + " = " + resultsInMap.get(a).toString())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println(getProductNameExpensive100(products));
        findMostExpensive(products);
        System.out.println(summaryForCategory(products));

    }
}
