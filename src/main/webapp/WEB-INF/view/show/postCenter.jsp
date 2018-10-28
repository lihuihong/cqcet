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
    <title>我的帖子</title>
</head>
<body>
    <jsp:include page="header.jsp" flush="true" />

    <div class="content">
        <div class="container">
            <div class="row">
                <jsp:include page="meun.jsp" flush="true" />
                <div class="col-md-9">
                    <div class="widget">
                        <div class="widget-header">
                            <h3>我的帖子</h3>
                        </div>
                        <div class="widget-content">
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
                                            <div class="right"><span style="color: #0f74a8">赞（${entity.liked}）</span>&nbsp&nbsp<span style="color: #0f74a8">阅读（${entity.viewCount}）</span>&nbsp&nbsp<span><fmt:formatDate value="${entity.updateTime}" pattern="yyyy/MM/dd  HH:mm:ss" /></span>
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
            window.location.href = "/show/answer.action"
                + "?pageNum="+pageNum

            ;
        }

    </script>
</body>
</html>
