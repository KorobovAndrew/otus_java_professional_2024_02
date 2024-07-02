package ru.otus.java.pro.homework10;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.java.pro.homework10.entity.Cart;
import ru.otus.java.pro.homework10.entity.Product;
import ru.otus.java.pro.homework10.repository.ProductRepository;

import java.util.NoSuchElementException;
import java.util.Scanner;

@ComponentScan
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        ProductRepository productRepository = context.getBean(ProductRepository.class);
        Cart cart = context.getBean(Cart.class);

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Доступные комманды: new cart; add, delete; exit");

            boolean isAppRun = true;
            while (isAppRun) {
                System.out.print("Введите команду: ");
                String command = scanner.nextLine();
                switch (command) {
                    case ("new cart"):
                        cart = context.getBean(Cart.class);
                        break;
                    case ("add"):
                        System.out.print("Введите id: ");
                        try {
                            Product addedProduct = cart.addProductById(scanner.nextLong());
                            scanner.nextLine();
                            System.out.printf("Продукт %s успешно добавлен в корзину\n", addedProduct.getTitle());
                        } catch (NoSuchElementException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case ("delete"):
                        System.out.print("Введите id: ");
                        try {
                            Product deletedProduct = cart.deleteProductById(scanner.nextLong());
                            scanner.nextLine();
                            System.out.printf("Продукт %s успешно удален из корзины\n", deletedProduct.getTitle());
                        } catch (NoSuchElementException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case ("exit"):
                        isAppRun = false;
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
