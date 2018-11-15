<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script language="Javascript"
			src="jsp/js/jquery.js"></script>
<script language="Javascript"
			src="jsp/js/bootstrap.min.js"></script>
<link href="jsp/css/bootstrap.min.css" rel="stylesheet">
<title>测试页面4</title>

</head>
<body>

<button id="aaa" onclick="search()">查询用户</button>
<table class="table">

  <thead>
    <tr>
      <th>姓名</th>
      <th>年龄</th>
      <th>邮箱</th></tr>
  </thead>
  <tbody id="tbodyId">
   
  </tbody>
</table>

</body>
<script type="text/javascript">

function search(){
		  $.get("getUserList",function(data,status){
			  var tbody = $("#tbodyId");
			  for(var i=0;i<data.length;i++){
				var child = "";
				child = '<tr class="active"> ';
				child+='<td>';
				child+=data[i].username;
				child+='</td>'
				child+='<td>';
				child+=data[i].age;
				child+='</td>'
				child+='<td>';
				child+=data[i].email;
				child+='</td>' 
			    child+='</tr>'
			  	tbody.append(child);
			  }
		  });
		  
}

</script>

</html>