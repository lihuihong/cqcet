<%--
  Created by IntelliJ IDEA.
  User: 那个谁
  Date: 2018/11/14
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
    <title>校园交易</title>
</head>
<body>
<jsp:include page="../show/header.jsp" flush="true"/>

<div class="content">
    <div class="container">
    <div class="row">
        <div class="col-md-12">
            <div id="myCarousel" class="carousel slide">
                <!-- 轮播（Carousel）指标 -->
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                </ol>
                <!-- 轮播（Carousel）项目 -->
                <div class="carousel-inner">
                    <div class="item active">
                        <img src="http://img.htmlsucai.com/huyoucss/banner/banner7.jpg" alt="First slide">
                    </div>
                    <div class="item">
                        <img src="http://img.htmlsucai.com/huyoucss/banner/banner7.jpg" alt="Second slide">
                    </div>
                    <div class="item">
                        <img src="http://img.htmlsucai.com/huyoucss/banner/banner7.jpg" alt="Third slide">
                    </div>
                </div>
                <!-- 轮播（Carousel）导航 -->
                <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left"></span>
                </a>
                <a class="right carousel-control" href="#myCarousel" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right"></span>
                </a>

            </div>
        </div>
    </div>
        <div class="row" style="margin-top: 20px">
            <c:forEach items="${categoryList}" var="entity" varStatus="status" >
            <div class="col-md-2 column">
                <div class="thumbnail groupbox">
                    <a href="${pageContext.request.contextPath}/shop/shop/forecategory.action?categoryName=${entity.name}&id=${entity.id}">
                        <div class="gb-cen"  >
                            <span><strong>${entity.name}</strong></span>
                        </div>
                    </a>
                </div>
            </div>
            </c:forEach>
        </div>
            <c:forEach items="${lists}" var="entity" varStatus="status" >
                <div class="row">
                    <div class="col-md-12" style="margin-bottom: 20px">
                        <div class="left-mark"></div>
                        <span class="categoryTitle">${entity.get(0).name}</span>
                    </div>
                </div>
                <div class="row">
                    <c:choose>
                    <c:when test="${fn:length(entity.get(1))==0}">
                        <div class="col-md-12">暂无商品</div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${entity.get(1)}" var="shopMessage" varStatus="status" >
                    <div class="col-md-3" style="width: 20%">
                        <a href="${pageContext.request.contextPath}/shop/shop/foreproduct.action?pid=${shopMessage.id}">
                            <div class="thumbnail groupbox">
                                <img alt="300x300" style="width: 187px;height: 190px;" src="${shopMessage.imgUrl}"/>
                                <div class="caption">
                                    <p style="font-size: 1px">${shopMessage.title}</p>
                                    <span class="productPrice">${shopMessage.promotePrice}</span>
                                </div>
                            </div>
                        </a>
                    </div>
                        </c:forEach>
                    </c:otherwise>
                    </c:choose>
                </div>
            </c:forEach>
</div>
</div>
<jsp:include page="../show/footer.jsp" flush="true"/>

<script>

    function post(acticleId) {

        //获取编辑器的内容
        var content = $("#content").val();

        $.ajax({
            url: "/show/saveAnswer.json",
            type: "POST",
            dataType: "json",
            data: {
                "acticleId": acticleId,
                "content": content,
                "answerId": null,
                "childId": null
            },
            success: function (rtn) {
                if (rtn.code == "000000") {
                    helper.toast({
                        content: "回帖成功，跳转查看",
                        type: "success"
                    });
                    // 刷新页面
                    window.location.href = "${pageContext.request.contextPath}/show/detail.action?id=" + acticleId;
                } else {
                    helper.toast({
                        content: rtn.message,
                        type: "error"
                    });
                }
            },

        });
    }

    var currentPage = "${articles.pageNum}";
    var pageCount = "${articles.pages}";

    helper.page({
        id: "page",
        pageCount: pageCount,	// 总页数
        currentPage: currentPage,// 默认选中第几页
        // 返回当前选中的页数
        callback: function (rtn) {
            search(rtn);
        }
    });

    var collegeId = ${college.id};
    function search(pageNum) {
        if (pageNum == undefined) {
            pageNum = 1;
        }
        window.location.href = "/show/answer.action"
            + "?pageNum=" + pageNum
            + "&collegeId=" + collegeId
        ;
    }

    $('.loginbtn').click(function () {
        window.location.href = "<%=request.getContextPath()%>/show/login.action";
    });

</script>

</body>
</html>

