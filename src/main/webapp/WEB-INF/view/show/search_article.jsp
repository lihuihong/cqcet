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
                                <c:when test="${fn:length(pageInfo.list) == 0 && name == '帖子'}">
                                    <div style="text-align:center;">暂无${name}</div>
                                </c:when>
                                <c:otherwise>
                                     <c:forEach items="${pageInfo.list}" var="entity" varStatus="status">
                                        <div class="form-group">
                                            <div class="news-content shadow-bg">
                                                <a href="${pageContext.request.contextPath}/show/detail.action?id=${entity.id}">
                                                    <c:if test="${entity.cover==null}">
                                                        <img src="http://heylhh.com/FgWPzwwYEQRoBYYvx1lL3epPtIws"/>
                                                    </c:if>
                                                    <c:if test="${entity.cover!=null}">
                                                        <img src="${entity.cover}"/>
                                                    </c:if>
                                                    <div class="news-title">
                                                        <h4 style="overflow: hidden;white-space: nowrap;text-overflow:ellipsis;margin-top:10px;padding-top: 10px;padding-bottom: 14px;">${entity.title}</h4>
                                                        <span>${entity.name}</span>
                                                        <div class="right"><span
                                                                style="color: #0f74a8">阅读（${entity.viewCount}）</span>&nbsp&nbsp<span><fmt:formatDate
                                                                value="${entity.updateTime}"
                                                                pattern="yyyy/MM/dd  HH:mm:ss"/></span>
                                                        </div>
                                                    </div>
                                                </a>
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
<script>
    var currentPage = "${pageInfo.pageNum}";
    var pageCount = "${pageInfo.pages}";

    helper.page({
        id : "page",
        pageCount : pageCount,	// 总页数
        currentPage : currentPage,// 默认选中第几页
        // 返回当前选中的页数
        callback:function(rtn) {
            search(rtn);
        }
    });
    var type = "${name}";
    var keyWord = "${keyWord}";
    function search(pageNum) {
        if (pageNum==undefined) {
            pageNum = 1;
        }
        window.location.href = "/show/search.action"
            + "?pageNum="+pageNum
            + "&type="+type
            + "&keyWord="+keyWord;
    }

</script>
</body>
</html>
