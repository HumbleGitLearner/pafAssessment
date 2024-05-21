package ibf2024.assessment.paf.batch4.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2024.assessment.paf.batch4.models.Order;
import ibf2024.assessment.paf.batch4.models.Order.OrderItem;
import ibf2024.assessment.paf.batch4.repositories.OrderRepository;

@Service
public class BeerService {

	@Autowired
	private OrderRepository orderRepo;
	

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	// generates the order id and returns the order id
	public String placeOrder(/* You can add any number parameters here */
					int brewery_id, List<OrderItem> order_items) {
		// TODO: Task 5 
		//generates an 8-character UUID for orderId
		String orderId= UUID.randomUUID().toString().substring(0,8);
		Order order = new Order(orderId, new Date(), brewery_id, order_items);
		try {
			orderRepo.save(order);
		} catch (Exception e) {

		}
		return orderId;
	}

}
