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

    <link rel="stylesheet" href="/css/js-persian-cal.css">
    <script src="/css/js-persian-cal.min.js"></script>
    <script src="/css/js/NumberFormat.js"></script>

    <!-- Additional CSS -->
    <link rel="stylesheet" href="/css/css/bootstrap.css"/>
    <link rel="stylesheet" href="/css/css/bootstrap-rtl.css"/>
    <link rel="stylesheet" href="/css/css/bootstrap-rtl-theme.css"/>

    <!-- Additional CSS -->

    <link rel="stylesheet" href="/css/styles.css"/>

    <script src="/css/jquery/jquery-ui-min/jquery-ui-min.js"></script>
    <script src="/css/jquery/jquery-3.3.1.js"></script>
    <script src="/css/js/persian-date-convert.js"></script>

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
        
        function number_3_3 (num, sep){
            var number = typeof num === "number"? num.toString() : num,
                separator = typeof sep === "undefined"? ',' : sep;
            return number.replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1"+separator);

            document.getElementById().value=number_3_3(${salevalue});

        }


    </script>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
    <div id="div0">
        <h1>گزارشگیری</h1>
    </div>
    <div id="inputreport" dir="rtl" style="background-color: antiquewhite">
        <aside>
            <form style="text-align:right" action="${pageContext.request.contextPath}/rp/resultreport" method="post">
                از تاریخ:
                <input style="text-align: center" type="text" size="10" name="fromdate" id="pcal1" value="">
                تاتاریخ:
                <input style="text-align: center" type="text" size="10" name="todate" id="pcal2" value="">
                <input type="submit" value="جستجو">

            </form>
        </aside>
        <aside>
            <form>

                <input type="submit" value="جستجوی کالا">
            </form>
        </aside>
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
        <input readonly type="text" id="sum" onclick="this.value=number_3_3(this.value)" value="${salevalue}">


    </div>
    <br>
    <div>
        <form action="/rp/print_report">
            از<input style="text-align: center;margin-right: 20px" type="text" size="10" name="fromdate" id="pcal3" value="">
            تا<input style="text-align: center" type="text" size="10" name="todate" id="pcal4" value="">
            <input type="submit" value="چاپ گزارش">
        </form>
    </div>
</div>
<script>
    var objCal1 = new AMIB.persianCalendar( 'pcal1' );
    var objCal2 = new AMIB.persianCalendar( 'pcal2' );
    var objCal3 = new AMIB.persianCalendar( 'pcal3' );
    var objCal4 = new AMIB.persianCalendar( 'pcal4' );
</script>

</body>
</html>