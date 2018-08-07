<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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

    <!-- Additional CSS -->

    <link rel="stylesheet" href="/css/styles.css"/>

    <!--title-->
    <title>صفحه اصلی</title>
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

        </ul>
        <form class="form-inline my-2 my-lg-0" dir="ltr" action="#">
            <input class="form-control mr-sm-2" type="text" placeholder="جستجو" aria-label="Search" dir="rtl"
                   name="firstname">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">جستجو</button>
        </form>
    </div>
</nav>
<div id="divparent" class="container" style="width: 100%;height: 600px;background-color: azure;padding-top: 0px ">
    <div id="divchild1" class="row" dir="rtl">
        <aside class="col-lg-4">
            <form style="text-align: right" action="/showCustomer" method="post">
                <label>کدمشتری</label><br/>
                <input type="text" name="cuid" value="${listcu.cuid}"><br>
                <label>نام</label><br/>
                <input type="text" name="firstname" value="${listcu.firstName}"><br/>
                <label>نام خانوادگی</label><br/>
                <input type="text" name="lastname" value="${listcu.lastName}"><br/>
                <label>آدرس</label><br/>
                <input type="text" name="addressname" value="${listcu.addressname}">
                <input type="submit" hidden value="مشتری">
            </form>

        </aside>
        <aside class="col-lg-8">
            <form>
                <label>حساب کل</label>
                <input type="text" name="finalRemain">
            </form>
            <br/>
            <div class="table-wrapper-scroll-y">
                <table id="tb" class="table table-bordered table-striped">
                    <tr>
                        <td>کدکالا</td>
                        <td>نام کالا</td>
                        <td>متراژ</td>
                        <td>عملیات</td>
                    </tr>
                    <c:forEach var="list" items="${prlists}">


                        <tr>
                            <form action="/savePr">
                                <td hidden><input type="hidden" name="id" value="${list.id}"></td>
                                <td><input type="text" name="prid" style="width: 60px" value="${list.prid}" readonly></td>
                                <td><input type="text" name="prname" style="width: 90px" value="${list.prName}"></td>
                                <td><input type="text" name="meter" style="width: 90px" value="${list.meter}"></td>
                                </td>
                                <td>
                                    <input type="button"  class="btn btn-primary" onclick="doo(${list.id}, 'delete')" value="delete">
                                    <input type="submit" id="editbtn" class="btn btn-primary" value="virayesh">
                                </td>
                            </form>
                        </tr>

                    </c:forEach>
                    <script>
                        function doo(id, operate) {

                            switch (operate) {
                                case 'delete':
                                    location.href = "/deletePr/" + id;
                                    break;
                            }
                        }


                    </script>


                </table>
            </div>
            <br/>

        </aside>
    </div>
    <div class="row" id="divchild2">

        <form style="text-align: right">
            <label>کدکالا</label>
            <input type="text" name="prid" size="7">
            <label>نام کالا</label>
            <input type="text" name="prname"><br/><br/>
            <label>تحویلی</label>
            <input type="text" name="exist" size="7">
            <label>موجودی</label>
            <input type="text" name="exist" size="7">
            <label></label>
            <input type="text" name="existfinal" size="7"><br/><br/>
            <label>قیمت واحد</label>
            <input type="text" name="price" size="7">
            <label>تخفیف</label>
            <input type="text" name="discount" size="7">
            <label>وجه </label>
            <input type="text" name="pay" size="7">
            <label>شماره فاکتور</label>
            <input type="text" name="factor" size="7">
            <label>مانده</label>
            <input type="text" name="remain" size="7">
            <input type="submit" class="btn btn-primary" value="ثبت">
        </form>
    </div>

    <div id="divchild3" class="row">
        <div class="table-wrapper-scroll-y">
            <table id="tbl1" class="table table-bordered table-striped">
                <tr>
                    <td>کدکالا</td>
                    <td>نام کالا</td>
                    <td>تحویلی</td>
                    <td>قیمت</td>
                    <td>تخفیف</td>
                    <td>وجه</td>
                    <td>شماره فاکتور</td>
                    <td>مانده</td>
                    <td>تاریخ</td>
                </tr>
                <tr>
                    <form action="/savePr">
                        <td hidden><input type="hidden" name="id" value="${list.id}"></td>
                        <td><input type="text" name="prid" style="width: 60px" value="${list.prid}" readonly></td>
                        <td><input type="text" name="prname" style="width: 90px" value="${list.prName}"></td>
                        <td><input type="text" name="meter" style="width: 90px" value="${list.meter}"></td>
                        <td><input type="text" name="meter" style="width: 90px" value="${list.meter}"></td>
                        <td><input type="text" name="meter" style="width: 90px" value="${list.meter}"></td>
                        <td><input type="text" name="meter" style="width: 90px" value="${list.meter}"></td>
                        <td><input type="text" name="meter" style="width: 90px" value="${list.meter}"></td>
                        <td><input type="text" name="meter" style="width: 90px" value="${list.meter}"></td>
                        <td><input type="text" name="meter" style="width: 90px" value="${list.meter}"></td>
                        <td>
                            <input type="button" class="btn btn-primary" onclick="doo(${list.id}, 'delete')"
                                   value="delete">
                            <input type="submit" id="editbtn" class="btn btn-primary" value="virayesh">
                        </td>
                    </form>
                </tr>
            </table>
        </div>

    </div>
</div>
<footer>
    <h1>will write here time and date</h1>
</footer>
</body>
</html>