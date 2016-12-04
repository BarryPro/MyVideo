<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="user" value="${sessionScope.user}"/>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a id="123" class="navbar-brand" href="index.jsp"><h1><img src="images/logo.png" alt=""/></h1></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <div class="top-search">
                <form class="navbar-form navbar-right" id="form_search" method="post">
                    <input type="text" class="form-control" placeholder="Search..." id="txt" name="txt">
                    <input type="button" value=" " id="btn_search">
                </form>
                <div class="signin">
                    <label id="label1" style="color: red"><b><i>${msg}</i></b></label>
                </div>
            </div>
            <!-- 用户登陆下拉菜单 -->
            <%@include file="dropdown.jsp"%>

            <div class="header-top-right">
                <div class="signin">
                    <!-- pop-up-box -->
                    <script type="text/javascript" src="js/modernizr.custom.min.js"></script>
                    <link href="css/popuo-box.css" rel="stylesheet" type="text/css" media="all"/>
                    <link href="css/diy.css" rel='stylesheet' type='text/css' media="all"/>
                    <script src="js/jquery.magnific-popup.js" type="text/javascript"></script>
                    <!--//pop-up-box -->
                    <div id="small-dialog2" class="mfp-hide">
                        <h3>注册</h3>
                        <%@include file="left_page.jsp"%>
                        <div class="signup">
                            <form id="r_form" method="post" enctype="multipart/form-data" action="UserControl">
                                <input type="text" class="email" placeholder="用户名" name="rusername" id="rusername"/>
                                <input type="password" placeholder="密码" name="rpassword" id="rpassword"/>
                                <input type="password" placeholder="确认密码" id="repwd"/>
                                <a href="javascript:;" class="file">选择文件.jpg|.png|.gif|.bmp
                                    <input type="file" name="file0" id="file0">
                                </a>
                                <br/><br/><br/><br/>
                                <hr/>
                                <input type="button" class="button" value="注册" id="register"/>
                            </form>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                    <div id="small-dialog3" class="mfp-hide">
                        <h3>上传</h3>
                        <%@include file="left_page.jsp"%>
                        <div class="signup">
                            <form id="u_form" method="post" enctype="multipart/form-data" action="VideoControl?action=upload&id=${user.id}">
                                <input type="text" class="email" placeholder="电影名字" name="vname" id="vname"/><br/>
                                <input type="text" class="email" placeholder="导演名" name="vdirector" id="vdirector"/><br/>
                                <input type="text" class="email" placeholder="演员表" name="vactor" id="vactor"/><br/>
                                <div align="left"><b>海报支持得格式：.jpg .gif .bmp .png</b></div>
                                <a href="javascript:;" class="file">选择你要上传的海报文件
                                    <input type="file" name="file2" id="file2">
                                </a><br/><br/><br/>
                                <input type="hidden" name="userid" value="${user.id}"/>
                                选择上传视频类型:
                                <select name="type" id="type">
                                    <option value="1">电视</option>
                                    <option value="2" selected>电影</option>
                                    <option value="3">MV</option>
                                    <option value="4">新闻</option>
                                    <option value="5">其他</option>
                                </select>
                                <textarea style="width: 90%;" name="vinfo" placeholder="电影描述" id="vinfo"></textarea><br/>
                                <div align="left"><b>视频支持得格式：.mp4 .avi</b></div>
                                <a href="javascript:;" class="file">选择你要上传的电影文件
                                    <input type="file" name="file1" id="file1" onchange="filesize(this)">
                                </a><br/><br/><br/>
                                <hr/>
                                <input type="button" class="button" value="上传" id="upload"/>
                            </form>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                    <div id="small-dialog7" class="mfp-hide">
                        <h3>Create Account</h3>
                        <%@include file="left_page.jsp"%>
                        <div class="signup">
                            <form >
                                <input type="text" class="email" placeholder="Email" required="required" pattern="([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?" title="Enter a valid email"/>
                                <input type="password" placeholder="Password" required="required" pattern=".{6,}" title="Minimum 6 characters required" autocomplete="off"/>
                                <input type="submit" value="Sign In"/>
                            </form>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                    <div id="small-dialog4" class="mfp-hide">
                        <h3>Feedback</h3>

                        <div class="feedback-grids">
                            <div class="feedback-grid">
                                <p>Suspendisse tristique magna ut urna pellentesque, ut egestas velit faucibus. Nullam
                                    mattis lectus ullamcorper dui dignissim, sit amet egestas orci ullamcorper.</p>
                            </div>
                            <div class="button-bottom">
                                <p><a href="#small-dialog" class="play-icon popup-with-zoom-anim">Sign in</a> to get
                                    started.</p>
                            </div>
                        </div>
                    </div>
                    <div id="small-dialog5" class="mfp-hide">
                        <h3>Help</h3>

                        <div class="help-grid">
                            <p>Suspendisse tristique magna ut urna pellentesque, ut egestas velit faucibus. Nullam
                                mattis lectus ullamcorper dui dignissim, sit amet egestas orci ullamcorper.</p>
                        </div>
                        <div class="help-grids">
                            <div class="help-button-bottom">
                                <p><a href="#small-dialog4" class="play-icon popup-with-zoom-anim">Feedback</a></p>
                            </div>
                            <div class="help-button-bottom">
                                <p><a href="#small-dialog6" class="play-icon popup-with-zoom-anim">Lorem ipsum dolor sit
                                    amet</a></p>
                            </div>
                            <div class="help-button-bottom">
                                <p><a href="#small-dialog6" class="play-icon popup-with-zoom-anim">Nunc vitae rutrum
                                    enim</a></p>
                            </div>
                            <div class="help-button-bottom">
                                <p><a href="#small-dialog6" class="play-icon popup-with-zoom-anim">Mauris at volutpat
                                    leo</a></p>
                            </div>
                            <div class="help-button-bottom">
                                <p><a href="#small-dialog6" class="play-icon popup-with-zoom-anim">Mauris vehicula
                                    rutrum velit</a></p>
                            </div>
                            <div class="help-button-bottom">
                                <p><a href="#small-dialog6" class="play-icon popup-with-zoom-anim">Aliquam eget ante non
                                    orci fac</a></p>
                            </div>
                        </div>
                    </div>
                    <div id="small-dialog6" class="mfp-hide">
                        <div class="video-information-text">
                            <h4>Video information & settings</h4>

                            <p>Suspendisse tristique magna ut urna pellentesque, ut egestas velit faucibus. Nullam
                                mattis lectus ullamcorper dui dignissim, sit amet egestas orci ullamcorper.</p>
                            <ol>
                                <li>Nunc vitae rutrum enim. Mauris at volutpat leo. Vivamus dapibus mi ut elit fermentum
                                    tincidunt.
                                </li>
                                <li>Nunc vitae rutrum enim. Mauris at volutpat leo. Vivamus dapibus mi ut elit fermentum
                                    tincidunt.
                                </li>
                                <li>Nunc vitae rutrum enim. Mauris at volutpat leo. Vivamus dapibus mi ut elit fermentum
                                    tincidunt.
                                </li>
                                <li>Nunc vitae rutrum enim. Mauris at volutpat leo. Vivamus dapibus mi ut elit fermentum
                                    tincidunt.
                                </li>
                                <li>Nunc vitae rutrum enim. Mauris at volutpat leo. Vivamus dapibus mi ut elit fermentum
                                    tincidunt.
                                </li>
                            </ol>
                        </div>
                    </div>
                </div>
                <%@include file="signin.jsp"%>
                <div class="clearfix"></div>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
</nav>
