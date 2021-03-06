<%--
  Created by IntelliJ IDEA.
  User: 那个谁
  Date: 2018/11/16
  Time: 11:19
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
    <link href="${pageContext.request.contextPath}/resources/show/shop/style.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<jsp:include page="../show/header.jsp" flush="true"/>
<div class="content">
    <div class="container">
        <div class="productPageDiv">
            <script>

                $(function () {
                    var stock = ${p.stock};
                    $(".productNumberSetting").keyup(function () {
                        var num = $(".productNumberSetting").val();
                        num = parseInt(num);
                        if (isNaN(num))
                            num = 1;
                        if (num <= 0)
                            num = 1;
                        if (num > stock)
                            num = stock;
                        $(".productNumberSetting").val(num);
                    });

                    $(".increaseNumber").click(function () {
                        var num = $(".productNumberSetting").val();
                        num++;
                        if (num > stock)
                            num = stock;
                        $(".productNumberSetting").val(num);
                    });
                    $(".decreaseNumber").click(function () {
                        var num = $(".productNumberSetting").val();
                        --num;
                        if (num <= 0)
                            num = 1;
                        $(".productNumberSetting").val(num);
                    });

                    $(".addCartButton").removeAttr("disabled");
                    $(".addCartLink").click(function () {
                        var page = "forecheckLogin";
                        $.get(
                            page,
                            function (result) {
                                if ("success" == result) {
                                    var pid = ${p.id};
                                    var num = $(".productNumberSetting").val();
                                    var addCartpage = "foreaddCart";
                                    $.get(
                                        addCartpage,
                                        {"pid": pid, "num": num},
                                        function (result) {
                                            if ("success" == result) {
                                                $(".addCartButton").html("已加入购物车");
                                                $(".addCartButton").attr("disabled", "disabled");
                                                $(".addCartButton").css("background-color", "lightgray")
                                                $(".addCartButton").css("border-color", "lightgray")
                                                $(".addCartButton").css("color", "black")

                                            }
                                            else {

                                            }
                                        }
                                    );
                                }
                                else {
                                    $("#loginModal").modal('show');
                                }
                            }
                        );
                        return false;
                    });
                    $(".buyLink").click(function () {
                        var page = "forecheckLogin";
                        $.get(
                            page,
                            function (result) {
                                if ("success" == result) {
                                    var num = $(".productNumberSetting").val();
                                    location.href = $(".buyLink").attr("href") + "&num=" + num;
                                }
                                else {
                                    $("#loginModal").modal('show');
                                }
                            }
                        );
                        return false;
                    });

                    $("button.loginSubmitButton").click(function () {
                        var name = $("#name").val();
                        var password = $("#password").val();

                        if (0 == name.length || 0 == password.length) {
                            $("span.errorMessage").html("请输入账号密码");
                            $("div.loginErrorMessageDiv").show();
                            return false;
                        }

                        var page = "foreloginAjax";
                        $.get(
                            page,
                            {"name": name, "password": password},
                            function (result) {
                                if ("success" == result) {
                                    location.reload();
                                }
                                else {
                                    $("span.errorMessage").html("账号密码错误");
                                    $("div.loginErrorMessageDiv").show();
                                }
                            }
                        );

                        return true;
                    });

                    $("img.smallImage").mouseenter(function () {
                        var bigImageURL = $(this).attr("bigImageURL");
                        $("img.bigImg").attr("src", bigImageURL);
                    });

                    $("img.bigImg").load(
                        function () {
                            $("img.smallImage").each(function () {
                                var bigImageURL = $(this).attr("bigImageURL");
                                img = new Image();
                                img.src = bigImageURL;

                                img.onload = function () {
                                    $("div.img4load").append($(img));
                                };
                            });
                        }
                    );

                    $("a.productDetailTopReviewLink").click(function(){
                        $("div.productReviewDiv").show();
                        $("div.productDetailDiv").hide();
                    });
                    $("a.productReviewTopPartSelectedLink").click(function(){
                        $("div.productReviewDiv").hide();
                        $("div.productDetailDiv").show();
                    });

                });

            </script>
            <div class="imgAndInfo">
                <div class="imgInimgAndInfo">
                    <img src="${p.imgUrl}" class="bigImg">
                    <div class="smallImageDiv">
                        <c:forEach items="${singleList}" var="pi">
                            <img src="${pi.value}" bigImageURL="${pi.value}" class="smallImage">
                        </c:forEach>
                    </div>
                    <div class="img4load hidden"></div>
                </div>
                <div class="infoInimgAndInfo">

                    <div class="productTitle">
                        ${p.title}
                    </div>
                    <div class="productSubTitle">
                        ${p.content}
                    </div>


                    <div class="productPrice">
                        <div class="juhuasuan">
                            <span class="juhuasuanBig">聚划算</span>
                            <span>此商品即将参加聚划算，<span class="juhuasuanTime">1天19小时</span>后开始，</span>
                        </div>


                        <div class="productPriceDiv">
                            <div class="gouwujuanDiv"><img height="16px"
                                                           src="${pageContext.request.contextPath}/resources/show/img/gouwujuan.png">
                                <span> 全天猫实物商品通用</span>

                            </div>
                            <div class="originalDiv">
                                <span class="originalPriceDesc">价格</span>
                                <span class="originalPriceYuan">¥</span>
                                <span class="originalPrice">
                        <fmt:formatNumber type="number" value="${p.price}" minFractionDigits="2"/>
                    </span>
                            </div>

                            <div class="promotionDiv">
                                <span class="promotionPriceDesc">促销价 </span>
                                <span class="promotionPriceYuan">¥</span>
                                <span class="promotionPrice">
                        <fmt:formatNumber type="number" value="${p.promotePrice}" minFractionDigits="2"/>
                    </span>
                            </div>
                        </div>
                    </div>

                    <div class="productSaleAndReviewNumber">
                        <div>销量 <span class="redColor boldWord"> ${saleCount }</span></div>
                        <div>累计评价 <span class="redColor boldWord"> ${count}</span></div>
                    </div>
                    <div class="productNumber">
                        <span>数量</span>
                        <span>
                <span class="productNumberSettingSpan">
                <input class="productNumberSetting" type="text" value="1">
                </span>
                <span class="arrow">
                    <a href="#nowhere" class="increaseNumber">
                    <span class="updown">
                            <img src="${pageContext.request.contextPath}/resources/show/img/increase.png">
                    </span>
                    </a>

                    <span class="updownMiddle"> </span>
                    <a href="#nowhere" class="decreaseNumber">
                    <span class="updown">
                            <img src="${pageContext.request.contextPath}/resources/show/img/decrease.png">
                    </span>
                    </a>

                </span>

            件</span>
                        <span>库存${p.stock}件</span>
                    </div>
                    <div class="serviceCommitment">
                        <span class="serviceCommitmentDesc">服务承诺</span>
                        <span class="serviceCommitmentLink">
                <a href="#nowhere">正品保证</a>
                <a href="#nowhere">极速退款</a>
                <a href="#nowhere">赠运费险</a>
                <a href="#nowhere">七天无理由退换</a>
            </span>
                    </div>

                    <div class="buyDiv">
                        <a class="buyLink" href="forebuyone?pid=${p.id}">
                            <button class="buyButton">立即购买</button>
                        </a>
                        <a href="#nowhere" class="addCartLink">
                            <button class="addCartButton"><span class="glyphicon glyphicon-shopping-cart"></span>加入购物车
                            </button>
                        </a>
                    </div>
                </div>

                <div style="clear:both"></div>
            </div>
            <div class="productReviewDiv">
                <div class="productReviewTopPart">
                    <a href="#nowhere" class="productReviewTopPartSelectedLink">商品详情</a>
                    <a href="#nowhere" class="selected">累计评价 <span class="productReviewTopReviewLinkNumber">${count}</span> </a>
                </div>


                <div class="productReviewContentPart">
                    <c:forEach items="${reviews}" var="r">
                        <div class="productReviewItem">

                            <div class="productReviewItemDesc">
                                <div class="productReviewItemContent">
                                        ${r.content }
                                </div>
                                <div class="productReviewItemDate"><fmt:formatDate value="${r.createDate}"
                                                                                   pattern="yyyy-MM-dd"/></div>
                            </div>
                            <div class="productReviewItemUserInfo">${r.user.username}<span class="userInfoGrayPart">（匿名）</span>
                            </div>

                            <div style="clear:both"></div>

                        </div>
                    </c:forEach>
                </div>

            </div>
            <div class="productDetailDiv">
                <div class="productDetailTopPart">
                    <a href="#nowhere" class="productDetailTopPartSelectedLink selected">商品详情</a>
                    <a href="#nowhere" class="productDetailTopReviewLink">累计评价 <span class="productDetailTopReviewLinkNumber">${count}</span> </a>
                </div>


                <div class="productParamterPart">
                    <div class="productParamter">产品参数：</div>

                    <div class="productParamterList">
                        <c:forEach items="${pvs}" var="pv">
                            <span>${pv.shopProperty.name}:  ${fn:substring(pv.shopPropertyValue.value, 0, 10)} </span>
                        </c:forEach>
                    </div>
                    <div style="clear:both"></div>
                </div>

                <div class="productDetailImagesPart">
                    <c:forEach items="${detailList}" var="pi">
                        <img src="${pi.value}">
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../show/footer.jsp" flush="true"/>
</body>
</html>
