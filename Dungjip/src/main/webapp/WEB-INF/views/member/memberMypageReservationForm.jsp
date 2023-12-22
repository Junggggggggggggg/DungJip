<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.kh.dungjip.member.model.vo.Member"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>예약내역</title>

<style>
.card {
	box-shadow: 1px 1px 7px 0;
	border: none;
	border-radius: 12px 12px 12px 12px;
}

.card h3 {
	font-size: 18px;
	margin-bottom: 10px;
	position: relative;
	display: block;
	padding: 15px 38px 13px 18px;
	background-color: #c2c2c2;
	border-radius: 12px 12px 0 0;
	color: #effcf1;
}

.card p {
	font-size: 14px;
}

.card a {
	color: #fff;
	text-decoration: none;
	font-size: 14px;
	display: inline-block;
	margin-top: 10px;
	padding-top: 10px;
}

.card ul {
	padding: 0 40px;
}

.item_text {
	padding: 20px 0 10px 0px;
	line-height: 30px;
	font-size: 14px;
	letter-spacing: -0.8;
}

.btn_edit {
	float: right;
	padding: 2px 9px 3px 10px;
	color: #7b8994;
	border: none;
}
</style>
</head>
<body>

	<%@ include file="../common/header.jsp"%>

	<div class="container" style="display: flex; width: 67%;">

		<!-- 마이페이지 메뉴바 -->
		<%@ include file="memberMypagemenubar.jsp"%>


		<section class="main-content"
			style="width: 100%; margin: 70px 0 70px 50px; margin-left: 4%;">
			<div class="container section_gap_top_75">
				<div class="cart_inner">
					<div class="table-responsive">
						<table class="table">
							<thead>
								<tr class="text-center">
									<th scope="col" style="width:10%;">#</th>
									<th scope="col">예약 상세정보</th>
									<th scope="col" style="width:15%;">리뷰</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach var="reservation" items="${rlist }">
								<tr>
									<td class="text-center">
										<h5>NO. 1</h5>
									</td>
									<td>
										<div class="media">
											<div class="d-flex"></div>
											<div class="media-body">
												<p>
													<b># 일시</b>
													<fmt:formatDate value="${rervation.reservationDate}" pattern="yyyy년 MM월 dd일" /> 
													${reservation.time.timeValue }
												</p>
												<p>
													<b># 중개사무소</b> ${reservation.estate.esName }
												</p>
											</div>
										</div>
									</td>	
									<td>
									<!-- 여기버튼 누르면 리뷰 작성 창 -->
									<div class="end">
									<div class="flex">
									<a href="#" class="genric-btn primary-border small" onclick="insertMyReview(${rv.playerNo});">작성</a>
									</div>
									</td>
									
							</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			
		</section>

	</div>


	<%@ include file="../common/footer.jsp"%>

</body>
</html>