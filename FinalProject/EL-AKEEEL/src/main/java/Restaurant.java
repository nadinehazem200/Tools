import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="Restaurant")
public class Restaurant
{
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Meal> getMeals() {
		return meals;
	}

	public void setMeals(Set<Meal> meals) {
		this.meals = meals;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;

	public RestaurantOwner getRestaurantOwner() {
		return restaurantOwner;
	}

	public void setRestaurantOwner(RestaurantOwner restaurantOwner) {
		this.restaurantOwner = restaurantOwner;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	@ManyToOne
	@JoinColumn(name = "ownerid")
	private RestaurantOwner restaurantOwner;

	@OneToMany(mappedBy = "restaurant")
	private Set<Meal> meals;

	@OneToMany(mappedBy = "restaurant")
	private Set<Order> orders;
	
}
