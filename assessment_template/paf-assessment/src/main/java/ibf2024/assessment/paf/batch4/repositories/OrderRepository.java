package ibf2024.assessment.paf.batch4.repositories;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import ibf2024.assessment.paf.batch4.models.Order;
import jakarta.json.JsonObject;

@Repository
public class OrderRepository {

	// TODO: Task 5
	@Autowired
	private MongoTemplate mongoTemplate;

	// Order.toJson
	//new ObjectId() before saving to Mongodb
	public void save(Order order){

		mongoTemplate.save(order, "beerOrders");
	}

	public void save(JsonObject orderJson){
		mongoTemplate.save(Document.parse(orderJson.toString())
					, "beerOrders");
	} 

}
