import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;


@Entity
@Table(name="Customer")
public class Customer extends User {

    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;
}
