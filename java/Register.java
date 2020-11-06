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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean test = true;

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String nameF = request.getParameter("name-first");
		String nameL = request.getParameter("name-last");
		String email = request.getParameter("email");
		String uname = request.getParameter("uname");
		String pass = request.getParameter("psw");
		String phone = request.getParameter("phone");
		pass = "aes_encrypt('" + pass + "', '4050')";

		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "root", "4122");
			Statement st = con.createStatement();
			ResultSet rsE = st.executeQuery("SELECT * from Customer where email_address='" + email + "'");

			String duplicate = null;

			while (rsE.next()) {
				duplicate = rsE.getString(1);
			}

			if (duplicate == null) {

				st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT max(customer_id) FROM bookstore.customer;");
				rs.next();
				int cusId = rs.getInt(1) + 1;

				PreparedStatement ps = con
						.prepareStatement("insert into user (user_id, password, first_name, last_name, User_Type)\r\n"
								+ "	values ( ?, ?, ?, ?, 'C');");

				ps.setString(1, uname);
				ps.setString(2, pass);
				ps.setString(3, nameF);
				ps.setString(4, nameL);

				PreparedStatement psc = con.prepareStatement(
						"insert into Customer (customer_id, user_id, email_address, phone_number, status)\r\n"
								+ "	values (?, ?, ?, ?, 'A');");

				psc.setInt(1, cusId);
				psc.setString(2, uname);
				psc.setString(3, email);
				psc.setString(4, phone);

				ps.executeUpdate();
				psc.executeUpdate();

			} else {
				test = false;
				out.println("<script>");
				out.println(
						"alert('This email is already in the database; please try again with a different email address.');");
				out.println("</script>");
				RequestDispatcher rd = request.getRequestDispatcher("registration.html");
				rd.include(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (test = true) {
			String host = "smtp.gmail.com";
			String port = "587";
			String emailId = "bookstore9c@gmail.com";
			String password = "Bookpassword9C";
			String toAddress = email;
			String subject = "Thank You For Registering With Us!";
			String message = "Thank you for registering with our Bookstore. You now have access to our site!";

			try {
				EmailUtility.sendEmail(host, port, emailId, password, toAddress, subject, message);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			RequestDispatcher rd = request.getRequestDispatcher("register-confirmation.html");
			rd.include(request, response);
		}

	}
}
