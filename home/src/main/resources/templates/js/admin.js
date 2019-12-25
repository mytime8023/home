var req = {
	// 登录
	login : "admin/login"
}
var A = {
	login : function (data){
		var param = JSON.stringify(data.field);
        $.ajax({
    		url: req.login,
    		async: true,
    		type: 'post',
    		dataType: 'text',
    		contentType: "application/json;charset-UTF-8",
    		data: param,
    		success: function(res){
    			res = JSON.parse(res);
    			if(res.code == 200){
    				location.href = "index";
    			} else {
    				layer.msg(res.message,null);
    			}
    		},
    		error: function(e){
    			layer.msg('网络异常，请稍候再试',{icon: 5,time:1000});
    		}
    	});
	}
}