<%--
  Created by IntelliJ IDEA.
  User: zcq
  Date: 2018/10/9
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人中心</title>
</head>
<body>
    <jsp:include page="header.jsp" flush="true" />

    <div class="content">
        <div class="container">

            <div class="row">
                <div class="col-md-3">
                    <div class="account-container">
                        <div class="account-avatar">
                            <img src="${user.avatar}"  alt="" class="thumbnail" />
                        </div>
                        <div class="account-details">
                            <span class="account-name">${user.username}</span>
                            <span class="account-role">${user.groupName}</span>
                            <span class="account-actions">
                                <a href="javascript:;">Profile</a> |
                                <a href="javascript:;">Edit Settings</a>
                            </span>
                        </div>
                    </div>
                    <hr />
                    <ul id="main-nav" class="nav nav-tabs nav-stacked">
                        <li class="active">
                            <a href="<%=request.getContextPath()%>/show/user/dashboard.action">
                                <i class="icon-home"></i>
                                总览
                            </a>
                        </li>
                        <li>
                            <a href="faq.html" tppabs="http://www.jq22.com/demo/matrix-admin0320160622/faq.html">
                                <i class="icon-pushpin"></i>
                                我的帖子
                            </a>
                        </li>
                        <li>
                            <a href="plans.html" tppabs="http://www.jq22.com/demo/matrix-admin0320160622/plans.html">
                                <i class="icon-th-list"></i>
                                我的回复
                            </a>
                        </li>
                        <li>
                            <a href="grid.html" tppabs="http://www.jq22.com/demo/matrix-admin0320160622/grid.html">
                                <i class="icon-th-large"></i>
                                最新消息
                                <span class="label label-warning pull-right">5</span>
                            </a>
                        </li>
                        <li>
                            <a href="#" tppabs="http://www.jq22.com/demo/matrix-admin0320160622/account.html">
                                <i class="icon-user"></i>
                                个人信息
                            </a>
                        </li>
                        <li >
                            <a href="#" onclick="logout()">
                                <i class="icon-lock"></i>
                                退出登录
                            </a>
                        </li>
                    </ul>
                    <hr />
                    <div class="sidebar-extra">
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud.</p>
                    </div>
                    <br/>
                </div>
                <div class="col-md-9">
                    <div class="widget">
                        <div class="widget-header">
                            <h3>基本信息</h3>
                        </div>
                        <div class="widget-content">
                            <div class="tabbable">
                                <ul class="nav nav-tabs">
                                    <li class="active">
                                        <a href="#1" data-toggle="tab">简况</a>
                                    </li>
                                    <li><a href="#2" data-toggle="tab">安全信息</a></li>
                                </ul>
                                <br />
                                <div class="tab-content">
                                    <div class="tab-pane active" id="1">
                                        <form id="edit-profile" class="form-horizontal" />
                                        <fieldset>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2">用户名</label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control input-medium" name="username" value="${user.username}" disabled="" />
                                                    <p class="help-block">这是您登录的名字，注册后无法更改！</p>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >所属专业</label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="input-medium" name="professionalId" value="${user.professionalName}" disabled=""/>
                                                    <p class="help-block">这是您的专业，不可修改！</p>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >学号</label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="input-medium" name="studentId" value="${user.studentId}" disabled=""/>
                                                    <p class="help-block">这是您的学号，不可修改！</p>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >用户等级</label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="input-large input-medium" name="groupnaame" value="${user.groupName}" disabled=""/>
                                                    <p class="help-block">这是您的用户等级，不可修改！</p>
                                                </div>
                                            </div>
                                            <br /><br />

                                            <br />
                                        </fieldset>
                                        </form>
                                    </div>
                                    <div class="tab-pane" id="2">
                                        <form id="edit-profile2" class="form-horizontal" />
                                        <fieldset>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >旧密码</label>
                                                <div class="col-sm-10">
                                                    <input type="password" class="input-medium" name="oldPassword" value="" />
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >新密码</label>
                                                <div class="col-sm-10">
                                                    <input type="password" class="input-medium" name="newPassword1" value="" />
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >确认新密码</label>
                                                <div class="col-sm-10">
                                                    <input type="password" class="input-medium" name="newPassword2" value="" />
                                                </div>
                                            </div>
                                            <br />
                                            <div class="form-actions" style="padding-left: 140px;">
                                                <button type="button" class="btn btn-primary" onclick="save()">保存</button> <button class="btn">取消</button>
                                            </div>
                                        </fieldset>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp" flush="true" />

<script>

    //保存信息
    function save() {
        $.ajax({
            url : "/show/user/save_password.json",
            type : "POST",
            dataType : "json",
            data : $("#edit-profile2").serialize(),
            success : function(rtn) {
                if (rtn.code=="000000") {
                    helper.toast({
                        content : "修改密码成功",
                        type : "success"
                    });
                    // 返回当前页面
                    window.location.href = "${refererUrl}";
                    return false;
                } else {
                    helper.toast({
                        content : rtn.message,
                        type : "error"
                    });
                }
            }
        });
    }

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
