<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width">
  <meta name="description" content="Online Bookstore">
  <meta name="keywords" content="bookstore, shopping, ecommerce, books, shop">
  <meta name="author" content="Nidesh">
  <title>BookStore | Home</title>
  <link rel="stylesheet" href="./css/stylesheet.css">
  <script src="./js/scritps.js"></script>
</head>

<body>
  <header>
    <div class="container">
      <div id="branding">
        <h1><span class="highlight">BookStore</span> Online</h1>
      </div>
      <nav>
        <ul>
          <li><a href="index.html">Home</a></li>
          <li><a href="login.html">Login</a></li>
          <li class="current"><a href="register.jsp">Register</a></li>
          <li><a href="shoppingcart.html">Shopping Cart</a></li>
          <li><a href="profile.html">Profile</a></li>
          <li><input type="text" placeholder="Search..."></li>
          <li><button type="submit" class="search_button" onclick="window.location.href='search.html';">Search</button></li>  
        </ul>
      </nav>
    </div>
  </header>

  <section id="sec-reg-log">
    <div class="container">
      <main id="main-reg-log">
        <form action="java/Register" method="post">
      <article id="art-reg-log">
        <h1 class="page-title">Register</h1>

        <p>Please fill in this form to create an account. All fields are required. </p>
        <hr>
        <label for="name" class="reg-label"> <b>Name</b></label>
        <input type="text" class="name" placeholder="First Name *" name="name-first" id="name-first" required>
        <input type="text" class="name" placeholder="Last Name *" name="name-last" id="name-last" required>

        <label for="email" class="reg-label"><b>Email</b></label>
        <input type="text" placeholder="Enter Email *" name="email" id="email" required>

        <label for="phone" class="reg-label"><b>Phone Number</b></label>
        <input type="text" placeholder="Enter Phone Number (XXX-XXX-XXXX) *" name="phone" id="phone" required>

        <label for="psw" class="reg-label"><b>Password</b></label>
        <input type="password" placeholder="Enter Password *" name="psw" id="psw" required>

        <label for="psw-repeat" class="reg-label"><b>Repeat Password</b></label>
        <input type="password" placeholder="Repeat Password *" name="psw-repeat" id="psw-repeat" required>
        <hr>

        <p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>
        <p>Already have an account? <a href="login.html">Sign In</a>.</p>
        <button type="submit" class="submitbtn">Register</button>
        
      </article>
    </form>
    </main>

    </div>
  </section>

  <footer>
    <p>CSCI4050 Team 9, Copyright &copy; 2020</p>
  </footer>
</body>

</html>
