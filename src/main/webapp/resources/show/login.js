$(function () {
    H_login = {};
    H_login.openLogin = function () {
        $('.loginbtn').click(function () {
            $('.login').show();
            $('.login-bg').show();
        });
        $('.registerbtn').click(function () {
            $('.register').show();
            $('.login-bg').show();
        });
        $('#go-register').click(function () {
            $('.login').hide();
            $('.register').show();
            $('.login-bg').show();
        });
        $('#go-login').click(function () {
            $('.register').hide();
            $('.login').show();
            $('.login-bg').show();
        });
    };
    H_login.closeLogin = function () {
        $('.close-login').click(function () {
            $('.login').hide();
            $('.login-bg').hide();
        });
        $('.close-register').click(function () {
            $('.register').hide();
            $('.login-bg').hide();
        });

    };
    H_login.loginForm = function () {
        $("#login-button-submit").on('click', function () {
            var username = $("#username");
            var usernameValue = $("#username").val();
            var password = $("#password");
            var passwordValue = $("#password").val();
            if (usernameValue == "") {
                alert("用户名不能为空");
                username.focus();
                return false;
            } else if (usernameValue.length > 15) {
                alert("用户名长度不能大于15字符");
                username.focus();
                return false;
            } else if (passwordValue == "") {
                alert("密码不能为空");
                password.focus();
                return false;
            } else if (passwordValue.length < 6 || passwordValue.length > 30) {
                alert("密码长度不能小于6或大于30位字符");
                password.focus();
                return false;
            } else {
                $.ajax({
                    url: "/show/index/login.json",
                    type: "POST",
                    dataType: "json",
                    async: false,
                    data: {username: usernameValue, password: passwordValue},
                    success: function (rtn) {
                        if (rtn.code == "000000") {
                            var info = rtn.data.info;
                            alert(rtn.message);
                            /*delCookie("userToken");
                             setCookie("userToken", info.userToken);*/
                            window.location.href=window.location.href;
                            setTimeout(function () {
                                $('.login').hide();
                                $('.login-bg').hide();
                                $('.list-input').val('');
                                // 跳转到首页

                                //location=location;
                                //window.location.href = window.location.href;
                            }, 2000);
                        } else {
                            alert(rtn.message);
                        }
                    },

                });

            }
        });
        $("#reg-button-submit").on('click', function () {
            var username = $("#reg-user");
            var usernameValue = $("#reg-user").val();
            var password = $("#reg-pwd");
            var passwordValue = $("#reg-pwd").val();
            var repassword = $("#reg-repwd");
            var repasswordValue = $("#reg-repwd").val();
            var student = $("#reg-num");
            var studentValue = $("#reg-num").val();
            var schoolValue = $("#reg-school").val();

            if (usernameValue == "") {
                alert("用户名不能为空");
                username.focus();
                return false;
            } else if (usernameValue.length > 15) {
                alert("用户名长度不能大于15字符");
                username.focus();
                return false;
            } else if (passwordValue == "") {
                alert("密码不能为空");
                password.focus();
                return false;
            } else if (passwordValue.length < 6 || passwordValue.length > 16) {
                alert("密码长度不能小于6或大于16位字符");
                password.focus();
                return false;
            } else if (passwordValue != repasswordValue) {
                alert("两次密码不一致");
                repassword.focus();
                return false;
            }else if (studentValue.length == 10) {
                alert("学号必须是10位");
                student.focus();
                return false;
            }else if (schoolValue == "") {
                alert("学院不能为空");
                student.focus();
                return false;
            } else {
                $.ajax({
                    url: "/show/index/register.json",
                    type: "POST",
                    dataType: "json",
                    async: false,
                    data: {username: usernameValue, password: passwordValue,studentId:studentValue,college:schoolValue},
                    success: function (rtn) {
                        if (rtn.code == "000000") {
                            var info = rtn.data.info;
                            alert("登陆"+rtn.message);
                            /*delCookie("userToken");
                             setCookie("userToken", info.userToken);*/
                            setTimeout(function () {
                                $('.login').hide();
                                $('.login-bg').hide();
                                $('.list-input').val('');
                                // 跳转到首页
                                //window.location.href = "${pageContext.request.contextPath}/";
                            }, 2000);
                        } else {
                            alert(rtn.message);
                        }
                    },

                });
            }
        });
    };
    H_login.run = function () {
        this.closeLogin();
        this.openLogin();
        this.loginForm();
    };
    H_login.run();

});