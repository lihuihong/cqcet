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
    <script src="${pageContext.request.contextPath}/resources/show/ewin.js"></script>

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
                                            dropdown-toggle" data-toggle="dropdown"><a id="select_name">全部</a>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="javascript:selectName('全部');">全部</a>
                                </li>
                                <li>
                                    <a href="javascript:selectName('用户');">用户</a>
                                </li>
                                <li>
                                    <a href="javascript:selectName('帖子');">帖子</a>
                                </li>
                            </ul>
                        </div>
                        <input type="text" class="form-control" id="search_content">
                        <span class="input-group-btn">
                            <button class="btn btn-primary" type="button" onclick="go_search()">搜 索</button>
                        </span>
                    </div>

                </form>
            </div>
        </div>
    </div>
</header>


<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="display: none;left: 50%;
     top: 40%;transform: translate(-50%,-50%);min-width:50%;overflow: visible;bottom: inherit; right: inherit;">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">发送私信</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label >发给：</label>
                    <input type="text" id="touser"  class="form-control"  placeholder="用户名">
                </div>
                <div class="form-group">
                    <label >内容：</label>
                    <textarea rows="3"  id="tocontent" class="form-control" placeholder="私信内容" ></textarea>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><span
                        class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
                </button>
                <button type="button" id="btn_submit" class="btn btn-success" data-dismiss="modal"><span
                        class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span> 发送
                </button>
            </div>
        </div>
    </div>
</div>

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
                                <li><a href="#">校园交易</a></li>
                                <li><a href="#">校园公告墙</a></li>
                                <li class="divider"></li>
                                <li><a href="#">就业平台</a></li>
                                <li class="divider"></li>
                                <li><a href="#">重电新闻</a></li>
                            </ul>
                        </li>
                        <c:choose>
                            <c:when test="${sessionScope.get(\"userInfo\") != null}">
                                <li>
                                    <a class="zu-top-nav-link" href="#" id="zh-top-nav-count-wrap" role="button"><span
                                            class="mobi-arrow"></span>消息<span id="zh-top-nav-count" class="zu-top-nav-count zg-noti-number" style="display: none;">0</span></a>
                                </li>
                            </c:when>
                            <c:otherwise>
                            </c:otherwise>
                        </c:choose>

                    </ul>
                </div>
            </div>
            <div class="col-md-4">
                <c:choose>
                    <c:when test="${sessionScope.get(\"userInfo\") != null}">
                        <div class="user" id="oklogin">
                            <a href="<%=request.getContextPath()%>/show/user/user.action">
                                <img src="<%=session.getAttribute("avatar")%>"/>
                                <span><%=session.getAttribute("username")%></span>
                            </a>
                            <div class="card-user">
                                <div class="card-top clearfix">
                                    <a href="<%=request.getContextPath()%>/show/user/user.action" class="l">
                                        <img src="<%=session.getAttribute("avatar")%>" alt="<%=session.getAttribute("username")%>">

                                    </a>
                                    <div class="card-top-right-box l">
                                        <p><%=session.getAttribute("username")%></p>
                                        <p>新手上路</p>
                                    </div>
                                </div>
                                <div class="user-center-box">
                                    <ul class="clearfix" style="padding: 0px;">
                                        <li class="l"><a href="<%=request.getContextPath()%>/show/user/user.action" target="_blank"><span class="glyphicon glyphicon-user"></span> 我的信息</a></li>
                                        <li class="l"><a href="<%=request.getContextPath()%>/show/user/postCenter.action" target="_blank"><span class="glyphicon glyphicon-book"></span> 我的帖子</a></li>
                                    </ul>
                                </div>
                                <div class="card-history">
                                    <span class="history-item">
                                        <span class="tit">最近帖子</span>
                                        <span class="media-name"><a href="" id="new_article_a">${new_article.title}</a></span>
                                        <div style="text-align: right;margin-top: 10px;"><a href="<%=request.getContextPath()%>/show/posted.action">点击发帖</a></div>
                                        <span class="glyphicon glyphicon-time abs-span"></span>
                                    </span>
                                </div>
                                <div class="card-sets">
                                    <a href="javascript:logout()"  class="l">安全退出</a>
                                </div>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="user" id="nologin" style="line-height: 50px;">
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

    $("#zh-top-nav-count-wrap").click(function () {
        $("#myModalLabel").text("发送私信");
        $('#myModal').modal();
    });

    var type;
    function selectName(name) {
        type = $('#select_name').text(name);
    };
    $.ajax({
        url: "/article/new_article.json",
        type: "GET",
        dataType: "json",
        async:true,
        success: function (rtn) {
            if (rtn.code == "000000") {
                //alert(rtn.data.data);
                $('#new_article_a').attr("href","<%=request.getContextPath()%>/show/detail.action?id="+rtn.data.article_id);
                $('#new_article_a').text(rtn.data.title);

                return false;
            }
        }
    });
    /*搜索帖子*/
    function go_search() {

        var type = $('#select_name').text();
        var search_content = $('#search_content').val();

        window.location.href = "${pageContext.request.contextPath}/show/search.action?type="+type+"&keyWord="+search_content;
    }

    //发送私信
    $("#btn_submit").click(function () {
        var touser = $("#touser").val();
        var tocontent = $("#tocontent").val();
        if (touser == null || touser == ""){
            helper.toast({
                content: "发送对象不能为空",
                type: "error"
            });
        }else if (tocontent == null || tocontent == ""){
            helper.toast({
                content: "发送内容不能为空",
                type: "error"
            });
        }else {
            $.ajax({
                url: "<%=request.getContextPath()%>/show/msg/addMessage.json",
                type: "POST",
                dataType: "json",
                data: {
                    "toName": touser,
                    "toContent": tocontent
                },
                success: function (rtn) {
                    if (rtn.code == "000000") {
                        helper.toast({
                            content: "发送成功",
                            type: "success"
                        });
                        // 刷新页面
                        window.location.href = "<%=request.getContextPath()%>/show/msg/list.action";
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
    // 退出登录
    function logout() {
        $.ajax({
            url : "/show/user/login_out.json",
            type : "POST",
            dataType : "json",
            async: false,
            success : function(rtn) {
                if (rtn.code=="000000") {
                    delCookie("userToken");
                    // 跳转到首页
                    window.location.href = "${pageContext.request.contextPath}/";
                }
            }
        });
    }

</script>
</body>
</html>
