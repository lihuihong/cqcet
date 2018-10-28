<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>消息详情</title>

</head>
<body>
<jsp:include page="header.jsp"/>
<div class="content">
    <div class="container">
        <div class="row">
            <jsp:include page="meun.jsp" flush="true"/>
            <div class="col-md-9">
                <div class="widget">
                    <div class="widget-header">
                        <h3>我的消息</h3>
                    </div>
                    <div class="widget-content">
                        <c:choose>
                            <c:when test="${fn:length(messages.list)==0}">

                                <div style="text-align:center;">暂无消息</div>
                            </c:when>
                            <c:otherwise>

                                <ul class="letter-chatlist">
                                <c:forEach items="${messages.list}" var="entity" varStatus="status">
                                                <li id="msg-item-4009580">
                                                    <a class="list-head">
                                                        <img alt="头像" src="${entity.headUrl}">
                                                    </a>
                                                    <div class="tooltip fade right in">
                                                        <div class="tooltip-arrow" style="border-right-color: #666;"></div>
                                                        <div class="tooltip-inner1 letter-chat clearfix">
                                                            <div class="letter-info">
                                                                <p class="letter-time"><fmt:formatDate value="${entity.message.createdDate}" pattern="yyyy/MM/dd  HH:mm:ss" /></p>
                                                                <!-- <a href="javascript:void(0);" id="del-link" name="4009580">删除</a> -->
                                                            </div>
                                                            <p class="chat-content">
                                                                    ${entity.message.content}
                                                            </p>
                                                        </div>
                                                    </div>
                                                </li>
                                </c:forEach>
                                </ul>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
<script>
    var currentPage = "${pageInfo.pageNum}";
    var pageCount = "${pageInfo.pages}";

    helper.page({
        id: "page",
        pageCount: pageCount,	// 总页数
        currentPage: currentPage,// 默认选中第几页
        // 返回当前选中的页数
        callback: function (rtn) {
            search(rtn);
        }
    });

    function search(pageNum) {
        if (pageNum == undefined) {
            pageNum = 1;
        }
        window.location.href = "/show/msg/detail.action"
            + "?pageNum=" + pageNum

        ;
    }

</script>
</body>
</html>
