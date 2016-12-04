<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <title>影视评论</title>
    <script type="text/javascript" src="ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
</head>
<style>
    ._mycolor{
        color: #adadad;
    }
</style>
<body>
<c:set var="review" value="${requestScope.review}"></c:set>
<div align="center">
    <table width="790px" cellpadding="0" cellspacing="1">
        <tr>
            <td rowspan="6" width="28%"><img width="240" height="300" id="_views"
                 src="VideoControl?action=pic&Vid=${review.vid}" alt="tupian" /></td>
            <td width="72%"><span class="_mycolor">影片名：</span>${review.video.vname}</td>
        </tr>
        <tr>
            <td><span class="_mycolor">导演名：</span>${review.vdirector}</td>
        </tr>
        <tr>
            <td><span class="_mycolor">演员表：</span>${review.vactor}</td>
        </tr>
        <tr>
            <td><span class="_mycolor">上传时间：</span>${review.video.date}</td>
        </tr>
        <tr>
            <td><span class="_mycolor">影片介绍：</span>${review.video.vinfo}</td>
        </tr>
    </table>
</div>
<div align="center">
    <span id="article_head"></span>
    <span id="article_info"></span>
    <div align="center">
        <a id="del">上一页</a>
        <a id="first">首页</a>
        <span id="herf_num"></span>
        <a id="add">下一页</a>
    </div>
</div>
<div align="center">
    <textarea id="content" name="content" id="content" rows="5"
              cols="50"></textarea>
    <script type="text/javascript">
        //配置ckeditor
        CKEDITOR.replace('content',{
            filebrowserBrowseUrl : 'ckfinder/ckfinder.html',
            filebrowserImageBrowseUrl : 'ckfinder/ckfinder.html?type=Images',
            filebrowserFlashBrowseUrl : 'ckfinder/ckfinder.html?type=Flash',
            filebrowserUploadUrl : 'ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files',
            filebrowserImageUploadUrl : 'ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images',
            filebrowserFlashUploadUrl : 'ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash'
        });
    </script>
</div>
<link href="css/diy.css" rel='stylesheet' type='text/css' media="all"/>
<div align="center">
    <table width="790px" id="tabelarticle" class="table-striped table-bordered">
        <tr>
            <td width="90%"><span style="color: #00CCFF">你对这部电影有什么看法，快和大家一起分享一下吧?</span></td>
            <td width="10%"><input style="background: chocolate" type="button" id="article" value="   发   表   "/></td>
        </tr>
    </table>
    <div style="display: none"><%@include file="user.jsp"%></div>
    <script>
        $(function(){
            //显示评论信息
            show_article(1);
            $("#article").click(function(){
               //只有登陆的用户才有发评论的资格
               if($("#my_image").attr("title") != -1){
                   $.ajax({
                       url:'ArticleControl?action=article',
                       type:'post',
                       data:'content='+CKEDITOR.instances.content.getData()+
                       '&userid='+$("#my_image").attr("title")+'&rootid='+0+'&Vid='+
                       ${review.vid}+'&cur_page='+$("#max_page_hidden").attr("title"),
                       dataType:'json',
                       success:showArticle
                   });
               } else {
                   alert("登录后才可以发帖哦！");
               }
                //清空在线编辑器的内容
               CKEDITOR.instances.content.setData("");
            });
            //显示评论信息表
            function showArticle(data){
                $("#article_head").empty();
                $("#article_head").append(
                        '<table width="790px" class="table-striped table-bordered">'+
                        '<tr>' +
                            '<th width="10%""><span>头像</span></th>' +
                            '<th width="10%">评论人</th>' +
                            '<th width="40%">评论内容</th>' +
                            '<th width="20%">评论日期</th>' +
                            '<th width="20%">操作</th>' +
                        '</tr></table>'
                );
                $("#article_info").empty();
                $(data.articles).each(function(i,article){
                    $("#article_info").append(
                            '<table id="_table" width="790px" class="table-striped table-bordered">'+
                            '<tr>' +
                                '<td width="10%">' +
                                '<div style="width:48px; height:48px; border-radius:50%;overflow: hidden"><img width="48px" height="48px" src="UserControl?action=pic&userid='+article.user.id+'" class="user_avatar" style="width:100% "/></div></td>' +
                                '<td width="10%">'+article.user.username+'</td>' +
                                '<td width="40%">'+article.content+'</td>' +
                                '<td width="20%">'+article.adate+'</td>' +
                                '<td width="20%"><a href="" title="删除本帖"><i class="icon-trash"></i></a></td>' +
                            '</tr>'+
                            '</table>'+
                            '<label style="display:none" id="cur_page_hidden" title='+data.cur_page+'/>'+
                            '<label style="display:none" id="max_page_hidden" title='+data.page_total+'/>'
                    );
                });
                $("#herf_num").empty();
                for(var j = 2;j<=data.page_total;j++){
                    if(j==data.page_total){
                        $("#herf_num").append(
                                '<a title="'+j+'">尾页</a>'
                        );
                    } else {
                        $("#herf_num").append(
                                '<a title="'+j+'"> '+j+' </a>'
                        );
                    }
                }
            }
            function show_article(n){
                $.ajax({
                    url:'ArticleControl?action=query',
                    type:'post',
                    data:'content='+CKEDITOR.instances.content.getData()+
                    '&userid='+$("#my_image").attr("title")+'&rootid='+0+'&Vid='+
                    ${review.vid}+'&cur_page='+n,
                    dataType:'json',
                    success:showArticle
                });
            }

            //处理分页
            $("#first").click(function(){
                show_article(1);
            })
            $("#herf_num").on('click','a',function(){                ;
                show_article($(this).attr("title"));
            })
            $("#del").click(function(){
                var cur = $("#cur_page_hidden").attr("title");
                if(cur != 1){
                    show_article(cur-1);
                }
            })
            $("#add").click(function(){
                var cur = $("#cur_page_hidden").attr("title");
                cur++;
                var max = $("#max_page_hidden").attr("title");
                if( cur <= max){
                    show_article(cur);
                }
            })
        })

    </script>
</div>
</body>
</html>
