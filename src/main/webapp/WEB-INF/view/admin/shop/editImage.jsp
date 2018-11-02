<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>商品图片管理</title>

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
                <span class="active">商品图片</span>
            </div>
        </div>
        <!--全部主体内容-->
        <div class="list-content">
            <!--块元素-->
            <div class="block">
                <!--修饰块元素名称-->
                <div class="banner">
                    <p class="tab fixed">上传商品图片<span class="hint">Product Editors</span></p>
                </div>
                <!--正文内容-->
                <div class="main">
                    <a href="javascript:;" class="file-container button indigo">
                        选择图片
                        <input type="file" class="file" id="upload" multiple
                               accept="image/gif, image/jpeg, image/jpg, image/png"/>
                    </a>
                    <!--分割线-->
                    <p class="divider"></p>
                    <!-- 图片存储容器 -->
                    <div id="container"></div>
                </div>
                <div style="display: none;">
                <form id="form" action="" method="">
                    <input type="hidden" id="imgUrl0" name="value1" value="" />
                    <input type="hidden" id="imgUrl1" name="value2" value="" />
                    <input type="hidden" id="imgUrl2" name="value3" value="" />
                    <input type="hidden" id="imgUrl3" name="value4" value="" />
                    <input type="hidden"  name="pid" value="${shopProductImage.pid}" />
                </form>
                </div>
            </div>

            <!--提交按钮-->
            <div class="unit" style="width: 800px; margin-top: 100px">
                <div style="text-align: center;">
                    <!--表单提交时，必须是input元素，并指定type类型为button，否则ajax提交时，会返回error回调函数-->
                    <input type="button" id="return" class="button no" value="返回"/>
                    <input type="button" id="save" class="button yes" value="保存"/>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    var imageUrl = ['${shopProductImage.value1}','${shopProductImage.value2}','${shopProductImage.value3}','${shopProductImage.value4}'];

    javaex.upload({
        type: "images",
        id: "upload",	// <input type="file" />的id
        containerId: "container",	// 图片承载容器的id
        maxNum: 5,			// 文件上传数量上限
        imgUrl: imageUrl,		// 回显图片（包含图片地址的 数组 或 List。切记：不能用引号包裹起来）
        width: 200,		// 显示的小图的宽度（单位：像素）
        height: 200,		// 显示的小图的高度（单位：像素）
        dataType: "base64",	// 返回的数据类型：base64
        callback: function (rtn) {
            // 返回图片的base64数组
            // 后台返回的数据
            // 上传图片
            for(var j = 0,len = rtn.length; j < len; j++){
                console.log(rtn[j]);

                $.ajax({
                    url: "upload.json",
                    type: "POST",
                    dataType: "json",
                    data: {
                        file64 : rtn[j]
                    },
                    success: function (rtn) {
                        if (rtn.code == "000000") {
                            var imgUrl = rtn.data.imgUrl;
                            $("#imgUrl1[j]").val(imgUrl);
                            javaex.optTip({
                                content: rtn.message,
                                type: "success"
                            });
                        } else {
                            javaex.optTip({
                                content: rtn.message,
                                type: "error"
                            });
                        }
                    }
                })
                /*javaex.upload({
                    type: "image",
                    url: "upload.json",	// 请求路径
                    id: "upload",	// <input type="file" />的id
                    maxSize: "5120",
                    param: "file",			// 参数名称，SSM中与MultipartFile的参数名保持一致
                    dataType: "url",		// 返回的数据类型：base64 或 url
                    callback: function (rtn) {
                        // 后台返回的数据
                        if (rtn.code == "000000") {
                            var imgUrl = rtn.data.imgUrl;
                            $("#imgUrl1[j]").val(imgUrl);
                        } else {
                            javaex.optTip({
                                content: rtn.message,
                                type: "error"
                            });
                        }
                    }
                });*/
            }

        }
    });


    $("#return").click(function () {
        history.back();
    });

    // 点击保存按钮事件
    $("#save").click(function () {
        if (javaexVerify()) {
            javaex.optTip({
                content: "数据提交中，请稍候...",
                type: "submit"
            });

            $.ajax({
                url: "image_save.json",
                type: "POST",
                dataType: "json",
                data: $("#form").serialize(),
                success: function (rtn) {
                    if (rtn.code == "000000") {
                        javaex.optTip({
                            content: rtn.message,
                            type: "success"
                        });
                        // 建议延迟加载
                        setTimeout(function () {
                            // 跳转页面
                            window.location.href = "list_normal.action";
                        }, 2000);
                    } else {
                        javaex.optTip({
                            content: rtn.message,
                            type: "error"
                        });
                    }
                }
            });
        }
    });
</script>
</html>
