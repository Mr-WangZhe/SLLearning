<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>听说在线学习平台 | 课程添加</title>
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<jsp:include page="../leftNavigation.jsp"></jsp:include>
<style type="text/css">

label {
	color: #ab0351
}
.col-top {
	margin-top: 30px;
}
</style>
</head>
<script>
	/*加载rateType*/
	function getRateType() {
	    var languageTypeId = $("#languageType").val();
	    $("#rateType").empty();
	    $.ajax({
	        type: "get",
	        url: "${pageContext.request.contextPath}/ratetype/user/getratetypebylanguagetypeid",
	        data: {"languageTypeId": languageTypeId},
	        success: function (data) {
	            $('#rateType').append("<option value='' selected='selected' >" + '请选择' + "</option>");
	            var info = data.info;
	            for (var i = 0; i < info.length; i++) {
	                $('#rateType').append("<option value='" + info[i].rateTypeId + "' >" + info[i].rateName + "</option>");
	            }
	        },
	        error: function () {
	            alert("加载类别类型");
	        }
	    });
	};
</script>
<body class="hold-transition sidebar-mini">
	<div class="content-wrapper">
		<section class="content">
			<div class="container-fluid">
				<!-- 添加阅读课程 -->
				
						<div class="card-warning card-outline">
							<div class="card-header">
								<h3 class="card-title">修改阅读课程</h3>
							</div>
							<div class="card-body">
									<form action="${pageContext.request.contextPath }/course/admin/updateReadingCourse" method="POST" enctype="multipart/form-data">
																					<input type="hidden" name="courseId" value="${readingCourse.courseId}"/>
											<label>请选择语言类别：</label>
											<select name="languageTypeId" class="form-control"  id="languageType" onchange="getRateType()">
						                        <option value="${readingCourse.languageType.languageTypeId}">${readingCourse.languageType.languageName }</option>
						                        <c:forEach items="${languageTypes}" var="languageType">
						                        		<option value="${languageType.languageTypeId}">${languageType.languageName}</option>
						                        </c:forEach>
						                    </select>
										<label></label>请选择类型类别：
											<select name="rateTypeId" class="form-control" id="rateType">
												<option value="${readingCourse.rateType.rateTypeId}">${readingCourse.rateType.rateName}</option>
											</select>
										<label>请设置课程名称：</label><input type="text" class="form-control" name="courseName" value="${readingCourse.courseName}"/>
										<label>请输入中文内容：</label><textarea class="form-control" name="courseChineseContent" rows="10" cols="30">${readingCourse.courseChineseContent}</textarea>
										<label>请输入英文内容：</label><textarea class="form-control" name="courseEnglishContent" rows="10" cols="30">${readingCourse.courseEnglishContent}</textarea>
										<label>下载量：</label><input type="text" class="form-control" name="downloadNum" value="${readingCourse.downloadNum}"/>
										<label>原课程图标：</label><img src="${readingCourse.instructionImgUrl }" height="30px" width="30px"/><br/>
										<label>请添加课程图标：</label><input type="file" class="form-control"  name="instructionImg"/>

										<button type="submit" class="btn btn-warning float-right col-top">提交</button>
									</form>
								
							
					</div>
				</div>
			</div>
		</section>
	</div>
</body>
</html>