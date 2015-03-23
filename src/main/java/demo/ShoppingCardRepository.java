package demo;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by jensw on 23.03.2015.
 */
public interface ShoppingCardRepository extends MongoRepository<ShoppingCardPojo, String> {
}
