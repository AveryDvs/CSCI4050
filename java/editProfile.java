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
		//out.println("<h1>" + "Edit Profile" + "</h1>");
		
		//get the information needed
		String firstName = request.getParameter("name-first");
		String lastName = request.getParameter("name-last");
		String ePassword = request.getParameter("psw");
		String password = request.getParameter("psw-repeat");
		String password2 = request.getParameter("psw-repeat");
		String phone = request.getParameter("number");
		/*
		 * String street = request.getParameter("street");
		 * String city = request.getParameter("city");
		 * String state = request.getParameter("state");
		 * String zip = request.getParameter("zip"); 
		 * 
		 * String cardNum = request.getParameter("card-number");
		 * String expirationDate = request.getParameter("expiration-date");
		 */
		
		String userID = "";
		Cookie c[] = request.getCookies();
		for(int i = 0; i < c.length(); c++) {
			if(c[i].getName().equals("uname")){
			userID = c[i].getValue();
			}
		}
		String customerID = "";
		try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","4122");
            
            //check for same password and correct current password
            if(password.equals(password2)) {
            	
            	//Gets customer_id for use
            	Statement st = con.createStatement();
            	ResultSet rs = st.executeQuery("SELECT customer_id, bookstore.user.user_id FROM bookstore.customer");
            		if(rs.getString(2).equals(userID)) {
            			customerID = rs.getString(1);
            		}
            	}
            	
            	//Update user table in db(First and last names, password
            	String query3 = "UPDATE user set first_name=?,last_name=?,password=? where userID=" + userID;
            	PreparedStatement stmt1 = con.prepareStatement(query3);
            	stmt1.setString(1,firstName);
            	stmt1.setString(2,lastName);
            	stmt1.setString(3,password);
            	int i = stmt1.executeUpdate();
         	
            	//Update Customer table in db(phone number and email)
            	PreparedStatement stmt2 = con.prepareStatement("UPDATE Customer set phone_number=? WHERE customerID=" + customerID);
            	stmt2.setString(1,phone);
            	int j = stmt2.executeUpdate();
            	
            	//Update Address
            	/*
            	 * PreparedStatement stmt3= con.prepareStatement("UPDATE Address set street=?,city=?,state=?,zip=? WHERE customerID=" + customerID);
            	 * stmt3.setString(1,street);
            	 * stmt3.setString(2,city);
            	 * stmt3.setString(3,state);
            	 * stmt3.setString(3,zip);
            	 * int k = stmt3.executeUpdate();
            	 */
            	
            	//Update Payment Card
            	/*
            	 * PreparedStatement stmt4= con.prepareStatement(UPDATE Payment_Card set card_number=?,expiration_date=? WHERE customer_ID=" + customerID);
            	 * stmt4.setString(1,cardNum);
            	 * stmt4.setString(2,expirationDate);
            	 */
            	out.println("<script>");
            	out.println("alert('Successfully Updated');");
            	out.println("</script>");
            }
            else {
            	out.println("<script>");
            	out.println("alert('Email or password incorrect, Try again');");
            	out.println("</script>");
            }
        }	catch(SQLException | ClassNotFoundException e) {
        	e.printStackTrace();
        }
	}
}
