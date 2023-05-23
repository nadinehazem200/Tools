import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="RestaurantOwner")
public class RestaurantOwner{
	
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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	protected String name;
	@OneToMany(mappedBy = "restaurantOwner")
	private Set<Restaurant> restaurant;
}
