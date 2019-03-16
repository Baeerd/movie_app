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
                    <h4 class="page-title">评论列表</h4>
                    <div class="clearfix"></div>
                 </div>
                  <!--End Page Title-->          
           
                 <!--Start row-->
                 <div class="row">
                     <div class="col-md-12">
                         <div class="white-box">
                             
                            <div class="search-box-top">
                              <form action="/comment/commentList">
                                  <input type="hidden" name="pageNum"/>
                                  <input type="hidden" name="pageSize"/>
                                <div class="input-group">
                                  <input name="commentSearch" class="form-control input-search" placeholder="搜索..." type="text" value="${commentSearch}">
                                  <span class="input-group-btn">
                                  <button class="btn btn-primary" type="submit"><i class="fa fa-search"></i></button>
                                  </span> </div>
                              </form>
                            </div>

                             <c:forEach items="${page.list}" var="comment">
                                 <div class="search-item">
                                     <h3><a href="javascript:void(0);">用户：${comment.createdBy}&nbsp;&nbsp;(${comment.partName}--${comment.movieName})</a></h3>
                                     <a class="search-link"   href="javascript:void(0);">${comment.createdDtView}</a>

                                     <div align="right">
                                         <button type="button" class="btn btn-danger round" onclick="deleteComment(${comment.id});">
                                             <span class="btn-label"><i class="fa fa-times"></i></span>删除
                                         </button>
                                     </div>
                                     <p>${comment.content}</p>
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
        function deleteComment(id) {
            $.post("/comment/deleteJson",{"id": id}, function(data) {
                alert(data.data);
                window.location.reload();
            });
        }
    </script>

</body>

</html>
