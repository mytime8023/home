var req = {
	// 修改用户状态
	edit_status : "user/edit/status",
	// 修改用户信息
	edit_info : "user/edit/info",
	// 修改用户密码
	edit_pass : "user/edit/pass",
	// 获取用户列表
	user_list : "user/static",
	// 获取用户数据
	user_info : "user/info",
	// 获取用户密码
	user_pass : "user/pass",
	// 删除用户
	user_delete : "user/delete",
	// 添加用户页
	open_add : "user/open/add",
	// 添加用户
	user_add : "user/add",
	// 批量删除用户
	user_delete_all : "user/delete/all"
}

var U = {
	/* 初始化用户状态 */
	init_status : function(data){
		for (var i = 0; i < data.length; i++) {
      		var obj = document.getElementById("user_stop_" + data[i].id);
    		if(data[i].status == 0){
    			$(obj).attr('title','启用');
                $(obj).find('i').html('&#xe62f;');
                $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
    		}
    	}
	},
	/* 更改用户状态(后端) */
	edit_status : function(obj, id){
		var tips = "确认要停用吗？";
		if($(obj).attr('title') == '启用'){
			tips = "确认要启用吗？";
		}
		layer.confirm(tips,function(){
			$.ajax({
				url : req.edit_status + "/" + id,
				type : 'post',
				async : true,
				success : function(res){
					if(res.code == 200){
						U.show_status(obj);
					} else {
						layer.msg('网络异常，请稍候再试',{icon: 5,time:1000});
					}
				},
				error : function(e){
					layer.msg('网络异常，请稍候再试',{icon: 5,time:1000});
				}
			});
			
        });
	},
	/* 修改用户状态(前端) */
	show_status : function(obj){
		if($(obj).attr('title')=='启用'){
        	$(obj).attr('title','停用')
        	$(obj).find('i').html('&#xe601;');
        	$(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
        	layer.msg('已启用!',{icon: 1,time:1000});
        }else{
        	$(obj).attr('title','启用')
        	$(obj).find('i').html('&#xe62f;');
        	$(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
        	layer.msg('已停用!',{icon: 5,time:1000});
        }
	},
	/* 修改用户信息 */
	edit_info : function(data,pageNum){
		data = JSON.stringify(data.field);
		$.ajax({
			url : req.edit_info + '/' + pageNum,
			type : 'post',
			async : true,
			data : data,
    		dataType: 'text',
    		contentType: "application/json;charset-UTF-8",
			success : function(res){
				var json = JSON.parse(res);
				if(json.code == 200){
					layer.msg('修改成功',{icon: 6,time:1000});
					setTimeout("xadmin.close()",1000);
					setTimeout("U.reload_parent('"+ json.pageNum +"')",1000);
				} else {
					layer.msg('网络异常，请稍候再试',{icon: 5,time:1000});
				}
			},
			error : function(e){
				layer.msg('网络异常，请稍候再试',{icon: 5,time:1000});
			}
		});
	},
	edit_tips : function(){
		layer.alert("修改成功",{icon: 6},U.close_frame);
	},
	/* 关闭悬浮框 */
	close_frame : function(){
		// 获得frame索引
        var index = parent.layer.getFrameIndex(window.name);
        //关闭当前frame
        parent.layer.close(index);
	},
	/* 修改用户密码 */
	edit_pass : function(data){
		var data = JSON.stringify(data.field);
		$.ajax({
			url :  req.edit_pass,
			type : 'post',
			async : true,
			data : data,
    		dataType: 'text',
    		contentType: "application/json;charset-UTF-8",
			success : function(res){
				var json = JSON.parse(res);
				if(json.code == 200){
					layer.msg("修改成功",{icon: 6,time:1000});
					setTimeout("xadmin.close()",1000)
				} else {
					var message = "无效的密码";
					if(json.code == 400){
						message = "网络异常";
					}
					layer.msg(message,{icon: 5,time:1000});
				}
			},
			error : function(e){
				layer.msg('网络异常，请稍候再试',{icon: 5,time:1000});
			}
		});
	},
	/* 刷新父列表页 */
	reload_parent : function(pageNum){
		var start = localStorage.getItem("start");
        var end = localStorage.getItem("end");
        var username = localStorage.getItem("username");
        
		var url = req.user_list + "?pageNum=" + pageNum;
		if(start){
			url += "&start=" + start;
		}
		if(end){
			url += "&end=" + end;
		}
		if(username){
			url += "&username=" + username;
		}
		parent.location.href = url;
	},
	/* 打开用户列表 */
	user_list : function(){
		xadmin.add_tab('会员列表',req.user_list);
	},
	/* 打开用户信息修改页 */
	open_info : function(id, pageNum){
		xadmin.open('编辑',req.user_info + '/'+ id +'/'+ pageNum,600,550);
	},
	/* 打开用户密码修改页 */
	open_pass : function(id){
		xadmin.open('编辑',req.user_pass + '/'+ id,600,550);
	},
	/* 刷新列表页 */
	reload_window : function(pageNum){
		var start = localStorage.getItem("start");
        var end = localStorage.getItem("end");
        var username = localStorage.getItem("username");
        
		var url = req.user_list + "?pageNum=" + pageNum;
		if(start){
			url += "&start=" + start;
		}
		if(end){
			url += "&end=" + end;
		}
		if(username){
			url += "&username=" + username;
		}
		location.href = url;
	},
	/* 打开添加用户页 */
	open_add : function(){
		xadmin.open('添加用户',req.open_add);
	},
	/* 删除用户 */
	user_delete : function(obj, id, pageNum){
		layer.confirm('确认要删除吗？',function(){
            $.ajax({
            	url : req.user_delete + "/" + id + "/" + pageNum,
            	type : "post",
            	async : true,
            	success : function (res){
            		if(res.code == 200){
						U.delete_show(obj);
						setTimeout("U.reload_window("+res.pageNum+")", 1000);
					} else {
						layer.msg('网络异常，请稍候再试',{icon: 5,time:1000});
					}
				},
				error : function(e){
					layer.msg('网络异常，请稍候再试',{icon: 5,time:1000});
				}
            });
        });
	},
	/* 删除用户（前端）*/
	delete_show : function(obj){
		 $(obj).parents("tr").remove();
         layer.msg('已删除!',{icon:1,time:1000});
	},
	/* 新增用户 */
	user_add : function(data){
		data = JSON.stringify(data.field);
		$.ajax({
			url : req.user_add,
			type : 'post',
			async : true,
			data : data,
    		dataType: 'text',
    		contentType: "application/json;charset-UTF-8",
			success : function(res){
				var json = JSON.parse(res);
				if(json.code == 200){
					layer.msg("添加成功",{icon: 6,time:1000});
					setTimeout("xadmin.close()",1000);
					setTimeout("xadmin.father_reload()",1000);
				} else if(json.code == 1001){
					layer.msg("用户名已存在",{icon: 5,time:1000});
				} else {
					layer.msg('网络异常，请稍候再试',{icon: 5,time:1000});
				}
			},
			error : function(e){
				layer.msg('网络异常，请稍候再试',{icon: 5,time:1000});
			}
		});
	},
	/* 批量删除用户 */
	delete_all : function(pageNum){
		var ids = [];

        // 获取选中的id 
        $('tbody input').each(function(index, el) {
            if($(this).prop('checked')){
               ids.push($(this).val())
            }
        });
  
        if(ids.length < 1){
        	layer.msg('请选中要删除的用户',{icon: 0,time:1000});
        	return;
        }
        
        layer.confirm('确认要删除'+ids.toString() + '吗？',function(){
            // 捕捉到所有被选中的，发异步进行删除
        	U.delete_all_req(ids,pageNum);
        });
	},
	/* 批量删除用户(前端) */
	delete_all_show : function(){
		layer.msg('删除成功', {icon: 1});
		setTimeout("U.reload_window("+res.pageNum+")", 1000);
	},
	/* 异步请求批量删除用户 */
	delete_all_req : function(ids,pageNum){
		$.ajax({
			url : req.user_delete_all + "/" + pageNum,
			type : 'post',
			async : true,
			data : "ids=" + ids,
			success : function(res){
				if(res.code == 200){
					layer.msg('删除成功', {icon: 1});
					setTimeout("U.reload_window("+res.pageNum+")", 1000);
				} else {
					layer.msg('网络异常，请稍候再试',{icon: 5,time:1000});
				}
			},
			error : function(){
				layer.msg('网络异常，请稍候再试',{icon: 5,time:1000});
			}
		});
	},
	/* 用户搜索 */
	user_search : function(){
		var start = $("#start").val();
		var end = $("#end").val();
		var username = $("#search_name").val();
		// 把搜索参数保存到本地
		localStorage.setItem("start",start);
		localStorage.setItem("end",end);
		localStorage.setItem("username",username);
		
		var url = req.user_list + "?start=" + start;
			url += "&end=" + end;
			url += "&username=" + username;
		U.reload_window(1,start,end,username);
	},
	/* 清空搜索框 */
	clear_search_form : function(){
		$("#start").val("");
  	  	$("#end").val("");
  	  	$("#search_name").val("");
  		// 清空本地搜索参数
        var start = localStorage.removeItem("start");
        var end = localStorage.removeItem("end");
        var username = localStorage.removeItem("username");
	}
}

var verify = {
		username: function(value) {
            if (value.length < 3) {
                return '用户名至少3位';
            }
        },
        nickname: function(value) {
            if (value.length < 1) {
                return '昵称不能为空';
            }
        },
        email: [/(^$)|^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/,'邮箱格式不正确'],
        phone: [/(^$)|^1\d{10}$/,'请输入正确的手机号'],
        pass: [/(.+){3,16}$/, '密码必须3到16位'],
        repass: function(value) {
            if ($('#L_pass').val() != $('#L_repass').val()) {
                return '两次密码不一致';
            }
        },
        url:[/(^$)|(^#)|(^http(s*):\/\/[^\s]+\.[^\s]+)/,'链接格式不正确'],
        number:[/(^$)|^\d+$/,'只能填写数字'],
        date:[/(^$)|^(\d{4})[-\/](\d{1}|0\d{1}|1[0-2])([-\/](\d{1}|0\d{1}|[1-2][0-9]|3[0-1]))*$/,'日期格式不正确'],
        identity:[/(^$)|(^\d{15}$)|(^\d{17}(x|X|\d)$)/,'请输入正确的身份证号']
}