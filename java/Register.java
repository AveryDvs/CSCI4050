import java.io.IOException; 
import java.io.PrintWriter; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Register")
public class Register extends HttpServlet {
    
   

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
            
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String nameF = request.getParameter("name-first");
        String nameL = request.getParameter("name-last");
        String email = request.getParameter("email");
        String pass = request.getParameter("psw");
        String phone = request.getParameter("phone");
        pass = "aes_encrypt('" + pass + "', '4050')";

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","lkjhlkjh");

            PreparedStatement ps = con.prepareStatement("insert into user values(?,?,?,?,?)");

            ps.setString(1,"test");
            ps.setString(2, pass);
            ps.setString(3, nameF);
            ps.setString(4, nameL);
            ps.setString(5, "'C'");
            
            PreparedStatement psc = con.prepareStatement("insert into Customer values(?,?,?,?,?)");

            psc.setString(1,"test");
            psc.setString(2,"test");
            psc.setString(3, email);
            psc.setString(4, phone);
            psc.setString(5, "'A'");

            
            PrintWriter out1 = response.getWriter(); 
            out1.println("<html><body><b>Successfully Inserted"
                        + "</b></body></html>");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        String query = "SELECT email_address, bookstore.user.user_id, cast(aes_decrypt(password, \"4050\") as char) FROM bookstore.customer inner join bookstore.user on bookstore.user.user_id = bookstore.customer.user_id;";
        boolean valid = false;
        
        try {
        	Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","4122");
			Statement st = con.createStatement();
			ResultSet rs =st.executeQuery(query);
			
			
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (valid) {
	        String host = "smtp.gmail.com";
	        String port = "587";
	        String emailId = "bookstore9c@gmail.com";
	        String password = "Bookpassword9C";
	        String toAddress = "bookstore9c@gmail.com";
	        String subject = "Thank You For Registering With Us!";
	        String message = "Thank you for regietring with our Bookstore. You now have access to our site!" ;
	        
	        try {
				EmailUtility.sendEmail(host, port, emailId, password, toAddress, subject, message);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        out.println("<script>");
            out.println("alert('Email sent successfully, Check your email');");
            out.println("</script>");
        	RequestDispatcher rs = request.getRequestDispatcher("register-confirmation.html");
            rs.include(request, response);
        
        } else {
        	out.println("<script>");
            out.println("alert('We do not have any account with provided email, Try again');");
            out.println("</script>");
        	RequestDispatcher rs = request.getRequestDispatcher("register-confirmation.html");
            rs.include(request, response);
        }
        
        
        }
}



