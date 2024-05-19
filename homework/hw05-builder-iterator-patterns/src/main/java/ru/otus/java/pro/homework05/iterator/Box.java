package ru.otus.java.pro.homework05.iterator;

import lombok.Getter;

import java.util.*;
import java.util.stream.Stream;

@Getter
public class Box implements Iterable<String> {
    public final List<String> firstList;
    public final List<String> secondList;
    public final List<String> thirdList;
    public final List<String> forthList;

    public Box() {
        this.firstList = new ArrayList<>();
        this.secondList = new ArrayList<>();
        this.thirdList = new ArrayList<>();
        this.forthList = new ArrayList<>();
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<>() {
            private final List<String> items = Stream.of(firstList, secondList, thirdList, forthList)
                    .flatMap(Collection::stream)
                    .toList();
            int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < items.size();
            }

            @Override
            public String next() {
                if (hasNext()) {
                    return items.get(currentIndex++);
                } else {
                    throw new NoSuchElementException();
                }

            }
        };
    }
}
