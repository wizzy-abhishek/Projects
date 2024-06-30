<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage = "ProblemOccured.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="style.css">
<title>SIGNING OFF</title>
<link href="WEBSITE_LOGO.png" rel="icon" type="image/x-icon">
<style>
  .full-height {
    height: 100vh;
  }
  .form-container {
    margin-top: 10px;
  }
</style>
</head>
<body>

<div class="container d-flex flex-column justify-content-center align-items-center full-height">
  <div class="header text-center">
    <img src="WEBSITE_LOGO.png" alt="Website Logo" height="250" width="250" style= "margin: 10 auto;" >
  </div>
  
  <div class="d-flex flex-column justify-content-center align-items-center form-container">
    <div class="form-container text-center">
      <form action="Good_BYE" method="post">
        <h2>Signing OFF CAPTAIN </h2>  
        <p class="text-center">INDEED IT WAS A GREAT JOURNEY <br> I HOPE WE MEET AGAIN </p>

        <div class="mb-3">
          <input name="userId_final" type="text" class="form-control" id="email" placeholder="User ID" required style="width: 250px; margin: 0 auto;">
        </div>

        <div class="mb-3 position-relative">
          <input name="password_final" type="password" class="form-control" id="password" placeholder="Password" required style="width: 250px; margin: 0 auto;">
        </div>

        <button type="submit" class="btn btn-primary btn-sm mx-auto" style="width: 250px; height: 45px ; margin: 0 auto;"> SEE YAA BUDDY  </button>
        <br>
      </form>

      <div class="text-center mt-3">
        <br>
        <a href="https://www.linkedin.com/in/abhishek-anand-60b529257/" target="_blank">
          <img src="linkedin_logo.png" alt="LinkedIn Logo" height="45" width="45">
        </a>
      </div>
    </div>
  </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
