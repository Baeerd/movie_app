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
                                                     <option value="${part.id}" selected>${part.partName}(${part.showTime})</option>
                                                 </c:when>
                                                 <c:otherwise>
                                                     <option value="${part.id}">${part.partName}(${part.showTime})</option>
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
                                         <img src="${movie.image}" width="300" height="200">
                                         <p/>
                                         <p>
                                             ${movie.remark}
                                         </p>
                                     </div>
                                 </div>
                             </div>


                             <table border="1" width="800px" height="400px" id="placeSelectList"></table>

                             <button type="button" class="btn btn-info round" onclick="submitOrder();">
                                 <span class="btn-label"><i class="fa fa-exclamation"></i></span>付款
                             </button>
                             <button type="button" class="btn btn-warning round" onclick="javascript:history.go(-1)">返回</button>


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

    <script src="/assets/plugins/sweetalert/sweet-alert.js"></script>
    <script src="/assets/pages/jquery.sweet-alert.custom.js"></script>

    <!-- End core plugin -->
    <script src="/js/plugin.js"></script>

    <script type="text/javascript">

        /**
         * 影院选择
         */
        function partChange(e) {
            $.post("/place/findByParam", {"rMovieId" : e.value}, function(data) {
                var placeDataList = data.data;
                var tr1 = '<tr align="center" width="600px">';
                var tr2 = '</tr>';
                var index = 0;
                var content = tr1;
                for(var index=1; index<=placeDataList.length; index++) {
                    if(placeDataList[index-1].isUse == '1') {
                        // 已占用
                        content += '<td style="background: red">'+placeDataList[index-1].placeNo+'</td>';
                    } else {
                        // 未占用
                        content += '<td onclick="selectPlace(this, '+placeDataList[index-1].id+');">'+placeDataList[index-1].placeNo+'</td>';
                    }
                    if(index == 50) {
                        content += tr2;
                    } else if(index % 10 == 0) {
                        content += tr2 + tr1;
                    }
                }
                $("#placeSelectList").html(content);
            });
        }


        var selectIds = [];
        /**
         * 座位选择
         */
        function selectPlace(e, id) {
            console.log(selectIds);
            if(selectIds.indexOf(id)!=-1) {
                $(e).css("background", "white");
                selectIds.remove(id);
            } else {
                $(e).css("background", "green");
                selectIds.push(id);
            }
        }

        /**
         * 付款
         */
        function submitOrder() {
            if(!$("#partIdSelect").val()) {
                swal("请选择影院场次!");
                return false;
            }
            if(selectIds.length == 0) {
                swal("请选择座位!");
                return false;
            }
            swal("付款成功!");
        }
    </script>

</body>

</html>
