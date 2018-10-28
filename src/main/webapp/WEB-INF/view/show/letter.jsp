
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>消息列表</title>
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
                        <h3>我的消息</h3>
                    </div>
                    <div class="widget-content">
                        <c:choose>
                            <c:when test="${fn:length(conversations.list)==0}">

                                <div  style="text-align:center;">暂无消息</div>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${conversations.list}" var="entity" varStatus="status" >
                                    <div class="form-group">
                                        <div class="news-content shadow-bg">
                                            <a href="${pageContext.request.contextPath}/show/msg/detail.action?conversationId=${entity.conversation.conversationId}">
                                                <c:if test="${entity.user.avatar==null}">
                                                    <img src="http://heylhh.com/FgWPzwwYEQRoBYYvx1lL3epPtIws" />
                                                </c:if>
                                                <c:if test="${entity.user.avatar!=null}">
                                                    <span class="msg-num">${entity.unread}</span>
                                                    <img src="${entity.user.avatar}" />
                                                </c:if>
                                                <div class="news-title">
                                                    <h5 style="overflow: hidden;white-space: nowrap;text-overflow:ellipsis;">${entity.user.username}</h5>
                                                    <span style="font-size: 20px">${entity.conversation.content}</span>
                                                    <div class="right"><span><fmt:formatDate value="${entity.conversation.createdDate}" pattern="yyyy/MM/dd  HH:mm:ss" /></span>
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
        window.location.href = "/show/msg/list.action"
            + "?pageNum="+pageNum

        ;
    }

</script>
</body>
</html>
