<%--
  Created by IntelliJ IDEA.
  User: 那个谁
  Date: 2018/10/20
  Time: 02:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <style type="text/css">
        .tagcloud{
            margin-bottom: 10px;
        }
        .tagcloud a {
            padding: 5px 10px;
            color: #333;
            width: 100%;
            float: left;
            font-size: 8px;
            border: 1px solid #e6e7e8;
            border-radius: 12px;
            background-color: #f2f4f8;
            text-decoration: none;
            white-space: nowrap;
            -webkit-box-shadow: 6px 4px 8px 0 rgba(151, 142, 136, .34);
            box-shadow: 6px 4px 8px 0 rgba(151, 142, 136, .34);

        }

        .tagcloud a:hover {
            color: #3385cf;
            background-color: #cccccc;
        }
    </style>
</head>
<body>
<div class="answer-item">
    <h4>帖子分类</h4>
    <hr/>
    <div class="container-fluid" style="padding: 0px">
        <div class="row">
            <c:forEach items="${typeList}" begin="0" var="entity" varStatus="status">
                <div class="col-md-6 tagcloud">
                    <a href="${pageContext.request.contextPath}/show/search.action?typeId=${entity.get(1).id}"
                       class="label label-default" style="float: left;">${entity.get(1).name}
                        <span class="badge" style="margin-left: 5px;">${entity.get(0)}</span>
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>

</div>

</body>
</html>