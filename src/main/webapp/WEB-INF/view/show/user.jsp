<%--
  Created by IntelliJ IDEA.
  User: zcq
  Date: 2018/10/9
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人中心</title>
    <link href="${pageContext.request.contextPath}/resources/show/head.css" type="text/css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/resources/javaex/pc/js/javaex.min.js"></script>
    <style>
        *, :before, :after {
            -webkit-box-sizing: border-box;
            box-sizing: border-box;
        }
        a.indigo{
            color: #fff;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp" flush="true"/>

<div class="content">
    <div class="container">

        <div class="row">
            <jsp:include page="meun.jsp" flush="true"/>
            <div class="col-md-9">
                <div class="widget">
                    <div class="widget-header">
                        <h3>基本信息</h3>
                    </div>
                    <div class="widget-content">
                        <div class="tabbable">
                            <ul class="nav nav-tabs">
                                <li class="active">
                                    <a href="#1" data-toggle="tab">简况</a>
                                </li>
                                <li><a href="#2" data-toggle="tab">安全信息</a></li>
                                <li><a href="#3" data-toggle="tab">修改头像</a></li>
                            </ul>
                            <br/>
                            <div class="tab-content">
                                <div class="tab-pane active" id="1">
                                    <form id="edit-profile" class="form-horizontal"/>
                                    <fieldset>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">用户名</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-medium" name="username"
                                                       value="${user.username}" disabled=""/>
                                                <p class="help-block">这是您登录的名字，注册后无法更改！</p>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">所属专业</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="input-medium" name="professionalId"
                                                       value="${user.professionalName}" disabled=""/>
                                                <p class="help-block">这是您的专业，不可修改！</p>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">学号</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="input-medium" name="studentId"
                                                       value="${user.studentId}" disabled=""/>
                                                <p class="help-block">这是您的学号，不可修改！</p>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">用户等级</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="input-large input-medium" name="groupnaame"
                                                       value="${user.groupName}" disabled=""/>
                                                <p class="help-block">这是您的用户等级，不可修改！</p>
                                            </div>
                                        </div>
                                        <br/><br/>

                                        <br/>
                                    </fieldset>
                                    </form>
                                </div>
                                <div class="tab-pane" id="2">
                                    <form id="edit-profile2" class="form-horizontal"/>
                                    <fieldset>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">旧密码</label>
                                            <div class="col-sm-10">
                                                <input type="password" class="input-medium" name="oldPassword"
                                                       value=""/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">新密码</label>
                                            <div class="col-sm-10">
                                                <input type="password" class="input-medium" name="newPassword1"
                                                       value=""/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">确认新密码</label>
                                            <div class="col-sm-10">
                                                <input type="password" class="input-medium" name="newPassword2"
                                                       value=""/>
                                            </div>
                                        </div>
                                        <br/>
                                        <div class="form-actions" style="padding-left: 140px;">
                                            <button type="button" class="btn btn-primary" onclick="save()">保存</button>
                                            <button class="btn">取消</button>
                                        </div>
                                    </fieldset>
                                    </form>
                                </div>
                                <div class="tab-pane" id="3">
                                    <!--静态提示-->
                                    <p class="tip warning">请勿使用包含不良信息或敏感内容的图片作为用户头像。</p>
                                    <!--分割线-->
                                    <p class="divider"></p>
                                    <!--上传组件区域-->
                                    <div class="unit">
                                        <a href="javascript:;" class="button indigo">
                                            <label for="upload-avatar">点击这里上传图片</label>
                                        </a>
                                        <input type="file" id="upload-avatar" class="hide"
                                               accept="image/gif, image/jpeg, image/jpg, image/png"/>
                                        <p class="hint">支持JPG、GIF、PNG格式，文件应小于5M，文件太大将导致无法读取</p>
                                    </div>
                                    <!--分割线-->
                                    <p class="divider"></p>
                                    <!--头像上传预览区域-->
                                    <div class="unit">
                                        <div class="original">
                                            <div id="image-box" class="image-box">
                                                <!--裁剪层-->
                                                <div id="cut-box" class="cut-box"></div>
                                                <!--背景层（可移动图片）-->
                                                <div id="move-box" class="move-box"></div>
                                            </div>
                                            <!--放大、缩小-->
                                            <span id="narrow" class="icon-zoom-out"
                                                  style="color: #666;font-size: 20px;"></span>
                                            <span id="enlarge" class="icon-zoom-in"
                                                  style="color: #666;font-size: 20px;float: right;"></span>
                                        </div>
                                        <!--裁剪后的预览区域-->
                                        <div class="preview">
                                            <!--静态提示-->
                                            <p class="tip">
                                                您上传的头像会自动生成3种尺寸，请注意头像是否清晰。
                                            </p>
                                            <div class="view">
                                                <div class="view-avatar180">
                                                    <div class="avatar180"></div>
                                                    <p class="hint">大尺寸头像，180像素X180像素</p>
                                                </div>
                                                <div class="view-avatar50">
                                                    <div class="avatar50"></div>
                                                    <p class="hint">中尺寸头像，50像素X50像素</p>
                                                </div>
                                                <div class="view-avatar30">
                                                    <div class="avatar30"></div>
                                                    <p class="hint">小尺寸头像，30像素X30像素</p>
                                                </div>
                                            </div>
                                        </div>
                                        <!--自动返回裁剪后的图片地址-->
                                        <input type="hidden" id="data-url" value=""/>
                                        <!--清浮动-->
                                        <span class="clearfix"></span>
                                    </div>
                                    <!--分割线-->
                                    <p class="divider"></p>
                                    <!--保存头像区域-->
                                    <div class="form-actions" style="padding-left: 140px;">
                                        <button type="button" class="btn btn-primary" onclick="saveAvatar()" >保存头像</button>
                                    </div>
                                    <!--
                                    <div class="unit">
                                        <button class="button navy" onclick="saveAvatar()">保存头像</button>
                                    </div>
                                    -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<jsp:include page="footer.jsp" flush="true"/>

<script>

    // 点击上传（必须用change）
    $("#upload-avatar").change(function() {
        javaex.uploadAvatar(
            this,	// 必填
            {
                imgDivId : "image-box",	// 本地上传的图片区域id
                cutBox : "cut-box",	// 裁剪区域id
                moveBox : "move-box",	// 背景区域id，可拖动
                dataUrl : "data-url",	// 最终将图片地址返回给哪个input存储
                type : "base64"		// 图片地址类型，目前仅支持base64
            }
        );
    });

    // 缩小
    $("#narrow").click(function() {
        javaex.narrow();
    });

    // 放大
    $("#enlarge").click(function() {
        javaex.enlarge();
    });

    //保存信息
    function save() {

        $.ajax({
            url : "/show/user/save_password.json",
            type : "POST",
            dataType : "json",
            data : $("#edit-profile2").serialize(),
            success : function(rtn) {
                if (rtn.code=="000000") {
                    helper.toast({
                        content : "修改密码成功",
                        type : "success"
                    });
                    // 返回当前页面
                    window.location.href = "${refererUrl}";
                    return false;
                } else {
                    helper.toast({
                        content : rtn.message,
                        type : "error"
                    });
                }
            }
        });
    }

    // 保存用户头像
    function saveAvatar() {
        helper.toast({
            content: "上传中...",
            type: "success"
        });
        $.ajax({
            url : "/show/user/head_avatar.json",
            type : "POST",
            dataType : "json",
            data : {
                "avatar" : $("#data-url").val()
            },
            success : function(rtn) {
                if (rtn.code=="000000") {
                    helper.toast({
                        content : "修改成功",
                        type : "success"
                    });
                    // 返回当前页面
                    window.location.href = "${refererUrl}";
                } else {
                    helper.toast({
                        content : rtn.message,
                        type : "error"
                    });
                }
            },
            error: function (rtn) {
                helper.toast({
                    content: rtn.message,
                    type: "error"
                });
            }
        });
    }

</script>
</body>
</html>
