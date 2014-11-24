<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<s:html title="Admin">
    <h2>
        Admin menu<br/></h2>

    <div class="col-sm-4">
        <div class="list-group">
            <a href="/admin/manage-currencies" class="list-group-item">manage currencies</a>
            <a href="/admin/manage-rates" class="list-group-item">manage rates</a>
            <a href="/admin/manage-accounts" class="list-group-item">manage accounts</a>
        </div>
    </div>

    <a href="/admin/logout">
        <button type="button" class="btn btn-xs btn-default">logout</button>
    </a><br>

</s:html>