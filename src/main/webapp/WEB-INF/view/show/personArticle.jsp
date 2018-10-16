<%--
  Created by IntelliJ IDEA.
  User: zcq
  Date: 2018/10/9
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <c:if test="${user.id eq sessionScope.get(\"user\")}">
    <title>Wo的帖子</title>
    </c:if>
    <c:if test="${user.id != sessionScope.get(\"user\")}">
        <title>Ta的帖子</title>
    </c:if>
</head>
<body>
    <jsp:include page="header.jsp" flush="true" />

    <div class="content" style="padding-top:30px;">
        <div class="container">
            <div class="row">
                <div class="col-md-3">
                    <div id="float_left">
                        <div class="answer-item">
                            <img class="answer-img" src="${user.avatar}" />
                            <div style="margin-left: 50%">
                                <h3 style="color: #269abc">${user.username}</h3>
                                <p class="dark-p">${user.groupName}</p><br>
                            </div>
                        </div>
                        <div class="answer-item">
                            <c:if test="${user.id eq sessionScope.get(\"user\")}">
                            <h4>Wo的帖子分类</h4>
                            </c:if>
                            <c:if test="${user.id != sessionScope.get(\"user\")}">
                                <h4>Ta的帖子分类</h4>
                            </c:if>
                            <hr/>
                            <c:forEach items="${typeList}" begin="0" end="10" var="entity" varStatus="status" >
                                <div class="ans-hot">
                                    <p><a href="${pageContext.request.contextPath}/show/article_type.action?id=${user.id}&typeId=${entity.get(0).id}">${entity.get(0).name}</a>（${entity.get(1)}）</p>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="col-md-9">
                    <div class="article-content">
                        <div class="navbar-default nav-title" style="border-top-left-radius: 15px;border-top-right-radius: 15px">
                            <c:if test="${user.id eq sessionScope.get(\"user\")}">
                                <p>Wo的帖子</p>
                            </c:if>
                            <c:if test="${user.id != sessionScope.get(\"user\")}">
                                <p>Ta的帖子</p>
                            </c:if>

                        </div>
                        <div class="answer-content">
                        <c:choose>
                            <c:when test="${fn:length(pageInfo.list)==0}">

                                <div  style="text-align:center;">暂无帖子</div>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${pageInfo.list}" var="entity" varStatus="status" >
                            <div class="form-group">
                                <div class="news-content shadow-bg">
                                    <a href="${pageContext.request.contextPath}/show/detail.action?id=${entity.id}">
                                        <c:if test="${entity.cover==null}">
                                            <img src="http://heylhh.com/FgWPzwwYEQRoBYYvx1lL3epPtIws" />
                                        </c:if>
                                        <c:if test="${entity.cover!=null}">
                                            <img src="${entity.cover}" />
                                        </c:if>
                                        <div class="news-title">
                                            <h3 style="overflow: hidden;white-space: nowrap;text-overflow:ellipsis;">${entity.title}</h3>
                                            <span>${entity.name}</span>
                                            <div class="right"><span style="color: #0f74a8">阅读（${entity.viewCount}）</span>&nbsp&nbsp<span><fmt:formatDate value="${entity.updateTime}" pattern="yyyy/MM/dd  HH:mm:ss" /></span>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                            </div>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>

                            <hr/>
                            <!-- 分页 -->
                            <div class="page">
                                <ul id="page" class="pagination"></ul>
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
        var currentPage = "${pageInfo.pageNum}";
        var pageCount = "${pageInfo.pages}";
        var id = "${user.id}";

        helper.page({
            id : "page",
            pageCount : pageCount,	// 总页数
            currentPage : currentPage,// 默认选中第几页
            // 返回当前选中的页数
            callback:function(rtn) {
                search(rtn);
            }
        });

        function search(pageNum) {
            if (pageNum==undefined) {
                pageNum = 1;
            }
            window.location.href = "/show/personArticle.action"
                + "?pageNum="+pageNum
                + "&id="+id

            ;
        }

    </script>
</body>
</html>
