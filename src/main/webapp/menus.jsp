<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link href="font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" rel="stylesheet">
<div class="col-sm-3 col-md-2 sidebar">
  <div class="top-navigation">
    <div class="t-menu">MENU</div>
    <div class="t-img">
      <img src="images/lines.png" alt="" />
    </div>
    <div class="clearfix"> </div>
  </div>
  <div class="drop-navigation drop-navigation">
    <ul class="nav nav-sidebar">
      <li><span class="glyphicon " aria-hidden="true"></span></li>
      <li><span class="glyphicon " aria-hidden="true"></span></li>
      <li><span id="counter"></span></li>
      <li class="active"><a href="javascript:void(0)" id="home" class="home-icon"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>Home</a></li>
      <li >
        <a href="javascript:void(0)" class="user-icon" id="tv">
          <span class="glyphicon glyphicon-home glyphicon-blackboard" aria-hidden="true"></span>TV</a></li>
      <li>
        <a href="javascript:void(0)" class="menu1" id="movie">
          <span class="glyphicon glyphicon-film" aria-hidden="true"></span>Movies</a></li>
      <li><a href="javascript:void(0)" class="menu" id="sport"><span class="glyphicon glyphicon-film glyphicon-king" aria-hidden="true"></span>MV</a></li>
      <!-- script-for-menu -->
      <script>
        $( "li a.menu" ).click(function() {
          $( "ul.cl-effect-1" ).slideToggle( 300, function() {
            // Animation complete.
          });
        });
      </script>
      <li><a href="javascript:void(0)" class="song-icon"><span class="glyphicon glyphicon-music" aria-hidden="true"></span>Songs</a></li>
      <li><a href="javascript:void(0)" class="news-icon" id="news"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>News</a></li>

    </ul>
    <!-- script-for-menu -->
    <script>
      $( ".top-navigation" ).click(function() {
        $( ".drop-navigation" ).slideToggle( 300, function() {
          // Animation complete.
        });
      });
    </script>
    <div class="side-bottom">
      <div class="side-bottom-icons">
        <ul class="nav2">
          <li><a href="javascript:void(0)" class="facebook"> </a></li>
          <li><a href="javascript:void(0)" class="facebook twitter"> </a></li>
          <li><a href="javascript:void(0)" class="facebook chrome"> </a></li>
          <li><a href="javascript:void(0)" class="facebook dribbble"> </a></li>
        </ul>
      </div>
      <div class="copyright">
        <p>Copyright &copy; 2016.Company name All rights Thanks Template's Home    ----author: belong</p>
      </div>
    </div>
  </div>
</div>
