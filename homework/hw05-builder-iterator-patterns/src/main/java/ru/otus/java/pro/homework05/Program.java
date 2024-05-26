package ru.otus.java.pro.homework05;

import ru.otus.java.pro.homework05.builder.Product;
import ru.otus.java.pro.homework05.iterator.Box;

public class Program {
    public static void main(String[] args) {
        //Builder task test
        var product = Product.builder()
                .id(1L)
                .title("anyTitle")
                .description("anyDescription")
                .cost(5.5)
                .build();

        //Iterator task test
        var box = new Box();
        box.firstList.add("1-1");
        box.firstList.add("1-2");
        box.secondList.add("2-1");
        box.secondList.add("2-2");
        box.thirdList.add("3-1");
        box.thirdList.add("3-2");
        box.forthList.add("4-1");
        box.forthList.add("4-2");

        for(var item : box){
            System.out.println(item);
        }
    }
}
