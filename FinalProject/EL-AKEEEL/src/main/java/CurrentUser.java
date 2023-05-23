import javax.ejb.Startup;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

@Stateful
@Startup
public class CurrentUser {

	public static String role = "";
}
