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

<div class="content2">
    <div class="container">
        <div class="row" style="margin-right: 0px;margin-left: 0px;">
            <div class="col-md-12">
                <div class="tag-banner video-bg">
                    <div class="tag-content"
                         style="background-image: url(http://how2j.cn/tmall/img/productSingle/672.jpg);">
                        <h1 style="color: cornflowerblue;">${categoryName}</h1>
                    </div>
                    <div class="row" style="margin-top: 20px ;margin-right: 0px; margin-left: 0px;">
                        <c:forEach items="${lists}" var="entity" varStatus="status">
                            <c:choose>
                                <c:when test="${entity.get(1).status == 999}">
                                    <div class="col-md-2 column">
                                        <div class="thumbnail groupbox">
                                            <a href="${pageContext.request.contextPath}/shop/shop/foreproduct.action?pid=${entity.get(1).id}">
                                                <div class="gb-cen">
                                                    <div class="gb-cen" style="background-image: url(${entity.get(1).imgUrl});">
                                                        <span class="productPrice">${entity.get(1).promotePrice}</span>
                                                        <p style="font-size: 1px">${entity.get(1).title}</p>
                                                    </div>
                                                </div>
                                            </a>
                                        </div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="content">
    <div class="container">
        <div class="row">
            <div class="categorySortBar">
                <table class="categorySortBarTable categorySortTable">
                    <tr>
                        <td
                                <c:if test="${'all'==param.sort||empty param.sort}">class="grayColumn"</c:if> ><a
                                href="?cid=${c.id}&sort=all">综合<span class="glyphicon glyphicon-arrow-down"></span></a>
                        </td>
                        <td
                                <c:if test="${'review'==param.sort}">class="grayColumn"</c:if> ><a
                                href="?cid=${c.id}&sort=review">人气<span
                                class="glyphicon glyphicon-arrow-down"></span></a></td>
                        <td <c:if test="${'date'==param.sort}">class="grayColumn"</c:if>><a
                                href="?cid=${c.id}&sort=date">新品<span class="glyphicon glyphicon-arrow-down"></span></a>
                        </td>
                        <td <c:if test="${'saleCount'==param.sort}">class="grayColumn"</c:if>><a
                                href="?cid=${c.id}&sort=saleCount">销量<span
                                class="glyphicon glyphicon-arrow-down"></span></a></td>
                        <td <c:if test="${'price'==param.sort}">class="grayColumn"</c:if>><a
                                href="?cid=${c.id}&sort=price">价格<span
                                class="glyphicon glyphicon-resize-vertical"></span></a></td>
                    </tr>
                </table>
                <table class="categorySortBarTable">
                    <tr>
                        <td><input class="sortBarPrice beginPrice" type="text" placeholder="请输入"></td>
                        <td class="grayColumn priceMiddleColumn">-</td>
                        <td><input class="sortBarPrice endPrice" type="text" placeholder="请输入"></td>
                    </tr>
                </table>
            </div>
        </div>
        <c:choose>
            <c:when test="${fn:length(lists)==0}">
                <div class="col-md-12">暂无商品</div>
            </c:when>
            <c:otherwise>
                <div class="row">
                    <c:forEach items="${lists}" var="entity" varStatus="status">
                        <div class="col-md-3" style="width: 20%">
                            <a href="${pageContext.request.contextPath}/shop/shop/foreproduct.action?pid=${entity.get(1).id}">
                                <div class="thumbnail groupbox">
                                    <img alt="300x300" style="width: 187px;height: 190px;"
                                         src="${entity.get(1).imgUrl}"/>
                                    <div class="caption">
                                        <span class="productPrice">${entity.get(1).promotePrice}</span>
                                        <p style="font-size: 1px">${entity.get(1).title}</p>
                                    </div>
                                    <div class="show1 productInfo">
                                        <span class="monthDeal" style="width: 80px">月成交 <span
                                                class="productDealNumber">${entity.get(2)}笔</span></span>
                                        <span class="productReview" style="width: 50px">评价<span
                                                class="productReviewNumber">${entity.get(3)}</span></span>
                                        <span class="wangwang">
                                        <a class="wangwanglink" href="#nowhere">
                                                ${entity.get(0).username}
                                        </a>
                                        </span>
                                    </div>
                                </div>

                            </a>
                        </div>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<jsp:include page="../show/footer.jsp" flush="true"/>
<script>
    $(function () {
        $("input.sortBarPrice").keyup(function () {
            var num = $(this).val();
            if (num.length == 0) {
                $("div.productUnit").show();
                return;
            }

            num = parseInt(num);
            if (isNaN(num))
                num = 1;
            if (num <= 0)
                num = 1;
            $(this).val(num);


            var begin = $("input.beginPrice").val();
            var end = $("input.endPrice").val();
            if (!isNaN(begin) && !isNaN(end)) {
                console.log(begin);
                console.log(end);
                $("div.productUnit").hide();
                $("div.productUnit").each(function () {
                    var price = $(this).attr("price");
                    price = new Number(price);

                    if (price <= end && price >= begin)
                        $(this).show();
                });
            }

        });
    });
</script>
</body>
</html>

