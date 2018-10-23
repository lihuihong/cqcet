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
                            <input type="text" class="answer-input" id="ans-text"/>
                            <p class="ans-other">
                                <a href="javascript:post('${article.id}','ans-text',null,null)" class="btn btn-info btn-sm">
                                    <span class="glyphicon glyphicon-tint"></span> 快速回帖
                                </a>

                            </p>
                        </div>
                    </div>
                    <hr/>
                    <c:forEach items="${answerList}" var="answer" varStatus="status">

                        <div class="answer-content">
                            <div class="left">
                                <div class="answer-left">
                                    <img src="${answer.user.avatar}" />
                                </div>
                            </div>
                            <div class="left" style="margin-left: 20px;width: 90%;">
                                <div>
                                    <p class="dark-p" ><a href="#" style="color:#fe6900;">${answer.user.username}</a></p>
                                    <p>${answer.answer.content}</p>
                                    <p class="dark-p" style="text-align: right;letter-spacing: 0px;">
                                        <a href="javascript:opup_reply('${status.index}','${answer.user.username}','${answer.answer.id}','${answer.answer.childId}')">回复</a>&nbsp;&nbsp;
                                        <fmt:formatDate value="${answer.answer.updateTime}" pattern="yyyy/MM/dd  HH:mm:ss" />
                                    </p>
                                </div>

                                <div>
                                <c:forEach items="${answer.child}" var="child" varStatus="status1">
                                    <div class="child_content">
                                        <div class="left">
                                            <div class="answer-left">
                                                <img src="${child.childUser.avatar}" />
                                            </div>
                                        </div>
                                        <div class="left" style="margin-left: 20px;width: 90%;">
                                            <div class="content_p">
                                                <c:choose>
                                                    <c:when test="${answer.answer.childId eq child.answer.parentId || child.childUser.username eq child.parentUser.username}">
                                                        <p class="dark-p" ><a href="#" style="color:#fe6900;">${child.childUser.username}</a></p>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <p class="dark-p">
                                                            <a href="#" style="color:#fe6900;">${child.childUser.username}</a>&nbsp;回复&nbsp;
                                                            <a href="#" style="color:#fe6900;">${child.parentUser.username}</a>
                                                        </p>
                                                    </c:otherwise>
                                                </c:choose>
                                                <p>${child.answer.content}</p>
                                                <p class="dark-p" style="text-align: right;letter-spacing: 0px;">
                                                    <a href="javascript:opup_reply('${status.index}','${child.childUser.username}','${child.answer.id}','${child.answer.childId}')">回复</a>&nbsp;&nbsp;
                                                    <fmt:formatDate value="${child.answer.updateTime}" pattern="yyyy/MM/dd  HH:mm:ss" />
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                                </div>

                                <div class="input_ans" id="id${status.index}">
                                    <input type="text" class="answer-input" id="input_${status.index}"/>
                                    <span style="margin-top: 10px;display: inline-block;">
                                        回复 : <span id="spId${status.index}" style="color:#fe6900"></span>
                                    </span>

                                    <a href="javascript:close_reply(${status.index})" class="btn btn-info btn-sm" style="float: right;margin-top: 10px;margin-left: 10px">
                                        <span class="glyphicon glyphicon-tint"></span> 收起回复
                                    </a>
                                    <a href="javascript:post('${article.id}','input_${status.index}')" class="btn btn-info btn-sm" style="float: right;margin-top: 10px;">
                                        <span class="glyphicon glyphicon-tint"></span> 回复
                                    </a>
                                </div>
                            </div>
                        </div>
                        <hr/>
                    </c:forEach>

                    <input type="hidden" id="answer_info" />
                    <input type="hidden" id="child_id" />

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
    
    function opup_reply(id,name,answer_id,child_id) {
        $("#id"+id).css("height","70px");
        $("#spId"+id).text(name);
        $("#answer_info").val(answer_id);
        $("#child_id").val(child_id);
    }

    function close_reply(id) {
        $("#id"+id).css("height","0px");
    }

    var articleId = new Array();
    articleId.push(${article.id});
    //编辑帖子
    function editor() {
        window.location.href = "<%=request.getContextPath()%>/show/posted.action?id=${article.id}";
    }
    //回复帖子
    function post(acticleId,input_id) {

        //获取编辑器的内容
        var content = $("#"+input_id).val();
        var answer_id =  $('#answer_info').val();
        var child_id =  $('#child_id').val();

        $.ajax({
            url: "/show/saveAnswer.json",
            type: "POST",
            dataType: "json",
            data: {
                "acticleId": acticleId,
                "content": content,
                "answerId" : answer_id,
                "childId" : child_id
            },
            success: function (rtn) {
                if (rtn.code == "000000") {
                    helper.toast({
                        content: "回帖成功，跳转查看",
                        type: "success"
                    });
                    // 刷新页面
                    window.location.reload();
                } else {
                    helper.toast({
                        content: rtn.message,
                        type: "error"
                    });
                }
            },

        });
    }
    //删除帖子
    function move() {
        Ewin.confirm({ message: "确认要删除帖子？" }).on(function (e) {
            if (!e) {
                return;
            }
        $.ajax({
            url : "delete.json",
            type : "POST",
            dataType : "json",
            traditional : "true",
            async:false,
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
                        window.location.href=document.referrer;

                    }, 2000);
                } else {
                    helper.toast({
                        content : rtn.message,
                        type : "error"
                    });
                }
            }
        });
        })
    }


</script>
</body>
</html>
