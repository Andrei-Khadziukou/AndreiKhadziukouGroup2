<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<s:html title="Exchange form">
    <h2 class="form-signin-heading"> Hello,
        <c:out value="${user}"/>
        ! <br/>
        Your account name:
        <c:out value="${acc.name}"/>
        <br/>
    </h2>

    <form action="/client/exchange" method="POST" id="exform">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Exchange information</h3>
            </div>
            <div class="panel-body">

                <div class="form-group">
                    <label for="to">
                        Your deposit selected:
                        <c:out value="${from}"/>
                    </label>
                </div>
                <div class="form-group">
                    <label for="to">exchange to</label>
                    <select class="form-control" id="to" name="to" form="exform" required="true">
                        <c:forEach items="${currs}" var="cur">
                            <option>
                                <c:out value="${cur.id}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <input type="hidden" name="from" value="${from}">

                <div class="form-group">
                    <label for="summ">summ</label>
                    <input type="number" step="0.01" name="summ" id="summ" required="true" value="${maxsumm}"
                           max="${maxsumm}" min="0" class="form-control">
                </div>
                <div class="form-group">

                    <button class="btn btn-xs btn-default" type="submit"> exchange</button>

                </div>
            </div>
        </div>
    </form>

    Available exchange rates:
    <table class="table table-striped">
        <c:forEach items="${rates}" var="r">
            <tr>
                <td>
                    <c:out value="${r.id}"/>
                </td>
                <td>
                    <c:out value="${r.rate}"/>
                </td>
            </tr>
        </c:forEach>
    </table>


    <a href="/client">
        <button type="button" class="btn btn-xs btn-default">back</button>
    </a>
    <a href="/client/logout">
        <button type="button" class="btn btn-xs btn-default">logout</button>
    </a><br>

</s:html>