import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name="Meal")
public class Meal
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int price;

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@ManyToOne
	@JoinColumn(name = "fk_restaurantId")
	private Restaurant restaurant;

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	@ManyToMany
	@JoinTable(
			name="OderMealsDetails",
			joinColumns=@JoinColumn(name="mealId"),
			inverseJoinColumns=@JoinColumn(name="orderId"))
	private Set<Order> orders;
}
