<%--
  Created by IntelliJ IDEA.
  User: zcq
  Date: 2018/9/13
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="utf-8"/>
    <!-- 引入 Bootstrap -->
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" type="text/css"
          rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/show/style.css" type="text/css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/show/font/font-awesome.css" type="text/css"
          rel="stylesheet"/>
</head>
<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
<script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.min.js"></script>
<!-- 包括所有已编译的插件 -->
<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/show/scroll.js"></script>
<script src="${pageContext.request.contextPath}/resources/javaex/pc/js/cookie.js"></script>

</head>
<body>
<header id="header">
    <div class="container">
        <div class="row head">
            <div class="col-md-2">
                <a href="index.jsp"><img src="${pageContext.request.contextPath}/resources/show/img/logo.png"
                                         style="width: 100%;"/></a>
            </div>
            <div class="col-md-6"></div>
            <div class="col-md-4" style="padding-top: 5px;">
                <form class="bs-example bs-example-form" role="form">
                    <div class="input-group">
                        <div class="input-group-btn">
                            <button type="button" class="btn btn-default
                                            dropdown-toggle" data-toggle="dropdown"><a id="">全部</a>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="javascript:;">全部</a>
                                </li>
                                <li>
                                    <a href="javascript:;">用户</a>
                                </li>
                                <li>
                                    <a href="javascript:;">文章</a>
                                </li>
                            </ul>
                        </div>
                        <input type="text" class="form-control">
                        <span class="input-group-btn">
                            <button class="btn btn-primary" type="button">搜 索</button>
                        </span>
                    </div>

                </form>
            </div>
        </div>
    </div>
</header>
<nav class="navbar navbar-default" role="navigation">
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target="#example-navbar-collapse">
                        <span class="sr-only">切换导航</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>

                </div>
                <div class="collapse navbar-collapse" id="example-navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="${pageContext.request.contextPath}/show/index/index.action">首 页</a></li>
                        <li><a href="${pageContext.request.contextPath}/show/app.action">应用中心</a></li>
                        <li class="dropdown active">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                论坛 <b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="${pageContext.request.contextPath}/show/forum.action">全部版块</a></li>
                                <li><a href="#">EJB</a></li>
                                <li><a href="#">Jasper Report</a></li>
                                <li class="divider"></li>
                                <li><a href="#">分离的链接</a></li>
                                <li class="divider"></li>
                                <li><a href="#">另一个分离的链接</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-md-4">
                <c:choose>
                    <c:when test="${sessionScope.get(\"userInfo\") != null}">
                        <div class="user" id="oklogin">
                            <a href="<%=request.getContextPath()%>/show/user/dashboard.action?userId=<%=session.getAttribute("user")%>">
                                <img src="<%=session.getAttribute("avatar")%>"/>
                                <span><%=session.getAttribute("username")%></span>
                            </a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="user" id="nologin">
                            <button type="button" class="btn btn-primary loginbtn">登陆</button>
                            <button type="button" class="btn btn-default registerbtn">注册</button>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</nav>
