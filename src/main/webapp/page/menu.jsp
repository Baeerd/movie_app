<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!--Start left side Menu-->
<div class="left-side sticky-left-side">
    <!--logo-->
    <div class="logo">
        <a href="/"><img src="/assets/images/logo-dark.png" alt="" height="50px;" width="100px;"></a>
    </div>

    <div class="logo-icon text-center">
        <a href="/"><img src="/assets/images/logo-dark.png" alt="" height="40px;" width="50px;"></a>
    </div>

    <div class="left-side-inner">
        <!--Sidebar nav-->
        <ul class="nav nav-pills nav-stacked custom-nav">

            <li class="menu-list nav-active"><a href="#"><i class="icon-layers"></i> <span>影视信息后台管理</span></a>
                <ul class="sub-menu-list">
                    <li><a href="/movie/movieListPanel"> 电影管理</a></li>
                    <li ><a href="/moviePart/partListPanel"> 影院管理</a></li>
                    <li><a href="/main/option">影院设置</a></li>
                    <li><a href="/order/orderList">订单查询</a></li>
                    <li><a href="/comment/commentList">评论管理</a></li>
                </ul>
            </li>

            <li class="menu-list nav-active"> <a href="#"><i class="icon-grid"></i> <span>电影浏览</span></a>
                <ul class="sub-menu-list">
                    <li><a href="/movie/movieListPanel"> 电影浏览</a></li>
                    <li><a href="/moviePart/partListPanel">影院浏览</a></li>
                </ul>
            </li>



        </ul>
        <!--End sidebar nav-->

    </div>
</div>