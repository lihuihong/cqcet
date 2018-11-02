<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>添加商品</title>

<style>
.unit .left {
	width: 120px !important;
}
</style>
</head>

<body>
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
				<span class="active">添加商品</span>
			</div>
		</div>

		<!--全部主体内容-->
		<div class="list-content">
			<!--块元素-->
			<div class="block">
				<!--修饰块元素名称-->
				<div class="banner">
					<p class="tab fixed">添加商品<span class="hint">product Editors</span></p>
				</div>
				<!--正文-->
				<div class="main">
					<!--表单-->
					<form id="form" action="" method="">
						<input type="hidden" name="id" value="${shopMessageList.id}" />
						<!--输入框-->
						<div class="unit">
							<div class="left">
								<p class="subtitle">商品标题</p>
							</div>
							<div class="right" style="width: 50%">
								<input type="text" class="text" name="title" data-type="必填" placeholder="请输入商品标题" value="${shopMessageList.title}" />
							</div>
							<!--清浮动-->
							<span class="clearfix"></span>
						</div>
						<!--分割线-->
						<p class="divider"></p>

						<!--下拉选择框-->
						<div class="unit">
							<div class="left">
								<p class="subtitle">商品分类</p>
							</div>
							<div class="right" style="width: 60%">
								<select id="category_id" name="categoryId">
									<c:forEach items="${categoryList}" var="categoryInfo" varStatus="status" >
										<option value="${categoryInfo.id}" <c:if test="${shopMessageList.categoryId==categoryInfo.id}">selected</c:if>>${categoryInfo.name}</option>
									</c:forEach>
								</select>
							</div>
							<!--清浮动-->
							<span class="clearfix"></span>
						</div>
						<!--分割线-->
						<p class="divider"></p>
						<!--下拉选择框-->
						<div class="unit">
							<div class="left">
								<p class="subtitle">所属用户</p>
							</div>
							<div class="right" style="width: 60%">
								<select id="user_id" name="userId">
									<c:forEach items="${user}" var="userInfo" varStatus="status" >
										<option value="${userInfo.id}" <c:if test="${shopMessageList.userId==userInfo.id}">selected</c:if>>${userInfo.username}</option>
									</c:forEach>
								</select>
							</div>
							<!--清浮动-->
							<span class="clearfix"></span>

						</div>
						<!--分割线-->
						<p class="divider"></p>
						<div class="unit">
							<div class="left">
								<p class="subtitle">商品原价格</p>
							</div>
							<div class="right" style="width: 8%">
								<input type="text" class="text" name="price" data-type="必填" placeholder="请输入商品原价格" value="${shopMessageList.price}" />
							</div>
							<!--清浮动-->
							<span class="clearfix"></span>
						</div>
						<!--分割线-->
						<p class="divider"></p>

						<div class="unit">
							<div class="left">
								<p class="subtitle">商品优惠价格</p>
							</div>
							<div class="right" style="width: 8%">
								<input type="text" class="text" name="promotePrice" data-type="必填" placeholder="请输入商品优惠价格" value="${shopMessageList.promotePrice}" />
							</div>
							<!--清浮动-->
							<span class="clearfix"></span>
						</div>

						<!--分割线-->
						<p class="divider"></p>

						<div class="unit">
							<div class="left">
								<p class="subtitle">库存</p>
							</div>
							<div class="right" style="width: 8%">
								<input type="text" class="text" name="stock" data-type="必填" placeholder="请输入商品库存" value="${shopMessageList.stock}" />
							</div>
							<!--清浮动-->
							<span class="clearfix"></span>
						</div>

						<!--分割线-->
						<p class="divider"></p>

						<!--商品封面-->
						<div class="unit">
							<div class="left">
								<p class="subtitle">商品封面</p>
							</div>
							<div class="right" style="width: 60%">
								<!--上传按钮-->
								<a href="javascript:;">
									<!-- 图片承载容器 -->
									<label id="container" for="upload" style="display: inline-block; width:132px;height:74px;">
										<c:choose>
											<c:when test="${empty shopMessageList.imgUrl}">
												<img src="${pageContext.request.contextPath}/resources/javaex/pc/images/default.png" width="100%" height="100%" />
											</c:when>
											<c:otherwise>
												<img src="${shopMessageList.imgUrl}" width="100%" height="100%" />
											</c:otherwise>
										</c:choose>
									</label>
									<input type="file" class="hide" id="upload" accept="image/gif, image/jpeg, image/jpg, image/png" />
								</a>
								<input type="hidden" id="imgUrl" name="imgUrl" value="" />
							</div>
							<!--清浮动-->
							<span class="clearfix"></span>
						</div>
						<!--分割线-->
						<p class="divider"></p>
						<!--文本域-->
						<div class="unit">
							<div class="left">
								<p class="subtitle">商品描述</p>
							</div>
							<div class="right" style="width: 60%">
								<div id="edit" class="edit-container"></div>
								<input type="hidden" id="content" name="content" value="" />
								<%--<input type="hidden" id="contentText" name="contentText" value="" />--%>
							</div>
							<!--清浮动-->
							<span class="clearfix"></span>
						</div>
						<!--分割线-->
						<p class="divider"></p>

						<!--提交按钮-->
						<div class="unit">
							<div style="margin-left: 200px;">
								<!--表单提交时，必须是input元素，并指定type类型为button，否则ajax提交时，会返回error回调函数-->
								<input type="button" id="return" class="button no" value="返回" />
								<input type="button" id="submit" class="button yes" value="保存" />
							</div>
							<!--清浮动-->
							<span class="clearfix"></span>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

