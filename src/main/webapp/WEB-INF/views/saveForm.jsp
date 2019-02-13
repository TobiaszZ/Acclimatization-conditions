<%--
  Created by IntelliJ IDEA.
  User: tobiasz
  Date: 31.01.19
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>save</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

</head>
<body>
<div align="center">
    <h2>Save data to csv file form</h2>
    <h3>Set dates</h3>
    <form action="/saveSpecific">

        <div class="form-row" align="center" >
            <div class="form-group col-md-4" >
                <label for=""></label>
                <input type="date" class="form-control" id="" placeholder="Wpisz date" name="od" hidden disabled>
            </div>
            <div class="form-group col-md-2" >
                <label for="od">From:</label>
                <input type="date" class="form-control" id="od" placeholder="Wpisz date" name="od">
            </div>
            <div class="form-group col-md-2">
                <label for="do">To:</label>
                <input type="date" class="form-control" id="do" placeholder="Wpisz date" name="do">
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
    <ul class="nav justify-content-center">
        <li class="nav-item">
            <a class="nav-link" href="/">Home Page</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/showForm">Show Data</a>
        </li>
    </ul>

</div>









<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</body>
</html>
