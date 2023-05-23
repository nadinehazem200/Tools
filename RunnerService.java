
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.Entity;

import javax.ws.rs.*;
import javax.ws.rs.core.*;


@Stateless
@Path("/runner-roles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class RunnerService {

	@PersistenceContext
	private EntityManager em;
	@PUT
	@Path("/markOrderStatusDelivered/{id}")
	public String markOrderStatusDelivered(@PathParam("id") int orderid)
	{
		try{
			Order order = em.find(Order.class, orderid);
			order.setOrderStatus("Delivered");
			em.merge(order);
			return "Order is marked as delivered ..\n";
		}
		catch (PersistenceException p)
		{
			return p.toString();
		}
	}

	@GET
	@Path("/numberOfTrips/{id}")
	public String getNumberOfTripsDeliveredByRunner(@PathParam("id") int runnerid)
	{
		try {
			Query numOfOrders = em.createQuery("SELECT COUNT(*) FROM Order WHERE status = Delivered, fk_runnerId = :id", Order.class);
			numOfOrders.setParameter("id", runnerid);
			return "Number of trips delivered by " + em.find(Runner.class, runnerid).getName() + " = " +
					(int) numOfOrders.getSingleResult();
		}
		catch (PersistenceException p)
		{
			return p.toString();
		}
	}
	
}
