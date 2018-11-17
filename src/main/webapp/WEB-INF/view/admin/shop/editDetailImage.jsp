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
    <script src="${pageContext.request.contextPath}/resources/updateImage/jquery.min.js"></script>
    <link href="${pageContext.request.contextPath}/resources/updateImage/style.css" type="text/css" rel="stylesheet"/>
    <!--核心组件-->
    <script src="${pageContext.request.contextPath}/resources/javaex/pc/js/javaex.js"></script>
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
                    <p class="tab fixed">上传商品详情图片<span class="hint">Product Editors</span></p>
                    <a href="${pageContext.request.contextPath}/admin/shop/productImage_single.action?productImageId=${productImageId}">
                        <button class="button blue"><span class="icon-eye"></span>商品单个图片管理</button>
                    </a>
                </div>
                <div>
                    <form id="form" action="" method="">
                        <div id="zhl_admin_box" class="zhl_admin_update_more"> <!-- 在这里更改class选择器名称，显示不同的样式-->
                            <div id="zhl_admin_update">
                                <input type="file" id="zhl_admin_file"  style="display:none"
                                       multiple="multiple">
                                <span id="zhl_admin_imgs"></span>
                                <c:choose>
                                <c:when test="${fn:length(detailList)==0}">
                                </c:when>
                                <c:otherwise>
                                <span id="zhl_admin_imgs1">
                                        <c:forEach items="${detailList}" var="entity" varStatus="status" >
                                        <img class="zhl_admin_update_imgtmp1" src="${entity.value}">
                                        </c:forEach>
                                </span>
                                </c:otherwise>
                                </c:choose>
                                <img src="${pageContext.request.contextPath}/resources/updateImage/photo_icon.jpg"
                                     tppabs="http://www.17sucai.com/preview/3177/2018-10-22/FileReaderBase64/img/photo_icon.jpg"
                                     id="zhl_admin_img" onclick="zhl_admin_file.click()"/>
                                <div id="zhl_admin_update_btn">上传</div>
                                <span id="zhl_update_hint">（点击图片删除）最多传10张</span>
                            </div>
                        </div>
                    </form>
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
</div>
<script>

    // 上传数量控制，给出默认值
    if($("#zhl_admin_box").is('.zhl_admin_update_no') || $("#zhl_admin_box").is('.zhl_admin_update_two')){
        var zhl_update_num = 1;
    }else{
        var zhl_update_num = 10;
    }
    // 只允许上传单个图片时移除文件多选属性
    $(".zhl_admin_update_no #zhl_admin_file").removeAttr('multiple');
    $(".zhl_admin_update_two #zhl_admin_file").removeAttr('multiple');

    // 上传数量控制，判断是否定义并赋值
    zhl_update_num = typeof zhl_update_num != "undefined" && zhl_update_num ? zhl_update_num : 1;
    // 上传大小控制，当前为1M
    var zhl_update_size = 1*1024*1024;
    /**
     * 文件选择触发
     */
    $("#zhl_admin_file").change(function (){
        var fileAll = this.files.length;
        for(var i=0;i<fileAll;i++){
            var file = this.files[i];
            if( file.size >  zhl_update_size){
                var d = parseInt(i+1);
                alert("你选择的文件第"+d+"个太大了！");
                return false;
            }
            if(!/image\/\w+/.test(file.type)){
                alert("文件必须为图片！");
                return false;
            }
            var reader = new FileReader();
            //读取文件过程方法
            reader.onerror = function (e) {
                console.log("文件读取异常....");alert('文件上传异常请关闭重试....');
            }
            reader.onabort = function(e) {
                console.log("文件读取异常....");alert('文件上传异常请关闭重试....');
            };
            reader.onload = function (e) {
                var imgstr='<img src="'+e.target.result+'" class="zhl_admin_update_imgtmp" />';
                //限制上传的图片数
                var len = $('#zhl_admin_imgs>img').length;
                if(len < zhl_update_num){
                    if(len == zhl_update_num-1){
                        $('#zhl_admin_img').hide();
                    }else{
                        $('#zhl_admin_img').show();
                    }
                    $('#zhl_admin_imgs').append(imgstr);
                }else{
                    alert('最多'+zhl_update_num+'张图片');
                    $('#zhl_admin_img').hide();
                    return false;
                }
            }
            reader.readAsDataURL(file);
        }
    });

    /**
     * 点击删除预选的图片
     */
    $("#zhl_admin_imgs").delegate("img","click",function(){
        if(confirm('删除该张图片？')){
            $(this).remove();
            $('#zhl_admin_img').show();
        }
    });

    $("#zhl_admin_imgs1").delegate("img","click",function(){
        if(confirm('删除该张图片？')){
            $(this).remove();
            $('#zhl_admin_img1').show();
            $.ajax({
                url: "image_delete.json",
                type: "POST",
                dataType: "json",
                data: {
                    "imageUrl": $(this).attr("src"),
                },
                traditional: true,//这里设置为true
                success: function (rtn) {
                    if (rtn.code == "000000") {
                        javaex.optTip({
                            content: "删除"+rtn.message,
                            type: "success"
                        });
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

    /**
     * 点击上传按钮
     */
    $('#zhl_admin_update_btn').on('click',function(){
        var base64Data = [];
        $("#zhl_admin_imgs img").each(function(e){
            base64Data[e] = $(this).attr('src');
        });
        if(!judgeEmpty(base64Data)){
            base64_uploading(base64Data);
        }else{
            alert('请选择文件');return false;
        }
    })

    /**
     * 判断文件是否为空
     */
    function judgeEmpty(data) {
        return (Array.isArray(data) && data.length === 0) || (Object.prototype.isPrototypeOf(data) && Object.keys(data).length === 0);
    }

    /**
     * ajax上传base64自定义函数
     */
    var imageUrl = new Array();
    function base64_uploading(base64Data){
        // ajax 上传
        for(var j = 0,len = base64Data.length; j < len; j++) {
            $.post("upload.json", {image: base64Data[j]}, function (data) {
                if (data.code == "000000") {
                    var imgUrl = data.data.imgUrl;
                    imageUrl.push(imgUrl);
                    javaex.optTip({
                        content: "上传"+data.message,
                        type: "success"
                    });
                }
            }, 'json');
        }
    }
    $("#return").click(function() {
        history.back();
    });
    // 点击保存按钮事件
    $("#save").click(function () {
        if(imageUrl.length == 0){
            javaex.optTip({
                content: "请先上传...",
                type: "submit"
            });
        }else {
            javaex.optTip({
                content: "数据提交中，请稍候...",
                type: "submit"
            });
            $.ajax({
                url: "image_save_detail.json",
                type: "POST",
                dataType: "json",
                data: {
                    "imageUrl": imageUrl,
                    "pid" : "${productImageId}",
                },
                traditional: true,//这里设置为true
                success: function (rtn) {
                    if (rtn.code == "000000") {
                        javaex.optTip({
                            content: "保存"+rtn.message,
                            type: "success"
                        });
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
</body>
</html>
