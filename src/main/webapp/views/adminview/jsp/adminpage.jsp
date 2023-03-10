<%@ page import="com.jareer.englishlearningplatform.domains.Users" %>
<%--
  Created by IntelliJ IDEA.
  User: javohir
  Date: 16/02/2023
  Time: 21:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Admin Page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.2.0/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.2/css/dataTables.bootstrap5.min.css"/>
</head>
<body >

<%--Begin Header--%>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/admin-page">
            <img src="/views/user/img/about-img.jpg" alt="Avatar Logo" style="width:40px;" class="rounded-pill">
        </a>
    </div>
</nav>

<%-- End Header --%>

<div class="container mt-5">
    <table id="example" class="table table-striped" style="width:100%">
        <thead>
        <tr>
            <th>№</th>
            <th>Username</th>
            <th>Fullname</th>
            <th>Email</th>
            <th>BirthDate</th>
            <th>Gender</th>
            <th>Level</th>
            <th>Score</th>
            <th>Role</th>
            <th>Deleted</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${i=i+1}</td>
                <td>${user.getUsername()}</td>
                <td>${user.getLastName()} ${user.getFirstName()}</td>
                <td>${user.getEmail()}</td>
                <td>${user.getBirthDate()}</td>
                <td>
                    <c:choose>
                        <c:when test="${user.isGender()==true}">MAN</c:when>
                        <c:otherwise>FEMALE</c:otherwise>
                    </c:choose>
                </td>
                <td>${user.getLevel()}</td>
                <td>${user.getScore()}</td>


                <td>
                    <div class="dropdown">
                        <a class="btn btn-secondary dropdown-toggle" id="dropdownMenuLink"
                           data-bs-toggle="dropdown" aria-expanded="false">
                                ${user.getRole()}
                        </a>

                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                            <li>
                                <form>
                                    <button onclick="choose(${user.getId()},'user')" class="dropdown-item"
                                            type="button"
                                            data-bs-target="#changeRoleModal"
                                            data-bs-toggle="modal">
                                        USER
                                    </button>
                                </form>
                            </li>
                            <li>
                                <form>
                                    <button onclick="choose(${user.getId()},'teacher')" class="dropdown-item"
                                            type="button"
                                            data-bs-target="#changeRoleModal"
                                            data-bs-toggle="modal">
                                        TEACHER
                                    </button>
                                </form>
                            </li>
                            <li>
                                <form>
                                    <button onclick="choose(${user.getId()},'admin')" class="dropdown-item"
                                            type="button"
                                            data-bs-target="#changeRoleModal"
                                            data-bs-toggle="modal">
                                        ADMIN
                                    </button>
                                </form>
                            </li>
                        </ul>
                    </div>
                </td>
                <td>
                    <form>
                        <c:choose>
                            <c:when test="${user.isDeleted()!=true}">
                                <form>
                                    <button onclick="changeDeletedHelper(${user.getId()})"
                                            class="btn btn-outline-danger"
                                            type="button"
                                            data-bs-target="#deleteUserModal"
                                            data-bs-toggle="modal">
                                        Deactivate
                                    </button>
                                </form>
                            </c:when>
                            <c:otherwise>
                                    <button onclick="changeActivatedHelperFunc(${user.getId()})"
                                            type="button"
                                            class="btn btn-outline-success"
                                            data-bs-target="#activateUserModal"
                                            data-bs-toggle="modal">
                                        Activate
                                    </button>
                            </c:otherwise>
                        </c:choose>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>




<!-- Modal -->
<div class="modal fade" id="deleteUserModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteModalLabel">User delete</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form method="post" action="/user/delete/*" id="v-deleted>">
                <div class="modal-body">
                    <input type="hidden" name="id" id="deleted_id" value="${deleted_id}">
                    Do you really want to delete this user?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-danger">Save changes</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="activateUserModal" tabindex="-1" aria-labelledby="activateUserLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="activateUserLabel">User active</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form method="post" action="/user/activated/*" id="v-activated">
                <div class="modal-body">
                    <input type="hidden" name="id" id="activated_id" value="${activated_id}">
                    Do you really want to activate this user?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-danger">Save changes</button>
                </div>
            </form>
        </div>
    </div>
</div>


<!-- Modal -->
<div class="modal fade" id="changeRoleModal" tabindex="-1" aria-labelledby="changeRoleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="changeRoleModalLabel">User change role </h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form method="post" action="/admin/change-role/*" id="new-line">
                <div class="modal-body">
                    <input type="hidden" name="id" id="id" value="${id}">
                    <input type="hidden" name="role" id="role" value="${role}">
                    Do you really change role this user?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-danger">Save changes</button>
                </div>
            </form>
        </div>
    </div>
</div>


<script src="/resources/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.datatables.net/1.13.2/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.13.2/js/dataTables.bootstrap5.min.js"></script>
<script src="/views/adminview/js/choose.js"></script>
<script src="/views/adminview/js/diagram.js"></script>


<script>

    $(document).ready(function () {
        $('#example').DataTable()
    })
</script>
</body>
</html>