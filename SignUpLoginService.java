
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//localhost\akeel\resgisteration\customer-login
import javax.ws.rs.*;
import javax.ws.rs.core.*;
@Stateless
@Path("/registration")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SignUpLoginService {

    @PersistenceContext
    private EntityManager em;
    @POST
    @Path("/customer-login")
    @Consumes(MediaType.APPLICATION_JSON)
    public String customerLogin(Customer c)
    {
        Customer customer = em.find(Customer.class, c.getId());
        if(customer == null)
            return "Failed log in, customer is not registered before";
        return "logged in successfully";
    }

    @POST
    @Path("/restaurantOwner-login")
    @Consumes(MediaType.APPLICATION_JSON)
    public String restaurantOwnerLogin(RestaurantOwner ro)
    {
        RestaurantOwner restaurantOwner = em.find(RestaurantOwner.class, ro.getId());
        if(restaurantOwner == null)
            return "Failed log in, restaurant owner is not registered before";
        return "logged in successfully";
    }
    @POST
    @Path("/runner-login")
    @Consumes(MediaType.APPLICATION_JSON)
    public String runnerLogin(Runner ru)
    {
        Runner runner = em.find(Runner.class, ru.getId());
        if(runner == null)
            return "Failed log in, runner owner is not registered before";
        return "logged in successfully";
    }

    @POST
    @Path("/customer-signup")
    @Consumes(MediaType.APPLICATION_JSON)
    public String customerSignup(Customer c)
    {
        Customer customer = em.find(Customer.class, c.getId());
        if(customer == null)
        {
            em.persist(c);
            return "customer registered successfully";
        }
        return "This customer is registered before";
    }

    @POST
    @Path("/restaurantOwner-signup")
    @Consumes(MediaType.APPLICATION_JSON)
    public String restaurantOwnerSignup(RestaurantOwner ro)
    {
        RestaurantOwner restaurantOwner = em.find(RestaurantOwner.class, ro.getId());
        if(restaurantOwner == null) {
            em.persist(ro);
            return "restaurant owner registered successfully";
        }
        return "This restaurant owner is registered before";
    }
    @POST
    @Path("/runner-signup")
    @Consumes(MediaType.APPLICATION_JSON)
    public String runnerSignup(Runner ru)
    {
        Runner runner = em.find(Runner.class, ru.getId());
        if(runner == null){
            em.persist(ru);
            return "runner registered successfully";
        }
        return "This runner is registered before";
    }
}
