
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage = "ProblemOccured.jsp" %>
    <%@ page import = "com.pojo.CredInfo_internal" %>
    <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="Info.css">
    <title>Main</title>
    <link href="WEBSITE_LOGO.png" rel="icon" type="image/x-icon">
    <link rel="stylesheet" href="Info.css">
</head>
<body>
<%response.setHeader("Cache-Control", "no-cache , no-store , must-revalidate");
	response.setHeader("Pragma", "no-cache");
%>
<div class = "container">
    <div class="left-column">
        <div class="sidebar">
            <p>WHY WORRY WE ARE HERE</p>
            <form action="View_passwords" method="post">
                <button type="submit" class="btn btn-primary">VIEW MY PASSWORDS</button>
            </form>
            <form action="Delete_account" method="post">
                <button type="submit" class="btn">DELETE MY ACCOUNT</button>
            </form>
            <div class="logos">
                <img src="java_logo.png" alt="Java Logo" style="width: 80px; height: 110px;">
                <img src="hibernate_logo.png" alt="Hibernate Logo" style="width: 180px; height: 75px;">
                <img src="mysql.png" alt="Mysql_Logo" style="width: 100px; height: 75px;">
                <img src="JAKARTA.png" alt="Mysql_Logo" style="width: 100px; height: 75px;">
            </div>
        </div>
        </div>
        
        <div class="right-column">
       		<%response.setHeader("Cache-Control", "no-cache , no-store , must-revalidate");
    		response.setHeader("Expires", "0"); %>
    		<img src="JAVA_CUP_LOGO.png" alt="Mysql_Logo" style="width: 150px; height: 150px;">
            <h1>Hello...<!-- ${username} --></h1>
            <h2>I'm Abhishek</h2>
            <p>I am a Java Developer and this is my first work of my craft. I hope you like it. I would appreciate your feedback. I will keep developing new things and I hope you will be my team and help me out by testing my innovative ideas and motivating me through that.</p>
        	
        </div>
        
<div class="footer" style = "margin-bottom : 0px ;">
<div class="footerimg">
        <form action="Logout" method="post">
            <button type="submit" style="width: 120px; height: 60px;" >Log Out</button>
        </form>
        </div>
    </div>
    <div class="center">
        <a href="https://www.linkedin.com/in/abhishek-anand-60b529257/" target="_blank">
            <img src="linkedin_logo.png" alt="LinkedIn Logo" style="width: 60px; height: 60px;">
        </a>
    </div>
</div>
</body>
</html>
