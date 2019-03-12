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


<section class="login-section">
    <div class="container">
        <div class="row">
            <div class="login-wrapper">
                <div class="login-inner">

                    <div class="logo">
                        <img src="/assets/images/logo-dark.png"  alt="logo"/>
                    </div>

                    <h2 class="header-title text-center">影视信息系统</h2>

                    <form action="/system/login">
                        <div class="form-group">
                            <input type="text" class="form-control" name="username" placeholder="用户名" >
                        </div>

                        <div class="form-group">
                            <input type="text" class="form-control" name="password"  placeholder="密码" >
                        </div>

                        <div class="form-group">
                            <div class="pull-left">
                                <div class="checkbox primary">
                                    <input  id="checkbox-2" type="checkbox">
                                    <label for="checkbox-2">记住密码</label>
                                </div>
                            </div>

                            <div class="pull-right">
                                <a href="reset-password.html" class="a-link">
                                    <i class="fa fa-unlock-alt"></i> 忘记密码?
                                </a>
                            </div>
                        </div>

                        <div class="form-group">
                            <input type="submit" value="登录" class="btn btn-primary btn-block" >
                        </div>

                        <div class="form-group text-center">
                            没有账号?  <a href="registration.html">点击注册 </a>
                        </div>

                    </form>

                    <div class="copy-text">
                        <p class="m-0">2019 &copy; 影视信息系统</p>
                    </div>

                </div>
            </div>

        </div>
    </div>
</section>
<!--End login Section-->

<!--Begin core plugin -->
<script src="/assets/js/jquery.min.js"></script>
<script src="/assets/js/bootstrap.min.js"></script>
<!-- End core plugin -->

</body>

</html>
