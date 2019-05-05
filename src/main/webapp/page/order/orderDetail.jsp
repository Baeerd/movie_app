<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
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
                <h4 class="page-title">订单详细信息</h4>
                <div class="clearfix"></div>
            </div>
            <!--End Page Title-->

            <!--body wrapper start-->
        <div class="row">
            <div class="col-md-12">
                <div class="white-box" align="center">

                    <!-- 影院详细信息 -->
                    <div class="col-md-4">
                        <div class="panel panel-color  panel-info">
                            <div class="panel-heading">
                                <h3 class="panel-title">${mainData.partName}</h3>
                            </div>
                            <div class="panel-body" align="center">
                                <img src="${mainData.partImage}" width="200" height="200">
                                <p/>
                                <p>
                                    ${mainData.partRemark}
                                </p>
                            </div>
                        </div>
                    </div>

                    <!-- 电影详细信息 -->
                    <div class="col-md-4">
                        <div class="panel panel-color  panel-info">
                            <div class="panel-heading">
                                <h3 class="panel-title">${mainData.movieName}</h3>
                            </div>
                            <div class="panel-body" align="center">
                                <img src="${mainData.movieImage}" width="200" height="200">
                                <p/>
                                <p>
                                    ${mainData.movieRemark}
                                </p>
                                <p id="moviePrice"></p>
                            </div>
                        </div>
                    </div>

                    <!-- 电影详细信息 -->
                    <div class="col-md-4">
                        <p>总额：${mainData.totalPrice}元(${mainData.price}元 x ${mainData.num}个)</p>
                        <p>座位号：${mainData.placeNo}</p>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">评价内容:</label>
                        <div class="col-sm-5">
                            <input id="comment" class="form-control m-b-15" type="text" />
                            <input id="commentRMovieId" type="hidden" value="${mainData.id}"/>
                        </div>
                        <button type="button" class="btn btn-info round" onclick="addComment();">
                            <span class="btn-label"><i class="fa fa-exclamation"></i></span>评价
                        </button>
                    </div>



                </div>
            </div>
        </div>

        <!--Start  Footer -->
        <footer class="footer-main"></footer>
         <!--End footer -->

       </div>


    <!--Begin core plugin -->
    <script src="/assets/js/jquery.min.js"></script>
    <script src="/assets/js/bootstrap.min.js"></script>
    <script src="/assets/plugins/moment/moment.js"></script>
    <script  src="/assets/js/jquery.slimscroll.js "></script>
    <script src="/assets/js/jquery.nicescroll.js"></script>
    <script src="/assets/js/functions.js"></script>
    <!-- End core plugin -->

    <script src="/assets/plugins/sweetalert/sweet-alert.js"></script>
    <script src="/assets/pages/jquery.sweet-alert.custom.js"></script>

    <script src="/js/plugin.js"></script>

    <script type="text/javascript">

        /**
         * 提交评论
         */
        function addComment() {
            var param = {};
            param['rMovieId'] = $("#commentRMovieId").val();
            param['content'] = $("#comment").val();
            $.post("/comment/addComment", param, function(data) {
                swal("评论成功!");
            });
        }

    </script>

</body>

</html>
