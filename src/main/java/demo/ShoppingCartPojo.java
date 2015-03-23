package demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by jensw on 23.03.2015.
 */
@Document
public class ShoppingCartPojo {
    @Id
    public String id;
    public String name;
    public List<ShoppingCartItem> items;
}
