<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tobiasz
  Date: 26.01.19
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ShowAll</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

</head>
<body>


<ul class="nav justify-content-center">
    <li class="nav-item">
        <a class="nav-link" href="/">Home Page</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="/showForm">Show Data</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="/saveForm">Save Data</a>
    </li>
</ul>

<div align="center">
    <%
        if (request.getParameter("od") != null) {
            out.println("Viewing data from "+request. getParameter("od")+ " to "+request.getParameter("do"));
        }
    %>
</div>

<p align="center">
    <option value="1" ${param.from} ==  null  ? ' Data from ${param.from} to ${param.to}' : ''}></option>
</p>

<table  border="1" class="table table-hover table-sm"  >
    <tr bgcolor="#7fffd4">
        <td>Lp.</td>
        <td>Temperatura</td>
        <td>Ciśnienie</td>
        <td>Wilgotność</td>
        <td>Data</td>
    </tr>
    <c:forEach var="data" items="${dataList}" varStatus="count">
        <tr>
            <td>${count.index+1}</td>
            <td>${data.temperature}</td>
            <td>${data.pressure}</td>
            <td>${data.humidity}</td>
            <td><fmt:formatDate value="${data.date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
        </tr>
    </c:forEach>
</table>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</body>
</html>
