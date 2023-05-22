import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Order")
public class Order 
{

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Set<Meal> getMeals() {
		return meals;
	}
	public void setMeals(Set<Meal> meals) {
		this.meals = meals;
	}
	public double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String date;

	@ManyToMany(mappedBy="orders")
	private Set<Meal> meals;
	@Column(name = "total price")
	private double total_price;

	@ManyToOne
	@JoinColumn(name = "fk_runnerId")
	private Runner runner;

	public Runner getRunner() {
		return runner;
	}

	public void setRunner(Runner runner) {
		this.runner = runner;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne
	@JoinColumn(name = "fk_restaurantId")
	private Restaurant restaurant;

	@ManyToOne
	@JoinColumn(name = "fk_customerId")
	private Customer customer;
	@Column(name = "status")
	private String orderStatus; //Preparing  or Canceled
	
	
}
