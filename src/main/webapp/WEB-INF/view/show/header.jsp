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
    <!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.min.js"></script>
    <!-- 包括所有已编译的插件 -->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/show/scroll.js"></script>
    <script src="${pageContext.request.contextPath}/resources/javaex/pc/js/cookie.js"></script>
</head>


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
                            <a href="<%=request.getContextPath()%>/show/user/dashboard.action">
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
<script>
    $('.loginbtn').click(function () {
        window.location.href = "<%=request.getContextPath()%>/show/login.action";
    });
    $('.registerbtn').click(function () {
        window.location.href = "<%=request.getContextPath()%>/show/register.action";
    });
</script>
</body>
</html>
