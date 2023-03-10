<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: abdullo
  Date: 2/10/23
  Time: 11:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="/resources/css/login.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <meta charset="UTF-8">
    <title> Title</title>
    <c:set var="us_err" value="${(username_error != null)? username_error : 'Username'}"/>
    <c:set var="p_err" value="${(password_error != null)? password_error : 'Password'}"/>
</head>
<body>



<form method="post">
    <div id="container" class="container">
        <!-- FORM SECTION -->
        <div class="row">
            <!-- SIGN UP -->
            <div class="col align-items-center flex-col sign-up">
                <div class="form-wrapper align-items-center">
                    <div class="form sign-up">
                        <div class="input-group">
                            <i class='bx bxs-user'></i>
                            <input type="text" name="username" placeholder="Username">
                        </div>
                        <div class="input-group">
                            <i class='bx bx-mail-send'></i>

                                <input type="email" name="email" placeholder="Email">

                        </div>
                        <div class="input-group">
                            <i class='bx bxs-lock-alt'></i>

                            <div id="passwordInput">
                            <input type="password" name="password" placeholder="${pass_err}">
                                <span id="showHide">SHOW</span>
                            </div>
                            <div id="passwordStrength">
                                <span id="poor"></span>
                                <span id="weak"></span>
                                <span id="strong"></span>
                            </div>
                            <div id="passwordInfo"></div>

                        </div>
                        <div class="input-group">
                            <i class='bx bxs-lock-alt'></i>
                                <input type="password" name="confirm_password" placeholder="${pass_err}">
                        </div>
                        <div>
                            <button type="submit">
                                Sign up
                            </button>
                        </div>
                        <p>
							<span>
								Already have an account?
							</span>
                            <b onclick="toggle()" class="pointer">
                                Sign in here
                            </b>
                        </p>
                    </div>
                </div>

            </div>
            <!-- END SIGN UP -->
            <!-- SIGN IN -->
            <div class="col align-items-center flex-col sign-in">
                <div class="form-wrapper align-items-center">
                    <div class="form sign-in ">
                        <div style="color: red">
                            <c:if test="${error != null}">
                                <snap class="small text-left"><c:out value="${error}"></c:out></snap>
                            </c:if>
                        </div>
                        <div class="input-group">
                            <i class='bx bxs-user'></i>
                            <input type="text" name="in_username" placeholder="${us_err}">
                        </div>


                        <div class="input-group">
                            <i class='bx bxs-lock-alt'></i>
                            <input type="password" name="in_password" placeholder="${p_err}">
                        </div>


<%--                        <div >--%>
<%--                            <div class="primary-switch">--%>
<%--                                <h6 class="text-center" style="color: black">Remember me</h6>--%>
<%--                                <i class='bx bxs-user'></i>--%>
<%--                                <input  type="checkbox" id="primary-switch" checked name="remember">--%>
<%--                                <label for="primary-switch"><br></label>--%>
<%--                            </div>--%>
<%--                        </div>--%>


                        <div>
                            <button type="submit">
                                Sign in
                            </button>
                        </div>
                        <p>
							<span>
								Don't have an account?
							</span>
                            <b class="pointer">
                                <a href="/register">Sign up here</a>
                            </b>
                        </p>
                    </div>

                </div>
                <div class="form-wrapper">

                </div>
            </div>
            <!-- END SIGN IN -->
        </div>
        <!-- END FORM SECTION -->
        <!-- CONTENT SECTION -->
        <div class="row content-row">
            <!-- SIGN IN CONTENT -->
            <div class="col align-items-center flex-col">
                <div class="text sign-in">
                    <h2 style="color: white">
                        Welcome!
                    </h2>

                </div>
                <div class="img sign-in">

                </div>
            </div>
            <!-- END SIGN IN CONTENT -->
        </div>
        <!-- END CONTENT SECTION -->
    </div>
</form>

<script src="/resources/js/login.js"></script>

</body>
</html>
