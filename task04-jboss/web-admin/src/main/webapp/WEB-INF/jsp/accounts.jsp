<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<s:html title="Accounts">
    <h2> Accounts</h2>

    <table class="table table-striped">
        <tr>
            <th>Passport</th>
            <th>user</th>
            <th>acc name</th>
            <th>phone</th>
            <th>address</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach items="${accs}" var="acc">
            <tr>
                <td>
                    <c:out value="${acc.passp}"/>
                </td>
                <td>
                    <c:out value="${acc.userId}"/>
                </td>
                <td>
                    <c:out value="${acc.name}"/>
                </td>
                <td>
                    <c:out value="${acc.phone}"/>
                </td>
                <td>
                    <c:out value="${acc.address}"/>
                </td>
                <td>
                    <form action="/admin/manage-accounts" method="POST">
                        <input type="hidden" name="id" value="${acc.id}">
                        <button class="btn btn-xs btn-default" type="submit">edit</button>
                    </form>
                </td>
                <td><a href="/admin/manage-accounts-edit?id=${acc.id}">
                    <button type="button" class="btn btn-xs btn-default">del</button>
                </a></td>
            </tr>
        </c:forEach>
    </table>
    <hr/>

    <form action="/admin/manage-accounts" method="POST">
        <button class="btn btn-xs btn-default" type="submit">add new</button>
    </form>

    <hr/>

    <a href="/admin">
        <button type="button" class="btn btn-xs btn-default">back</button>
    </a>
    <a href="/admin/logout">
        <button type="button" class="btn btn-xs btn-default">logout</button>
    </a><br>

</s:html>