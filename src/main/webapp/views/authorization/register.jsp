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

    <link rel="stylesheet" href="/views/user/css/password.css">

    <link rel="stylesheet" href="/resources/css/login.css">
    <link rel="stylesheet" href="/resources/css/main.css">

    <meta charset="UTF-8">
    <title> Title</title>

    <c:set var = "u_pass" scope = "session" value = "Password"/>
    <c:set var = "c_pass" scope = "session" value = "Confirm Password"/>
    <c:set var="problem" scope="session" value="${(pass_conf_err != null || email_error != null || username_error != null)? 'There was a problem with your credentials!' : null}"/>
    <c:set var="pass_err" value="${(pass_conf_err != null)? pass_conf_err : u_pass}"/>
    <c:set var="confirm_pass_err" value="${(pass_conf_err != null)? pass_conf_err : c_pass}"/>
    <c:set var="e_error" value="${(email_error != null)? email_error : 'Email'}"/>
    <c:set var="u_error" value="${(username_error != null)? username_error : 'Username'}"/>
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

                        <div style="color: red">
                            <c:if test="${problem != null}">
                                <snap class="small text-left"><c:out value="${problem}"></c:out></snap>
                            </c:if>
                        </div>

                        <div class="input-group">
                            <i class='bx bxs-user'></i>
                            <input type="text" name="username" placeholder="${u_error}">
                        </div>
                        <div class="input-group">
                            <i class='bx bx-mail-send'></i>

                            <input type="email" name="email" placeholder="${e_error}">

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
                            <input type="password" name="confirm_password" placeholder="${confirm_pass_err}">
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
                            <b class="pointer">
                                <a href="/login">Sign in</a>
                            </b>
                        </p>
                    </div>
                </div>

            </div>
            <!-- END SIGN UP -->

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
            <!-- SIGN UP CONTENT -->
            <div class="col align-items-center flex-col">
                <div class="img sign-up">

                </div>
                <div class="text sign-up">
                    <h2 style="color: white">
                        Join with us
                    </h2>

                </div>
            </div>
            <!-- END SIGN UP CONTENT -->
        </div>
        <!-- END CONTENT SECTION -->
    </div>
</form>

<script src="/views/user/js/password.js"></script>
<script src="/resources/js/register.js"></script>

</body>
</html>
