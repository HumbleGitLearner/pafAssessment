package ibf2024.assessment.paf.batch4.repositories;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2024.assessment.paf.batch4.models.Beer;
import ibf2024.assessment.paf.batch4.models.BeerSummary;
import ibf2024.assessment.paf.batch4.models.Brewery;
import ibf2024.assessment.paf.batch4.models.Style;
import static ibf2024.assessment.paf.batch4.repositories.Queries.*;

@Repository
public class BeerRepository {

	@Autowired
    private JdbcTemplate jdbcTemplate;

	// DO NOT CHANGE THE SIGNATURE OF THIS METHOD
	/*   select  s.style_name , count(b.id) as beer_count
            from styles s
            join beers b
            on s.id= b.style_id
            group by s.style_name ;     
 */
	public List<Style> getStyles() throws InvalidResultSetAccessException, ParseException{
		// TODO: Task 2
        final List<Style> styles= new LinkedList<>();
        SqlRowSet rs  = null;
        rs = jdbcTemplate.queryForRowSet(Queries.SQL_SELECT_ALL_STYLE_COUNT);
        while(rs.next()){
           styles.add(Style.create(rs));
			  
        }
        return styles;
    }

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public List<Beer> getBreweriesByBeer(int styleId/* You can add any number parameters here */) {
		// TODO: Task 3
		final List<Beer> beers= new LinkedList<>();
        SqlRowSet rs  = null;
        rs = jdbcTemplate.queryForRowSet(Queries.SQL_GET_BEER_BREWERY_BY_STYLE,styleId);
        while(rs.next()){
			beers.add(Beer.create(rs));			  
        }
        return beers;
	
	}

	public int getBeerStyleID(String name){
		SqlRowSet rs  = null;
		int id=0;
        rs = jdbcTemplate.queryForRowSet(Queries.SQL_GET_STYLE_ID, name);
        if (rs.first()){
			id= rs.getInt("id");
        }
		return id;
	}

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public Optional<Brewery> getBeersFromBrewery(int id) {
		// TODO: Task 4
	
		List<BeerSummary> beers= new LinkedList<>();
		Optional<SqlRowSet> rs  = null;
		Brewery bw = new Brewery();
        rs = Optional.ofNullable(jdbcTemplate.queryForRowSet(Queries.SQL_GET_BREWERY__BY_ID, id));
		if (!rs.isPresent()){
			return Optional.empty();
	    }
		SqlRowSet results = rs.get();
		while(results.next()){
		 bw = Brewery.create(results, beers);
	  
        }
        return Optional.of(bw);

	}
}
