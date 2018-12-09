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
    <title>مدیریت کالا</title>
    <script type="text/javascript">
        window.onload = function () {
            tarikh();
        }

        function tarikh() {
            var now = new Date();
            var year = now.getFullYear();
            var month = now.getMonth() + 1;
            var day = now.getDate();

            document.getElementById('t1').value = gregorian_to_jalali(year, month, day);
            document.getElementById('tarikh').value = gregorian_to_jalali(year, month, day);


        }
    </script>
</head>
<body>
<%@include file="header.jsp" %>
<div id="div0" class="container">
    <div class="row">
        <div>
            <form:form action="/pr/resultPr" method="get">
                <input class="form-control mr-sm-2" type="text" placeholder="جستجوکالا" aria-label="Search" dir="rtl" name="names">
                <input type="submit" hidden>
            </form:form>
        </div>
        <div>
            <a href="/pr/productionPage"><img style="margin-right: 10px;margin-top: 8px" src="/open-iconic-master/png/reload.png"></a>
        </div>
    </div>
    <h1>مدیریت کالا</h1>
</div>
<div id="div1" class="container" class="row">
    <aside class="col-lg-4">
       <div id="sabeMoshtary">
           <form id="frm1" method="post" action="/pr/savePr1">
               <input type="hidden" name="id" value="">
               کدکالا:
               <br/>
               <input type="text" name="prid"/><br/><br>
               نام کالا:
               <br/>
               <input type="text" name="prName"/><br/><br>
               متراژ:
               <br/>
               <input type="text" name="meterPr"><br/><br>
               تاریخ:
               <br/>
               <input type="text" name="tarikh" id="t1" style="text-align: center"><br/><br>
               <input type="submit" value="ثبت" class="btn btn-primary">
           </form>
       </div>
    </aside>
    <aside class="col-lg-8">
        <div style="text-align: center">
            <label>کدخطای${exceptionMessage.errorCode}: ${exceptionMessage.errorMessage}</label>
        </div>
        <div class="table-wrapper-scroll-y">
            <table id="tbl1" class="table table-bordered table-striped">
                <tr>
                    <td>کدکالا</td>
                    <td>نام کالا</td>
                    <td>متراژ ورودی</td>
                    <td>مقدار موجود</td>
                    <td>تاریخ</td>
                    <td>عملیات</td>
                </tr>

                <c:forEach var="list" items="${prlists}" >


                    <tr>
                        <form action="/pr/savePr">
                            <td hidden><input type="hidden" name="id" value="${list.id}"></td>
                            <td><input type="text" name="prid" style="width: 60px" value="${list.prid}"></td>
                            <td><input type="text" name="prName" style="width: 90px" value="${list.prName}"></td>
                            <td><input type="text" name="meterPr" style="width: 90px" value="${list.meterPr}"></td>

                            <td><input type="text" value="${list.remainMeter}" size="7"></td>

                            <td><fmt:formatDate  value="${list.tarikh}" pattern="yyyy-MM-dd"/></td>

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
                                location.href = "/pr/deletePr/" + id;
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