<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>jQuery带播放暂停按钮焦点图代码</title>

<link rel="stylesheet" type="text/css" href="css/jquery.slider.css" />

<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery.slider.min.js"></script>
<script type="text/javascript">
$(document).ready(function ($) {
	$(".slider").slideshow({
		width: 722,
		height: 267,
		transition: ['bar', 'Rain', 'square', 'squareRandom', 'explode']
	});
});
</script>

</head>
<body>
<div style="width:722px;margin:0 auto;">
	<div class="slider">
		<span></span>
		<div><a href="#"><img src="images/10.jpg" alt="" width="722" height="267"></a></div>
		<div><a href="#"><img src="images/datouwang2.jpg" alt=""></a></div>
		<div><a href="#"><img src="images/datouwang3.jpg" alt=""></a></div>
		<div><a href="#"><img src="images/datouwang4.jpg" alt=""></a></div>
		<div><a href="#"><img src="images/datouwang5.jpg" alt=""></a></div>
		<div><a href="#"><img src="images/datouwang6.jpg" alt=""></a></div>      
		<div><a href="#"><img src="images/datouwang7.jpg" alt=""></a></div>
	</div>
</div>
<div class="modal hide fade" id="post">
	<div class="modal-header">
		<button class="close" data-dismiss="modal"
				onclick="javascript:document.getElementById('submenu').innerHTML='${txt_ini}'">×</button>
		<h2>灌水</h2>
		<a href="javascript:void(0)" id="sticky_a"></a> <a
			href="javascript:void(0)" id="sticky_a1"></a> <a
			href="javascript:void(0)" id="sticky_a2"></a> <a
			href="javascript:void(0)" id="sticky_a3"></a> <a
			href="javascript:void(0)" id="sticky_a4"></a> <a
			href="javascript:void(0)" id="sticky_a5"></a>
	</div>
	<div class="modal-body" data-backdrop="static">
		<div class="alert alert-info">请不要发布违法信息，否则，你懂得！</div>
		<jsp:include page="content.jsp">
			<jsp:param value="" name="userid"/>
		</jsp:include>
		<div class="modal-footer">
			<button class="btn btn-inverse pull-right" type="button" id="add"
					name="add" onclick="add()">灌水</button>	</div>
	</div>

	<script type="text/javascript" src="ckfinder/ckfinder.js"></script>
	<script type="text/javascript">

	</script>
</div>
</body>
</html>
