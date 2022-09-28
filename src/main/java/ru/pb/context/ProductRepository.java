package ru.pb.context;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ProductRepository {

    private List<Product> products;

    @PostConstruct
    public void init(){
        products = new ArrayList<>(Arrays.asList(
                new Product(1l, "Bread", 50),
                new Product(2l, "Milk", 80),
                new Product(3l, "Orange", 100),
                new Product(4l, "Potato", 15),
                new Product(5l, "Water", 10)
        ));
    }

    public Product findById(long id){
        return products.stream().filter(p->p.getId()==id).findFirst().orElseThrow();
    }

    public int getProductCount(){
        return products.size();
    }
    public Product getProductFromList(int listId){
        return products.get(listId);
    }


}
