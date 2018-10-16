<%--
  Created by IntelliJ IDEA.
  User: zcq
  Date: 2018/10/10
  Time: 8:49
  发帖页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>发布帖子</title>
    <script src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"></script>
    <script src="${pageContext.request.contextPath}/resources/ckeditor/config.js"></script>
</head>
<body>
<jsp:include page="header.jsp" flush="true"/>


<div class="container content">
    <div class="row">
        <div class="col-md-9">
            <div class="article-content" style="padding: 20px 40px;">
                <form action="">
                    <h3>发布帖子</h3>
                    <br/>
                    <hr/>
                    <br/>
                    <div name="form1">
                        <textarea name="title" id="title" class="answer-input form-control" rows="2"
                                  placeholder="请输入标题"></textarea>
                        <br/>
                        <textarea name="content" id="content"></textarea>
                        <script type="text/javascript">CKEDITOR.replace('content');</script>
                        <div style="padding-top: 10px;padding-bottom: 10px">
                            <c:forEach items="${typeList}" var="entity" varStatus="status">
                                <label class="radio-inline">
                                    <input type="radio" name="optionsRadiosinline" id="${entity.id}"
                                           value="${entity.name}">${entity.name}
                                </label>
                            </c:forEach>
                        </div>
                    </div>
                    <br/><br/>
                    <a href="javascript:post()">
                        <div class="fb-div-sub">
                            <div class="bor-rai fb-sub">提交</div>
                        </div>
                    </a>
                </form>
            </div>
        </div>
        <div class="col-md-3">

            <div class="answer-item">
                <h4>常见反馈</h4>
                <hr/>
                <div class="ans-hot">
                    <p><a href="">论坛意见提交？</a></p>
                    <p><a href="">BUG告知</a></p>
                    <p><a href="">通知通告？</a></p>
                    <p><a href="">合作交谈？</a></p>
                    <p><a href="">账号帖子相关问题？</a></p>
                    <p><a href="">嗯。。。。？</a></p>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp" flush="true"/>
</body>

<script>


    function post() {
        //获取编辑器的内容
        var stem = CKEDITOR.instances.content.getData();
        var title = $("#title").val();
        //获取帖子分类类型
        var type = $("input[type='radio']:checked").val();
        $.ajax({
            url: "save_article.json",
            type: "POST",
            dataType: "json",
            data: {
                "stem": stem,
                "title": title,
                "type": type
            },
            success: function (rtn) {
                if (rtn.code == "000000") {
                    helper.toast({
                        content: "发布成功",
                        type: "success"
                    });
                    // 刷新页面
                    window.location.href = "${refererUrl}";
                } else {
                    helper.toast({
                        content: rtn.message,
                        type: "error"
                    });
                }
            },

    });

        function Trim(str, is_global){
            var result;
            result = str.replace(/(^\s+)|(\s+$)/g, "");
            if (is_global.toLowerCase() == "g") {
                result = result.replace(/\s/g, "");
            }
            return result;
        };
    }


</script>
</html>
