<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>商品管理</title>
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
						<span>商品管理</span>
						<span class="divider">/</span>
						<span class="active">商品列表</span>
					</div>
				</div>
				<!--全部主体内容-->
				<div class="list-content">
					<!--块元素-->
					<div class="block">
						<!--页面有多个表格时，可以用于标识表格-->
						<h2>商品列表</h2>
						<!--正文内容-->
						<div class="main-20">
							<div style="text-align:right;margin-bottom:10px;">
								<!-- 文章分类 -->
								<select id="category_id" class="no-shadow">
									<option value="">请选择商品分类</option>
									<c:forEach items="${categoryList}" var="categoryInfo" varStatus="status" >
										<option value="${categoryInfo.id}" <c:if test="${categoryInfo.id==categoryId}">selected</c:if>>${categoryInfo.name}</option>
									</c:forEach>
								</select>
								<!-- 日期范围 -->
								<input type="text" id="date2" class="date" style="width: 300px;" value="" readonly/>
								<!-- 标题检索 -->
								<input type="text" class="text" id="title" value="${keyWord}" placeholder="检索商品标题" />
								<!-- 点击查询按钮 -->
								<button class="button blue" style="margin-top: -3px;" onclick="search()"><span class="icon-search"></span></button>
							</div>
							
							<!--表格上方的操作元素，添加、删除、搜索等-->
							<div class="operation-wrap">
								<div class="buttons-wrap">
									<a href="${pageContext.request.contextPath}/admin/shop/edit.action">
										<button id="add" class="button blue"><span class="icon-plus"></span> 添加商品</button>
									</a>
									<a href="${pageContext.request.contextPath}/admin/shop/category_edit.action">
										<button id="addcategory" class="button blue"><span class="icon-plus"></span> 商品分类管理</button>
									</a>
								</div>
							</div>
							<table id="table" class="table">
								<thead>
									<tr>
										<th style="width:20px;"><input type="checkbox" class="fill listen-1"/> </th>
										<th>商品图片</th>
										<th>分类</th>
										<th>商品标题</th>
										<th>原价格</th>
										<th>优惠价格</th>
										<th>库存数量</th>
										<th>上架日期</th>
										<th>图片管理</th>
										<th>设置属性</th>
										<th>编辑</th>
									</tr>
								</thead>
								<tbody>
									<c:choose>
										<c:when test="${fn:length(pageInfo.list)==0}">
											<tr>
												<td colspan="7" style="text-align:center;">暂无商品</td>
											</tr>
										</c:when>
										<c:otherwise>
											<c:forEach items="${pageInfo.list}" var="entity" varStatus="status" >
												<tr>
													<td><input type="checkbox" class="fill listen-1-2" name="id" value="${entity.id}" /> </td>
													<td>
														<div style="width:50px;height:50px;overflow:hidden;">
															<a href="javascript:;" onclick="img(this)">
																<img src="${entity.imgUrl}" width="100%">
															</a>
														</div>
													</td>
													<td>${entity.categoryName}</td>
													<td>${entity.title}</td>
													<td>${entity.price}</td>
													<td>${entity.promotePrice}</td>
													<td>${entity.stock}</td>
													<td><fmt:formatDate value="${entity.updateTime}" pattern="yyyy/MM/dd  HH:mm:ss" /></td>
													<td>
														<a href="${pageContext.request.contextPath}/admin/shop/productImage.action?productImageId=${entity.id}">
															<button class="button blue"><span class="icon-eye"></span>图片管理</button>
														</a>
													</td>
													<td>
														<a href="${pageContext.request.contextPath}/admin/shop/property.action?categoryId=${entity.categoryId}&shopId=${entity.id}">
															<button class="button green"><span class="icon-tags"></span>商品属性</button>
														</a>
													</td>
													<td>
														<a href="${pageContext.request.contextPath}/admin/shop/edit.action?id=${entity.id}">
															<button class="button wathet"><span class="icon-edit"></span>商品编辑</button>
														</a>
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
							
							<!--块元素-->
							<div class="block no-shadow">
								<!--banner用来修饰块元素的名称-->
								<div class="banner">
									<p class="tab fixed">批量操作<span class="hint">Batch Opt</span></p>
								</div>
								<!--正文内容-->
								<div class="main" style="margin-bottom:200px;">
									<label zoom="1.0"><input type="radio" class="fill" name="radio" value="move" />批量移动到分类  </label>
									<select id="category_id2" class="no-shadow">
										<c:forEach items="${categoryList}" var="categoryInfo" varStatus="status" >
											<option value="${categoryInfo.id}">${categoryInfo.name}</option>
										</c:forEach>
									</select>
									<br />
									<label zoom="1.0"><input type="radio" class="fill" name="radio" value="recycle" />批量删除</label>
									<br />
									<button id="submit" class="button green" style="margin-top:20px;"><span class="icon-check"></span> 提交</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	var currentPage = "${pageInfo.pageNum}";
	var pageCount = "${pageInfo.pages}";

	javaex.select({
		id : "category_id",
		isSearch : false
	});
	
	javaex.select({
		id : "category_id2",
		isSearch : false
	});
	
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

    function img(obj) {
        javaex.dialog({
            type : "image",	// 弹出层类型
            width : "400",	// 弹出层宽度
            url : obj.children[0].src	// 图片地址
        });
    }
	
	function search(pageNum) {
		if (pageNum==undefined) {
			pageNum = 1;
		}
		
		// 文章分类
		var categoryId = $("#category_id").val();
		// 关键字检索
		var keyWord = $("#title").val();
		
		window.location.href = "list.action"
				+ "?pageNum="+pageNum
				+ "&categoryId="+categoryId
				+ "&startDate="+startDate
				+ "&endDate="+endDate
				+ "&keyWord="+keyWord
				;
	}
	
	// 批量提交按钮点击事件
	$("#submit").click(function() {
		var idArr = new Array();
		$(':checkbox[name="id"]:checked').each(function() {
			idArr.push($(this).val());
		});
		
		// 判断至少选择一条记录
		if (idArr.length==0) {
			javaex.optTip({
				content : "至少选择一条记录",
				type : "error"
			});
			return;
		}
		
		// 判断选择的哪一个单选框进行操作
		var opt = $(':radio[name="radio"]:checked').val();
		if (opt=="move") {
			// 获取目标分类的id
			var categoryId = $("#category_id2").val();
			
			$.ajax({
				url : "move.json",
				type : "POST",
				dataType : "json",
				traditional : "true",
				data : {
					"idArr" : idArr,
					"categoryId" : categoryId
				},
				success : function(rtn) {
					if (rtn.code=="000000") {
						javaex.optTip({
							content : "批量移动"+rtn.message
						});
						// 建议延迟加载
						setTimeout(function() {
							// 刷新页面
							window.location.reload();
						}, 2000);
					} else {
						javaex.optTip({
							content : rtn.message,
							type : "error"
						});
					}
				}
			});
		} else if (opt=="recycle") {
			$.ajax({
				url : "bacth_delete.json",
				type : "POST",
				dataType : "json",
				traditional : "true",
				data : {
					"idArr" : idArr
				},
				success : function(rtn) {
					if (rtn.code=="000000") {
						javaex.optTip({
							content : "批量删除"+rtn.message
						});
						// 建议延迟加载
						setTimeout(function() {
							// 刷新页面
							window.location.reload();
						}, 2000);
					} else {
						javaex.optTip({
							content : rtn.message,
							type : "error"
						});
					}
				}
			});
		}
	});
</script>
</html>