<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <script src="/css/js/persian-date-convert.js"></script>

    <!-- Additional CSS -->

    <link rel="stylesheet" href="/css/styles.css"/>

    <!--title-->
    <title>مدیریت مشتری</title>
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
<%@include file="header.jsp" %>
<div id="div0" class="container">
    <div class="row">
        <div>
            <form:form action="/cu/resultCu" method="get">
                <input class="form-control mr-sm-2" type="text" placeholder="جستجو مشتری" aria-label="Search" dir="rtl" name="names">
                <input type="submit" hidden>
            </form:form>
        </div>
        <div>
            <a href="/cu/customerPage"><img style="margin-right: 10px;margin-top: 8px" src="/open-iconic-master/png/reload.png"></a>
        </div>
    </div>
    <h1>مدیریت مشتری</h1>
</div>
<div id="div1" class="container" class="row">
    <aside class="col-lg-4">
        <form id="frm1" method="post" action="/cu/saveCu">
            <input type="hidden" name="id" value="">
            کدمشتری:
            <br/>
            <input type="text" name="cuid"/><br/><br>
            نام:
            <br/>
            <input type="text" name="firstName"/><br/><br>
            نام خانوادگی:
            <br/>
            <input type="text" name="lastName"><br/><br>
            آدرس:
            <br/>
            <input type="text" name="addressname"><br/>
            <input type="text" hidden name="mande" value="0"><br/>
            <input type="submit" value="ثبت" class="btn btn-primary">
        </form>
    </aside>

    <aside class="col-lg-8">


        <div class="table-wrapper-scroll-y">
            <table id="tbl1" class="table table-bordered table-striped">
                <tr>
                    <td>کد مشتری</td>
                    <td>نام</td>
                    <td>نام خانوادگی</td>
                    <td>آدرس</td>
                    <td>مانده حساب</td>
                    <td>عملیات</td>
                </tr>
                <c:forEach var="list" items="${lists}">

                    <tr>
                        <form action="/cu/saveCu">
                            <td hidden><input type="hidden" name="id" value="${list.id}"></td>
                            <td><input type="text" name="cuid" style="width: 60px" value="${list.cuid}"></td>
                            <td><input type="text" name="firstName" style="width: 90px" value="${list.firstName}"></td>
                            <td><input type="text" name="lastName" style="width: 90px" value="${list.lastName}"></td>
                            <td><input type="text" name="addressname" style="width: 120px" value="${list.addressname}">
                            </td>
                            <td><input type="text" name="mande" size="9" value="${list.mande}"></td>
                            <td>
                                <input type="button" class="btn btn-primary" onclick="doo(${list.id}, 'delete')"
                                       value="delete">
                                <input type="submit" id="editbtn" class="btn btn-primary" value="virayesh">
                            </td>
                        </form>
                    </tr>

                </c:forEach>
                <script>
                    function doo(id, operate) {

                        switch (operate) {
                            case 'delete':
                                location.href = "/cu/deleteCu/" + id;
                                break;
                        }
                    }


                </script>


            </table>
        </div>
    </aside>
</div>
</body>
</html>