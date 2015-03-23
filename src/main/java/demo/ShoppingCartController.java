package demo;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by jensw on 23.03.2015.
 */
@RestController
@RequestMapping(value = "/carts")
@Api(value = "/carts", description = "Simple crud functionalities")
public class ShoppingCartController {

    @Resource
    private ShoppingCartRepository shoppingCartRepository;

    @ApiOperation(value = "get all carts", response = ShoppingCartPojo[].class)
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<ShoppingCartPojo>> getAllCarts() {
        Iterable<ShoppingCartPojo> allCarts = shoppingCartRepository.findAll();
        return new ResponseEntity<>(allCarts, HttpStatus.OK);
    }

    @ApiOperation(value = "save one or more shopping carts", response = ShoppingCartPojo[].class)
    @RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<ShoppingCartPojo>> saveCarts(@RequestBody List<ShoppingCartPojo> cartsToSave) {
        Iterable<ShoppingCartPojo> savedCarts = shoppingCartRepository.save(cartsToSave);
        return new ResponseEntity<>(savedCarts, HttpStatus.OK);
    }

    @ApiOperation(value = "returns one single cart", response = ShoppingCartPojo.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShoppingCartPojo> getSingleCartItem(@PathVariable("id") String id) {
        ShoppingCartPojo cart = shoppingCartRepository.findOne(id);
        if(cart != null) {
            return new ResponseEntity<>(cart, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "deletes a single cart")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteSingleCart(@PathVariable("id") String id) {
        ShoppingCartPojo cartToDelete = shoppingCartRepository.findOne(id);
        if(cartToDelete != null) {
            shoppingCartRepository.delete(cartToDelete);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
