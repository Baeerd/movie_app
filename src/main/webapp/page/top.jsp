<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="/js/plugin.js"></script>
<div class="header-section">

    <a class="toggle-btn"><i class="fa fa-bars"></i></a>

    <!--notification menu start -->
    <div class="menu-right">
        <ul class="notification-menu">
            <li>
                <a href="#" class="btn btn-default dropdown-toggle info-number" data-toggle="dropdown">
                    <i class="fa fa-tasks"></i>
                    <span class="badge">${userOrderList.size()}</span>
                </a>
                <div class="dropdown-menu dropdown-menu-head pull-right">
                    <h5 class="title">我的订单</h5>
                    <ul class="dropdown-list">
                        <li class="notification-scroll-list notification-list ">
                            <c:forEach items="${userOrderList}" var="order">
                                <!-- list item-->
                                <a href="javascript:void(0);" class="list-group-item">
                                    <div class="media">
                                        <div class="pull-left p-r-10">
                                            <img src="${order.image}" width="50" height="50"/>
                                        </div>
                                        <div class="media-body">
                                            <h5 class="media-heading">${order.movieName}</h5>
                                            <p class="m-0">
                                                <small>上映时间：${order.showStartView}</small>
                                            </p>
                                            <p class="m-0">
                                                <small>总额：${order.totalPrice}</small>
                                                <small>票数：${order.num}</small>
                                            </p>
                                            <p class="m-0">
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
                                            </p>
                                        </div>
                                    </div>
                                </a>
                            </c:forEach>
                        </li>
                        <li class="last"> <a href="#">查看全部订单</a> </li>
                    </ul>
                </div>
            </li>

            <li>
                <a href="#" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    <img src="/assets/images/users/avatar-6.jpg" alt="" />
                    admin
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu dropdown-menu-usermenu pull-right">
                    <li> <a href="#"> <i class="fa fa-wrench"></i> 设置 </a> </li>
                    <li> <a href="#"> <i class="fa fa-user"></i> 我的订单 </a> </li>
                    <li> <a href="#"> <i class="fa fa-info"></i> 我的评论 </a> </li>
                    <li> <a href="#"> <i class="fa fa-lock"></i> 注销 </a> </li>
                </ul>
            </li>

        </ul>
    </div>
    <!--notification menu end -->

</div>