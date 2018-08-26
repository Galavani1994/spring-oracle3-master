<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">


    <!-- Bootstrap CSS -->
    <%--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">--%>

    <link rel="stylesheet" href="/css/css/bootstrap.css"/>
    <link rel="stylesheet" href="/css/css/bootstrap-rtl.css"/>
    <link rel="stylesheet" href="/css/css/bootstrap-rtl-theme.css"/>
    <script src="/css/js/persian-date-convert.js"></script>


    <!-- Additional CSS -->

    <link rel="stylesheet" href="/css/styles.css"/>

    <!--title-->
    <title>گزارشگیری</title>
    <script type="text/javascript">
        window.onload = function () {
            tarikh();
        }

        function tarikh() {
            var now = new Date();
            var year = now.getFullYear();
            var month = now.getMonth() + 1;
            var day = now.getDate();

            document.getElementById('tarikh').value = gregorian_to_jalali(year, month, day);
            document.getElementById('tarikh1').value = gregorian_to_jalali(year, month, day);
        }
    </script>
</head>
<body>
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
                <a class="nav-link" href="/customerPage">مدیریت مشتری</a>
            </li>
            <li class="nav-item">
                <a class="nav-link " href="/productionPage">مدیریت کالا</a>
            </li>
            <li class="nav-item">
                <a class="nav-link " href="/reportPage">گزارش گیری</a>
            </li>

        </ul>
        <form class="form-inline my-2 my-lg-0" dir="ltr" action="/resultPr">
            <input class="form-control mr-sm-2" type="text" placeholder="جستجو" aria-label="Search" dir="rtl"
                   name="names">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">جستجو</button>
            <a href="/reportPage">Refresh</a>
            <p> " " </p>
            <input type="text" id="tarikh" name="kaladate" style="text-align: center;font-family: 'B Titr'">
        </form>
    </div>
</nav>
<div class="container">
    <div id="div0">
        <h1>گزارشگیری</h1>
    </div>
    <div id="inputreport" dir="rtl" style="background-color: antiquewhite">
        <form style="text-align:right" action="/resultreport" method="post">
            از تاریخ:
            <input style="text-align: center" type="text" size="10" name="fromdate" value="">
            تاتاریخ:
            <input style="text-align: center" type="text" size="10" name="todate" value="">
            <input type="submit" value="جستجو">

        </form>
    </div>

    <div class="table-wrapper-scroll-y" style="width: 100%;height: 180px;">
        <table id="tbl1" class="table table-bordered table-striped">
            <tr>
                <td>کدمشتری</td>
                <td>کدکالا</td>
                <td>نام کالا</td>
                <td>تحویلی</td>
                <td>وجه</td>
                <td>تاریخ</td>
            </tr>
            <c:forEach var="li" items="${listcpbydate}">
                <tr>
                    <form>
                        <td><input type="text" name="prid" size="5" value="${li.cuid}" readonly></td>
                        <td><input type="text" name="prname" size="5" value="${li.prid}"></td>
                        <td><input type="text" name="metercp" size="12" value="${li.prName}"></td>
                        <td><input type="text" name="metercp" size="5" value="${li.metercp}"></td>
                        <td><input type="text" name="metercp" size="10" value="${li.pay}"></td>
                        <td><fmt:formatDate value="${li.kaladate}" pattern="yyyy/MM/dd"/></td>
                        <td><input type="text" name="metercp" value="-"></td>

                    </form>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div>
        جمع فروش
        <input readonly type="text" value="${salevalue}">
    </div>
</div>

</body>
</html>