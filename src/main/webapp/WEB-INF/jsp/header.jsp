<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
    <a class="navbar-brand" href="#">ATA</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault"
            aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarsExampleDefault" dir="rtl">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/">صفحه اصلی <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/cu/customerPage">مدیریت مشتری</a>
            </li>
            <li class="nav-item">
                <a class="nav-link " href="/pr/productionPage1">مدیریت کالا</a>
            </li>
            <li class="nav-item">
                <a class="nav-link " href="/rp/reportPage">گزارش گیری</a>
            </li>

        </ul>
        <form:form class="form-inline my-2 my-lg-0" dir="ltr" action="${pageContext.request.contextPath}/logout"
                   method="POST">

            <a href="/logout" style="margin-right: 15px;margin-top: 5px">خروج</a>
            <p style="color: white;margin-top: 14px">
                شما <security:authentication property="principal.username" /> هستید
            </p>

            <input type="text" id="tarikh" name="kaladate"
                   style="text-align: center;font-family: 'B Titr';margin-left: 20%;margin-top: 12px;Border-radius:15px 0px 15px 15px;" size="11">
        </form:form>

    </div>
</nav>