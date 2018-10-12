<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <div class="content">
        <div class="container">
            <div class="row">
                <c:forEach begin="1" end="4">
                    <div class="col-md-4">
                        <a href="http://ecard.cqcet.edu.cn/">
                            <div class="tag-item">
                                <img src="${pageContext.request.contextPath}/resources/show/img/ecard.png" class="pull-left"/>
                                <div>
                                    <h3>一卡通系统</h3>
                                    <div style="height: 1px;width: 80px;background: #00b4ef;margin-bottom: 10px"></div>
                                    <p>包含师生校园就餐、购物、缴费等消费功能、银行卡圈存转帐以及图书借阅、车辆进出、会议签到等身份识别功能和校园其他管理功能的20多个子系统。</p>
                                </div>
                            </div>
                        </a>
                    </div>

                    <div class="col-md-4">
                        <a href="http://oaa.cqcet.edu.cn/xxfw/jwxt.htm">
                            <div class="tag-item">
                                <img src="${pageContext.request.contextPath}/resources/show/img/headimg1.png" class="pull-left"/>
                                <div>
                                    <h3>教务系统</h3>
                                    <div style="height: 1px;width: 80px;background: #00b4ef;margin-bottom: 10px"></div>
                                    <p>主要功能模块包括系统维护、学籍管理、师资管理、教学计划管理、智能排课、考试管理、选课管理、成绩管理、实践管理、教学质量评价、毕业生管理、体育管理等。</p>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-4">
                        <a href="http://42.247.8.131:8017/xgxt/">
                            <div class="tag-item">
                                <img src="${pageContext.request.contextPath}/resources/show/img/headimg1.png" class="pull-left"/>
                                <div>
                                    <h3>学生管理</h3>
                                    <div style="height: 1px;width: 80px;background: #00b4ef;margin-bottom: 10px"></div>
                                    <p>面向学生处、思政教师、学生学业导师和全体学生，包括系统维护、思想教育管理、评奖评优管理、对外交流管理、学生资助管理、勤工助学管理等子系统。</p>
                                </div>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>

        </div>

    </div>

    <jsp:include page="footer.jsp" flush="true" />
</body>
</html>
