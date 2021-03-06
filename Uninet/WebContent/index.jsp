<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>유니넷</title>
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
    <link rel="icon" href="favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="css/master.css" />
    <link rel="stylesheet" href="css/login.css"> 
  </head>
  <body>
    <div class="container">
      <div class="login-page">
        <div class="index-title">
          <img src="images/logo.png" alt="유니넷 로고" class="index-logo">
          <h1 class="login-greeting">
                지금 <strong>유니넷</strong>을 시작하세요!
          </h1>
        </div>
        <form action="login" method="post" class="login-form">
          <input type="text" name="id" id="input-id" class="input" placeholder="아이디" autocomplete="off"/>
          <input
            type="password"
            name="password"
            id="input-pwd"
            class="input"
            placeholder="비밀번호"
          />
          <input type="submit" value="로그인" class="submitBtn"/>
        </form>
        <p class="register">유니넷에 처음이신가요? <a href="register" class="register-link">회원가입</a></p>
      </div>
    </div>
  </body>
</html>

