package ibf2024.assessment.paf.batch4.models;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.support.rowset.SqlRowSet;

// DO NOT MODIFY THIS FILE.

public class Style {

    private int styleId;
    private String name;
    private int beerCount;

    public int getStyleId() {
        return styleId;
    }
    public void setStyleId(int styleId) {
        this.styleId = styleId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getBeerCount() {
        return beerCount;
    }
    public void setBeerCount(int beerCount) {
        this.beerCount = beerCount;
    }

    
    public static Style create(SqlRowSet rs){
        Style s= new Style();
      //  s.setStyleId(Integer.parseInt(rs.getInt("id")));
        s.setName(rs.getString("style_name"));
        s.setBeerCount(Integer.parseInt(rs.getString("beer_count")));
        return s;
    }

    @Override
    public String toString() {
        return "Style [styleId=" + styleId + ", name=" + name + ", beerCount=" + beerCount + "]";
    }
}
