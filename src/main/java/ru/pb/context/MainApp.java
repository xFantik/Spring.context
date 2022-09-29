package ru.pb.context;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ru.pb.context");

        ProductService productService = context.getBean(ProductService.class);
        Cart cart = context.getBean(Cart.class);

        System.out.println(productService);
        System.out.println(cart);

        Scanner sc = new Scanner(System.in);
        while (true) {
            String in = sc.nextLine();
            System.out.println("\n\n");
            String[] inputArr = in.split(" ");


            long id = 0;
            int count = 1;
            try {
                id = Long.parseLong(inputArr[1]);
            } catch (Exception e) {
                System.out.println("                        не удалось распознать команду: <+/-> <id> [<count>]\n");
            }
            if (inputArr.length > 2) {
                try {
                    count = Integer.parseInt(inputArr[2]);
                } catch (NumberFormatException e) {
                    System.out.println("                    не удалось распознать команду: <+/-> <id> [<count>]\n");
                }
            }
            if (inputArr[0].equals("+")) {
                cart.addProduct(id, count);
            } else if (inputArr[0].equals("-")) {
                cart.removeProduct(id, count);
            }
            System.out.println(productService);
            System.out.println(cart);
        }
    }
}
