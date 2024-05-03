package ibf2024.assessment.paf.batch4.models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.support.rowset.SqlRowSet;

// DO NOT MODIFY THIS FILE.


public class Beer {

	private int beerId;
	private String beerName;
	private String beerDescription;

	private int breweryId;
	private String breweryName;

	public int getBeerId() {
		return beerId;
	}
	public void setBeerId(int beerId) {
		this.beerId = beerId;
	}
	public String getBeerName() {
		return beerName;
	}
	public void setBeerName(String beerName) {
		this.beerName = beerName;
	}
	public String getBeerDescription() {
		return beerDescription;
	}
	public void setBeerDescription(String beerDescription) {
		this.beerDescription = beerDescription;
	}
	public int getBreweryId() {
		return breweryId;
	}
	public void setBreweryId(int breweryId) {
		this.breweryId = breweryId;
	}
	public String getBreweryName() {
		return breweryName;
	}
	public void setBreweryName(String breweryName) {
		this.breweryName = breweryName;
	}
	// //        select br.id as beer_id, br.name as beer_name , br.descript, 
	// bw.id as bewery_id, bw.name as brewery_name
	// from beers br 
	// left join breweries bw
	// on br.brewery_id= bw.id 
	// where style_id=13
	// order by br.name asc       """;
	
     public static Beer create(SqlRowSet rs){
        Beer b = new Beer();
		b.setBeerId(rs.getInt("bewery_id"));
		b.setBeerName(rs.getString("beer_name"));
		b.setBeerDescription(rs.getString("descript"));
		b.setBreweryId(rs.getInt("bewery_id"));
		b.setBreweryName(rs.getString("brewery_name"));
           return b;
    }

	@Override
	public String toString() {
		return "Beer [beerId=" + beerId + ", beerName=" + beerName + ", beerDescription=" + beerDescription
				+ ", breweryId=" + breweryId + ", breweryName=" + breweryName + "]";
	}
}
