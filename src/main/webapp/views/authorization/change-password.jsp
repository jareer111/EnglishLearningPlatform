<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: abdullo
  Date: 2/19/23
  Time: 1:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Account Settings UI Design</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" type="text/css"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="views/user/account-setting/css/style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<c:set var="firstname" value="${(not empty user.getFirstName())? user.getFirstName() : 'Firstname'}"/>
<c:set var="lastname" value="${(not empty user.getLastName())? user.getLastName() : 'Lastname'}"/>
<body>
<section class="py-5 my-5">
    <form method="post" action="/change-password">
        <div class="container">
            <h1 class="mb-5">Account Settings</h1>
            <div class="bg-white shadow rounded-lg d-block d-sm-flex">
                <div class="profile-tab-nav border-right">
                    <div class="p-4">
                        <div class="img-circle text-center mb-3">
                            <img src="views/user/account-setting/img/user2.jpg" alt="Image" class="shadow">
                        </div>
                        <h4 class="text-center">${firstname.concat(' '.concat(lastname))}</h4>
                    </div>
                    <div class="nav flex-column nav-pills" id="password-tab" role="tablist" aria-orientation="vertical">
                        <a class="nav-link" href="/profile">
                            <i class="fa fa-home text-center mr-1"></i>
                            Account
                        </a>
                        <a class="nav-link active" id="password-tab1" data-toggle="pill" href="#password" role="tab"
                           aria-controls="password" aria-selected="true">
                            <i class="fa fa-key text-center mr-1"></i>
                            Password
                        </a>
                        <a class="nav-link" href="/profile/history">
                            <i class="fa fa-history text-center mr-1"></i>
                            View History
                        </a>
                    </div>
                </div>
                <div class="tab-content p-4 p-md-5" id="v-pills-tabContent">
                    <div class="tab-pane fade show active" id="account" role="tabpanel" aria-labelledby="account-tab">
                        <h3 class="mb-4">Password Settings</h3>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Old password</label>
                                    <input type="password" class="form-control" name="old_password">
                                    <div style="color: red">
                                        <c:if test="${old_pass_error != null}">
                                            <snap class="small text-left danger"><c:out value="${old_pass_error}"></c:out></snap>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>New password</label>
                                    <input type="password" class="form-control" name="new_password" placeholder="${pass_match_error}">
                                    <div style="color: red">
                                        <c:if test="${new_pass_error != null}">
                                            <snap class="small text-left danger"><c:out value="${new_pass_error}"></c:out></snap>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Confirm new password</label>
                                    <input type="password" class="form-control" name="confirm_password" placeholder="${pass_match_error}">
                                    <div style="color: red">
                                        <c:if test="${conf_pass_error != null}">
                                            <snap class="small text-left danger"><c:out value="${conf_pass_error}"></c:out></snap>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div>
                            <input type="hidden" name="user_id" value="${user_id}">
                            <button class="btn btn-primary">Update</button>
                            <button class="btn btn-light"><a href="/user">Cancel</a> </button>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </form>

</section>

<c:if test="${not empty success_message}">
    <div class="modal fade" id="passwordChangedModal" tabindex="-1" role="dialog" aria-labelledby="passwordChangedModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="passwordChangedModalLabel">Password Changed</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <p>Your password has been changed successfully.</p>
                    <p>Please log in again.</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-outline-warning" onclick="location.href='/logout'">OK</button>
                </div>
            </div>
        </div>
    </div>
    <script>
        $(document).ready(function() {
            $('#passwordChangedModal').modal({
                backdrop: 'static',
                keyboard: false
            });
            $('#passwordChangedModal').on('hidden.bs.modal', function (event) {
                // Check if the OK button was clicked to close the modal
                if (!$('#passwordChangedModal').data('okClicked')) {
                    // If not, show the modal again
                    $('#passwordChangedModal').modal('show');
                } else {
                    // If yes, redirect the user to the logout page
                    window.location.href = '/logout';
                }
            });
            $('#passwordChangedModal').on('click', '.modal-footer button', function (event) {
                // Set a flag to indicate that the OK button was clicked to close the modal
                $('#passwordChangedModal').data('okClicked', true);
            });
        });
    </script>
</c:if>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
