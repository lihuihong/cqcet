<%--
  Created by IntelliJ IDEA.
  User: 那个谁
  Date: 2018/10/16
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>帖子搜索</title>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/card/css/htmleaf-demo.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/card/css/normalize.css">

</head>
<body>
<jsp:include page="header.jsp" flush="true"/>
<div class="container">
    <div class="content" style="padding-top:30px;">
        <div class="container">
            <div class="row">
                <div class="col-md-9">
                    <div class="article-content">
                        <div class="navbar-default nav-title"
                             style="border-top-left-radius: 15px;border-top-right-radius: 15px">
                            <h4>搜索${name}结果</h4>
                        </div>
                        <hr/>
                        <div class="answer-content">
                            <c:choose>
                                <c:when test="${fn:length(userlist) == 0}">
                                <div style="text-align:center;">暂无${name}</div>
                                </c:when>
                                <c:otherwise>
                                     <c:forEach items="${userlist}" var="entity" varStatus="status">
                                        <div class="col-md-3 col-sm-6">
                                            <div class="our-team">
                                                <div class="pic">
                                                    <img src="${entity.avatar}" style="width: 100%">
                                                </div>
                                                <div class="team-content">
                                                    <h3 class="title">${entity.username}</h3>
                                                        <%--<span class="post">${entity.collegeName}</span>--%>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                            <hr/>

                            <hr/>
                        </div>
                    </div>
                    <hr/>
                    <!-- 分页 -->
                    <div class="page">
                        <ul id="page" class="pagination"></ul>
                    </div>
                </div>

                <div class="col-md-3">
                    <div id="float_right">
                        <div class="answer-item">
                            <h4>热门帖子</h4>
                            <hr/>
                            <c:forEach items="${articleList}" begin="0" end="4" var="entity" varStatus="status">
                                <div class="ans-hot">
                                    <p>
                                        <a href="${pageContext.request.contextPath}/show/detail.action?id=${entity.id}">${entity.title}</a>
                                    </p>
                                    <p style="text-align:right;">更新时间 <a href="#" style="color:#ff82e4;"><fmt:formatDate
                                            value="${entity.updateTime}" pattern="yyyy/MM/dd"/></a></p>
                                </div>
                            </c:forEach>

                        </div>
                        <jsp:include page="tagcloud.jsp"></jsp:include>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
</body>
</html>
