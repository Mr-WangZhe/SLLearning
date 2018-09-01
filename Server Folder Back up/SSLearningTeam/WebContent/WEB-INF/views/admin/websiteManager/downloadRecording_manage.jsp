<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>听说在线学习平台 | 轮播图管理</title>
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1">

<jsp:include page="../leftNavigation.jsp"></jsp:include>
</head>
<body class="hold-transition sidebar-mini">
	<div class="content-wrapper">
		<section class="content">
			<div class="container-fluid">
				<!-- 语言分类列表 -->
				<div class="card-body">
					<table id="example1" class="table table-bordered table-striped">
						<thead>
							<tr style="text-align: center; color: #f71f83">
								<th>下载编号</th>
								<th>下载用户</th>
								<th>课程名称</th>
								<th>下载时间</th>
								<th style="text-align: center;">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="downloadRecording" items="${downloadRecordings}" varStatus="status">
								<tr style="text-align: center">
									<td>${status.count}</td>
									<td>${downloadRecording.user.userName}</td>
									<td>${downloadRecording.course.courseName}</td>
									<td>${downloadRecording.downloadRecordingTime}</td>
									<td><a href="#">删除</a></td>
								</tr>
							</c:forEach>
					</table>
					<!-- 分页 -->
					<div class="card-footer clearfix">
						<ul class="pagination pagination-sm m-0 float-right">
							<li class="page-item"><a class="page-link" href="#">«</a></li>
							<c:forEach varStatus="count" begin="1" end="${pageNumber}" step="1">
								<li class="page-item"><a class="page-link" href="#">${count.index}</a></li>
							</c:forEach>
							<li class="page-item"><a class="page-link" href="#">»</a></li>
						</ul>
					</div>
				</div>
			</div>
		</section>
	</div>
	<div>${pageName }</div>
	<div>${languageTypes}</div>
</body>
</html>