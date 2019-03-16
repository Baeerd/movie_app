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

    <link href="/assets/plugins/datatables/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
    <link href="/assets/plugins/datatables/css/jquery.dataTables-custom.css" rel="stylesheet" type="text/css"/>

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
                    <h4 class="page-title">电影管理</h4>
                    <div class="clearfix"></div>
                 </div>
                  <!--End Page Title-->


             <!--Start row-->
             <div class="row">
                 <div class="col-md-12">
                   <div class="white-box">
                       <div class="col-md-6">
                           <form class="form-horizontal" id="queryParamForm" onchange="movieFormChange(this);">
                               <div class="form-group">
                                   <label class="col-sm-3 control-label">电影名称</label>
                                   <div class="col-sm-4">
                                       <input class="form-control queryParam" name="name" placeholder="" type="text">
                                   </div>
                               </div>
                           </form>
                       </div>
                   </div>
                  </div>
              </div>
             <!--End row-->

             <!--列表-->
            <!--Start row-->
            <div class="row">
                <div class="col-md-12">
                    <div class="white-box">
                        <h2 class="header-title">电影列表</h2>
                        <div class="table-responsive">
                            <table class="display table dataTable">
                                <thead>
                                <tr>
                                    <th><input type='checkbox' name='checkBox1' value='" + date + "'/><span class='lbl'></span></th>
                                    <th>序号</th>
                                    <th>创建人</th>
                                    <th>电影名称</th>
                                    <th>电影简介</th>
                                    <th>图片地址</th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <!-- end 列表 -->

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

    <!--Begin Page Level Plugin-->
    <script src="/assets/plugins/datatables/js/jquery.dataTables.min.js"></script>
    <!--End Page Level Plugin-->

    <script type="text/javascript">
        $(function () {
            var url = "/movie/findByParam";
            // 加载table列表
            var colModel = [
                {
                    "sTitle": '',
                    "sClass": "center", "bSortable": false, "sWidth": "20",
                    "mRender": function (settings, rowIdx, rec, type) {
                        var date = rec.id + "/" + rec.cjr;

                        /*var btnBind = "<label><input type='checkbox' name='checkBox1' value='" + rec.basewxid + "'/><span class='lbl'></span></label>";*/
                        var btnBind = "<label><input type='checkbox' name='checkBox1' value='" + date + "'/><span class='lbl'></span></label>";
                        return btnBind;
                    },
                    "bSortable": false
                },
                {"data": "id","bSortable": false},
                {"data": "createdBy","bSortable": true},
                {"data": "name","bSortable": false},
                {"data": "remark","bSortable": false},
                {"data": "image","bSortable": false}
            ];
            dataTableInit(url, colModel, function(sSource,aoData, fnCallback) {
                $.post(url, tableDataParam, function (result) {
                    fnCallback(result);//把返回的数据传给这个方法就可以了,datatable会自动绑定数据的
                });
            });
        });

        // 查询
        function movieFormChange(e) {
            tableDataParam = getFormJson(e.id);
            var table = $('.dataTable').DataTable();
            table.ajax.reload();
        }

    </script>

</body>

</html>
