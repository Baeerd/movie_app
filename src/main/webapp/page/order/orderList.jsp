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
                    <h4 class="page-title">订单列表</h4>
                    <div class="clearfix"></div>
                 </div>
                  <!--End Page Title-->          
           
                 <!--Start row-->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="white-box">
                             
                            <div class="search-box-top">
                              <form action="/order/orderList">
                                  <input type="hidden" name="pageNum"/>
                                  <input type="hidden" name="pageSize"/>
                                <div class="input-group">
                                    <input name="orderSearch" class="form-control input-search" placeholder="搜索..." type="text" value="${orderSearch}">
                                  <span class="input-group-btn">
                                  <button class="btn btn-primary" type="submit"><i class="fa fa-search"></i></button>
                                  </span> </div>
                              </form>
                            </div>

                             <c:forEach items="${page.list}" var="order">
                                 <div class="search-item">
                                     <h3><a href="javascript:void(0);"><img src="${order.image}" width="50" height="50"/>&nbsp;&nbsp;${order.movieName}&nbsp;&nbsp;（影院：${order.partName}）</a></h3>
                                     <a class="search-link" href="javascript:void(0);">
                                         <c:choose>
                                             <c:when test="${order.state == '1'}">
                                                 <small>
                                                     <span style="color: #0e90d2; ">已支付</span>
                                                 </small>
                                             </c:when>
                                             <c:when test="${order.state == '2'}">
                                                 <small>
                                                     <span style="color: black; ">已退款</span>
                                                 </small>
                                             </c:when>
                                             <c:otherwise>
                                                 <small>
                                                     <span style="color: red; ">未支付</span>
                                                 </small>
                                             </c:otherwise>
                                         </c:choose>
                                         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                         上映时间：${order.showStartView}
                                     </a>
                                     <div align="right">
                                         <button type="button" class="btn btn-info round" onclick="showOrderDetail(${order.id});">
                                             <span class="btn-label"><i class="fa fa-exclamation"></i></span>查看详细信息
                                         </button>
                                     </div>
                                     <p></p>
                                 </div>
                             </c:forEach>

                             <!-- 分页 -->
                             <%@include file="../pageInfo.jsp"%>
                         </div>
                     </div>
                 </div>
                 <!--End row-->
           
			   
			    </div>
        <!-- End Wrapper-->

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

    <script src="/js/plugin.js"></script>

    <script type="text/javascript">

        /**
         * 显示订单详细信息
         */
        function showOrderDetail(orderId) {
            window.location = "/order/orderDetail?id="+orderId;
        }

    </script>

</body>

</html>
