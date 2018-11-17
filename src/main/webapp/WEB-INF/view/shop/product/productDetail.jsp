

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	
<div class="productDetailDiv" >
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
		<%--<c:forEach items="${p.productDetailImages}" var="pi">
			<img src="img/productDetail/${pi.id}.jpg">
		</c:forEach>--%>
			<img src="${pi.value1}" bigImageURL="${pi.value1}" class="smallImage">
			<img src="${pi.value2}" bigImageURL="${pi.value2}" class="smallImage">
			<img src="${pi.value3}" bigImageURL="${pi.value3}" class="smallImage">
			<img src="${pi.value4}" bigImageURL="${pi.value4}" class="smallImage">
	</div>
</div>

