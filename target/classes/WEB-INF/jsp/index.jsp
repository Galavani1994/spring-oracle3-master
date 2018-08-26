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

    <!-- Additional CSS -->

    <link rel="stylesheet" href="/css/styles.css"/>

    <script src="/css/jquery/jquery-ui-min/jquery-ui-min.js"></script>
    <script src="/css/jquery/jquery-3.3.1.js"></script>
    <script src="/css/js/persian-date-convert.js"></script>

    <!--title-->
    <title>صفحه اصلی</title>
    <script type="text/javascript">

        function sum() {
            document.getElementById('tb3').value = parseInt(document.getElementById('mcp').value) *
                parseInt(document.getElementById('price').value)
                - parseInt(document.getElementById('discount').value) - parseInt(document.getElementById('pay').value);
        }

        function minus() {

            document.getElementById('fex').value = parseInt(document.getElementById('mex').value) - parseInt(document.getElementById('mcp').value);
        }

    </script>
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
        <form class="form-inline my-2 my-lg-0" dir="ltr" method="post">
            <input class="form-control mr-sm-2" type="text" placeholder="جستجو" aria-label="Search" dir="rtl"
                   name="firstname">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">جستجو</button>
            <a href="/">refresh</a>
            <p> " " </p>
            <input type="text" id="tarikh" name="kaladate" style="text-align: center;font-family: 'B Titr'">
        </form>
    </div>
</nav>
<div id="divparent" class="container" style="width: 100%;height: 600px;background-color: azure;padding-top: 0px ">
    <%--this part is for showing customer information--%>
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
        <%--this part search production information--%>
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
                                <td><input type="text" name="prid" style="width: 60px" value="${list.prid}" readonly>
                                </td>
                                <td><input type="text" name="prname" style="width: 90px" value="${list.prName}"></td>
                                <td><input type="text" name="meterPr" style="width: 90px" value="${list.meterPr}"></td>
                                </td>
                                <td>
                                    <input type="button" class="btn btn-primary" onclick="doo(${list.id}, 'delete')"
                                           value="delete">
                                    <input type="submit" id="editbtn" class="btn btn-primary" value="virayesh">
                                </td>
                            </form>
                        </tr>

                    </c:forEach>
                </table>
            </div>
            <br/>
        </aside>
    </div>


    <%--this part is for register Cp table--%>

    <div class="row" id="divchild2">


        <form style="text-align: right" action="/showproduction" method="post">
            <input type="text" hidden name="cuid" value="${listcu.cuid}">
            <label>کدکالا</label>
            <input type="text" name="prid" size="7" value="${listpr.prid}">
            <label>نام کالا</label>
            <input type="text" name="prName" value="${listpr.prName}"><br/><br/>

            <input type="text" hidden name="meterPr" size="7">
            <label>موجودی</label>
            <input type="text" name="exist" value="${liremain}" size="7">

            <input type="text" hidden name="existfinal" size="7"><br/><br/>

            <input type="text" hidden name="price" size="7">

            <input type="text" hidden name="discount" size="7">

            <input type="text" hidden name="pay" size="7">

            <input type="text" hidden name="factore" size="7">

            <input type="text" hidden name="remain" size="7">
            <input type="submit" hidden class="btn btn-primary" value="ثبت">


        </form>
        <form action="/saveCP" method="post" style="text-align: right">


            <input type="text" hidden name="cuid" value="${listcu.cuid}">

            <input type="text" hidden name="id" size="7" value="${listpr.id}">

            <input type="text" hidden name="prid" size="7" value="${listpr.prid}">

            <input type="text" hidden name="prName" value="${listpr.prName}"><br/><br/>

            <label>تحویلی</label>
            <input type="text" name="metercp" id="mcp" onkeyup="minus();sum();" size="7" value="0">
            <input type="text" hidden name="exist" id="mex" onkeyup="minus()" value="${listpr.meterPr}" size="7">
            <input type="text" id="tarikh1" size="11" name="kaladate"><br/><br/>
            <label>قیمت واحد</label>
            <input type="text" id="price" onkeyup="sum()" name="price" size="7" value="0">
            <label>تخفیف</label>
            <input type="text" name="discount" id="discount" onkeyup="sum()" value="0" size="7">
            <label>وجه </label>
            <input type="text" name="pay" id="pay" onkeyup="sum()" value="0" size="7">
            <label>شماره فاکتور</label>
            <input type="text" name="factore" size="7">
            <label>مانده</label>
            <input type="text" name="remain" id="tb3" size="7" value="0">
            <input type="submit" value="ذخیره">
        </form>
    </div>


    <%-- this part shows the table of CPtable--%>

    <div id="divchild3" class="row">
        <div class="table-wrapper-scroll-y" style="width: 100%;height: 180px;">
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
                <c:forEach var="li" items="${listbycu}">
                    <tr>
                        <form>
                            <td><input type="text" name="id" size="5" value="${li.prid}"></td>
                            <td><input type="text" name="prid" size="10" value="${li.prName}" readonly></td>
                            <td><input type="text" name="prname" size="5" value="${li.metercp}"></td>
                            <td><input type="text" name="metercp" size="11" value="${li.price}"></td>
                            <td><input type="text" name="metercp" size="11" value="${li.discount}"></td>
                            <td><input type="text" name="metercp" size="11" value="${li.pay}"></td>
                            <td><input type="text" name="metercp" size="5" value="${li.factore}"></td>
                            <td><input type="text" name="metercp" size="11" value="${li.remain}"></td>
                            <td><fmt:formatDate  value="${li.kaladate}" pattern="yyyy-MM-dd"/> </td>
                            <td><input type="text" name="metercp" style="width: 90px" value="-"></td>

                        </form>
                    </tr>
                </c:forEach>
            </table>
        </div>

    </div>
</div>
</body>
</html>