</div>
</body>
<script>
    javaex.select({
        id : "category_id",
        isSearch : false
    });

    javaex.upload({
        type : "image",
        url : "${pageContext.request.contextPath}/admin/upload.json",	// 请求路径
        id : "upload",	// <input type="file" />的id
        param : "file",			// 参数名称，SSM中与MultipartFile的参数名保持一致
        dataType : "url",		// 返回的数据类型：base64 或 url
        callback : function (rtn) {
            // 后台返回的数据
            if (rtn.code=="000000") {
                var imgUrl = rtn.data.imgUrl;
                $("#container img").attr("src", imgUrl);
                $("#imgUrl").val(imgUrl);
            } else {
                javaex.optTip({
                    content : rtn.msg,
                    type : "error"
                });
            }
        }
    });

    var content = '${shopMessageList.content}';
    javaex.edit({
        id : "edit",
        image : {
            url : "${pageContext.request.contextPath}/admin/upload.json",	// 请求路径
            param : "file",		// 参数名称，SSM中与MultipartFile的参数名保持一致
            dataType : "url",	// 返回的数据类型：base64 或 url
            rtn : "rtnData",	// 后台返回的数据对象，在前面页面用该名字存储
            imgUrl : "data.imgUrl"	// 根据返回的数据对象，获取图片地址。  例如后台返回的数据为：{code: "000000", message: "操作成功！", data: {imgUrl: "1.jpg"}}
        },
        content : content.trim(),	// 这里必须是单引号，因为html代码中都是双引号，会产生冲突
        callback : function(rtn) {
            console.log(rtn);
            $("#content").val(rtn.html);
			/*$("#contentText").val(rtn.text.substring(0, 100));*/
        }
    });

    $("#return").click(function() {
        history.back();
    });

    $("#submit").click(function() {
        var data = $("#form").serialize();
        $.ajax({
            url : "save.json",
            type : "POST",
            dataType : "json",
            data : data,
            success : function(rtn) {
                if (rtn.code=="000000") {
                    javaex.optTip({
                        content : rtn.message
                    });
                    // 建议延迟加载
                    setTimeout(function() {
                        // 跳转页面
                        window.location.href = "${pageContext.request.contextPath}/admin/shop/list.action";
                    }, 2000);
                } else {
                    javaex.optTip({
                        content : rtn.message,
                        type : "error"
                    });
                }
            },
            error :function (e) {
                javaex.optTip({
                    content : e.message
                });
            }
        });
    });
</script>
</html>
