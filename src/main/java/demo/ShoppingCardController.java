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
@RequestMapping(value = "/cards")
@Api(value = "/cards", description = "Simple crud functionalities")
public class ShoppingCardController {

    @Resource
    private ShoppingCardRepository shoppingCardRepository;

    @ApiOperation(value = "get all cards", response = ShoppingCardPojo[].class)
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<ShoppingCardPojo>> getAllCards() {
        Iterable<ShoppingCardPojo> allCards = shoppingCardRepository.findAll();
        return new ResponseEntity<>(allCards, HttpStatus.OK);
    }

    @ApiOperation(value = "save one or more shopping cards", response = ShoppingCardPojo[].class)
    @RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<ShoppingCardPojo>> saveCards(@RequestBody List<ShoppingCardPojo> cardsToSave) {
        Iterable<ShoppingCardPojo> savedCards = shoppingCardRepository.save(cardsToSave);
        return new ResponseEntity<>(savedCards, HttpStatus.OK);
    }

    @ApiOperation(value = "returns one single card", response = ShoppingCardPojo.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShoppingCardPojo> getSingleCardItem(@PathVariable("id") String id) {
        ShoppingCardPojo card = shoppingCardRepository.findOne(id);
        if(card != null) {
            return new ResponseEntity<>(card, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "deletes a single card")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteSingleCard(@PathVariable("id") String id) {
        ShoppingCardPojo cardToDelete = shoppingCardRepository.findOne(id);
        if(cardToDelete != null) {
            shoppingCardRepository.delete(cardToDelete);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
