<%--
  Created by IntelliJ IDEA.
  User: zcq
  Date: 2018/9/13
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <title>重电校园</title>
</head>
<body>
    <jsp:include page="header.jsp" flush="true" />

    <div class="container">

        <div class="row">
            <div class="col-md-12">
                <div class="ans-head">
                    <img src="${college.avatar}" class="pull-left" />
                    <div style="margin-top: 10px">
                        <h2>${college.name}</h2>
                        <p class="dark-p">${college.des}</p><br/>
                    </div>
                </div>
            </div>

        </div>

    </div>
    <div class="content" >&nbsp;</div>
    <div class="container content">
        <div class="row">
            <div class="col-md-9">
                <div class="article-content">
                    <div class="navbar-default nav-title" style="border-top-left-radius: 15px;border-top-right-radius: 15px">
                        <a href="">全部帖子</a>    <a href="">精品专区</a>     <a href="">最新帖子</a>
                    </div>
                    <hr/>
                <c:choose>
                    <c:when test="${fn:length(articles.list)==0}">

                            <div  style="text-align:center;">暂无记录</div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${articles.list}" var="entity" varStatus="status" >
                        <div class="answer-content">
                        <div class="left">
                            <img style="width: 50px;height: 50px" src="${entity.avatar}" />
                        </div>
                        <div class="left ans-con-rig" style="width: 90%">
                            <p>${entity.title}</p>
                            <div class="dark-p">
                                <div class="row">
                                    <div class="col-md-4">
                                        <p>来自 <a href="#" style="color:#ff82e4;">${entity.username}</a> </p>
                                    </div>
                                    <div class="col-md-4">
                                        <p>回复 <span style="color:#ff82e4;">3018</span> / 查看 <span style="color:#ff82e4;">${entity.viewCount}</span> </p>
                                    </div>
                                    <div class="col-md-4" style="text-align:right">
                                        <p>时间 <span style="color:#ff82e4;"><fmt:formatDate value="${entity.updateTime}" pattern="yyyy/MM/dd  HH:mm:ss" /></span> </p>
                                    </div>

                                </div>

                            </div>
                            <input type="text" class="answer-input" />
                            <p class="ans-other">
                                <a href="#" class="btn btn-info btn-sm">
                                    <span class="glyphicon glyphicon-tint"></span> 快速回帖
                                </a>
                                <a href="${pageContext.request.contextPath}/show/detail.action?id=${entity.id}" class="btn btn-default btn-sm">
                                    <span class="glyphicon glyphicon-globe"></span> 查看详情
                                </a>
                            </p>
                        </div>
                    </div>
                            <hr/>
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
            <div class="col-md-3">
                <div id="float_right">
                    <c:choose>
                        <c:when test="${sessionScope.get(\"user\") != null}">
                            <div class="answer-item">
                                <img class="answer-img" src="${user.avatar}" />
                                <div style="margin-left: 50%">
                                    <h3 style="color: #269abc">${user.username}</h3>
                                    <p class="dark-p">${user.groupName}</p><br>
                                </div>
                                <div class="answer-bod">
                                    <a href="<%=request.getContextPath()%>/show/posted.action" class="btn btn-info btn-sm">
                                        点 击 发 贴
                                    </a>
                                </div>
                            </div>
                            <div class="answer-item">
                                <h4>我的帖子</h4>
                                <hr/>
                                <c:forEach items="${articleList}" begin="0" end="2" var="entity" varStatus="status" >
                                <div class="ans-hot">
                                    <p><a href="">${entity.title}</a></p>
                                    <p style="text-align:right;">更新时间 <a href="#" style="color:#ff82e4;"><fmt:formatDate value="${entity.updateTime}" pattern="yyyy/MM/dd" /></a></p>
                                </div>
                                </c:forEach>

                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="answer-item">
                                <img class="answer-img" src="/resources/show/img/unll.jpg" />
                                <div style="margin-left: 50%">
                                    <h3 style="color: #269abc"></h3>
                                    <p class="dark-p"></p><br>
                                </div>
                                <div class="answer-bod">
                                    <a href="<%=request.getContextPath()%>/show/posted.action" class="btn btn-info btn-sm">
                                        请 登 陆
                                    </a>
                                </div>
                            </div>
                            <div class="answer-item">
                                <h4>推荐帖子</h4>
                                <hr/>
                                <div class="ans-hot">
                                    <p><a href="">开发工具怎么下载？</a></p>
                                    <p style="text-align:right;">来自 <a href="#" style="color:#ff82e4;">前端</a></p>
                                </div>
                                <div class="ans-hot">
                                    <p><a href="">购买了课程可以开发票么？</a></p>
                                    <p style="text-align:right;">来自 <a href="" style="color: #fe6900;">后台</a></p>
                                </div>
                            </div>

                        </c:otherwise>
                    </c:choose>

                </div>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp" flush="true" />
    <script>

        var currentPage = "${articles.pageNum}";
        var pageCount = "${articles.pages}";

        helper.page({
            id : "page",
            pageCount : pageCount,	// 总页数
            currentPage : currentPage,// 默认选中第几页
            // 返回当前选中的页数
            callback:function(rtn) {
                search(rtn);
            }
        });

        var collegeId = ${college.id};
        function search(pageNum) {
            if (pageNum==undefined) {
                pageNum = 1;
            }
            window.location.href = "/show/answer.action"
                + "?pageNum="+pageNum
                + "&collegeId="+collegeId
            ;
        }

    </script>
</body>
</html>
