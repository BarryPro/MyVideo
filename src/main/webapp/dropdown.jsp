<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="search">
    <div class="navbar-inner">
        <div class="container-fluid">
            <ul class="nav user_menu pull-right">
                <li class="hidden-phone hidden-tablet">
                    <div class="nb_boxes clearfix">
                        <a data-toggle="modal" data-backdrop="static" href="#login"
                           class="label ttip_b" title="登录">
                            <i class="splashy-calendar_week"></i>
                        </a>
                    </div>
                </li>
                <script src="js/diy/ajax.js" type="text/javascript"></script>
                <li class="divider-vertical hidden-phone hidden-tablet"></li>
                <li class="dropdown">
                    <a href="javascript:void(0)" class="dropdown-toggle " data-toggle="dropdown">
                        <%@include file="user.jsp"%>
                        <ul class="dropdown-menu">
                            <c:if test="${user==null}">
                            <li>
                                <a href="#small-dialog" class="play-icon popup-with-zoom-anim">登录</a>
                                <li class="divider"></li>
                            </li>
                            </c:if>

                            <li><a href="#small-dialog2" class="play-icon popup-with-zoom-anim">注册</a></li>
                            <c:if test="${user!=null}">
                                <li class="divider"></li>
                                <li><a href="#small-dialog3" class="play-icon popup-with-zoom-anim">上传</a></li>
                            </c:if>
                            <li class="divider"></li>
                            <li><a href="UserControl?action=loginout">注销</a></li>
                            <li class="divider"></li>
                            <li><a href="javascript:void(0)" id="dispear">消息</a></li>
                            <c:if test="${user!=null}">
                            <li class="divider"></li>
                            <li>
                                <select name="row" id="my_select">
                                    <option value="3">设置行数/每页</option>
                                    <option value="4">4=========rows</option>
                                    <option value="8">8=========rows</option>
                                    <option value="12">12=========rows</option>
                                </select>
                            </li>
                            </c:if>
                        </ul>
                </li>
            </ul>
        </div>
    </div>
</div>




	
	
	
	
	

