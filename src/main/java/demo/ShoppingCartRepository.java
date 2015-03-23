package demo;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by jensw on 23.03.2015.
 */
public interface ShoppingCartRepository extends MongoRepository<ShoppingCartPojo, String> {
}
