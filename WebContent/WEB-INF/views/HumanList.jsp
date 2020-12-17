<!-- /**
  * @FileName : HumanListController.java
  * @Project : springmvc03
  * @Date : 2020. 11. 11. 오후 11:00:40
  * @작성자 : 최호석
  * @변경이력 :
  * @프로그램 설명 : javascript 와 jsp 다까먹음
  					 form , input, button 다시 공부
  					 자바스크립트 ready() 함수
  					 $와 #d을 붙이는 이유 val() 변수 지정
  					 submit() 공부 
  					 EL 태그 공부
*/ -->

<!--  -->
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<style type="text/css">

   *
   {
      line-height: 150%;
   }
   #customers td
   {
      text-align: center;
   }
   #submitBtn
   {
      height: 150%;
      width: 240px;
      font-size: 18px;
      font-weight: bold;
      font-family: 맑은 고딕;
      color: #343;
   }
	#err
   {
      display: none;
      color: red;
      font-size: small;
   }
</style>

<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>

<script type="text/javascript">

	$(document).ready(function()
	{
		$("#submitBtn").click(function()
		{
			var name = $("#name").val();
			var telephone = $("#telephone").val();
			
			if(name == "" || telephone == "")
			{
				$("#err").show();
				return false;
			}
		
			// attr, prop 둘중 하나 underfied 뜨는거 빼고 사용해라
			
			$("#humanForm").submit();
		})
	});
	
</script>
</head>
<body>

<div>
   <h1>회원 관리(HumanList.jsp)</h1>
   <hr>
</div>

<div>
	<!-- 폼태그를 이용하여 post방식으로 humaninsert.action 액션처리 -->
	<!-- 폼태그 id는 humanForm -->
	<form action="humaninsert.action" method="post" id="humanForm">
	<!-- input 태그를 이용하여 입력 버튼 구성 name과 id를 지정하여 submit으로 객체 이동 -->
		이름 <input type="text" name="name" id="name"><br>
		전화 <input type="text" name="telephone" id="telephone"><br>
		<button type="button" id="submitBtn">회원 추가</button>
		<span id="err">모든 항목을 입력해야 합니다.</span>
	</form>
	<br>
	
	<!-- EL 표현에 의한 인원 수 출력 부분 -->
	<p>전체 인원수 : ${count }</p>
	
	<table border="1">
		<tr>
			<th>번호</th><th>이름</th><th>전화번호</th>
		</tr>
	
	
		<!-- EL, JSTL 표현에 의한 회원 리스트 출력 부분 -->
		<c:forEach var="human" items="${humanlist }">
			<tr>
			<!-- HumanDTO 객체의 getMid() ~ getTelePhone() 메소드 호출 -->
			<!-- 할 수 있는 EL 표현 활용 부분 -->
				<td>${human.mid }</td>
				<td>${human.name }</td>
				<td>${human.telephone }</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>