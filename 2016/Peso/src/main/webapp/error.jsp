<%-- 
    Document   : error
    Created on : 10-oct-2016, 19:46:35
    Author     : oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <h1>ERROR</h1>
        <h1> <c:out value="${error}"/>  </h1>
      
    </body>
</html>