<div class="login" style="padding: 20px 0;display:none;">
    <div class="container">
        <div class="row">
            <div class="col-md-offset-4 col-md-4">
                <form class="login-form" id="form">
                    <span class="close-login"><a href="javascript:void(0);">关闭</a></span>
                    <span class="heading">用户登录</span>
                    <div class="form-group">
                        <input type="text" class="form-control" id="username" placeholder="用户名或电子邮件">
                        <i class="fa fa-user"></i>
                    </div>
                    <div class="form-group help">
                        <input type="password" class="form-control" id="password" placeholder="密　码">
                        <i class="fa fa-lock"></i>
                        <a href="#" class="fa fa-question-circle"></a>
                    </div>
                    <div class="form-group" style="margin-top:40px;height: 30px;">
                        <button type="button" class="btn btn-default" style="float:left;background-color: #C5C4C4;"
                                id="go-register">前往注册
                        </button>
                        <button type="submit" class="btn btn-default " id="login-button-submit">登录</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="register" style="padding: 20px 0;display:none;">
    <div class="container">
        <div class="row">
            <div class="col-md-offset-4 col-md-4">
                <form class="login-form" id="reg">
                    <span class="close-register"><a href="javascript:void(0);">关闭</a></span>
                    <span class="heading">用户注册</span>
                    <div class="form-group">
                        <input type="text" class="form-control" id="reg-user" placeholder="用户名或电子邮件">
                        <i class="fa fa-user"></i>
                    </div>
                    <div class="form-group">
                        <input type="number" class="form-control" id="reg-num" placeholder="学   号">
                        <i class="fa fa-user"></i>
                    </div>
                    <div class="form-group help">
                        <input type="password" class="form-control" id="reg-pwd" placeholder="密　码">
                        <i class="fa fa-lock"></i>
                        <a href="#" class="fa fa-question-circle"></a>
                    </div>
                    <div class="form-group help">
                        <input type="password" class="form-control" id="reg-repwd" placeholder="确认密码">
                        <i class="fa fa-lock"></i>
                        <a href="#" class="fa fa-question-circle"></a>
                    </div>
                    <div class="form-group help">
                        <input type="select" class="form-control" id="reg-school" placeholder="学   院">
                        <i class="fa fa-lock"></i>
                        <a href="#" class="fa fa-question-circle"></a>
                    </div>
                    <div class="form-group help">
                        <input type="text" class="form-control" id="reg-professional" placeholder="专   业">
                        <i class="fa fa-lock"></i>
                        <a href="#" class="fa fa-question-circle"></a>
                    </div>
                    <div class="form-group" style="margin-top:40px;">
                        <button type="submit" class="btn btn-default" style="float:left;" id="reg-button-submit">注册
                        </button>
                        <button type="button" class="btn btn-default" style="background-color: #C5C4C4;" id="go-login">
                            前往登陆
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="login-bg"></div>
<script>
    H_login = {};
    H_login.openLogin = function () {
        $('.loginbtn').click(function () {
            $('.login').show();
            $('.login-bg').show();
        });
        $('.registerbtn').click(function () {
            $('.register').show();
            $('.login-bg').show();
        });
        $('#go-register').click(function () {
            $('.login').hide();
            $('.register').show();
            $('.login-bg').show();
        });
        $('#go-login').click(function () {
            $('.register').hide();
            $('.login').show();
            $('.login-bg').show();
        });
    };
    H_login.closeLogin = function () {
        $('.close-login').click(function () {
            $('.login').hide();
            $('.login-bg').hide();
        });
        $('.close-register').click(function () {
            $('.register').hide();
            $('.login-bg').hide();
        });

    };
    H_login.loginForm = function () {
        $("#login-button-submit").on('click', function () {
            helper.toast({
                content: "登陆中...",
                type: "success"
            });
            var username = $("#username");
            var usernameValue = $("#username").val();
            var password = $("#password");
            var passwordValue = $("#password").val();
            if (usernameValue == "") {
                alert("用户名不能为空");
                username.focus();
                return false;
            } else if (usernameValue.length > 15) {
                alert("用户名长度不能大于15字符");
                username.focus();
                return false;
            } else if (passwordValue == "") {
                alert("密码不能为空");
                password.focus();
                return false;
            } else if (passwordValue.length < 6 || passwordValue.length > 30) {
                alert("密码长度不能小于6或大于30位字符");
                password.focus();
                return false;
            } else {

                $.ajax({
                    url: "/show/index/login.json",
                    type: "POST",
                    dataType: "json",
                    data: {username: usernameValue, password: passwordValue},
                    success: function (rtn) {
                        if (rtn.code == "000000") {
                            var info = rtn.data.info;
                            delCookie("userToken");
                            setCookie("userToken", info.userToken);
                            helper.toast({
                                content: "登录成功，即将为您跳转到首页",
                                type: "success"
                            });

                            window.location.href = "${pageContext.request.contextPath}/";

                            setTimeout(function () {
                                location.reload(true);
                                // 跳转到首页
                                $('.login').hide();
                                $('.login-bg').hide();
                                $('.list-input').val('');

                            }, 2000);
                        } else {
                            helper.toast({
                                content: rtn.message,
                                type: "error"
                            });
                        }
                    },

                });

            }
        });
        $("#reg-button-submit").on('click', function () {
            helper.toast({
                content: "注册中...",
                type: "success"
            });
            var username = $("#reg-user");
            var usernameValue = $("#reg-user").val();
            var password = $("#reg-pwd");
            var passwordValue = $("#reg-pwd").val();
            var repassword = $("#reg-repwd");
            var repasswordValue = $("#reg-repwd").val();
            var student = $("#reg-num");
            var studentValue = $("#reg-num").val();
            var schoolValue = $("#reg-school").val();
            var professional = $("#reg-professional");
            var professionalValue = $("#reg-professional").val();

            if (usernameValue == "") {
                alert("用户名不能为空");
                username.focus();
                return false;
            } else if (usernameValue.length > 15) {
                alert("用户名长度不能大于15字符");
                username.focus();
                return false;
            } else if (passwordValue == "") {
                alert("密码不能为空");
                password.focus();
                return false;
            } else if (passwordValue.length < 6 || passwordValue.length > 16) {
                alert("密码长度不能小于6或大于16位字符");
                password.focus();
                return false;
            } else if (passwordValue != repasswordValue) {
                alert("两次密码不一致");
                repassword.focus();
                return false;
            } else if (studentValue.length == 8) {
                alert("学号必须是10位");
                student.focus();
                return false;
            } else if (schoolValue == "") {
                alert("学院不能为空");
                student.focus();
                return false;
            } else if (professionalValue == "") {
                alert("专业不能为空");
                student.focus();
                return false;
            } else {

                $.ajax({
                    url: "/show/index/register.json",
                    type: "POST",
                    dataType: "json",
                    data: {
                        username: usernameValue,
                        password: passwordValue,
                        studentId: studentValue,
                        college: schoolValue,
                        professional: professionalValue
                    },
                    success: function (rtn) {
                        if (rtn.code == "000000") {
                            var info = rtn.data.info;
                            helper.toast({
                                content: "注册成功，即将为您跳转到首页",
                                type: "success"
                            });
                            delCookie("userToken");
                            setCookie("userToken", info.userToken);
                            // 跳转到首页
                            window.location.href = "${pageContext.request.contextPath}/";
                            setTimeout(function () {
                                $('.login').hide();
                                $('.login-bg').hide();
                                $('.list-input').val('');
                            }, 2000);
                        } else {
                            helper.toast({
                                content: rtn.message,
                                type: "error"
                            });
                        }
                    },

                });
            }

        });
    };
    H_login.run = function () {
        this.closeLogin();
        this.openLogin();
        this.loginForm();
    };
    H_login.run();

</script>

</body>
</html>
