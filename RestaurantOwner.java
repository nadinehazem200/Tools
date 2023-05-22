import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="RestaurantOwner")
public class RestaurantOwner extends User{
	@OneToMany(mappedBy = "restaurantOwner")
	private Set<Restaurant> restaurant;
}
