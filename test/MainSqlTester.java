
import java.sql.*;


public class MainSqlTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		MainSqlTester main = new MainSqlTester();
		main.runIt();
		
	}
	//sql compact execution
	private void runIt(){
		try{
			String IP_SYSTEMA_AS400 = "10.13.1.22"; 
			String userId = "systema";
			String pass = "straffe12";
			
			String DRIVER = "com.ibm.as400.access.AS400JDBCDriver"; 
			String URL = "jdbc:as400://" + IP_SYSTEMA_AS400 + ";naming=system";
			
			Connection conn = null;
			 
			 //Connect to iSeries 
			 Class.forName(DRIVER); 
			 DriverManager.setLoginTimeout(2);
			 conn = DriverManager.getConnection(URL, userId, pass);
			 if(!conn.isClosed()){
				 System.out.println("<"+ conn + "> " + "Connection OK!");
				 //SQL-statements
				
				 //SQL 1 (Christer)
				 String sql = "select knavn, adr1, adr2, postnr, adr3 from syspedf/cundf  where knavn like ?";
				 PreparedStatement stmt = conn.prepareStatement(sql);
				 stmt.setString(1, "%");
				 ResultSet rs = stmt.executeQuery();
				 while (rs.next()) {
					 String field1 = rs.getString(1);
					 String field2 = rs.getString(2);
					 String field3 = rs.getString(3);
					 String field4 = rs.getString(4);
					 //String field2 = rs.getString("fieldname");
					 System.out.println(field1 + ":" + field2 + ":" + field3 + ":" + field4);
				 }
				 
				 //SQL 2 (Svein)
				 /*String sql = "select faavd, fabeln, faopd, fali from sampledb/fakt where fabeln <= ? ";
				 PreparedStatement stmt = conn.prepareStatement(sql);
				 stmt.setString(1, "200");
				 */
				 //OSCARS yyy table
				 /*String sql = "select count(*) from sampledb/xxx ";
				 PreparedStatement stmt = conn.prepareStatement(sql);
				 
				 ResultSet rs = stmt.executeQuery();
				 while (rs.next()) {
					 String field1 = rs.getString(1);
					 String field2 = rs.getString(2);
					 String field3 = rs.getString(3);
					 String field4 = rs.getString(4);
					 //String field2 = rs.getString("fieldname");
					 System.out.println(field1 + ":" + field2 + ":" + field3 + ":" + field4);
				 }
				 */
				 
				 
				 
				 rs.close();
				 stmt.close();
				 conn.close();
				 
				
				 //CREATE TABLE
				 /*
				 String sql = "create table sampledb/yyy (os   integer  NOT NULL with default) ";
				 Statement stmt = conn.createStatement();
				 stmt.execute(sql);
				 
				 stmt.close();
				 conn.close();
				 */
			 }else {
				 System.out.println("No connection...");
			 }
			 
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}

}
