<%--
  Created by IntelliJ IDEA.
  User: zcq
  Date: 2018/10/10
  Time: 8:49
  发帖页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发布帖子</title>
    <script src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"></script>
    <script src="${pageContext.request.contextPath}/resources/ckeditor/config.js"></script>
</head>
<body>
    <jsp:include page="header.jsp" flush="true" />


    <div class="container content">
        <div class="row">
            <div class="col-md-9">
                <div class="article-content" style="padding: 20px 40px;">
                    <form action="" method="post" name="form1">
                    <h3>发布帖子</h3>
                    <br/><hr/><br/>
                    <div>
                        <textarea name="title" class="answer-input form-control" rows="2" placeholder="请输入标题"></textarea>
                        <br/>
                        <textarea name="content"></textarea>
                        <script type="text/javascript">CKEDITOR.replace('content');</script>
                        <div style="padding-top: 10px;padding-bottom: 10px">
                            <label class="radio-inline">
                                <input type="radio" name="optionsRadiosinline"  value="option1" checked> 内容意见
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="optionsRadiosinline"  value="option2"> 产品建议
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="optionsRadiosinline"  value="option3"> 技术问题
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="optionsRadiosinline"  value="option4"> 其它
                            </label>
                        </div>
                    </div>
                    <br/><br/>

                    <div class="fb-div-sub">
                        <div class="bor-rai fb-sub"><a href="javascript:document.form1.submit();">提交</a></div>
                    </div>
                    </form>
                </div>
            </div>
            <div class="col-md-3">

                <div class="answer-item">
                    <h4>常见反馈</h4>
                    <hr/>
                    <div class="ans-hot">
                        <p><a href="">购买了课程可以退换课么？</a></p>
                        <p><a href="">视频播放相关问题</a></p>
                        <p><a href="">购买了课程可以退换课么？</a></p>
                        <p><a href="">视频播放相关问题</a></p>
                        <p><a href="">购买了课程可以退换课么？</a></p>
                        <p><a href="">视频播放相关问题</a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <jsp:include page="footer.jsp" flush="true" />
</body>
</html>
