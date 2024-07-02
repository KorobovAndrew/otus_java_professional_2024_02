package ru.otus.java.pro.homework10.handler;

import org.springframework.stereotype.Component;
import ru.otus.java.pro.homework10.entity.Cart;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Component
public class ConsoleCommandHandler {

    Map<String, Consumer<Long>> commands;

    public ConsoleCommandHandler(Cart cart) {
        commands = new HashMap<>();
        commands.put("cart/add?id=", id -> System.out.println(cart.addProductById(id)));
        commands.put("cart/delete?id=", id -> System.out.println(cart.deleteProductById(id)));
    }
}
