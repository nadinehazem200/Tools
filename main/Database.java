import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import javax.ejb.Stateless;
import java.sql.*;
import javax.persistence.Entity;


@Stateless
@ApplicationPath("/api")
public class Database extends Application {
	
    public static void createNewDatabase(String fileName) {

        String url = "jdbc:sqlite:C:\\sqlite\\db\\" + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
            else
            	System.out.print("already done");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void connectDatabase(String name) {
        Connection c = null;
        
        try {
           Class.forName("org.sqlite.JDBC");
           c = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\db\\" + name);
        } catch ( Exception e ) {
           System.err.println( e.getClass().getName() + ": " + e.getMessage() );
           System.exit(0);
        }
        System.out.println("Opened database successfully");
     }
    
    public static void createRunnerTable(String databaseName)
    {
    	Connection c = null;
        Statement stmt = null;
        try {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\db\\" + databaseName);
          System.out.println("Opened database successfully");
    
          stmt = c.createStatement();
          String sql = "CREATE TABLE Runner " +
                       "(ID INT PRIMARY KEY     NOT NULL," +
                       " NAME           TEXT    NOT NULL, " + 
                       " Status         TEXT            , " +
                       " deliveryfees   REAL)"; 
          stmt.executeUpdate(sql);    
          stmt.close();    
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Table created successfully");
      }
    /*
    public static void main(String[] args) {
        connectDatabase("Al-Akeel");
    	//createRunnerTable("Al-Akeel.db");
    	RestaurantOwner r = new RestaurantOwner();
    	r.setId(1);
    	r.setName("naymar");
    	RestaurantOwnerService r2 = new RestaurantOwnerService();
    	System.out.print("lio");
    	try {
			r2.add(r);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }*/
	
    /*public static void main(String[] args) throws ClassNotFoundException
	{

	        String url = "jdbc:sqlite:Akeel.db";
	        Class.forName("org.sqlite.JDBC");

	        try (Connection conn = DriverManager.getConnection(url)) {
	            if (conn != null) {
	                DatabaseMetaData meta = conn.getMetaData();
	                System.out.println("The driver name is " + meta.getDriverName());
	                System.out.println("A new database has been created.");
	            }

	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    
	
		//Runner r = new Runner();

}*/
}
