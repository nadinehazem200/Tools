import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Runner")
public class Runner
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getDeliveryfees() {
		return deliveryfees;
	}
	public void setDeliveryfees(double deliveryfees) {
		this.deliveryfees = deliveryfees;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "NAME")

	private String name;
	@Column(name = "Status")

	private String status; //available or busy
	@Column(name = "delivery fees")
	private double deliveryfees;

	@OneToMany(mappedBy = "runner")
	private Set<Order> orders;
}
