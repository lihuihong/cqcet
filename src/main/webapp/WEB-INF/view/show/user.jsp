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
                            <img src="<%=request.getContextPath()%>/resources/show/img/headimg1.png"  alt="" class="thumbnail" />
                        </div>
                        <div class="account-details">
                            <span class="account-name">zcq1314</span>
                            <span class="account-role">普通用户</span>
                            <span class="account-actions">
                                <a href="javascript:;">Profile</a> |
                                <a href="javascript:;">Edit Settings</a>
                            </span>
                        </div>
                    </div>
                    <hr />
                    <ul id="main-nav" class="nav nav-tabs nav-stacked">
                        <li>
                            <a href="<%=request.getContextPath()%>/show/user/dashboard.action">
                                <i class="icon-home"></i>
                                总览
                            </a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/show/user/postCenter.action">
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
                            <a href="grid.html" >
                                <i class="icon-th-large"></i>
                                最新消息
                                <span class="label label-warning pull-right">5</span>
                            </a>
                        </li>
                        <li class="active">
                            <a href="<%=request.getContextPath()%>/show/user/user.action">
                                <i class="icon-user"></i>
                                个人信息
                            </a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/show/user/login_out.action">
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
                                                <label class="control-label col-sm-2" for="username">用户名</label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control input-medium" id="username" value="zcq1314" disabled="" />
                                                    <p class="help-block">这是您登录的名字，注册后无法更改！</p>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" for="firstname">用户昵称</label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="input-medium" id="firstname" value="八月君" />
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" for="lastname">学号</label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="input-medium" id="lastname" value="" />
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" for="email">邮箱地址</label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="input-large input-medium" id="email" value="test@example.com" />
                                                </div>
                                            </div>
                                            <br /><br />

                                            <br />
                                            <div class="form-actions" style="padding-left: 140px;">
                                                <button type="submit" class="btn btn-primary">保存</button>
                                                <button class="btn">取消</button>
                                            </div>
                                        </fieldset>
                                        </form>
                                    </div>
                                    <div class="tab-pane" id="2">
                                        <form id="edit-profile2" class="form-horizontal" />
                                        <fieldset>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" for="password1">原密码</label>
                                                <div class="col-sm-10">
                                                    <input type="password" class="input-medium" id="password0" value="" />
                                                </div>
                                            </div>
                                            <div clas
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" for="password1">新密码</label>
                                                <div class="col-sm-10">
                                                    <input type="password" class="input-medium" id="password1" value="" />
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" for="password2">确认密码</label>
                                                <div class="col-sm-10">
                                                    <input type="password" class="input-medium" id="password2" value="" />
                                                </div>
                                            </div>
                                            <br />
                                            <div class="form-actions" style="padding-left: 140px;">
                                                <button type="submit" class="btn btn-primary">保存</button> <button class="btn">取消</button>
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

        </div>
    </div>



    <jsp:include page="footer.jsp" flush="true" />
</body>
</html>
