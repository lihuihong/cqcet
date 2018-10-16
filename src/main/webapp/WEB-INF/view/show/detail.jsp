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

    <div class="content" >&nbsp;</div>
    <div class="container content">
        <div class="row">
            <div class="col-md-3">
                <div id="float_left">
                    <div class="answer-item">
                        <img class="answer-img" src="${article.avatar}" />
                        <div style="margin-left: 50%">
                            <h3 style="color: #269abc">${user.username}</h3>
                            <p class="dark-p">${user.groupName}</p><br>
                        </div>
                        <div class="answer-bod">
                            <a href="${pageContext.request.contextPath}/show/personArticle.action?id=${user.id}" class="btn btn-info btn-sm">
                                查 看 更 多
                            </a>
                        </div>
                    </div>
                    <div class="answer-item">
                        <c:if test="${user.id eq sessionScope.get(\"user\")}">
                            <h4>Wo的更多帖子</h4>
                        </c:if>
                        <c:if test="${user.id != sessionScope.get(\"user\")}">
                            <h4>Ta的更多帖子</h4>
                        </c:if>

                        <hr/>
                        <c:forEach items="${articleList}" begin="0" end="1" var="entity" varStatus="status" >
                            <div class="ans-hot">
                                <p><a href="${pageContext.request.contextPath}/show/detail.action?id=${entity.id}">${entity.title}</a></p>
                                <p style="text-align:right;">更新时间 <a href="#" style="color:#ff82e4;"><fmt:formatDate value="${entity.updateTime}" pattern="yyyy/MM/dd" /></a></p>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="col-md-9">
                <div class="article-content">
                    <div class="navbar-default nav-title" style="border-top-left-radius: 15px;border-top-right-radius: 15px">

                    </div>
                    <hr/>
                    <div class="answer-content">
                        <div class="ans-con-rig">

                            <p>${article.title}</p>

                            <div class="right">
                                <c:if test="${user.id eq sessionScope.get(\"user\")}">
                                    <button type="button" id="editor" class="btn btn-success" onclick="editor()">编辑</button>
                                    <button type="button" id="delete" class="btn btn-danger" onclick="move()">删除</button>
                                </c:if>

                            </div>

                            <div class="dark-p">
                                <div class="row">
                                    <div class="col-md-4">
                                        <p>来自 <a href="#" style="color:#ff82e4;">${article.username}</a> </p>
                                    </div>
                                    <div class="col-md-4">
                                        <p>回复 <span style="color:#ff82e4;">3018</span> / 查看 <span style="color:#ff82e4;">${article.viewCount}</span> </p>
                                    </div>
                                    <div class="col-md-4" style="text-align:left">
                                        <p>时间 <span style="color:#ff82e4;"><fmt:formatDate value="${article.updateTime}" pattern="yyyy/MM/dd  HH:mm:ss" /></span> </p>
                                    </div>

                                </div>

                            </div>
                            <hr/>
                            <br/><br/>
                            <div>
                            <img src="${article.cover}">
                            <p>${article.content}</p>
                            </div>
                            <input type="text" class="answer-input" />
                            <p class="ans-other">
                                <a href="#" class="btn btn-info btn-sm">
                                    <span class="glyphicon glyphicon-tint"></span> 快速回帖
                                </a>

                            </p>
                        </div>
                    </div>
                    <hr/>
                    <div class="answer-content">
                        <div class="left">
                            <div class="bor-rai" style="background: #fe6900;">后台</div>
                        </div>
                        <div class="left ans-con-rig">
                            <p class="dark-p" >来自 <a href="#" style="color:#fe6900;">后台</a></p>
                            <p>对于一个，只想更好的使用电脑和手机，并对它们进行维护的学生一枚，应该学什么？</p>
                            <input type="text" class="answer-input" />
                            <p class="ans-other">
                                <a href="#" class="btn btn-info btn-sm">
                                    <span class="glyphicon glyphicon-tint"></span> 快速回帖
                                </a>
                                <a href="#" class="btn btn-default btn-sm">
                                    <span class="glyphicon glyphicon-globe"></span> 查看详情
                                </a>
                            </p>
                        </div>
                    </div>
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
                </div>
            </div>

        </div>
    </div>
    <jsp:include page="footer.jsp" flush="true" />

<script>
    var articleId = new Array();
    articleId.push(${article.id});
    //编辑帖子
    function editor() {
        window.location.href = "<%=request.getContextPath()%>/show/posted.action?id=${article.id}";
    }
    //删除帖子
    function move() {
        $.ajax({
            url : "delete.json",
            type : "POST",
            dataType : "json",
            traditional : "true",
            data : {
                "articleId" : articleId,
                "status" : "0"
            },
            success : function(rtn) {
                if (rtn.code=="000000") {
                    helper.toast({
                        content : "删除"+rtn.message
                    });
                    // 建议延迟加载
                    setTimeout(function() {
                        // 返回到上一个页面
                        window.history.go(-1);
                    }, 2000);
                } else {
                    helper.toast({
                        content : rtn.message,
                        type : "error"
                    });
                }
            }
        });

    }


</script>
</body>
</html>
