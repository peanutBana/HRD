<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="style.css">
	<script type="text/javascript" src="script.js"></script>
	<%@ page import="DTO.Member"%> 
	<%
		Member m = new Member();
		m = (Member)request.getAttribute("member");
	%>
	<title>Insert title here</title>
</head>
<body>
	<%@ include file="topmenu.jsp" %>
<section>
	<div class="title">홈쇼핑 회원 정보 수정</div>
	<form name="frm" action="update">
		<input type="hidden" id="GUBUN" value="insert">
		<div class="wrapper">
			<table>
				<tr>
					<th>회원번호(자동발생)</th>
					<td><input type="text" name="custno" value="<%=m.getCustno()%>" readonly></td>
				</tr>
				<tr>
					<th>회원성명</th>
					<td><input type="text" name="custname" value=<%=m.getCustname()%>></td>
				</tr>
				<tr>
					<th>회원전화</th>
					<td><input type="text" name="phone" value=<%=m.getPhone()%>></td>
				</tr>
				<tr>
					<th>회원주소</th>
					<td><input type="text" name="address" value=<%=m.getAddress()%>></td>
				</tr>
				<tr>
					<th>가입일자</th>
					<td><input type="text" name="joindate" value=<%=m.getJoindate()%>></td>
				</tr>
				<tr>
					<th>고객등급[A:VIP, B:일반, C:일반]</th>
					<td><input type="text" name="grade" value=<%=m.getGrade()%>></td>
				</tr>
				<tr>
					<th>도시코드</th>
					<td><input type="text" name="city" value=<%=m.getCity()%>></td>
				</tr>
				<tr>
					<td colspan="2">
						<button class="btn" type="submit" onclick="fn_submit(); return false;">수정</button>
						<button class="btn" type="button" onclick="location='list'">조회</button>
					</td>
				</tr>
			</table>
		</div>
		
	</form>
</section> 

<%@ include file="footer.jsp" %> 
</body>
</html>