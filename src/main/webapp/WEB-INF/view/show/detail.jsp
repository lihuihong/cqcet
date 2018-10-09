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

    <div class="content" >&nbsp;</div>
    <div class="container content">
        <div class="row">
            <div class="col-md-3">
                <div  id="float_left">
                    <div class="answer-item">
                        <img class="answer-img" src="${pageContext.request.contextPath}/resources/show/img/headimg1.png" />
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
                            <hr/>
                            <br/><br/>
                            <p>
                                在昨天正式申请成为了猿码学院的认证作者，在今后的学习生活中会与大家分享我从小白成长为架构师的全部历程，今天这篇文章就来聊聊猿码学院这个平台
                            </p><br/>
                            <h4>初识慕课</h4>
                            <p>
                                我是2013年正式踏入大学的大门开始了我的编程不归路！最初步入大学的时候和大多数同学们一样学习c，学习数据库sqlServer，然后开始我现在的主力语言的学习java，这个网站很早就有接触但是在校期间一直没法集中精力在这个上面
                            </p>
                            <p>先下班拉！回家在写把</p><hr/>

                            <h4>从慕课取经</h4>
                            <p>回家用了我差不多一个小时时间，现在躺在床上想起这里还有一篇文章没有发布，继续谈谈猿码学院对于我现在的发展多带来的帮助吧，我个人觉得猿码学院在我自己的发展道路上还是对我帮助蛮大的，在慕课我学的课程其实蛮多的，而且也很杂从最开始猿码学院全是免费的课程一步一步看着猿码学院的改变，在这个知识付费的时代做起了这一块但是同样保证了免费课程的同步更新，相对来看总体还是不错的。虽然会出现某些课程的敷衍外大多数还是挺不错的。
                            </p><hr/>
                            <h4>给初级程序员的一些建议</h4>
                            <p>对于初级程序员我不得不说一句基础思想很重要，虽然学习基础会很枯燥但是对后面阅读源码真的很有帮助</p>
                            <p>这里就猿码学院的java基础123的学习课程推荐</p><br/>

                            <h4>工欲善其事 必先利奇器</h4>
                            <p>这一块不得不说好的ide能够为开发节省不止一点点的时间这里就推荐idea的课程学习</p><br/>

                            <h4>团队协作不可避免</h4>
                            <p>对于团队协作那就是猿码学院的git相关课程的学习</p><br/>

                            <h4>spring相关全家桶</h4>
                            <p>这是作为java程序员谁都逃不脱的春天，我只想说，水很深，骚年们慢慢游，不怕告诉你们好多已经淹死了，哈哈哈 其实是想告诉大家spring学习是需要投入很多时间的，所以一定做好打持久战的准备
                            </p><br/>
                            <h4>最后</h4>
                            <p>当完成上述课程的学习那么你就可以开心的开始把想法落地啦！！！！后面我会分享一些我在项目开发中遇到的问题，大家可以订阅我，关注我哦！我的知识永不收费，知识共享
                            </p>
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
