<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
</head>
<body>
	<div class="container">
	<h1>회원가입 폼</h1>
	<hr></hr>
	<br/>
		<form action="signup.do" method="post" id="signup-form">
			<div class="form-group">
				<label for="id">아이디</label>
				<input type="text" class="form-control" name="id" id="id" />
			</div>
			
			<div class="form-group">
				<label for="pwd">비밀번호</label>
				<input type="password" class="form-control" name="pwd" id="pwd" />
			</div>
			
			<div class="form-group">
				<label for="pwd2">비밀번호 확인</label>
				<input type="text" class="form-control" name="pwd2" id="pwd2" />
			</div>
			
			<div class="form-group">
				<label for="email">이메일</label>
				<input type="text" class="form-control" name="email" id="email" />
			</div>
			
			<button type="submit" class="btn btn-primary">가입하기</button>
			<button type="reset" class="btn btn-danger">취소하기</button>
		</form>
	</div>
	<script src="${pageContext.request.contextPath }/resources/js/jquery-3.5.1.js"></script>
	
	<script>
		var reg_id = /^[a-z].{4,9}$/;
		var reg_pwd = /^.{5,10}$/;
		var reg_email = /@/;
		
		var isIdValid = false;
		var isPwdValid = false;
		var isEmailValid = false;
	
		$("#id").on("input", function(){
			var inputId = $("#id").val();
			var result = reg_id.test(inputId);
			$(this).removeClass("is-valid is-invalid");
			if(result){
				$.ajax({
					url : "checkid.do",
					method : "get",
					data : "inputId="+inputId,
					success:function(data){
						$(this).removeClass("is-valid is-invalid");
						if(data.isExist){
							isIdValid = false;
							$("#id").addClass("is-invalid");
						}else{
							isIdValid = true;
							$("#id").addClass("is-valid");
						}
					}
					
				});
			}else{
				isIdValid = false;
				$("#id").addClass("is-invalid");
			}
		});
		

		$("#pwd, #pwd2").on("input", function(){
			var inputPwd = $("#pwd").val();
			var inputPwd2 = $("#pwd2").val();
			
			var result = reg_pwd.test(inputPwd);
			
			$("#pwd").removeClass("is-valid is-invalid");
			if(result){
				if(inputPwd == inputPwd2){
					isPwdValid = true
					$("#pwd").addClass("is-valid");
				}else{
					isPwdValid = false;
					$("#pwd").addClass("is-invalid");
				}
			}else{
				isPwdValid = false;
				$("#pwd").addClass("is-invalid");
			}
		});
		
		$("#email").on("input", function(){
			var inputEmail = $("#email").val();
			
			isEmailValid = reg_email.test(inputEmail);
			$(this).removeClass("is-valid is-invalid");
			if(isEmailValid){
				$(this).addClass("is-valid");
			}else{
				$(this).addClass("is-invalid");
			}
		});
	</script>
</body>
</html>