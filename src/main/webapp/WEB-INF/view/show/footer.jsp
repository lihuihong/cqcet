<%--
  Created by IntelliJ IDEA.
  User: zcq
  Date: 2018/9/13
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
    <div class="content2 shadow-bg" id="footer" style="margin-top: 80px">
        <nav>
            <a href="about.html">关于我们</a><i>|</i>
            <a href="about.html">联系我们</a><i>|</i>
            <a href="feedback.html">常见问题</a><i>|</i>
            <a href="feedback.html">意见反馈</a><i>|</i>
            <a href="<%=request.getContextPath()%>/admin/list.action">后台管理</a>
        </nav>
        <div class="copyright"><p></p>Copyright © 2018-2020 www.heylhh.com All Rights Reserved.备案号： <a href="http://www.miitbeian.gov.cn/">渝ICP备18013192号</a></div>
    </div>

    <script>
        $(window).scroll(function () {
            if ($(document).scrollTop() > 150) {$(".navbar").addClass("top-nav");
            }else {$(".navbar").removeClass("top-nav");}
        })

        //左右侧固定浮动的div建议放在html的最低部
        //右侧固定
        //scrollx({id:'float_right', r:0, t:200, f:1});
        //左侧固定
        //scrollx({id:'float_left', f:1});
        //相对父级相定固定
        //scrollx({id:'float_father', l:300, t:200, f:1});
        //页面滚动同时滚动固定对像
        //scrollx({id:'float_right', l:0, t:0, f:0});
        /*
        scrollx参数说明

        id：浮动对象的id
        r：右边距（窗口右边距，不写为靠左浮动）
        l：左边距（距离父级对象的左边距） “r”和“l”只能有其中一个参数
        t：上边距（默认贴着底边，0是贴着顶边）
        f：1表示固定（不写或者0表示滚动）
        */
    </script>
</body>
</html>
