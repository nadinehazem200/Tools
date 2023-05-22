import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.persistence.Entity;


@Stateless
@Path("customerServices")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerService {

	@PersistenceContext
	private EntityManager em;
	@Path("createOrder")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String createOrder(Order order)
	{
		try {
			//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime date = LocalDateTime.now();
			//Date date = new Date();
			order.setDate(dateFormat.format(date));
			double totalPrice = 0;
			String meals = "";

			for(Meal m: order.getMeals()) {
				totalPrice += m.getPrice();
				meals = "Meals: \n" + m.getName() + "      " + "Price: " + m.getPrice() + "\n";
			}
			order.setTotal_price(totalPrice);
			order.setOrderStatus("Preparing");
			TypedQuery<Runner> q = em.createQuery("SELECT r FROM Runner r WHERE r.Status = Available", Runner.class);
			Runner r = q.getSingleResult();
			r.setStatus("busy");
			em.merge(r);
			order.setRunner(r);
			em.persist(order);
			String orderReceipt = "Date: " + order.getDate() + "\n" + meals + "Delivery Fees: " + r.getDeliveryfees() + "Runner Name: " + r.getName() +
					"\nTotal Price: " + totalPrice + r.getDeliveryfees();

			return "Order is created successfully..\n" + orderReceipt;
		}
		catch (PersistenceException p)
		{
			return p.toString();
		}
	}

	@PUT
	@Path("editeOrder")
	@Consumes(MediaType.APPLICATION_JSON)
	public String editOrder(Order order)
	{
		try{
			Order or = em.find(Order.class, order.getId());
			if(or.getOrderStatus().equals("Preparing")) {
				or.setMeals(order.getMeals());
				em.merge(or);
				return "Your Order with ID " + order.getId() + " is updated successfully\n";
			}
			else if (or.getOrderStatus().equals("Cancelled")){
				return "Error, Your Order with ID " + order.getId() + " was cancelled before\n";
			}
			else{
				return "Error, Your Order with ID " + order.getId() + " is already delivered\n";
			}
		}
		catch (Exception p)
		{
			return p.toString();
		}
	}


	public String cancelOrder(int id)
	{
		try {
			em.remove(em.find(Order.class, id));
			return "Order removed successfully\n";
		}
		catch (Exception p)
		{
			return p.toString();
		}
	}
	@GET
	@Path("restaurantsList")
	public String listAllRestaurants()
	{
		try {
			TypedQuery<Restaurant> q = em.createQuery("SELECT * FROM Restaurant;", Restaurant.class);
			List<Restaurant> restaurants = q.getResultList();
			String restaurantsList = "";
			for (Restaurant r : restaurants)
				restaurantsList += r.toString() + "\n";

			return restaurantsList;
		}
		catch (Exception p)
		{
			return p.toString();
		}
	}

	@GET
	@Path("listOrdersByCustomer/{id}")
	public List<Order> listOrdersByCustomerId(@PathParam("id") int custId)
	{
		try {
			TypedQuery<Order> q = em.createQuery("SELECT o FROM Order o WHERE o.fk_customerId =:id ;", Order.class);
			q.setParameter("id", custId);
			return q.getResultList();
		}
		catch (Exception p)
		{
			return null;
		}
	}
	
}
