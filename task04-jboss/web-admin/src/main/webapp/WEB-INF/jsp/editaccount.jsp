<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<s:html title="Creating/updating account">
    <h2> Creating/updating account</h2>


    <form action="/admin/manage-accounts-edit" method="POST">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Please, fill the form</h3>
            </div>
            <div class="panel-body">
                <input type="hidden" name="id" value="${acc.id}">

                <div class="form-group">
                    <label for="passp">passport</label>
                    <input type="text" name="passp" id="passp" value="${acc.passp}" class="form-control"><br/>

                    <label for="user">user login</label>
                    <input type="text" name="user" id="user" value="${acc.userId}" class="form-control"><br/>

                    <label for="name">account name</label>
                    <input type="text" name="name" id="name" value="${acc.name}" class="form-control"><br/>

                    <label for="address">address</label>
                    <input type="text" name="address" id="address" value="${acc.address}" class="form-control"><br/>

                    <label for="phone">phone</label>
                    <input type="text" name="phone" id="phone" value="${acc.phone}" class="form-control"><br/>
                </div>
                <button class="btn btn-xs btn-default" type="submit"> submit</button>

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