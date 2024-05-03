package ibf2024.assessment.paf.batch4.repositories;

public interface Queries {

    public static final String SQL_SELECT_ALL_STYLE_COUNT = """
        select  s.style_name , count(b.id) as beer_count
            from styles s
            join beers b
            on s.id= b.style_id
            group by s.style_name 
            order by s.style_name asc;     
    """;
    public static final String SQL_GET_STYLE_ID="""
        select id from styles where style_name=?      
            """;
  
    public static final String SQL_GET_BEER_BREWERY_BY_STYLE="""
        select br.id as beer_id, br.name as beer_name , br.descript, 
        bw.id as bewery_id, bw.name as brewery_name
        from beers br 
        left join breweries bw
        on br.brewery_id= bw.id 
        where style_id=13
        order by br.name asc     
        """;
               
    public static final String SQL_GET_BREWERY__BY_ID="""   
        select bw.* , 
        br.id as beer_id, br.name as beer_name, br.descript as beer_descript 
        from breweries bw
        join beers br
        on bw.id = br.brewery_id
        where bw.id =?;
        """;
 

}
