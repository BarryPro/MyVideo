<%--
  Created by IntelliJ IDEA.
  User: belong
  Date: 16-11-16
  Time: 下午8:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:if test="${user==null}">
  <input type="hidden" title="-1" id="my_image"/>
  <img src="images/login.png" alt="请登录" class="user_avatar myimg" />
  游客
</c:if>
<c:if test="${user!=null}">
  <input type="hidden" title="${user.id}" id="my_image"/>
  <img src="UserControl?action=pic&userid=${user.id}" alt="请登录" class="user_avatar myimg"/>
  <b>${user.username}</b>
  <input type="hidden" title="${user.pagenum}" id="user_pagenum"/>
</c:if>
