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
            <h4 class="page-title">电影新增</h4>
            <div class="clearfix"></div>
        </div>
        <!--End Page Title-->

        <div class="row">
            <div class="col-md-12">
                <div class="white-box">

                    <form id="addMovieForm" class="form-horizontal" enctype="multipart/form-data" method = 'post'  action = '/movie/upload'>
                        <div class="login-group">
                            <label class="col-md-2 control-label" for="name">名称</label>
                            <div class="col-md-10">
                                <input id="name" name="name" class="form-control" placeholder="电影名称" type="text">
                            </div>
                        </div>

                        <br/><br/><br/><br/>

                        <div class="login-group">
                            <label class="col-md-2 control-label" for="name">图片</label>
                            <div class="col-md-10">
                                <input id="file" name="file" class="form-control" type="file">
                            </div>
                        </div>

                        <br/><br/><br/><br/>

                        <div class="login-group">
                            <label class="col-md-2 control-label">简介</label>
                            <div class="col-md-10">
                                <textarea id="remark" name="remark" class="form-control" rows="5"></textarea>
                            </div>
                        </div>

                        <div align="center">
                            <button type="submit" class="btn btn-success round">提交</button>
                            <button type="button" class="btn btn-warning round" onclick="javascript:history.go(-1)">返回</button>
                        </div>

                    </form>
                </div>
            </div>
        </div>
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

<script src="/assets/plugins/sweetalert/sweet-alert.js"></script>
<script src="/assets/pages/jquery.sweet-alert.custom.js"></script>

<!-- End core plugin -->
<script src="/js/plugin.js"></script>

<script type="text/javascript">


</script>

</body>

</html>
