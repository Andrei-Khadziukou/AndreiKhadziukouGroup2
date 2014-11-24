<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<s:html title="Exchange rates">
    <h2> Exchange rates</h2>


    <table class="table table-striped">
        <c:forEach items="${rates}" var="r">
            <tr>
                <td>
                    <c:out value="${r.id}"/>
                </td>
                <td>
                    <form action="/admin/manage-rates-edit" method="POST">
                        <input type="hidden" name="id" value="${r.id}">
                        <input type="number" step="0.01" name="rate" value="${r.rate}" required="true">
                        <button class="btn btn-xs btn-default" type="submit"> change</button>
                    </form>
                </td>
                <td><a href="/admin/manage-rates-edit?id=${r.id}">
                    <button type="button" class="btn btn-xs btn-default">del</button>
                </a></td>
            </tr>
        </c:forEach>
    </table>


    <form action="/admin/manage-rates" method="POST" id="addform">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Adding new currency</h3>
            </div>
            <div class="panel-body">
                <div class="form-group">
                    <label for="from">from</label>
                    <select id="from" name="from" form="addform" class="form-control">
                        <c:forEach items="${currs}" var="cur">
                            <option>
                                <c:out value="${cur.id}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="to">to</label>
                    <select id="to" name="to" form="addform" class="form-control">
                        <c:forEach items="${currs}" var="cur">
                            <option>
                                <c:out value="${cur.id}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="rate">rate</label>
                    <input type="number" step="0.01" name="rate" id="rate" required="true" class="form-control">
                </div>
                <button class="btn btn-xs btn-default" type="submit"> add new</button>
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