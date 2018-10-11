<%--
  Created by IntelliJ IDEA.
  User: zcq
  Date: 2018/9/13
  Time: 10:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <title>重电校园</title>
    <!-- 引入 Bootstrap -->
  </head>
  <body>
  <jsp:include page="header.jsp" flush="true" />
  <div class="content">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div id="myCarousel" class="carousel slide">
            <!-- 轮播（Carousel）指标 -->
            <ol class="carousel-indicators">
              <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
              <li data-target="#myCarousel" data-slide-to="1"></li>
              <li data-target="#myCarousel" data-slide-to="2"></li>
            </ol>
            <!-- 轮播（Carousel）项目 -->
            <div class="carousel-inner">
              <div class="item active">
                <img src="http://img.htmlsucai.com/huyoucss/banner/banner7.jpg" alt="First slide">
              </div>
              <div class="item">
                <img src="http://img.htmlsucai.com/huyoucss/banner/banner7.jpg" alt="Second slide">
              </div>
              <div class="item">
                <img src="http://img.htmlsucai.com/huyoucss/banner/banner7.jpg" alt="Third slide">
              </div>
            </div>
            <!-- 轮播（Carousel）导航 -->
            <a class="left carousel-control" href="#myCarousel" data-slide="prev">
              <span class="glyphicon glyphicon-chevron-left"></span>
            </a>
            <a class="right carousel-control" href="#myCarousel" data-slide="next">
              <span class="glyphicon glyphicon-chevron-right"></span>
            </a>

          </div>
        </div>
      </div>
      <div class="row" style="margin-top:20px">
        <div class="col-md-6">
          <div class="block">
            <div class="content_title">
              <h3>重电新闻</h3>
              <p>下面插播几条最新消息</p>
            </div>
            <ul>
              <li><a href="">【扫黑除恶】我校召开扫黑除恶专项斗争工作部</a><div class="pull-right">2018-09-13</div></li>
              <li><a href="">【扫黑除恶】我校召开扫黑除恶专项斗争工作部</a><div class="pull-right">2018-09-13</div></li>
              <li><a href="">【扫黑除恶】我校召开扫黑除恶专项斗争工作部</a><div class="pull-right">2018-09-13</div></li>
              <li><a href="">【扫黑除恶】我校召开扫黑除恶专项斗争工作部</a><div class="pull-right">2018-09-13</div></li>
              <li><a href="">【扫黑除恶】我校召开扫黑除恶专项斗争工作部</a><div class="pull-right">2018-09-13</div></li>
            </ul>
          </div>
        </div>
        <div class="col-md-3">
          <div style="background:#7FA8CC;height:120px;width:100%;margin-bottom:20px"></div>
          <div style="background:#7FA8CC;height:120px;width:100%"></div>
        </div>
        <div class="col-md-3">
          <div style="background:#7FA8CC;height:120px;width:100%;margin-bottom:20px"></div>
          <div style="background:#7FA8CC;height:120px;width:100%"></div>
        </div>
      </div>
    </div>

  </div>
  <jsp:include page="footer.jsp" flush="true" />
  </body>
</html>
