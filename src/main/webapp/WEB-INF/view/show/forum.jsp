<%--
  Created by IntelliJ IDEA.
  User: zcq
  Date: 2018/9/13
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <title>重电校园</title>
</head>
<body>
    <jsp:include page="header.jsp" flush="true" />
    <div class="content">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="forum-box">
                        <div class="navbar-default nav-title">学院专区</div>
                        <div class="row" style="padding: 10px;">
                            <div class="col-md-4">
                                <div class="app_box">
                                    <a href=""><img src="${pageContext.request.contextPath}/resources/show/img/ecard.png" class="pull-left"/></a>
                                    <dl class="box_dl">
                                        <dt><a href="${pageContext.request.contextPath}/show/answer.action">人工智能与大数据</a></dt>
                                        <dd style="width: 200px; overflow: hidden; white-space: nowrap;">
                                            <span>原软件学院和计算机学院合并</span>
                                        </dd>
                                        <dd style="color:#999;"><span>主题:3270, 帖子:8620</span></dd>
                                    </dl>
                                </div>

                            </div>
                            <div class="col-md-4">
                                <div class="app_box">
                                    <a href=""><img src="${pageContext.request.contextPath}/resources/show/img/ecard.png" class="pull-left"/></a>
                                    <dl class="box_dl">
                                        <dt><a href="">人工智能与大数据</a></dt>
                                        <dd style="width: 200px; overflow: hidden; white-space: nowrap;">
                                            <span>原软件学院和计算机学院合并</span>
                                        </dd>
                                        <dd style="color:#999;"><span>主题:3270, 帖子:8620</span></dd>
                                    </dl>
                                </div>

                            </div>
                            <div class="col-md-4">
                                <div class="app_box">
                                    <a href=""><img src="${pageContext.request.contextPath}/resources/show/img/ecard.png" class="pull-left"/></a>
                                    <dl class="box_dl">
                                        <dt><a href="">人工智能与大数据</a></dt>
                                        <dd style="width: 200px; overflow: hidden; white-space: nowrap;">
                                            <span>原软件学院和计算机学院合并</span>
                                        </dd>
                                        <dd style="color:#999;"><span>主题:3270, 帖子:8620</span></dd>
                                    </dl>
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
