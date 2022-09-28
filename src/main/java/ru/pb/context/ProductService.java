package ru.pb.context;

import org.springframework.stereotype.Component;

@Component
public class ProductService {

    //@Autowired - не лучший способ
    private ProductRepository repository;


    //Вместо @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public String getTitleById(long id) {
        return repository.findById(id).getTitle();
    }

//    @Autowired
//    public void setRepository(ProductRepository inMemoryRepository) {
//        this.inMemoryRepository = inMemoryRepository;
//    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Доступные товары:\n");
        Product p;
        for (int i = 0; i < repository.getProductCount(); i++) {
            p = repository.getProductFromList(i);
            sb.append(p.getId()).append(" ").append(p.getTitle()).append(", ");
        }
        sb.append("\n");


        return sb.toString();
    }
}
