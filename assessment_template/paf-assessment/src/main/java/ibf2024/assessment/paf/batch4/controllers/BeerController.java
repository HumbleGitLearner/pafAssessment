package ibf2024.assessment.paf.batch4.controllers;


import java.text.BreakIterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysql.cj.protocol.ExportControlled;

import ibf2024.assessment.paf.batch4.services.BeerService;
import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.servlet.http.HttpSession;
import ibf2024.assessment.paf.batch4.models.Beer;
import ibf2024.assessment.paf.batch4.models.Brewery;
import ibf2024.assessment.paf.batch4.models.Style;
import ibf2024.assessment.paf.batch4.models.StyleName;
import ibf2024.assessment.paf.batch4.repositories.BeerRepository;
import ibf2024.assessment.paf.batch4.services.BeerService;


@Controller
@RequestMapping

public class BeerController {
 
	//  @Autowired
    //  private BeerService beerService;

	@Autowired
	private BeerRepository beerRepo;


/*
	view0
					<tr data-th-each="s: ${styles}" data-th-object="${s}">
					<td>
						<span data-th-text="*{name}"></span> 
					</td>
					<td>
						<a data-th-href="@{/beer/style/{id}?styleName={styleName}(id=*{styleId}, styleName=*{name})}">
							<span data-th-text="*{beerCount}"></span>
*/
	//TODO Task 2 - view 0
 	@GetMapping(path = "/")
    public String stylesAll(Model model) {
        //List<RSVP> rsvpList = rsvpService.getAllRSVP("");
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
    public String getBreweriesByBeer(@PathVariable String styleId,
									@RequestBody  StyleName styleName, Model model) {
		//get  style_id by styleName when styleId is not passed in
		String name = styleName.getName();
		int stylId = beerRepo.getBeerStyleID(name);
		List<Beer> rsults= beerRepo.getBreweriesByBeer(stylId);	
		model.addAttribute("styleName", name);
		model.addAttribute("beerDetails", rsults);
		return "beerlist";
	}


	//TODO Task 4 - view 2
	
    @GetMapping("/beer/brewer/{id}")
    public String getBreweriesByBeer(@PathVariable String bwId,
									 Model model) {
		Optional<Brewery> results = beerRepo.getBeersFromBrewery(Integer.parseInt(bwId));
		if (results.isEmpty()){
			model.addAttribute("error", "Brewery Not Found");
			return "error";
		} else {
		model.addAttribute("breweryDetails",results );
		}
		return "breweryDetails";
	}

	
	//TODO Task 5 - view 2, place order
  /*
    @PostMapping
    public String postReviews(@RequestBody MultiValueMap<String, String> form, Model model) {
        String username = form.getFirst("user");
        String rating = form.getFirst("rating");
        String comment = form.getFirst("comment");
        String gameId = form.getFirst("gameId");
        String boardGameName = form.getFirst("name");
        Review r = new Review(
                username, Integer.parseInt(rating),
                comment, Integer.parseInt(gameId),
                boardGameName);
        Review insertedRv = reviewSvc.insertReview(r);
        model.addAttribute("review", insertedRv);
        return "review_inserted";
    }
}
 */
}
