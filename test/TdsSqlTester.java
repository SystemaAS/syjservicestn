
import java.sql.*;


public class TdsSqlTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		TdsSqlTester main = new TdsSqlTester();
		main.runIt();
		
	}
	//sql compact execution
	private void runIt(){
		try{
			String IP_SYSTEMA_AS400 = "10.13.1.22"; 
			String userId = "systema";
			String pass = "straffe12";
			
			String DRIVER = "com.ibm.as400.access.AS400JDBCDriver"; 
			String URL = "jdbc:as400://" + IP_SYSTEMA_AS400 + ";naming=system;libraries=syendre,syendptf,syspedf,sysped,l1dat"; //CB inloggnings ex
			//String URL = "jdbc:as400://" + IP_SYSTEMA_AS400 + ";naming=system;";
			Connection conn = null;
			 
			 //Connect to iSeries 
			 Class.forName(DRIVER); 
			 DriverManager.setLoginTimeout(2);
			 conn = DriverManager.getConnection(URL, userId, pass);
			 if(!conn.isClosed()){
				 System.out.println("<"+ conn + "> " + "Connection OK!");
				 //SQL-statements
				
				 //SQL 1 (Christer)
				 /*
				 exec sql declare  A33  cursor for                     	   
				 select SVTX15_02, SVTX15_10, SVTX15_11, SVTX15_33        
				 from SVTX15F                                             
				 where SVTX15_01 IN (:A1, :A2, :A3, :A4, :A5) AND         
				       SVTX15_04 IN ('109', '110', '111') AND             
				       SVTX15_33<>' ';                                    
				 }*/
				 
				 //SQL 2 (CB)
				 StringBuffer sql = new StringBuffer();
				 sql.append(" select LTRIM(RTRIM(svtx15_02)) svtx15_02, svtx15_10, svtx15_11, svtx15_33");
				 sql.append(" from svtx15f");
				 sql.append (" where svtx15_01 in(?, ?, ?, ?, ?)");
				 sql.append (" and svtx15_04 in(?, ?, ?)");
				 sql.append (" and svtx15_33<>?");

				 PreparedStatement stmt = conn.prepareStatement(sql.toString());
				 //_01
				 //String itemNo = "6601992000";
				 String itemNo = "1702907900";
				 String a1 = itemNo;
				 String a2 = itemNo.substring(0,8) + "00";
				 String a3 = itemNo.substring(0,6) + "0000";
				 String a4 = itemNo.substring(0,4) + "000000";
				 String a5 = itemNo.substring(0,2) + "00000000";
				 
				 stmt.setString(1, a1); 
				 stmt.setString(2, a2);
				 stmt.setString(3, a3); 
				 stmt.setString(4, a4);
				 stmt.setString(5, a5);
				 //_04
				 stmt.setString(6, "109");
				 stmt.setString(7, "110");
				 stmt.setString(8, "111");
				 stmt.setString(9, " ");
				 
				  
				 ResultSet rs = stmt.executeQuery();
				 while (rs.next()) {
					 String field1 = rs.getString(1);
					 String field2 = rs.getString(2);
					 String field3 = rs.getString(3);
					 String field4 = rs.getString(4);
					 System.out.println(field1 + ", " + field2 + ", " + field3 + ", " + field4);
					 
				 }
				 
				 rs.close();
				 stmt.close();
				 conn.close();
				 
			
				 
				 
				 //TEST
				 //http://localhost:8080/syjservices/syjsSVT010R.do?user=OSCAR&kod=6601992000&lk=NO
				 /*
				 StringBuffer sql = new StringBuffer();
				 sql.append(" select svtx082_01, svtx082_02");
				 sql.append(" from svtx08f2");
				 sql.append (" where svtx082_01 = ?");
				 sql.append (" and svtx082_02 = ?");
				 
				 PreparedStatement stmt = conn.prepareStatement(sql.toString());
				 //_01
				 String countryCode = "NO";
				 String region = "1011";
				 
				 stmt.setString(1, countryCode); 
				 stmt.setString(2, region);
				  
				 ResultSet rs = stmt.executeQuery();
				 while (rs.next()) {
					 String field1 = rs.getString(1);
					 String field2 = rs.getString(2);
					 System.out.println(field1 + ", " + field2);
					 //System.out.println(field1);
				 }
				 
				 rs.close();
				 stmt.close();
				 conn.close();
				 */
				 
				 //JOINs in Extra Mengd ex
				 /*
				 StringBuffer sql = new StringBuffer();
				 sql.append(" select a.svtx15_02, a.svtx15_10, a.svtx15_11, a.svtx15_33, b.svtx082_01, b.svtx082_02");
				 sql.append(" from svtx15f a, svtx08f2 b ");
				 sql.append (" where a.svtx15_01 in(?, ?, ?, ?, ?)");
				 sql.append (" and a.svtx15_04 in(?, ?, ?)");
				 sql.append (" and a.svtx15_33<>?");
				 sql.append (" and b.svtx082_01 = 'NO'");
				 sql.append (" and a.svtx15_02 = b.svtx082_02");
				 
				 PreparedStatement stmt = conn.prepareStatement(sql.toString());
				 //_01
				 String itemNo = "6601992000";
				 String a1 = itemNo;
				 String a2 = itemNo.substring(0,8) + "00";
				 String a3 = itemNo.substring(0,6) + "0000";
				 String a4 = itemNo.substring(0,4) + "000000";
				 String a5 = itemNo.substring(0,2) + "00000000";
				 
				 stmt.setString(1, a1); 
				 stmt.setString(2, a2);
				 stmt.setString(3, a3); 
				 stmt.setString(4, a4);
				 stmt.setString(5, a5);
				 //_04
				 stmt.setString(6, "109");
				 stmt.setString(7, "110");
				 stmt.setString(8, "111");
				 stmt.setString(9, " ");
				  
				 ResultSet rs = stmt.executeQuery();
				 while (rs.next()) {
					 String field1 = rs.getString(1);
					 String field2 = rs.getString(2);
					 String field3 = rs.getString(3);
					 String field4 = rs.getString(4);
					 String field5 = rs.getString(5);
					 String field6 = rs.getString(6);
					 System.out.println(field1 + ", " + field2 + ", " + field3 + ", " + field4 + ", " + field5 + ", " + field6);
				 }
				 rs.close();
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
