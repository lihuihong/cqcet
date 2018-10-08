<%--
  Created by IntelliJ IDEA.
  User: zcq
  Date: 2018/9/13
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <title>重电校园</title>
    <!-- 引入 Bootstrap -->
    <link href="css/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <link href="css/style.css" type="text/css" rel="stylesheet" />
</head>
<body>
    <jsp:include page="header.jsp" flush="true" />
    <div class="content">
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <a href="">
                        <div class="app_box">
                            <img src="${pageContext.request.contextPath}/resources/show/img/ecard.png" class="pull-left"/>
                            <div class="right_p">
                                <h3>一卡通系统</h3>
                                <p>包含师生校园就餐、购物、缴费等消费功能、银行卡圈存转帐以及图书借阅、车辆进出、会议签到等身份识别功能和校园其他管理功能的20多个子系统</p>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-md-4">
                    <a href="">
                        <div class="app_box">
                            <img src="${pageContext.request.contextPath}/resources/show/img/ecard.png" class="pull-left"/>
                            <div class="right_p">
                                <h3>一卡通系统</h3>
                                <p>包含师生校园就餐、购物、缴费等消费功能、银行卡圈存转帐以及图书借阅、车辆</p>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-md-4">
                    <a href="">
                        <div class="app_box">
                            <img src="${pageContext.request.contextPath}/resources/show/img/ecard.png" class="pull-left"/>
                            <div class="right_p">
                                <h3>一卡通系统</h3>
                                <p>包含师生校园就餐、购物、缴费等消费功能、银行卡圈存转帐以及图书借阅、车辆</p>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-md-4">
                    <a href="">
                        <div class="app_box">
                            <img src="${pageContext.request.contextPath}/resources/show/img/ecard.png" class="pull-left"/>
                            <div class="right_p">
                                <h3>一卡通系统</h3>
                                <p>包含师生校园就餐、购物、缴费等消费功能、银行卡圈存转帐以及图书借阅、车辆</p>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-md-4">
                    <a href="">
                        <div class="app_box">
                            <img src="${pageContext.request.contextPath}/resources/show/img/ecard.png" class="pull-left"/>
                            <div class="right_p">
                                <h3>一卡通系统</h3>
                                <p>包含师生校园就餐、购物、缴费等消费功能、银行卡圈存转帐以及图书借阅、车辆</p>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-md-4">
                    <a href="">
                        <div class="app_box">
                            <img src="${pageContext.request.contextPath}/resources/show/img/ecard.png" class="pull-left"/>
                            <div class="right_p">
                                <h3>一卡通系统</h3>
                                <p>包含师生校园就餐、购物、缴费等消费功能、银行卡圈存转帐以及图书借阅、车辆</p>
                            </div>
                        </div>
                    </a>
                </div>
            </div>

        </div>

    </div>
    <!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
    <script src="js/jquery.min.js"></script>
    <!-- 包括所有已编译的插件 -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/login.js"></script>
    <script>
        $(window).scroll(function () {
            if ($(document).scrollTop() > 80) {$(".navbar").addClass("top-nav");
            }else {$(".navbar").removeClass("top-nav");}
        })
    </script>
</body>
</html>
