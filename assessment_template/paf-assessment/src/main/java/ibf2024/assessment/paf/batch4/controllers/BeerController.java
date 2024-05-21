package ibf2024.assessment.paf.batch4.controllers;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//import org.bson.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ibf2024.assessment.paf.batch4.models.Beer;
import ibf2024.assessment.paf.batch4.models.Brewery;
import ibf2024.assessment.paf.batch4.models.Order.OrderItem;
import ibf2024.assessment.paf.batch4.models.Style;
import ibf2024.assessment.paf.batch4.repositories.BeerRepository;
import ibf2024.assessment.paf.batch4.services.BeerService;


@Controller
@RequestMapping

public class BeerController {
 
	  @Autowired
      private BeerService beerService;

	@Autowired
	private BeerRepository beerRepo;

	private static final Logger logger = LoggerFactory.getLogger(BeerController.class);

	//TODO Task 2 - view 0
 	@GetMapping(path = "/")
    public String stylesAll(Model model) {
      try{ 
		List<Style> bstyles = beerRepo.getStyles();
        model.addAttribute("styles", bstyles);
	  } catch (Exception e){ //get Logger
		System.out.println(e.getMessage());
	  }
        return "view0";
    }

	
	//TODO Task 3 - view 1
        
    @GetMapping("/beer/style/{id}")
    public String getBreweriesByBeer(@PathVariable int id,
                                    @RequestParam String styleName,
                                    Model model) {
        try {
            List<Beer> results = beerRepo.getBreweriesByBeer(id);   
            model.addAttribute("styleName", styleName);
            model.addAttribute("beerDetails", results);
            logger.info("BeerController.getBreweriesByBeer(): {}", results.toString());
            return "beerlist";
        } catch (DataAccessException e) {
            logger.error("Error retrieving breweries by beer: {}", e.getMessage());
            model.addAttribute("error", "Beers Not Found");
            return "error";
        } catch (Exception e) {
            logger.error("Unexpected error: {}", e.getMessage());
            model.addAttribute("error", "Unexpected Error");
            return "error";
        }
    }

 


	//TODO Task 4 - view 2
	
    @GetMapping("/beer/brewery/{id}")
    public String getBeersFromBrewery(@PathVariable int id,
                                        Model model) {
        try {
            Optional<Brewery> result = beerRepo.getBeersFromBrewery(id);   
            if (result.isEmpty()){
                model.addAttribute("error", "Brewery Not Found");
                return "error";
            }
            model.addAttribute("breweryDetails", result.get() );
            logger.info("{}", result.toString());
        } catch (DataAccessException e) {
            logger.error("Error retrieving brewery details {}", e.getMessage());
            model.addAttribute("error", "Error retrieving brewery details");
            return "error";
         } catch (Exception e) {
            logger.error("Unexpected error: {}", e.getMessage());
            model.addAttribute("error", "Unexpected Error");
            return "error";
        }
		return "breweryDetails";

	}

	
	//TODO Task 5 - view 2, place order
    @PostMapping ("/brewery/{breweryId}/order")
    public String postOrder(@PathVariable int breweryId, 
        @RequestBody MultiValueMap<String, String> form, Model model) {

        //populate a list of OrderItems (ordered beers) from the post form
         List<OrderItem> items = new LinkedList<OrderItem>();

        //populating order items only those with quantity >0
        for (Map.Entry<String, List<String>> beer : form.entrySet()) {
            int order_q = Integer.parseInt(beer.getValue().getFirst());
            if (order_q >0) { //populate each order item
               //extracting the numb before "-" as beerId and the order quantity
                String[] orderQStr = beer.getKey().split("_");
                OrderItem item = new OrderItem(Integer.parseInt(orderQStr[0]), order_q);
                items.add(item);
            }
        }

        String orderId = beerService.placeOrder(breweryId, items);
        if (orderId.isBlank()){
            model.addAttribute("error",
                 "Errors in inserting an order");
            return "error";
        }

        logger.info("After inserting an order: {}", orderId);
        
        model.addAttribute("result", "not done");
        model.addAttribute("orderId", orderId);
        return "order_inserted";
    }


}
 
