import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


//Manditory: name, email address, phone and password
//Optional:shipping address info (street, city, state, and zip code)
//Optiopnal:payment info (card type, number, expiration date)
@WebServlet("/EditProfile")
public class editProfile extends HttpServlet{

	//grabs the customer info and displays it
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//set up and introduce file
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<h1>" + "Edit Profile" + "</h1>");
		
		//get the information needed
		String firstName = request.getParameter("name-first");
		String lastName = request.getParameter("name-last");
		String ePassword = request.getParameter("psw");
		String password = request.getParameter("psw-repeat");
		String password2 = request.getParameter("psw-repeat");
		String email = request.getParameter("email");
		String phone = request.getParameter("number");
		
		//statements used to update/check database information
		Statement stmt1 = null;
		Statement stmt2 = null;
		Statement stmt3 = null;
		Statement stmt4 = null;
		
		//query statements used to select/update certain attributes in the database
		String query1 = "select user_id,cast(aes_decrypt(password,  \"4050\") as char),from bookstore.user;";
		String query2 = "select customer_id from bookstore.customer;";
		
		
		try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","4122");
         
            //Check for valid current password
            boolean valid = false;
            stmt3 = con.createStatement();
			ResultSet rs1 = stmt3.executeQuery(query1);
			String userID = rs1.getString(1);
			
			while(rs1.next() && !valid) {
				if (rs1.getString(2).equals(ePassword)) {
					valid = true;
				}
			}
			
			//Grab customer_id for location of table to update
			stmt4=con.createStatement();
			ResultSet rs2 = stmt4.executeQuery(query2);
			String customerID = rs2.getString(1);
			
            //check for same password and correct current password
            if(password.equals(password2) && valid) {
            	
            	//Update user table in db(First and last names, password
            	stmt1 = con.createStatement();
            	String query3 = "UPDATE user set first_name= " + firstName + ", last_name= " + lastName + ", password= " + password 
            			+ "WHERE user_id =" + userID;
            	stmt1.executeUpdate(query3);    	
         	
            	//Update Customer table in db(phone number and email)
            	stmt2 = con.createStatement();
            	String query4 = "UPDATE customer set phone= " + phone + ", email_address= " + email + "WHERE customer_id= " + customerID;
            	stmt2.executeUpdate(query4);
            	
            	out.println("<html><body><b>" + "Successfully Updated" + "</b></body></html>");
            }
            else {
            	out.println("Invalid information, Try again");
            }
        }	catch(SQLException | ClassNotFoundException e) {
        	e.printStackTrace();
        }
	}
}
