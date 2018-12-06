<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script language="Javascript"
			src="jsp/js/jquery.js"></script>
<script language="Javascript"
			src="jsp/js/bootstrap.min.js"></script>
<script language="Javascript"
			src="jsp/js/tableExport.js"></script>
<script language="Javascript"
			src="jsp/js/bootstrap-table.js"></script>
<link href="jsp/css/bootstrap.min.css" rel="stylesheet">
<link href="jsp/css/bootstrap-table.css" rel="stylesheet">
<title>上传列表</title>

</head>
<body>

 
<button id="btn_print" type="button" style="margin-left:20px;margin-top:20px" id="btn_download" class="btn btn-primary">
	<span class="glyphicon glyphicon-print" ></span>删除
</button>
<button type="button" id="download" style="margin-left:20px;margin-top:20px" id="btn_download" class="btn btn-primary" onClick ="$('#log').tableExport({ type: 'excel', escape: 'false' })">数据导出</button>

  <table id="log"></table>
</body>
<script type="text/javascript">
	$(function () {

	    //1.初始化Table
	    var oTable = new TableInit();
	    oTable.Init();

	    //2.初始化Button的点击事件
	   // var oButtonInit = new ButtonInit();
	   // oButtonInit.Init();

	});


	var TableInit = function () {
	    var oTableInit = new Object();
	    //初始化Table
	    oTableInit.Init = function () {
	        $('#log').bootstrapTable({
	            url: 'getUploadData',         //请求后台的URL（*）
	            method: 'get',                      //请求方式（*）
	            toolbar: '#toolbar',                //工具按钮用哪个容器
	            striped: true,                      //是否显示行间隔色
	         //   cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	         //   pagination: true,                   //是否显示分页（*）
	         //   sortable: false,                     //是否启用排序
	         //   sortOrder: "asc",                   //排序方式
	        //    queryParams: oTableInit.queryParams,//传递参数（*）
	         //   sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
	         //   pageNumber:1,                       //初始化加载第一页，默认第一页
	        //    pageSize: 10,                       //每页的记录行数（*）
	         //   pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
	         //   search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
	         //   strictSearch: true,
	        //    showColumns: true,                  //是否显示所有的列
	        //    showRefresh: true,                  //是否显示刷新按钮
	            minimumCountColumns: 2,             //最少允许的列数
	            clickToSelect: true,                //是否启用点击选中行
	            height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
	            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
	        //    showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
	            cardView: false,                    //是否显示详细视图
	            detailView: false,                   //是否显示父子表
	            columns: [{
	                checkbox: true
	            }, {
	                field: 'uploadData',
	                title: '上传内容',
	                width:100
 
	            } ,
	            {
	                field: 'createDate',
	                title: '上传时间',
	                formatter:formatDate,
	                width:100
	            }]
	        });
	    };

	    //得到查询的参数
	    oTableInit.queryParams = function (params) {
	        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
	            limit: params.limit,   //页面大小
	            offset: params.offset,  //页码
	            departmentname: $("#txt_search_departmentname").val(),
	            statu: $("#txt_search_statu").val()
	        };
	        return temp;
	    };
	    return oTableInit;
	};


	var ButtonInit = function () {
	    var oInit = new Object();
	    var postdata = {};

	    oInit.Init = function () {
	        //初始化页面上面的按钮事件
	    };

	    return oInit;
	};
	function formatDate(value, row, index){
		var createDate = row.createDate;
		 var date = new Date(createDate );//时间戳为10位需*1000，时间戳为13位的话不需乘1000
	        var Y = date.getFullYear() + '-';
	        var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
	        var D = date.getDate() + ' ';
	        var h = date.getHours() + ':';
	        var m = date.getMinutes() + ':';
	        var s = date.getSeconds();
	        if(s<10){
	        	s = "0" + s;
	        }
	        
	      var neDate = Y+M+D+h+m+s;
		var html =('<span>'+neDate+'</span>');

		return html;
	}

	
	
	$("#btn_print").click(function(){
		var a= $("#log").bootstrapTable('getSelections');
		 if(a.length<=0){
			 alert("请选中一行")
		}else{
			var ids= "";
			for(var i=0;i<a.length;i++){
				ids= ids + a[i].id + ","
			}
			ids = ids.substring(0,ids.length-1);
			  var url="updateDelstatus";
	  		    $.ajax({
	  		        //dataType: "json",
	  		        traditional:true,//这使json格式的字符不会被转码
	  		        data: {"ids":ids},
	  		        type: "post", 
	  		        url: url,
	  		        success : function (data) {
	  		        	if("success"==data){
	  		            	alert("删除成功！");
	  		            	window.location.reload();
	  		        	}else{
	  		        		alert("删除失败!");
	  		        		
	  		        	}
	  		        },
	  		        error : function (data){
	  		            alert("删除异常");
	  		        }
	  		    });
		} 
		
	});

</script>

</html>