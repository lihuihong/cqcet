<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>交易管理</title>
</head>

<body>
	<div class="wrap">
		<!-- 头部 -->
		<div id="header">
			<c:import url="../header.jsp"></c:import>
		</div>
		<!-- 左侧菜单和主体内容 -->
		<div class="grid-1-7" style="flex: 1;margin:0;">
			<!-- 左侧菜单 -->
			<c:import url="../menu.jsp"></c:import>
			<!-- 面包屑导航和主体内容 -->
			<div class="content">
				<!--面包屑导航-->
				<div class="content-header">
					<div class="breadcrumb">
						<span>交易管理</span>
						<span class="divider">/</span>
						<span class="active">订单管理</span>
					</div>
				</div>
				<!--全部主体内容-->
				<div class="list-content">
					<!--块元素-->
					<div class="block">
						<!--页面有多个表格时，可以用于标识表格-->
						<h2>订单管理</h2>
						<!--正文内容-->
						<div class="main-20">
							<div style="text-align:right;margin-bottom:10px;">

								<!-- 日期范围 -->
								<input type="text" id="date2" class="date" style="width: 300px;" value="" readonly/>
								<!-- 标题检索 -->
								<input type="text" class="text" id="title" value="${keyWord}" placeholder="检索商品标题" />
								<!-- 点击查询按钮 -->
								<button class="button blue" style="margin-top: -3px;" onclick="search()"><span class="icon-search"></span></button>
							</div>
							<table id="table" class="table">
								<thead>
									<tr>
										<th>订单号</th>
										<th>状态</th>
										<th>金额</th>
										<th>商品数量</th>
										<th>买家名称</th>
										<th>创建时间</th>
										<th>支付时间</th>
										<th>发货时间</th>
										<th>确认收货时间</th>
										<th>编辑</th>
									</tr>
								</thead>
								<tbody>
									<c:choose>
										<c:when test="${fn:length(pageInfo.list)==0}">
											<tr>
												<td colspan="11" style="text-align:center;">暂无商品</td>
											</tr>
										</c:when>
										<c:otherwise>
											<c:forEach items="${pageInfo.list}" var="entity" varStatus="status" >


												<tr>
													<td>${entity.orderCode}</td>
													<td>${entity.statusDesc}</td>
													<td>￥<fmt:formatNumber type="number" value="${entity.total}" minFractionDigits="2"/></td>
													<td align="center">${entity.totalNumber}</td>
													<td align="center">${entity.user.username}</td>

													<td><fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
													<td><fmt:formatDate value="${entity.payDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
													<td><fmt:formatDate value="${entity.deliveryDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
													<td><fmt:formatDate value="${entity.confirmDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>

													<td>
															<button class="button green orderPageCheckOrderItems" oid=${entity.id}><span class="icon-tags"></span>查看详情</button>

														<c:if test="${entity.status=='waitDelivery'}">
															<a href="order_delivery?id=${entity.id}">
																<button class="button wathet"><span class="icon-edit"></span>发货</button>
															</a>
														</c:if>
													</td>
												</tr>

												<tr class="orderPageOrderItemTR" style="display:none;" oid=${entity.id}>
													<td colspan="10" align="center">

														<div  class="orderPageOrderItem" style=" width:100%;padding:20px;">
															<table width="800px" align="center" class="orderPageOrderItemTable">
																<c:forEach items="${entity.orderItems}" var="oi">
																	<tr>
																		<td align="left">
																			<img width="40px" height="40px" src="${oi.shopMessage.imgUrl}">
																		</td>

																		<td>
																			<a href="foreproduct?pid=${oi.shopMessage.id}">
																				<span>${oi.shopMessage.title}</span>
																			</a>
																		</td>
																		<td align="right">

																			<span class="text-muted">${oi.number}个</span>
																		</td>
																		<td align="right">

																			<span class="text-muted">单价：￥${oi.shopMessage.promotePrice}</span>
																		</td>

																	</tr>
																</c:forEach>

															</table>
														</div>

													</td>
												</tr>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</tbody>
							</table>
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
</body>
<script>
    $(function(){
        $("button.orderPageCheckOrderItems").click(function(){
            var oid = $(this).attr("oid");
            $("tr.orderPageOrderItemTR[oid="+oid+"]").toggle();
        });
    });

</script>
<script>
	var currentPage = "${pageInfo.pageNum}";
	var pageCount = "${pageInfo.pages}";


	
	var startDate = "";
	var endDate = "";
	javaex.date({
		id : "date2",		// 承载日期组件的id
		monthNum : 2,	// 2代表选择范围日期
		alignment : "right",
		startDate: "${startDate}",	// 开始日期
		endDate: "${endDate}",	// 结束日期
		// 重新选择日期之后返回一个时间对象
		callback: function (rtn) {
			startDate = rtn.startDate;
			endDate = rtn.endDate;
		}
	});
	
	javaex.page({
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

		// 关键字检索
		var keyWord = $("#title").val();
		
		window.location.href = "list.action"
				+ "?pageNum="+pageNum
				+ "&startDate="+startDate
				+ "&endDate="+endDate
				+ "&keyWord="+keyWord
				;
	}

</script>
</html>