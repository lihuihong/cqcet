<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="menu" class="menu" style="width: 260px;">
	<div class="menu-title">
		<h1><i>论坛后台管理</i></h1>
	</div>
	<ul>

		<li class="menu-item">
			<a href="javascript:;"><span>学院及专业管理</span><i class="my-icon menu-more"></i></a>
			<ul>
				<li><a href="${pageContext.request.contextPath}/collage/college_list.action"><span>学院管理</span></a></li>
				<li><a href="${pageContext.request.contextPath}/collage/professional_list.action"><span>专业管理</span></a></li>
			</ul>

		</li>

		<li class="menu-item">
			<a href="javascript:;"><span>用户管理</span><i class="my-icon menu-more"></i></a>
			<ul>
				<%--<li><a href="${pageContext.request.contextPath}/type/list.action"><span>管理组</span></a></li>--%>
				<li><a href="${pageContext.request.contextPath}/type/userGrade.action"><span>会员用户组</span></a></li>
				<li><a href="${pageContext.request.contextPath}/user/list_normal.action"><span>正常用户</span></a></li>
				<li><a href="${pageContext.request.contextPath}/user/list_ban.action"><span>封禁用户</span></a></li>
			</ul>

		</li>

		<li class="menu-item">
			<a href="javascript:;"><span>帖子分类管理</span><i class="my-icon menu-more"></i></a>
			<ul>
				<li><a href="${pageContext.request.contextPath}/type/list.action"><span>帖子分类</span></a></li>
			</ul>
		</li>
		<li class="menu-item">
			<a href="javascript:;"><span>帖子管理</span><i class="my-icon menu-more"></i></a>
			<ul>
				<li><a href="${pageContext.request.contextPath}/admin/list.action"><span>帖子列表</span></a></li>
				<li><a href="${pageContext.request.contextPath}/admin/recycle.action"><span>回收站</span></a></li>
			</ul>
		</li>
		<li class="menu-item">
			<a href="javascript:;"><span>商品交易</span><i class="my-icon menu-more"></i></a>
			<ul>
				<li><a href="${pageContext.request.contextPath}/admin/shop/list.action"><span>商品管理</span></a></li>
				<li><a href="${pageContext.request.contextPath}/admin/shop/shopOrder.action"><span>订单管理</span></a></li>
				<li><a href="${pageContext.request.contextPath}/admin/recycle.action"><span>交易管理</span></a></li>
			</ul>
		</li>
	</ul>
</div>

<script>
	javaex.menu({
		id : "menu",
		isAutoSelected : true
	});
</script>