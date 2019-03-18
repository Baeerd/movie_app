<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <h4 class="page-title">订票信息</h4>
                    <div class="clearfix"></div>
                 </div>
                  <!--End Page Title-->          
           
                 <!--Start row-->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="white-box" align="center">

                             <div class="form-group">
                                 <label class="col-sm-3 control-label">选择影院</label>
                                 <div class="col-sm-5">
                                     <select id="partIdSelect" name="partId" onchange="partChange(this);" class="form-control m-b-15" >
                                         <option value="" selected>--选择影院--</option>
                                         <c:forEach items="${partList}" var="part">
                                             <c:choose>
                                                 <c:when test="${partId != null and partId == part.id}">
                                                     <option value="${part.id}" selected>${part.partName}</option>
                                                 </c:when>
                                                 <c:otherwise>
                                                     <option value="${part.id}">${part.partName}</option>
                                                 </c:otherwise>
                                             </c:choose>
                                         </c:forEach>
                                     </select>
                                 </div>

                             </div>

                             <!-- 电影详细信息 -->
                             <div class="col-md-4">
                                 <div class="panel panel-color  panel-info" onclick="selectPanel(this, ${movie.id});">
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

                             <div class="col-md-4">
                                 <div class="panel panel-color  panel-info" onclick="selectPanel(this, ${movie.id});">
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


                             <table border="1" width="300px" height="300px">
                                <tr align="center" width="10px" height="10px">
                                    <td>1</td>
                                    <td>2</td>
                                    <td>3</td>
                                    <td>4</td>
                                    <td>5</td>
                                    <td>6</td>
                                    <td>7</td>
                                    <td>8</td>
                                    <td>9</td>
                                    <td>10</td>
                                </tr>
                                <tr align="center">
                                    <td>1</td>
                                    <td>2</td>
                                    <td>3</td>
                                    <td>4</td>
                                    <td>5</td>
                                    <td>6</td>
                                    <td>7</td>
                                    <td>8</td>
                                    <td>9</td>
                                    <td>10</td>
                                </tr>
                                <tr align="center">
                                    <td>1</td>
                                    <td>2</td>
                                    <td>3</td>
                                    <td>4</td>
                                    <td>5</td>
                                    <td>6</td>
                                    <td>7</td>
                                    <td>8</td>
                                    <td>9</td>
                                    <td>10</td>
                                </tr>
                                <tr align="center">
                                    <td>1</td>
                                    <td>2</td>
                                    <td>3</td>
                                    <td>4</td>
                                    <td>5</td>
                                    <td>6</td>
                                    <td>7</td>
                                    <td>8</td>
                                    <td>9</td>
                                    <td>10</td>
                                </tr>
                                <tr align="center">
                                    <td>1</td>
                                    <td>2</td>
                                    <td>3</td>
                                    <td>4</td>
                                    <td>5</td>
                                    <td>6</td>
                                    <td>7</td>
                                    <td>8</td>
                                    <td>9</td>
                                    <td>10</td>
                                </tr>
                            </table>

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
                                         <button type="button" class="btn btn-info round">
                                             <span class="btn-label"><i class="fa fa-exclamation"></i></span>查看详细信息
                                         </button>
                                         <button type="button" class="btn btn-danger round">
                                             <span class="btn-label"><i class="fa fa-times"></i></span>退款
                                         </button>
                                     </div>
                                     <p></p>
                                 </div>
                             </c:forEach>

                         </div>
                     </div>
                 </div>
                 <!--End row-->
           
			   
			    </div>
        <!-- End Wrapper-->

        <!--Start  Footer -->
        <footer class="footer-main">Copyright &copy; 2018.Company name All rights reserved.</footer>
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
    

</body>

</html>
