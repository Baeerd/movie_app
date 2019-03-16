<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/assets/images/favicon.png" type="image/png">
    <title></title>
    <link href="/assets/css/icons.css" rel="stylesheet">
    <link href="/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="/assets/css/style.css" rel="stylesheet">
    <link href="/assets/css/responsive.css" rel="stylesheet">

</head>

<body class="sticky-header">

    <!-- 菜单 -->
    <%@include file="../menu.jsp"%>

<!-- main content start-->
<div class="main-content" >

    <!-- 头部 -->
    <%@include file="../top.jsp"%>

    <!--body wrapper start-->
    <div class="wrapper">

        <!--Start Page Title-->
        <div class="page-title-box">
            <h4 class="page-title">电影浏览</h4>
            <div class="clearfix"></div>
        </div>
        <!--End Page Title-->
        <!-- 分页 -->
        <%@include file="../pageInfo.jsp"%>

        <!--Start row-->
        <div class="row">
            <div class="panel-wrap">

                <c:forEach items="${page.list}" var="movie">

                    <div class="col-md-4">
                        <div class="panel panel-color  panel-info">
                            <div class="panel-heading">
                                <h3 class="panel-title">${movie.name}</h3>
                            </div>
                            <div class="panel-body" align="center">
                                <img src="${movie.image}" width="200" height="200">
                                <p/>
                                <p>
                                    ${movie.remark}
                                </p>
                            </div>
                        </div>
                    </div>

                </c:forEach>

            </div>
        </div>
        <!--End row-->

    </div>
    <!-- End Wrapper-->


    <!--Start  Footer -->
    <footer class="footer-main">Copyright &copy; 2018.Company name All rights reserved.</footer>
    <!--End footer -->

</div>
<!--End main content -->



<!--Begin core plugin -->
<script src="/assets/js/jquery.min.js"></script>
<script src="/assets/js/bootstrap.min.js"></script>
<script src="/assets/plugins/moment/moment.js"></script>
<script  src="/assets/js/jquery.slimscroll.js "></script>
<script src="/assets/js/jquery.nicescroll.js"></script>
<script src="/assets/js/functions.js"></script>

<!-- End core plugin -->
<script src="/js/plugin.js"></script>


</body>

</html>
