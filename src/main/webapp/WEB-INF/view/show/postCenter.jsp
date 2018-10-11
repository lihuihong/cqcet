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
                        <li class="active">
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
                        <li>
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
                            <h3>我的帖子</h3>
                        </div>
                        <div class="widget-content">
                            <div class="form-group">
                                <div class="news-content shadow-bg">
                                    <a href="#">
                                        <img src="<%=request.getContextPath()%>/resources/show/img/headimg1.png" />
                                        <div class="news-title">
                                            <h3>『中级篇』docker Image概述（13）</h3>
                                            <span>最新文章/日常生活</span>
                                            <div class="right">06.06 00:39</span>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="news-content shadow-bg">
                                    <a href="#">
                                        <img src="<%=request.getContextPath()%>/resources/show/img/headimg1.png" />
                                        <div class="news-title">
                                            <h3>『中级篇』docker Image概述（13）</h3>
                                            <span>最新文章/日常生活</span>
                                            <div class="right">06.06 00:39</span>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                            </div>

                            <div class="form-actions" style="padding: 0px;">
                                <nav class="ul-center">
                                    <ul class="pagination">
                                        <li><a href="#">&laquo;</a></li>
                                        <li class="active"><a href="#">1</a></li>
                                        <li><a href="#">2</a></li>
                                        <li><a href="#">3</a></li>
                                        <li><a href="#">4</a></li>
                                        <li><a href="#">5</a></li>
                                        <li><a href="#">&raquo;</a></li>
                                    </ul>
                                </nav>
                                <!--<button type="submit" class="btn btn-primary">保存</button> <button class="btn">取消</button> -->
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
