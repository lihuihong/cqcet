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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>个人中心</title>
    <link href="${pageContext.request.contextPath}/resources/show/head.css" type="text/css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/resources/javaex/pc/js/javaex.min.js"></script>
    <style>
        *, :before, :after {
            -webkit-box-sizing: border-box;
            box-sizing: border-box;
        }
        a.indigo{
            color: #fff;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp" flush="true"/>

<div class="content">
    <div class="container">

        <div class="row">
            <jsp:include page="meun.jsp" flush="true"/>
            <div class="col-md-9">
                <div class="widget">
                    <div class="widget-header">
                        <h3>关注及粉丝</h3>
                    </div>
                    <div class="widget-content">
                        <div class="tabbable">
                            <ul class="nav nav-tabs">
                                <li class="active">
                                    <a href="#1" data-toggle="tab">关注</a>
                                </li>
                                <li><a href="#2" data-toggle="tab">粉丝</a></li>
                            </ul>
                            <br/>
                            <div class="tab-content">
                                <div class="tab-pane active" id="1">
                                    <div class="widget-content">
                                        <c:choose>
                                            <c:when test="${fn:length(followees)==0}">
                                                <div style="text-align:center;">暂无关注</div>
                                            </c:when>
                                            <c:otherwise>
                                                <ul class="letter-chatlist">
                                                    <p class="tip warning">${followeeCurUser.username} 关注了 ${followeeCount} 人。</p>
                                                    <c:forEach items="${followees}" var="entity" varStatus="status">
                                                        <div class="form-group">
                                                            <div class="news-content shadow-bg">
                                                                <a href="${pageContext.request.contextPath}/show/msg/detail.action?conversationId=">
                                                                    <c:if test="${entity.user.avatar==null}">
                                                                        <img src="http://heylhh.com/FgWPzwwYEQRoBYYvx1lL3epPtIws" />
                                                                    </c:if>
                                                                    <c:if test="${entity.user.avatar!=null}">
                                                                        <img src="${entity.user.avatar}" />
                                                                    </c:if>
                                                                    <div class="news-title">
                                                                        <h4 style="overflow: hidden;white-space: nowrap;text-overflow:ellipsis;">${entity.user.username}</h4>
                                                                        <span style="font-size: 15px">（${entity.followerCount}）粉丝</span>
                                                                        <span style="font-size: 15px">（${entity.followeeCount}）关注</span>
                                                                        <div class="right">
                                                                            <span>

                                                                                <c:if test="${entity.followed==true}">
                                                                                    <input type="hidden" id="userId" name="userId" value="${entity.user.id}" />
                                                                                    <a href="javascript:void(0);" onclick="unfollowUser();" class="btn btn-info btn-sm">
                                                                                        已 关 注
                                                                                 </a>
                                                                                </c:if>
                                                                                <c:if test="${entity.followed==false}">
                                                                                    <input type="hidden" id="userId" name="userId" value="${entity.user.id}" />
                                                                                    <a href="javascript:void(0);" onclick="followUser();"  class="btn btn-info btn-sm">
                                                                                         关 注
                                                                                 </a>
                                                                                </c:if>
                                                                            </span>
                                                                        </div>
                                                                    </div>
                                                                </a>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                </ul>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                                <div class="tab-pane" id="2">
                                    <div class="widget-content">
                                        <c:choose>
                                            <c:when test="${fn:length(followers)==0}">
                                                <div style="text-align:center;">暂无粉丝</div>
                                            </c:when>
                                            <c:otherwise>
                                                <ul class="letter-chatlist">
                                                    <p class="tip warning">${followerCurUser.username} 的粉丝有 ${followerCount} 人。</p>
                                                    <c:forEach items="${followers}" var="entity" varStatus="status">
                                                        <div class="form-group">
                                                            <div class="news-content shadow-bg">
                                                                <a href="${pageContext.request.contextPath}/show/msg/detail.action?conversationId=">
                                                                    <c:if test="${entity.user.avatar==null}">
                                                                        <img src="http://heylhh.com/FgWPzwwYEQRoBYYvx1lL3epPtIws" />
                                                                    </c:if>
                                                                    <c:if test="${entity.user.avatar!=null}">
                                                                        <img src="${entity.user.avatar}" />
                                                                    </c:if>
                                                                    <div class="news-title">
                                                                        <h4 style="overflow: hidden;white-space: nowrap;text-overflow:ellipsis;">${entity.user.username}</h4>
                                                                        <span style="font-size: 15px">（${entity.followerCount}）粉丝</span>
                                                                        <span style="font-size: 15px">（${entity.followeeCount}）关注</span>
                                                                        <div class="right">
                                                                            <span>
                                                                                <input type="hidden" id="userId" name="userId" value="${entity.user.id}" />
                                                                                <c:if test="${entity.followed==true}">

                                                                                    <a href="javascript:void(0);" class="btn btn-info btn-sm">
                                                                                        已 关 注
                                                                                 </a>
                                                                                </c:if>
                                                                                <c:if test="${entity.followed==false}">
                                                                                    <a href="javascript:void(0);"  class="btn btn-info btn-sm">
                                                                                         未 关 注
                                                                                 </a>
                                                                                </c:if>
                                                                            </span>
                                                                        </div>
                                                                    </div>
                                                                </a>
                                                            </div>
                                                        </div>
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
            </div>
        </div>
    </div>
</div>


<jsp:include page="footer.jsp" flush="true"/>

<script>
    var userId = $("#userId").val();
    //关注
    function followUser() {
        $.ajax({
            url: "/show/followUser.json",
            type: "POST",
            dataType: "json",
            data:{
                "userId":userId
            },
            success: function (rtn) {
                if (rtn.code == "000000") {
                    helper.toast({
                        content: "关注"+rtn.message,
                        type: "success"
                    });
                    // 刷新页面
                    window.location.reload();
                } else {
                    helper.toast({
                        content: "关注"+rtn.message,
                        type: "error"
                    });
                }
            },
        });
    };
    //关注
    function unfollowUser() {
        $.ajax({
            url: "/show/unfollowUser.json",
            type: "POST",
            dataType: "json",
            data:{
                "userId":userId
            },
            success: function (rtn) {
                if (rtn.code == "000000") {
                    helper.toast({
                        content: "取消关注"+rtn.message,
                        type: "success"
                    });
                    // 刷新页面
                    window.location.reload();
                } else {
                    helper.toast({
                        content: "取消关注"+rtn.message,
                        type: "error"
                    });
                }
            },
        });
    }

</script>
</body>
</html>
