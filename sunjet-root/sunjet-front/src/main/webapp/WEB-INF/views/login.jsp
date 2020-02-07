<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h4>登入頁面</h4>
	
	<form action='<spring:url value="/loginAction"/>' method="post">
<!-- <form  class="form-signin" action="/loginAction" method="post"> -->
    <table>
      <tr>
        <td>Username</td>
        <td><input type="text" name="username"></td>
      </tr>
      <tr>
        <td>Password</td>
        <td><input type="password" name="password"></td>
      </tr>
      <tr>
        <td>
        <button type="submit">Login${1+1}</button>
<%--         <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> --%>
        </td>
      </tr>
    </table>
  </form>
  <br/>
</body>
</html>