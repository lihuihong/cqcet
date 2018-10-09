<%--
  Created by IntelliJ IDEA.
  User: zcq
  Date: 2018/9/13
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <title>重电校园</title>
</head>
<body>
    <jsp:include page="header.jsp" flush="true" />

    <div class="container">

        <div class="row">
            <div class="col-md-12">
                <div class="ans-head">
                    <img src="${pageContext.request.contextPath}/resources/show/img/ecard.png" class="pull-left" />
                    <div style="margin-top: 10px">
                        <h2>人工智能与大数据</h2>
                        <p class="dark-p">前身软件学院和计算机学院，后合并为人工智能与大数据</p><br/>
                    </div>
                </div>
            </div>

        </div>

    </div>
    <div class="content" >&nbsp;</div>
    <div class="container content">
        <div class="row">
            <div class="col-md-9">
                <div class="article-content">
                    <div class="navbar-default nav-title" style="border-top-left-radius: 15px;border-top-right-radius: 15px">
                        <a href="">全部帖子</a>    <a href="">精品专区</a>     <a href="">最新帖子</a>
                    </div>
                    <hr/>
                    <div class="answer-content">
                        <div class="left">
                            <div class="bor-rai" style="background: #ff82e4;">前端</div>
                        </div>
                        <div class="left ans-con-rig">

                            <p>对于一个，只想更好的使用电脑和手机，并对它们进行维护的学生一枚，应该学什么？</p>
                            <div class="dark-p">
                                <div class="row">
                                    <div class="col-md-4">
                                        <p>来自 <a href="#" style="color:#ff82e4;">前端</a> </p>
                                    </div>
                                    <div class="col-md-4">
                                        <p>回复 <span style="color:#ff82e4;">3018</span> / 查看 <span style="color:#ff82e4;">4019</span> </p>
                                    </div>
                                    <div class="col-md-4" style="text-align:right">
                                        <p>时间 <span style="color:#ff82e4;">2018.9.13</span> </p>
                                    </div>

                                </div>

                            </div>
                            <input type="text" class="answer-input" />
                            <p class="ans-other">
                                <a href="#" class="btn btn-info btn-sm">
                                    <span class="glyphicon glyphicon-tint"></span> 快速回帖
                                </a>
                                <a href="${pageContext.request.contextPath}/show/detail.action" class="btn btn-default btn-sm">
                                    <span class="glyphicon glyphicon-globe"></span> 查看详情
                                </a>
                            </p>
                        </div>
                    </div>
                    <hr/>
                    <div class="answer-content">
                        <div class="left">
                            <div class="bor-rai" style="background: #fe6900;">后台</div>
                        </div>
                        <div class="left ans-con-rig">
                            <p class="dark-p" >来自 <a href="#" style="color:#fe6900;">后台</a></p>
                            <p>对于一个，只想更好的使用电脑和手机，并对它们进行维护的学生一枚，应该学什么？</p>
                            <input type="text" class="answer-input" />
                            <p class="ans-other">
                                <a href="#" class="btn btn-info btn-sm">
                                    <span class="glyphicon glyphicon-tint"></span> 快速回帖
                                </a>
                                <a href="detail.jsp" class="btn btn-default btn-sm">
                                    <span class="glyphicon glyphicon-globe"></span> 查看详情
                                </a>
                            </p>
                        </div>
                    </div>
                    <nav class="ul-center">
                        <ul class="pagination">
                            <li><a href="#">&laquo;</a></li>
                            <li class="active"><a href="#">1</a></li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li><a href="#">4</a></li>
                            <li><a href="#">5</a></li>
                            <li><a href="#">&raquo;</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
            <div class="col-md-3">
                <div id="float_right">
                    <div class="answer-item">
                        <img class="answer-img" src="${pageContext.request.contextPath}/resources/show/img/headimg1.png" />
                        <div class="answer-bod">
                            <a href="#" class="btn btn-info btn-sm">
                                点 击 发 贴
                            </a>
                        </div>
                    </div>
                    <div class="answer-item">
                        <h4>我的帖子</h4>
                        <hr/>
                        <div class="ans-hot">
                            <p><a href="">开发工具怎么下载？</a></p>
                            <p style="text-align:right;">来自 <a href="#" style="color:#ff82e4;">前端</a></p>
                        </div>
                        <div class="ans-hot">
                            <p><a href="">购买了课程可以开发票么？</a></p>
                            <p style="text-align:right;">来自 <a href="" style="color: #fe6900;">后台</a></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp" flush="true" />
</body>
</html>
