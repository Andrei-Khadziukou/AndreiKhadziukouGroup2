<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<s:html title="Account">
    <h2 class="form-signin-heading">
        Hello,
        <c:out value="${user}"/>
        ! <br/>
        Your account name:
        <c:out value="${acc.name}"/>
        <br/>
        Your deposits:
    </h2>
    <table class="table table-striped">
        <c:forEach items="${acc.deposits}" var="dep">
            <tr>
                <td>
                    <c:out value="${dep.type}"/>
                </td>
                <td>
                    <c:out value="${dep.amount}"/>
                </td>
                <td>

                    <a href="/client/exchange?from=${dep.type}">
                        <button type="button" class="btn btn-xs btn-default">exchange</button>
                    </a>

                </td>
            </tr>
        </c:forEach>
    </table>

    <a href="/client/logout">
        <button type="button" class="btn btn-xs btn-default">logout</button>
    </a><br>

</s:html>