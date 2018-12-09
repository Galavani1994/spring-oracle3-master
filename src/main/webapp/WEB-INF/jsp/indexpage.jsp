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


    <link rel="icon" href="/favicon-icon-13.jpg">
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

        function moneyCommaSep(ctrl) {
            var separator = ",";
            var int = ctrl.value.replace(new RegExp(separator, "g"), "");
            var regexp = new RegExp("\\B(\\d{3})(" + separator + "|$)");
            do {
                int = int.replace(regexp, separator + "$1");
            }
            while (int.search(regexp) >= 0)
            ctrl.value = int;
        }
    </script>

</head>
<body>
<%@include file="header.jsp" %>

<div id="divparent" class="container" style="width: 100%;height: 600px;background-color: azure;padding-top: 0px ">
    <%--this part is for showing customer information--%>
    <div id="divchild1" class="row" dir="rtl">
        <aside class="col-lg-4">
            <form style="text-align: right" action="/cp/showCustomer" method="post">
                <label style="margin-left: 5%">کدمشتری:</label>
                <input type="text" name="cuid" value="${listcu.cuid}" style="margin-bottom: 20px"><br>
                <label style="margin-left: 13%">نام:</label>
                <input type="text" name="firstname" value="${listcu.firstName}" style="margin-bottom: 20px"><br/>
                <label>نام خانوادگی:</label>
                <input type="text" name="lastname" value="${listcu.lastName}" style="margin-bottom: 20px"><br/>
                <label style="margin-left: 8%">آدرس:</label>
                <input type="text" name="addressname" value="${listcu.addressname}" style="margin-bottom: 20px"><br>
                <label>مانده حساب کل:</label>
                <input type="text" name="finalRemain" value="${listcu.mande}" size="17" style="margin-bottom: 20px">
                <input type="submit" hidden value="مشتری">
            </form>
        </aside>
        <aside class="col-lg-3">
            <form style="text-align: right" action="/cp/showproduction" method="post">
                <input type="text" hidden name="cuid" value="${listcu.cuid}">
                <label>کدکالا</label>
                <input type="text" name="prid" size="7" value="${listpr.prid}" style="margin-bottom: 20px"><br>
                <label>نام کالا</label>
                <input type="text" name="prName" value="${listpr.prName}" style="margin-bottom: 20px"><br>
                <label>موجودی</label>
                <input type="text" name="exist" value="${liremain}" size="7" style="margin-bottom: 20px"><br>
                <input type="text" hidden name="meterPr" size="7">
                <input type="text" hidden name="existfinal" size="7"><br/><br/>
                <input type="text" hidden name="price" size="7">
                <input type="text" hidden name="discount" size="7">

                <input type="text" hidden name="pay" size="7">

                <input type="text" hidden name="factore" size="7">

                <input type="text" hidden name="remain" size="7">
                <input type="submit" hidden class="btn btn-primary" value="ثبت">
            </form>
        </aside>
        <aside class="col-lg-5">
            <form action="/cp/saveCP" method="post" style="text-align: right">


                <label>تحویلی</label>
                <input type="text" name="metercp" id="mcp" onkeyup="minus();sum();" size="7" value="${edit.metercp}"
                       style="margin-bottom: 20px">
                <label>قیمت واحد</label>
                <input type="text" id="price" onkeyup="sum();" name="price" value="${edit.price}"
                       style="margin-bottom: 20px"><br>
                <label>تخفیف</label>
                <input type="text" name="discount" id="discount" onkeyup="sum();" value="${edit.discount}" size="12"
                       style="margin-bottom: 20px">
                <label>وجه </label>
                <input type="text" name="pay" id="pay" onkeyup="sum();" value="${edit.pay}" size="12"
                       style="margin-bottom: 20px">
                <label>شماره فاکتور</label>
                <input type="text" name="factore" size="7" value="${edit.factore}" style="margin-bottom: 20px"><br>
                <label>مانده</label>
                <input type="text" name="remain" id="tb3" size="12" value="${edit.remain}" style="margin-right: 10px">
                <label style="margin-right: 33%">تاریخ</label>
                <input type="text" id="tarikh1" size="11" name="kaladate" style="text-align: center"
                       value="${edit.kaladate}"><br/>
                <input type="text" hidden name="cuid" value="${listcu.cuid}">
                <%--<input type="text" hidden name="id" size="7" value="${listpr.id}">--%>
                <input type="text" hidden name="prid" size="7" value="${listpr.prid}">
                <input type="text" hidden name="prName" value="${listpr.prName}"><br/><br/>
                <input type="text" name="id" hidden value="${edit.id}">
                <input type="text" hidden name="exist" id="mex" onkeyup="minus()" value="${listpr.meterPr}" size="7">
                <input type="submit" value="ذخیره" class="btn btn-done" style="margin-right: 50%">
            </form>


        </aside>

    </div>

    <%--this part is for register Cp table--%>

    <div class="row" id="divchild2">

        <div class="table-wrapper-scroll-y" style="width: 100%;height: 280px;">
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
                        <form action="/cp/editcp/" method="post">
                            <input type="text" name="id" hidden value="${li.id}">
                            <input type="text" name="cuid" hidden value="${listcu.cuid}">
                            <td><input type="text" name="prid" size="5" value="${li.prid}"></td>
                            <td><input type="text" name="prName" size="10" value="${li.prName}" readonly></td>
                            <td><input type="text" name="metercp" size="5" value="${li.metercp}"></td>
                            <td><input type="text" name="price" size="11" value="${li.price}"></td>
                            <td><input type="text" name="discount" size="11" value="${li.discount}"></td>
                            <td><input type="text" name="pay" size="11" value="${li.pay}"></td>
                            <td><input type="text" name="factore" size="5" value="${li.factore}"></td>
                            <td><input type="text" name="remain" size="11" value="${li.remain}"></td>
                            <td><fmt:formatDate value="${li.kaladate}" pattern="yyyy-MM-dd"/></td>
                            <td>
                                <input type="submit" value="ویرایش">
                                <input type="button" value="حذف" onclick="del(${li.id})">
                            </td>
                        </form>
                    </tr>
                </c:forEach>
                <script>
                    function del(id) {
                        location.href = "/cp/deletCP/" + id;
                    }
                </script>
            </table>
        </div>
    </div>

</div>
</body>
</html>