package ru.pb.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;


@Component
//@Scope("singleton") // по-умолчанию
//@Scope("session") // ?
//@Scope("globalsession") // ?
@Scope("prototype")
public class Cart {
    private HashMap<Long, Integer> products;
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository repository;


    @PostConstruct
    public void init() {
        products = new HashMap<>();
    }


    public void addProduct(long productId, int count) {
        try {
            productService.getTitleById(productId);
            System.out.println("                           положили в корзину: "+ count+" "+ productService.getTitleById(productId));

            products.put(productId, products.getOrDefault(productId, 0) + count);
        } catch (NoSuchElementException e) {
            System.out.println("                           Товар с id = " + productId + " не найден");
        }

    }

    public void removeProduct(long productId, int count) {
        System.out.println("                                убрали из корзины: "+ count+" "+ productService.getTitleById(productId));
        products.put(productId, products.getOrDefault(productId, 0) - count);
        if (products.get(productId) <= 0) {
            products.remove(productId);
        }
    }

    @Override
    public String toString() {
        int cost = 0;
        StringBuilder sb = new StringBuilder("         Корзина:\n");
        Product p;
        for (Map.Entry<Long, Integer> entry : products.entrySet()) {
            p = repository.findById(entry.getKey());
            int price = p.getPrice() * entry.getValue();
            sb.append(p.getTitle()).append(" (").
                    append(entry.getValue()).append("шт)");
            sb.append(" ".repeat(Math.max(0, 20 - p.getTitle().length())));
            sb.append(":").append(price).append(" руб.\n");
            cost += price;
        }
        sb.append("--------------\nИтог: ").append(cost).append(" руб.");
        return sb.toString();
    }
}
