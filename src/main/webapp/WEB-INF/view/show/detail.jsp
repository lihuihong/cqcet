<%--
  Created by IntelliJ IDEA.
  User: zcq
  Date: 2018/9/13
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <title>重电校园</title>
</head>
<body>
    <jsp:include page="header.jsp" flush="true" />

    <div class="content" >&nbsp;</div>
    <div class="container content">
        <div class="row">
            <div class="col-md-3">
                <div  id="float_left">
                    <div class="answer-item">
                        <img class="answer-img" src="${article.avatar}" />
                        <div class="answer-bod">
                            <a href="#" class="btn btn-info btn-sm">
                                点 击 发 贴
                            </a>
                        </div>
                    </div>
                    <div class="answer-item">
                        <h4>他的更多帖子</h4>
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
            <div class="col-md-9">
                <div class="article-content">
                    <div class="navbar-default nav-title" style="border-top-left-radius: 15px;border-top-right-radius: 15px">

                    </div>
                    <hr/>
                    <div class="answer-content">
                        <div class="ans-con-rig">

                            <p>${article.title}</p>

                            <div class="dark-p">
                                <div class="row">
                                    <div class="col-md-4">
                                        <p>来自 <a href="#" style="color:#ff82e4;">${article.username}</a> </p>
                                    </div>
                                    <div class="col-md-4">
                                        <p>回复 <span style="color:#ff82e4;">3018</span> / 查看 <span style="color:#ff82e4;">${article.viewCount}</span> </p>
                                    </div>
                                    <div class="col-md-4" style="text-align:right">
                                        <p>时间 <span style="color:#ff82e4;"><fmt:formatDate value="${article.updateTime}" pattern="yyyy/MM/dd  HH:mm:ss" /></span> </p>
                                    </div>

                                </div>

                            </div>
                            <hr/>
                            <br/><br/>
                            <div>
                            <img src="${article.cover}">
                            <p>${article.content}</p>
                            </div>
                            <input type="text" class="answer-input" />
                            <p class="ans-other">
                                <a href="#" class="btn btn-info btn-sm">
                                    <span class="glyphicon glyphicon-tint"></span> 快速回帖
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
                                <a href="#" class="btn btn-default btn-sm">
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

        </div>
    </div>
    <jsp:include page="footer.jsp" flush="true" />
</body>
</html>
