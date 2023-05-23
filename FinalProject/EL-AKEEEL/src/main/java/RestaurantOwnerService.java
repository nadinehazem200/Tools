
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Set;
import javax.persistence.Entity;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Stateless
@Path("/restaurantOwnerServices")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class RestaurantOwnerService {
	
	public RestaurantOwnerService() {
		super();
	}
	@PersistenceContext
	private EntityManager em;
	@POST
	@Path("/createNewRestaurant")
	@Consumes(MediaType.APPLICATION_JSON)
	public String createRestaurantMenu(Restaurant r)
	{
		if(CurrentUser.role.equals("Restaurant Owner")) {
		try {
			em.persist(r);
			return "This restaurant menu is created successfully\n";
		}
		catch (Exception p)
		{
			return p.toString();
		}
		}
		else
			return "You are not allowed to do so\n";
	}

	@Path("/editRestaurantMenu/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public String editRestaurant(@PathParam("id") int id, Set<Meal> newMeals)
	{
		if(CurrentUser.role.equals("Restaurant Owner")) {
		try{
			Restaurant r = em.find(Restaurant.class, id);
			r.setMeals(newMeals);
			em.merge(r);
			return "The restaurant menu has been edited successfully\n";
		}
		catch(Exception p)
		{
			return p.toString();
		}
		}
		else
			return "You are not allowed to do so\n";
	}

	@GET
	@Path("/getRestaurantDetails/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Restaurant getRestaurantDetails(@PathParam("id") int restaurantid)
	{
		if(CurrentUser.role.equals("Restaurant Owner")) {
		try{
			Restaurant r = em.find(Restaurant.class, restaurantid);
			return r;
		}
		catch(Exception p)
		{
			return null;
		}
		}
		else
			return null;
	}
	@GET
	@Path("/getRestaurantReport/{id}")
	public String createRestaurantReport(@PathParam("id") int restaurantid)
	{
		if(CurrentUser.role.equals("Restaurant Owner")) {
		try {
			Query totalAmountsOfCompletedOrders = em.createQuery("SELECT SUM(o.total price) FROM Order o WHERE o.status = Delivered, o.fk_restaurantId = :id", Order.class);
			Query numOfCompletedOrders = em.createQuery("SELECT COUNT(*) FROM Order WHERE status = Delivered, fk_restaurantId = :id", Order.class);
			Query numOfCancelledOrders = em.createQuery("SELECT COUNT(*) FROM Order WHERE status = Cancelled, fk_restaurantId = :id", Order.class);
			totalAmountsOfCompletedOrders.setParameter("id", restaurantid);
			numOfCompletedOrders.setParameter("id", restaurantid);
			numOfCancelledOrders.setParameter("id", restaurantid);

			return " ------------ Report -------------" +
					"\nRestaurant Name: " + em.find(Restaurant.class, restaurantid).getName() +
					"\nTotal amount of all completed orders: " + (double) totalAmountsOfCompletedOrders.getSingleResult() +
					"\nNumber of completed orders: " + (double) numOfCompletedOrders.getSingleResult() +
					"\nNumber of cancelled orders: " + (double) numOfCancelledOrders.getSingleResult() + "\n";
		}
		catch (Exception p)
		{
			return p.toString();
		}
		}
		else
			return "You are not allowed to do so\n";
	}
	public void add(RestaurantOwner r2){
            this.em.persist(r2);
	}
}
