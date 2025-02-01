<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Đăng nhập</title>
</head>
<body>
<%--<div class="container">--%>
<%--	<!-- <h1 class="form-heading">login Form</h1> -->--%>
<%--	<div class="login-form">--%>
<%--		<div class="main-div">--%>
<%--			<c:if test="${not empty message}">--%>
<%--				<div class="alert alert-${alert}">--%>
<%--						${message}--%>
<%--				</div>--%>
<%--			</c:if>--%>
<%--			<form action="<c:url value='/dang-nhap'/>" id="formLogin" method="post">--%>
<%--				<div class="form-group">--%>
<%--					<input type="text" class="form-control" id="username" name="username"--%>
<%--						   placeholder="Tên đăng nhập">--%>
<%--				</div>--%>

<%--				<div class="form-group">--%>
<%--					<input type="password" class="form-control" id="password" name="password"--%>
<%--						   placeholder="Mật khẩu">--%>
<%--				</div>--%>
<%--				<input type="hidden" value="login" name="action"/>--%>
<%--				<button type="submit" class="btn btn-primary" >Đăng nhập</button>--%>
<%--			</form>--%>
<%--		</div>--%>
<%--	</div>--%>
<%--</div>--%>

<section class="mw-100 gradient-custom">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card bg-dark text-white" style="border-radius: 1rem;">
                    <div class="card-body p-4 text-center">
                        <div class="mb-md-5 mt-md-4 pb-5">
                            <h2 class="fw-bold mb-2 text-uppercase">Đăng Nhập</h2>
                            <p class="text-white-40 mb-5">Nhập vào Username và Password của bạn!</p>
                            <c:if test="${not empty message}">
                                <div class="alert alert-${alert}">
                                        ${message}
                                </div>
                            </c:if>
                            <form action="<c:url value='/dang-nhap'/>" id="formLogin" method="post">
                                <div data-mdb-input-init class="form-outline form-white mb-4">
                                    <input style="font-size: 15px;" value="${username}" type="text" id="username" name="username" class="form-control form-control-lg" placeholder="Username"/>
                                </div>
                                <div data-mdb-input-init class="form-outline form-white mb-4">
                                    <input style="font-size: 15px;" value="${password}" type="password" id="password" name="password" class="form-control form-control-lg" placeholder="Password"/>
                                </div>
                                <div class="form-check d-flex justify-content-start align-items-center mb-4 pr-1">
                                    <input class="form-check-input" type="checkbox" value="" name="remember" id="remember" />
                                    <label class="form-check-label" for="remember">Nhớ mật khẩu</label>
                                </div>
                                <input type="hidden" value="login" name="action"/>
<%--                                <p  class="small mb-2 pb-lg-2"><a class="text-white-30" href="#!">Forgot password?</a></p>--%>
                                <div class="form-outline form-white mb-4">
                                    <button data-mdb-button-init data-mdb-ripple-init
                                            class="btn btn-outline-light btn-lg px-5" type="submit">Đăng Nhập
                                    </button>
                                </div>
                                <div>
                                    <p class="mb-0">Bạn chưa có tài khoản? <a href="#!" class="text-white-50 fw-bold">Tạo tài khoản</a>
                                    </p>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>