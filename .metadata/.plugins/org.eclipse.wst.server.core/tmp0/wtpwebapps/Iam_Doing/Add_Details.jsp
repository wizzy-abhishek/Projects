<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage = "ProblemOccured.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pojo.RealInfo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.encrption_decryption.EncryptDecrypt"  %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="GeneralDegine.css">
    <title>ADD DETAILS</title>
    <link href="WEBSITE_LOGO.png" rel="icon" type="image/x-icon">
    <style>
       
    </style>
</head>
<body>
<%response.setHeader("Cache-Control", "no-cache , no-store , must-revalidate");
	response.setHeader("Pragma", "no-cache");
%>
<nav class="navbar navbar-expand-lg navbar-light bg-light w-100">
  <div class="container-fluid">
    <a class="navbar-brand">ADD PAGE</a>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav mx-auto">
        <li class="nav-item">
          <form action="LoggedIn" method="post">
            <button type="submit" class="btn nav-link active" aria-current="page">Home</button>
          </form>
        </li>
        <li class="nav-item">
          <form action="Add_Details" method="post">
            <button type="submit" class="btn nav-link">ADD</button>
          </form>
        </li>
        <li class="nav-item">
          <form action="Deleting" method="post">
            <button type="submit" class="btn nav-link">DELETE</button>
          </form>
        </li>
        <li class="nav-item">
          <form action="Modifying" method="post">
            <button type="submit" class="btn nav-link">MODIFY</button>
          </form>
        </li>
        <li class="nav-item">
          <form action="Logout" method="post">
            <button type="submit" class="btn nav-link logout-btn">LOG OUT</button>
          </form>
        </li>
      </ul>
    </div>
  </div>
</nav>

<div class="left-column">
    <div class="header text-center">
    <img src="Buffalo.png" alt="Image" height="300" width="300" >
    </div>
    
    
    <form action="Complete_ADD" method="post">
        <div class="form-group">
            <input type="text" name="Domain" placeholder=" &nbsp Domain like Insta/FB" required style="width: 310px; height: 45px; margin: 0 auto;">
        </div>
        <div class="form-group">
            <input type="text" name="UserId_of_Domain" placeholder=" &nbsp USER_ID like ab@12" required  style="width: 310px; height: 45px; margin: 0 auto;">
        </div>
        <div class="form-group">
            <input type="text" name="Password_of_User_ID" placeholder=" &nbsp PASSWORD OF THE ID" required  style="width: 310px; height: 45px; margin: 0 auto;">
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-sm"  style="width: 310px; height: 45px; margin: 0 auto;">ADD</button>
        </div>
    </form>
</div>
<hr>

<div class="right-column">
    <div class="scrollable-table">
        <table>
            <thead>
                <tr>
                    <th>Provided No.</th>
                    <th>Domain</th>
                    <th>ID of the Domain</th>
                    <th>PASS WORD</th>
                    <th>DATE and TIME</th>
                </tr>
            </thead>
            <tbody id="table-body">
                <%
                    EncryptDecrypt end = new EncryptDecrypt();
                    List<RealInfo> realInfoList = (List<RealInfo>) session.getAttribute("realInfoList");
                    if (realInfoList != null) {
                        int providedNo = 1;
                        for (RealInfo info : realInfoList) {
                            String decryptedPasscode = end.decrypt(info.getPassword());
                            %>
                            <tr>
                                <td><%= info.getId() %></td>
                                <td><%= info.getDomain() %></td>
                                <td><%= info.getUserID() %></td>
                                <td><%= decryptedPasscode %></td>
                                <td><%= info.getAddDate() %></td>
                            </tr>
                            <%
                        }
                    } else {
                        %>
                        <tr>
                            <td colspan="5">No data available</td>
                        </tr>
                        <%
                    }
                %>
            </tbody>
        </table>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
