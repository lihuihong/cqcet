;(function() {
    var helper = function () {
        // 默认属性
        function defaults(args) {
            var defaults = {
                id : "",	// 元素id
                type : "",	// 组件类型
                url : "",	// 请求地址

                // 弹出层属性
                mask : true,			// 是否使用遮罩
                width : 350,			// 弹出层宽度，单位px
                height : "",			// 弹出层高度
                top : "30%",			// 距离顶部距离，支持 200px 这种写法
                title : "温馨提示",		// 弹出层标题
                closeIcon : true,		// 是否显示右上角关闭 X
                content : "",			// 弹出层内容
                textAlign : "left",		// 内容位置：left；center；right
                tip : "删除后不可恢复，请慎重操作",	// 提示
                confirmName : "确定",	// 确定按钮名称
                cancelName : "取消",	// 取消按钮名称
                callback : function() {return true;},	// 回调函数
                live : 2000,		// 操作提示存在时间
                selector : "",		// 图片数组选择器


                // 分页属性
                pageCount: null,	// 总页数
                currentPage: 1,	// 默认选中第几页
                position: "center",	// 分页显示位置。 left:居左；center:居中；right:居右
            }
            return $.extend(defaults, args);
        }

        var info = {
            // 提示气泡
            toast : function(args) {
                var opt = defaults(args);
                var tipHtml = '';
                var type = opt.type;
                if (type=="success" || type=="") {
                    tipHtml = '<div class="top-tip animated fadeInDown" style="background-color: #5cb85c;">' + opt.content + '</div>';
                } else {
                    tipHtml = '<div class="top-tip animated shake" style="background-color: #fd4c5b;">' + opt.content + '</div>';
                }
                $(document.body).append(tipHtml);
                setTimeout(function() {
                    $(".top-tip").remove();
                }, opt.live);
            },
            // page分页
            page: function (args) {
                var opt = defaults(args);
                var pageId = opt.id;
                // 获取总页数
                var pageCount = opt.pageCount;
                if (pageCount == null) {
                    return false;
                }
                // 获取默认选中页
                var currentPage = opt.currentPage;
                if (currentPage < 1 || currentPage > pageCount) {
                    currentPage = 1;
                }
                // 获取显示位置
                var position = opt.position;
                $("#" + pageId).parent().css("margin", "16px 0");
                if (position == "left") {
                    $("#" + pageId).parent().css("text-align", "left");
                } else if (position == "center") {
                    $("#" + pageId).parent().css("text-align", "center");
                } else if (position == "right") {
                    $("#" + pageId).parent().css("text-align", "right");
                }

                // 判断总页数数量
                if (pageCount <= 1) {
                    $("#" + pageId).append('');
                    return;
                } else if (pageCount < 7) {
                    var html = '<li><a aria-label="Previous">首页</a></li><li><a>上一页</a></li>';
                    for (var i = 1; i <= pageCount; i++) {
                        if (i == currentPage) {
                            html += '<li class="active" page="' + i + '"><a>' + i + '</a></li>';
                        } else {
                            html += '<li page="' + i + '"><a>' + i + '</a></li>';
                        }
                    }
                    html += '<li><a>下一页</a></li><li><a aria-label="Next">尾页</a></li>';
                    $("#" + pageId).append(html);
                    init(pageId);
                } else {
                    newPages(pageId, "jump", currentPage);
                }

                /**
                 * 初始化
                 */
                function init(pageId) {
                    // 监听点击分页事件
                    $("#" + pageId).children("li").click(function () {
                        // 当前点击对象（点击非数字时，需要改变）
                        var element = $(this);
                        // 当前点击的是第几页
                        var pageText = $(this).children("a").text();
                        // 当前页
                        var currentPage = "";
                        // 上一页
                        var lastPage = $("#" + pageId).children("li.active").attr("page");
                        // 判断点击的是数字页还是上一页、下一页之类的
                        if (isNaN(pageText)) {
                            switch (pageText) {
                                case "首页":
                                    if (lastPage == "1") {
                                        return;
                                    }
                                    if (pageCount > 6) {
                                        newPages(pageId, "首页", 1);
                                    }
                                    element = $("#" + pageId).children("li[page=1]");
                                    break;
                                case "上一页":
                                    if (lastPage == "1") {
                                        return;
                                    }
                                    if (lastPage >= (pageCount - 1) || lastPage <= 3 || pageCount < 7) {
                                        element = $("#" + pageId).children("li.active").prev();
                                    } else {
                                        newPages(pageId, "prev", (parseInt(lastPage) - 1));
                                        element = $("#" + pageId).children("li.active");
                                    }
                                    break;
                                case "下一页":
                                    if (lastPage == pageCount) {
                                        return;
                                    }
                                    if (lastPage >= (pageCount - 2) || lastPage < 3 || pageCount < 7) {
                                        element = $("#" + pageId).children("li.active").next();
                                    } else {
                                        newPages(pageId, "next", (parseInt(lastPage) + 1));
                                        element = $("#" + pageId).children("li.active");
                                    }
                                    break;
                                case "尾页":
                                    if (lastPage == pageCount) {
                                        return;
                                    }
                                    if (pageCount > 6) {
                                        newPages(pageId, "尾页", pageCount);
                                    }
                                    element = $("#" + pageId).children("li[page=" + pageCount + "]");
                                    break;
                                case "...":
                                    return;
                            }
                        } else {
                            if (pageCount > 6) {
                                if (pageText <= 3 || pageText >= (pageCount - 3)) {
                                    newPages(pageId, "jump", pageText);
                                }
                            }
                        }

                        currentPage = activePage(pageId, element);
                        if (currentPage != "" && currentPage != lastPage) {
                            opt.callback(currentPage);
                        }
                    });
                }

                /**
                 * 激活页
                 */
                function activePage(pageId, element) {
                    element.addClass("active").siblings().removeClass("active");
                    return $("#" + pageId).children("li.active").text();
                }

                function newPages(pageId, type, i) {
                    var htmlLeft = "";
                    var htmlRight = "";
                    var htmlC = "";
                    var HL = '<li><a>...</a></li>';
                    var html = '<li><a aria-label="Previous">首页</a></li><li><a>上一页</a></li>';
                    for (var n = 0; n < 3; n++) {
                        htmlC += '<li ' + ((n - 1) == 0 ? 'class="active"' : '') + ' page="' + (i + n - 1) + '"><a>' + (i + n - 1) + '</a></li>';
                        htmlLeft += '<li ' + ((n + 2) == i ? 'class="active"' : '') + ' page="' + (n + 2) + '"><a>' + (n + 2) + '</a></li>';
                        htmlRight += '<li ' + ((pageCount + n - 3) == i ? 'class="active"' : '') + ' page="' + (pageCount + n - 3) + '"><a>' + (pageCount + n - 3) + '</a></li>';
                    }

                    switch (type) {
                        case "next":
                            if (i <= 4) {
                                html += '<li page="1"><a>1</a></li>' + htmlLeft + HL + '<li page="' + pageCount + '"><a>' + pageCount + '</a></li>';
                            } else if (i >= (pageCount - 3)) {
                                html += '<li page="1"><a>1</a></li>' + HL + htmlRight + '<li page="' + pageCount + '"><a>' + pageCount + '</a></li>';
                            } else {
                                html += '<li page="1"><a>1</a></li>' + HL + htmlC + HL + '<li page="' + pageCount + '"><a>' + pageCount + '</a></li>';
                            }
                            break;
                        case "prev":
                            if (i <= 4) {
                                html += '<li page="1"><a>1</a></li>' + htmlLeft + HL + '<li page="' + pageCount + '"><a>' + pageCount + '</a></li>';
                            } else if (i >= (pageCount - 3)) {
                                html += '<li page="1"><a>1</a></li>' + HL + htmlRight + '<li page="' + pageCount + '"><a>' + pageCount + '</a></li>';
                            } else {
                                html += '<li page="1"><a>1</a></li>' + HL + htmlC + HL + '<li page="' + pageCount + '"><a>' + pageCount + '</a></li>';
                            }
                            break;
                        case "首页":
                            html += '<li class="active" page="1"><a>1</a></li>' + htmlLeft + HL + '<li page="' + pageCount + '"><a>' + pageCount + '</a></li>';
                            break;
                        case "尾页":
                            html += '<li page="1"><a>1</a></li>' + HL + htmlRight + '<li class="active" page="' + pageCount + '"><a>' + pageCount + '</a></li>';
                            break;
                        case "jump":
                            if (i <= 4) {
                                if (i == 1) {
                                    html += '<li class="active" page="1"><a>1</a></li>' + htmlLeft + HL + '<li page="' + pageCount + '"><a>' + pageCount + '</a></li>';
                                } else {
                                    html += '<li page="1"><a>1</a></li>' + htmlLeft + HL + '<li page="' + pageCount + '"><a>' + pageCount + '</a></li>';
                                }
                            } else if ((i >= pageCount - 3) && (pageCount >= 7)) {
                                if (i == pageCount) {
                                    html += '<li page="1"><a>1</a></li>' + HL + htmlRight + '<li class="active" page="' + pageCount + '"><a>' + pageCount + '</a></li>';
                                } else {
                                    html += '<li page="1"><a>1</a></li>' + HL + htmlRight + '<li page="' + pageCount + '"><a>' + pageCount + '</a></li>';
                                }
                            } else {
                                html += '<li page="1"><a>1</a></li>' + HL + htmlC + HL + '<li page="' + pageCount + '"><a>' + pageCount + '</a></li>';
                            }
                    }
                    html += '<li><a>下一页</a></li><li><a aria-label="Next">尾页</a></li>';
                    if (pageCount > 5 || pageCount < 3) {
                        $("#" + pageId).empty();
                        $("#" + pageId).append(html);
                        init(pageId);
                    }
                }
            }

        };
        return info;
    };
    window.helper = helper();
})();

    function scrollx(p) {
        var d = document, dd = d.documentElement, db = d.body, w = window, o = d.getElementById(p.id), ie6 = /msie 6/i.test(navigator.userAgent), style, timer;
        if (o) {
            cssPub = ";position:"+(p.f&&!ie6?'fixed':'absolute')+";"+(p.t!=undefined?'top:'+p.t+'px;':undefined);
            if (p.r != undefined && p.l == undefined) {
                o.style.cssText += cssPub + ('right:'+p.r+'px;');
            } else {
                o.style.cssText += cssPub + ('margin-left:'+p.l+'px;');
            }
            if(p.f&&ie6){
                cssTop = ';top:expression(documentElement.scrollTop +'+(p.t==undefined?dd.clientHeight-o.offsetHeight:p.t)+'+ "px" );';
                cssRight = ';right:expression(documentElement.scrollright + '+(p.r==undefined?dd.clientWidth-o.offsetWidth:p.r)+' + "px")';
                if (p.r != undefined && p.l == undefined) {
                    o.style.cssText += cssRight + cssTop;
                } else {
                    o.style.cssText += cssTop;
                }
                dd.style.cssText +=';background-image: url(about:blank);background-attachment:fixed;';
            }else{
                if(!p.f){
                    w.onresize = w.onscroll = function(){
                        clearInterval(timer);
                        timer = setInterval(function(){
                            //双选择为了修复chrome 下xhtml解析时dd.scrollTop为 0
                            var st = (dd.scrollTop||db.scrollTop),c;
                            c = st - o.offsetTop + (p.t!=undefined?p.t:(w.innerHeight||dd.clientHeight)-o.offsetHeight);
                            if(c!=0){
                                o.style.top = o.offsetTop + Math.ceil(Math.abs(c)/10)*(c<0?-1:1) + 'px';
                            }else{
                                clearInterval(timer);
                            }
                        },10)
                    }
                }
            }
        }
    }
