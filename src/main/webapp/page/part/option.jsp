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

    <!-- BEGIN PAGE LEVEL STYLES -->
    <link rel="stylesheet" type="text/css" href="/assets/plugins/jquery-multi-select/css/multi-select.css" />
    <link href="/assets/plugins/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.css" rel="stylesheet" />

    <!-- BEGIN PAGE LEVEL STYLES -->
    <link href="/assets/plugins/timepicker/bootstrap-timepicker.min.css" rel="stylesheet">
    <link href="/assets/plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.min.css" rel="stylesheet">
    <link href="/assets/plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css" rel="stylesheet">
    <link href="/assets/plugins/clockpicker/css/bootstrap-clockpicker.min.css" rel="stylesheet">
    <link href="/assets/plugins/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">
    <!-- END PAGE LEVEL STYLES -->
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
                <h4 class="page-title">影院设置</h4>
                <div class="clearfix"></div>
             </div>
              <!--End Page Title-->

             <div class="row">

             </div>

            <!--Start row-->
            <div class="row">

                <div class="col-md-12">
                    <div class="white-box">
                    <h2 class="header-title"> 影院设置</h2>
                       <form action="#" class="form-horizontal " id="optionForm">

                            <div class="form-group">
                                 <label class="col-sm-3 control-label">选择影院</label>
                                 <div class="col-sm-5">
                                      <select id="partIdSelect" name="partId" onchange="partChange(this);" class="form-control m-b-15" >
                                          <option value="" selected>--选择影院--</option>
                                          <c:forEach items="${partList}" var="part">
                                              <c:if test="${partId == part.id}">
                                                  <option value="${part.id}" selected>${part.partName}</option>
                                              </c:if>
                                              <c:if test="${partId != part.id}">
                                                  <option value="${part.id}">${part.partName}</option>
                                              </c:if>
                                          </c:forEach>
                                      </select>
                                 </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3">电影名称</label>
                                <div class="col-md-9">
                                    <select name="movieId" class="multi-select" multiple="" id="my_multi_select3">
                                        <c:forEach items="${movieList}" var="movie">
                                            <c:if test="${movie.isCurentPart == '1'}">
                                                <option value="${movie.id}" selected>${movie.name}</option>
                                            </c:if>
                                            <c:if test="${movie.isCurentPart == '0'}">
                                                <option value="${movie.id}">${movie.name}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                           <div class="col-md-8">
                               <div class="form-group">
                                   <label class="control-label col-md-4">选择场次时间</label>
                                   <div class="col-md-8">
                                       <div class="input-group">
                                           <input type="text" name="startDate" class="form-control" id="datepicker-multiple-date">
                                           <span class="input-group-addon b-0 text-white"><i class="icon-calender"></i></span>
                                       </div>
                                   </div>
                               </div>
                           </div>

                           <div class="form-group">
                               <label class="col-sm-3 control-label">选择场次号</label>
                               <div class="col-sm-5">
                                   <select name="showNo" class="form-control m-b-15 initSelect" typeId="show_no">
                                       <option value="#value">#name</option>
                                   </select>
                               </div>
                           </div>

                           <div class="form-group">
                               <label class="col-sm-3 control-label">影片价格</label>
                               <div class="col-sm-5">
                                   <input name="price" class="form-control m-b-15" type="text" />
                               </div>
                           </div>

                       </form>

                    </div>

                    <div align="center">
                        <button type="button" id="optionSubmit" onclick="optionSubmit();" class="btn btn-success round">
                            <span class="btn-label"><i class="fa fa-check"></i></span>保存
                        </button>
                    </div>
                </div>
            </div>
            <!--End row-->

            </div>

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

    <!-- BEGIN PAGE LEVEL SCRIPTS -->
    <script src="/assets/plugins/autoNumeric/autoNumeric.js" type="text/javascript"></script>
    <script src="/assets/plugins/bootstrap-maxlength/bootstrap-maxlength.min.js" type="text/javascript"></script>
    <script src="/assets/plugins/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.js" type="text/javascript"></script>
    <script src="/assets/plugins/inputmask/jasny-bootstrap.min.js" type="text/javascript"></script>
    <script src="/assets/plugins/tagsinput/bootstrap-tagsinput.js" type="text/javascript"></script>
    <script type="text/javascript" src="/assets/plugins/jquery-quicksearch/jquery.quicksearch.js"></script>
    <script type="text/javascript" src="/assets/plugins/jquery-multi-select/js/jquery.multi-select.js"></script>
    <script src="/assets/pages/multi-select-init.js"></script>

    <script src="/assets/plugins/moment/moment.js"></script>
    <script src="/assets/plugins/timepicker/bootstrap-timepicker.js"></script>
    <script src="/assets/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.min.js"></script>
    <script src="/assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
    <script src="/assets/plugins/clockpicker/js/bootstrap-clockpicker.min.js"></script>
    <script src="/assets/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>

    <script src="/assets/plugins/sweetalert/sweet-alert.js"></script>
    <script src="/assets/pages/jquery.sweet-alert.custom.js"></script>

    <script src="/js/plugin.js"></script>


    <script>

        /**
         * 影院下拉框改变事件
         */
        function partChange(e) {
            var partId = e.value;
            window.location = "/main/option?partId="+partId;
        }

        /**
         * 保存影院配置
         */
        function optionSubmit() {
            if($("#partIdSelect").val() == null || $("#partIdSelect").val() == "") {
                swal("请选择影院!");
            }
            if($("#datepicker-multiple-date").val() == null || $("#datepicker-multiple-date").val() == "") {
                swal("请选择场次时间!");
            }
            var data = getFormJson("optionForm");
            data.movieId = formatSelect($("#my_multi_select3").val());
            console.log(data);

            $.post("/main/saveOption", data, function(response) {
                swal("保存成功!", "", "success")
            });
        }

        function formatSelect(array) {
            var result = "";
            if(!array) {
                return result;
            }
            for(var i=0; i<array.length; i++) {
                result += array[i] + ",";
            }
            return result;
        }

        jQuery(function($) {
              $('.autonumber').autoNumeric('init');
          });

            //Bootstrap-TouchSpin
            $(".vertical-spin").TouchSpin({
                verticalbuttons: true,
                buttondown_class: "btn btn-primary",
                buttonup_class: "btn btn-primary",
                verticalupclass: 'ti-plus',
                verticaldownclass: 'ti-minus'
            });
            var vspinTrue = $(".vertical-spin").TouchSpin({
                verticalbuttons: true
            });
            if (vspinTrue) {
                $('.vertical-spin').prev('.bootstrap-touchspin-prefix').remove();
            }

            $("input[name='demo1']").TouchSpin({
                min: 0,
                max: 100,
                step: 0.1,
                decimals: 2,
                boostat: 5,
                maxboostedstep: 10,
                buttondown_class: "btn btn-primary",
                buttonup_class: "btn btn-primary",
                postfix: '%'
            });
            $("input[name='demo2']").TouchSpin({
                min: -1000000000,
                max: 1000000000,
                stepinterval: 50,
                buttondown_class: "btn btn-primary",
                buttonup_class: "btn btn-primary",
                maxboostedstep: 10000000,
                prefix: '$'
            });
            $("input[name='demo3']").TouchSpin({
                buttondown_class: "btn btn-primary",
                buttonup_class: "btn btn-primary"
            });
            $("input[name='demo3_21']").TouchSpin({
                initval: 40,
                buttondown_class: "btn btn-primary",
                buttonup_class: "btn btn-primary"
            });
            $("input[name='demo3_22']").TouchSpin({
                initval: 40,
                buttondown_class: "btn btn-primary",
                buttonup_class: "btn btn-primary"
            });

            $("input[name='demo5']").TouchSpin({
                prefix: "pre",
                postfix: "post",
                buttondown_class: "btn btn-primary",
                buttonup_class: "btn btn-primary"
            });
            $("input[name='demo0']").TouchSpin({
                buttondown_class: "btn btn-primary",
                buttonup_class: "btn btn-primary"
            });

           
            //Bootstrap-MaxLength
            $('input#defaultconfig').maxlength()

            $('input#thresholdconfig').maxlength({
                threshold: 20
            });

            $('input#moreoptions').maxlength({
                alwaysShow: true,
                warningClass: "label label-success",
                limitReachedClass: "label label-danger"
            });

            $('input#alloptions').maxlength({
                alwaysShow: true,
                warningClass: "label label-success",
                limitReachedClass: "label label-danger",
                separator: ' out of ',
                preText: 'You typed ',
                postText: ' chars available.',
                validate: true
            });

            $('textarea#textarea').maxlength({
                alwaysShow: true
            });

            $('input#placement').maxlength({
                alwaysShow: true,
                placement: 'top-left'
            });
   </script>

    <script>

        jQuery('#datepicker-multiple-date').datepicker({
            format: "yyyy-mm-dd"
        });
    </script>

</body>

</html>
