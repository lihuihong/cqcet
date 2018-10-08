$(function () {
    H_login = {};
    H_login.openLogin = function(){
        $('.loginbtn').click(function(){
            $('.login').show();
            $('.login-bg').show();
        });
		$('.registerbtn').click(function(){
            $('.register').show();
            $('.login-bg').show();
        });
		$('#go-register').click(function(){
			$('.login').hide();
            $('.register').show();
            $('.login-bg').show();
        });
		$('#go-login').click(function(){
            $('.register').hide();
			$('.login').show();
            $('.login-bg').show();
        });
    };
    H_login.closeLogin = function(){
        $('.close-login').click(function(){
            $('.login').hide();
            $('.login-bg').hide();
        });
		 $('.close-register').click(function(){
            $('.register').hide();
            $('.login-bg').hide();
        });
		
    };
    H_login.loginForm = function () {
        $("#login-button-submit").on('click',function(){
              var username = $("#lo-user");
              var usernameValue = $("#lo-user").val();
              var password = $("#lo-pwd");
              var passwordValue = $("#lo-pwd").val();
            if(usernameValue == ""){
                alert("用户名不能为空");
                username.focus();
                return false;
            }else if(usernameValue.length > 15){
                alert("用户名长度不能大于15字符");
                username.focus();
                return false;
            }else if(passwordValue == ""){
                alert("密码不能为空");
                password.focus();
                return false;
            }else if(passwordValue.length < 6 || passwordValue.length > 30){
                alert("密码长度不能小于6或大于30位字符");
                password.focus();
                return false;
            }else{
                alert("登录成功");
                setTimeout(function(){
                    $('.login').hide();
                    $('.login-bg').hide();
                    $('.list-input').val('');
                },2000);
            }
        });
		$("#reg-button-submit").on('click',function(){
              var username = $("#reg-user");
              var usernameValue = $("#reg-user").val();
              var password = $("#reg-pwd");
              var passwordValue = $("#reg-pwd").val();
			  var repassword = $("#reg-repwd");
              var repassword = $("#reg-repwd").val();
            if(usernameValue == ""){
                alert("用户名不能为空");
                username.focus();
                return false;
            }else if(usernameValue.length > 15){
                alert("用户名长度不能大于15字符");
                username.focus();
                return false;
            }else if(passwordValue == ""){
                alert("密码不能为空");
                password.focus();
                return false;
            }else if(passwordValue.length < 6 || passwordValue.length > 30){
                alert("密码长度不能小于6或大于30位字符");
                password.focus();
                return false;
            }else{
                alert("登录成功");
                setTimeout(function(){
                    $('.login').hide();
                    $('.login-bg').hide();
                    $('.list-input').val('');
                },2000);
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