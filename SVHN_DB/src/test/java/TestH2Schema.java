import java.sql.*;

/**
 * Created by Dims on 11.03.2017.
 */
public class TestH2Schema {

   public static void main(String[] args) throws SQLException {

      Driver driver = new org.h2.Driver();
      Connection conn = driver.connect("jdbc:h2:file:D:/Users/Dims/Design/TESTS/SVHN_DB/db/svhn", null);

      Statement stmt = conn.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT ID FROM IMAGE WHERE PATH='train/1.png';");
      while(rs.next()) {
         System.out.println(rs.getLong("ID"));
      }

      rs.close();
      stmt.close();
      conn.close();

   }
}
