package ibf2024.assessment.paf.batch4.models;

import java.io.StringReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;

public class Order {

    public record OrderItem( int id, int quantity){};

    private String orderId;
    private Date date;
    private int breweryId;
    private List<OrderItem> orders;

    public Order() {
    }

    public Order(String orderId, Date date, int breweryId, List<OrderItem> orders) {
        this.orderId = orderId;
        this.date = date;
        this.breweryId = breweryId;
        this.orders = orders;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getBreweryId() {
        return breweryId;
    }

    public void setBreweryId(int breweryId) {
        this.breweryId = breweryId;
    }

    public List<OrderItem> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderItem> orders) {
        this.orders = orders;
    }


    public JsonObject toJsonObject(){               
        //build an JsonArray for "orders"
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (OrderItem item : orders) {
                JsonObjectBuilder orderJson = Json.createObjectBuilder()
                        .add("beerId", item.id)
                        .add("quantity", item.quantity);
                jsonArrayBuilder.add(orderJson);
            }
        return Json.createObjectBuilder()
                    .add("orderId", orderId)
                    .add("date", date.toString())
                    .add("breweryId", breweryId)
                    .add("orders", jsonArrayBuilder)
                    .build();
    }

	public static Order toOrder(Document doc) {
		Order order = new Order();
        order.setOrderId(doc.getString("orderId"));
        order.setDate(doc.getDate("date"));
        order.setBreweryId(doc.getInteger("breweryId"));
       // order.setOrders(null);
        return order;
	}


    public static Order create(JsonObject readObject) throws ParseException{
        Order order = new Order();
        order.setOrderId(readObject.getString("orderId"));
        String dateString= readObject.getString("date");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // LocalDate date = LocalDate.parse(dateString, formatter);
        order.setDate(dateFormat.parse(dateString));
        order.setBreweryId(readObject.getInt("breweryId"));
        JsonArray orderArray = readObject.getJsonArray("orders");
        List<OrderItem> orders = new LinkedList<OrderItem>();
        orderArray.forEach(itemObj -> {
            JsonObject obj = (JsonObject) itemObj;
            OrderItem item = new OrderItem(obj.getInt("beeerId"), 
                                            obj.getInt("quantity"));
            orders.add(item);            
        });
        order.setOrders(orders);       
        return order;
    }


    public static Order create(String jsonStr) throws ParseException{
        JsonReader jsonReader = Json.createReader(new StringReader(jsonStr));
        return create(jsonReader.readObject());
    }

    
    
    @Override
	public String toString() {
		return "Order[orderId=%s, Date=%s,  Brewery Id=%d, orders=%s]"
			.formatted(orderId, date.toString(), breweryId, orders.toString());
	}


}
