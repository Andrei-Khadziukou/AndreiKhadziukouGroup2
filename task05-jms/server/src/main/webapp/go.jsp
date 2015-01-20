<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
Publish message
<form method="POST" action="publish">
    <label>Topic</label> <input type="text" name="topic" value="${last}"><br/>
    <label>Message</label> <input type="text" name="message"><br/>
    <input class="button" type="submit"/>
</form>
<hr/>
<c:if test="${respCode eq 200}">
    Message sent.
</c:if>
<c:if test="${respCode eq 500}">
    <c:out value="${respMess}"/>
</c:if>
</body>
</html>
