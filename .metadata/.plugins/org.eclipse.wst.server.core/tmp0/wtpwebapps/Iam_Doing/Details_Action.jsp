<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage = "ProblemOccured.jsp"%>
<%@ page import="java.util.List" %>
<%@ page import="com.pojo.RealInfo" %>
<%@ page import="com.encrption_decryption.EncryptDecrypt"  %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="GeneralDegine.css">
    <title>VIEW DETAILS </title>
    <link href="WEBSITE_LOGO.png" rel="icon" type="image/x-icon">
</head>
<body>
<%response.setHeader("Cache-Control", "no-cache , no-store , must-revalidate");
	response.setHeader("Pragma", "no-cache");
%>
<nav class="navbar navbar-expand-lg navbar-light bg-light w-100">
  <div class="container-fluid">
    <a class="navbar-brand">VIEW PAGE </a>
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

<div class="centre">
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


<script>
    // Sample data to demonstrate dynamic row addition
    const data = [
       
    ];

    const tableBody = document.getElementById('table-body');

    data.forEach(rowData => {
        const row = document.createElement('tr');
        rowData.forEach(cellData => {
            const cell = document.createElement('td');
            cell.textContent = cellData;
            row.appendChild(cell);
        });
        tableBody.appendChild(row);
    });
</script>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
