<%--
  Created by IntelliJ IDEA.
  User: zcq
  Date: 2018/9/13
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
    <title>重电校园</title>
</head>
<body>
<jsp:include page="header.jsp" flush="true"/>
<div class="content">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="forum-box">
                    <div class="navbar-default nav-title">学院专区</div>

                    <div class="row" style="padding: 10px;">
                        <c:forEach items="${list}" var="entity" varStatus="status">
                            <div class="col-md-4">
                                <div class="app_box">
                                    <a href=""><img src="${entity.collegeAvatar}" class="pull-left"/></a>
                                    <dl class="box_dl">
                                        <c:choose>
                                            <c:when test="${sessionScope.get(\"userInfo\") != null}">
                                                <dt>
                                                    <a href="${pageContext.request.contextPath}/show/answer.action?collegeId=${entity.collegeId}&userId=<%=session.getAttribute("user")%>">${entity.collegeName}</a>
                                                </dt>
                                                <dd style="width: 200px; overflow: hidden; white-space: nowrap;">
                                                    <span>${entity.collegeDes}</span>
                                                </dd>
                                                <dd style="color:#999;">
                                                    <span>主题:${entity.typeCount}, 帖子:${entity.articleCount}</span></dd>
                                            </c:when>
                                            <c:otherwise>
                                                <dt>
                                                    <a href="${pageContext.request.contextPath}/show/answer.action?collegeId=${entity.collegeId}">${entity.collegeName}</a>
                                                </dt>
                                                <dd style="width: 200px; overflow: hidden; white-space: nowrap;">
                                                    <span>${entity.collegeDes}</span>
                                                </dd>
                                                <dd style="color:#999;">
                                                    <span>主题:${entity.typeCount}, 帖子:${entity.articleCount}</span></dd>
                                            </c:otherwise>
                                        </c:choose>

                                    </dl>
                                </div>

                            </div>
                        </c:forEach>
                    </div>

                </div>

                <div class="forum-box">
                    <div class="navbar-default nav-title">校园专区</div>

                    <div class="row" style="padding: 10px;">
                            <div class="col-md-4">
                                <div class="app_box">
                                    <a href=""><img src="http://heylhh.com/mm.jpg" class="pull-left"/></a>
                                    <dl class="box_dl">
                                        <dt>
                                            <a href="${pageContext.request.contextPath}/show/answer.action?collegeId=">校园交易</a>
                                        </dt>
                                        <dd style="width: 200px; overflow: hidden; white-space: nowrap;">
                                            <span>旧书、闲置物品交易</span>
                                        </dd>
                                        <dd style="color:#999;">
                                            <span>分类:12, 交易:999</span>
                                        </dd>
                                    </dl>
                                </div>

                            </div>
                    </div>

                </div>
            </div>

        </div>

    </div>
</div>
<jsp:include page="footer.jsp" flush="true"/>
</body>
</html>
