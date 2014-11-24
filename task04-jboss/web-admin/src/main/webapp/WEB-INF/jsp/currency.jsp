<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<s:html title="Currencies">
    <h2> Currencies</h2>

    <table class="table table-striped">
        <c:forEach items="${currs}" var="cur">
            <tr>
                <td>
                    <c:out value="${cur.id}"/>
                </td>
                <td><a href="/admin/manage-currencies-del?id=${cur.id}">
                    <button type="button" class="btn btn-xs btn-default">del</button>
                </a></td>
            </tr>
        </c:forEach>
    </table>


    <form action="/admin/manage-currencies" method="POST">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Adding new currency</h3>
            </div>
            <div class="panel-body">
                <div class="form-group">
                    <input type="text" name="curr" required="true" class="form-control"></div>

                <button class="btn btn-xs btn-default" type="submit"> add</button>

            </div>
        </div>
    </form>


    <a href="/admin">
        <button type="button" class="btn btn-xs btn-default">back</button>
    </a>
    <a href="/admin/logout">
        <button type="button" class="btn btn-xs btn-default">logout</button>
    </a><br>

</s:html>