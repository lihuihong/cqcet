<%--
  Created by IntelliJ IDEA.
  User: 那个谁
  Date: 2018/10/12
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>

<body>
<div class="col-md-3">
    <div class="account-container">
        <div class="account-avatar">
            <img src="${user.avatar}" alt="" class="thumbnail"/>
        </div>
        <div class="account-details">
            <span class="account-name">${user.username}</span>
            <span class="account-role">${user.groupName}</span>
            <span class="account-actions">
                                <a href="javascript:;">最近登录</a> |
                                <a href="javascript:;"><fmt:formatDate value="${user.lastLoginTime}" pattern="yyyy/MM/dd HH:mm:ss"/></a>
                            </span>
        </div>
    </div>
    <hr/>
    <ul id="main-nav" class="nav nav-tabs nav-stacked ">
        <li>
            <a href="<%=request.getContextPath()%>/show/user/dashboard.action">
                <i class="icon-home"></i>
                总览
            </a>
        </li>
        <li>
            <a href="<%=request.getContextPath()%>/show/user/user.action" tppabs="http://www.jq22.com/demo/matrix-admin0320160622/account.html">
                <i class="icon-user"></i>
                个人信息
            </a>
        </li>
        <li>
            <a href="<%=request.getContextPath()%>/show/user/postCenter.action"
               tppabs="http://www.jq22.com/demo/matrix-admin0320160622/faq.html">
                <i class="icon-pushpin"></i>
                我的帖子
            </a>
        </li>

        <li>
            <a href="<%=request.getContextPath()%>/show/user/followees.action?userId=${user.id}"
               tppabs="http://www.jq22.com/demo/matrix-admin0320160622/faq.html">
                <i class="icon-tags"></i>
                我的关注、粉丝
            </a>
        </li>
        <li>
            <a href="plans.html" tppabs="http://www.jq22.com/demo/matrix-admin0320160622/plans.html">
                <i class="icon-th-list"></i>
                我的回复
            </a>
        </li>
        <li>
            <a href="<%=request.getContextPath()%>/show/msg/list.action" tppabs="http://www.jq22.com/demo/matrix-admin0320160622/grid.html">
                <i class="icon-th-large"></i>
                最新消息
                <span class="label label-warning pull-right">${unreadCount}</span>
            </a>
        </li>
        <li>
            <a href="#" onclick="logout()">
                <i class="icon-lock"></i>
                退出登录
            </a>
        </li>
    </ul>
    <hr/>
    <div class="sidebar-extra">
        <p></p>
    </div>
    <br/>
</div>

<script>
    $("#main-nav > li a").each(function(){
        $this = $(this).parents('#main-nav > li');
        if(this.href==window.location.href){
            $this.addClass("active");
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
