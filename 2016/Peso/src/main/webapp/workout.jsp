<%-- 
    Document   : workout
    Created on : 10-oct-2016, 19:47:44
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

        <h1>Hola <c:out value="${nombre}"/>  </h1>
        
        <h1>peso actual es de <c:out value="${peso}"/>  </h1>
        
        <h1>segundos con lesion <c:out value="${lesionado}"/>  </h1>
    </body>
</html>
