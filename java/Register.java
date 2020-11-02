import java.io.IOException; 
import java.io.PrintWriter; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement; 
  
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
        
        
        }
}



