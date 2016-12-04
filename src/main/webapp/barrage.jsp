<%--
  Created by IntelliJ IDEA.
  User: belong
  Date: 16-11-14
  Time: 下午12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Java开发视频弹幕技术SreenText</title>
    <meta name="Keywords" content="关键词,关键词">
    <meta name="description" content="Version-1.0">
    <link rel="stylesheet" href="css/barrage.css" type="text/css"/>
</head>

<body>
<a href="#" id="click_screen">点击弹幕</a>
<!--screen start-->
<div class="screen">
    <!--s_dm start-->
    <div class="s_dm">
        <a href="#" class="s_close">退出弹幕</a>

        <div class="mask"></div>
        <div class="s_show">
        </div>
    </div>
    <!--end s_dm-->
    <!--send start-->
    <div class="send">
        <div class="s_con">
            <!--两个元素同时使用时，不加回车可以避免两者间的空隙出现-->
            <input type="text" class="s_txt"/><input type="button" class="s_btn" value="发表评论"/>
        </div>
    </div>
    <!--end send-->
</div>
<!--end screen-->
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
<script type="text/javascript">
    $(function () {
        //点击展开
        $("#click_screen").click(function () {
            $(".screen").toggle(600);

        });
        $(".s_close").click(function () {
            $(".screen").toggle(600);

        });
        //发表评论
        $(".s_btn").click(function () {
            var text = $(".s_txt").val();
            $(".s_show").append("<div>" + text + "</div>");
            init_screen();
        });

        $(".s_txt").keydown(function () {
            var code = window.event.keyCode;
            //alert(code);
            if (code == 13)//回车键按下时，输出到弹幕
            {
                var text = $(".s_txt").val();
                $(".s_show").append("<div>" + text + "</div>");
                init_screen();
            }
        });

    });
    //初始化弹幕
    function init_screen() {
        var _top = 0;
        $(".s_show").find("div").show().each(function () {
            var _left = $(window).width() - $(this).width();
            var _height = $(window).height();
            _top = _top + 80;
            if (_top > _height - 100) {
                _top = 80;
            }
            var time = 10000;//10秒
            if ($(this).index() % 2 == 0) {
                time = 20000;
            }
            //设定文字的初始化位置
            $(this).css({left: _left, top: _top, color: getRandomColor()});
            $(this).animate({left: "-" + _left + "px"}, time, function () {

            });
        });
    }
    //随机获取颜色值
    function getRandomColor() {
        return '#' + (function (h) {
                    return new Array(7 - h.length).join("0") + h
                })((Math.random() * 0x1000000 << 0).toString(16))
    }
</script>
</body>
</html>
