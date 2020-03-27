<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>

<script type="text/javascript">
	function studentDelete(questionId){
		if(confirm("确定要删除这条记录吗?")){
			$.post("questionDelete",{questionId:questionId},
				function(result){
					var result = eval('('+result+')');
					if(result.error){
						alert(result.error);
					}else{
						alert("删除成功！");
						window.location.href="${pageContext.request.contextPath}/questionList";
					}
				}
			);
		}
	}
	function formReset(){
		$("input[type='text']").val("");
		window.location.href="${pageContext.request.contextPath}/questionList";
	}

</script>
<div class="data_list">
	<div class="data_info">
		<p>试题信息管理</p>
	</div>
	<div class="search_content">
		<form id="myform" action="${pageContext.request.contextPath}/questionList" method="post">
			<table align="center">
				<tr>
					<td><label>考试题目：</label></td>
					<td><input type="text" id="s_subject" name="subject" value="${subject}"/></td>
					<tr>
				<td><label>试题：</label></td>
				<td>
				<select id="paperId" name="paperId">
								<option value="">请选择...</option>
								<c:forEach var="paper" items="${paperList }">
									<option value="${paper.id}" >${paper.paperName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>

					<td><button class="btn btn-primary" style="margin-bottom: 8px;" type="submit" >查询</button></td>
					<td><button style="margin-bottom: 8px;" type="button"  class="btn btn-primary" onclick="formReset();">重置</button></td>
				</tr>
			</table>
		</form>
		<button class=" btn btn-primary" style="float: left;margin-bottom: 5px;" type="button" onclick="javascript:window.location='preSave'">添加考生题目</button>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<button class=" btn btn-primary"  type="button" onclick="javascript:window.location='getDetailPaper'">查看考试试卷</button>
	</div>
	<div class="data_content">
		<table class="table table-bordered table-hover">
			 <tr>
				<th>序号</th>
				<th>考试题目</th>
				<th>加入时间</th>
				<th>题目类型</th>
				<th>所属试卷</th>
				<th>操作</th>
			</tr>
			<c:forEach var="question" items="${questionList}" varStatus="status" >
				<tr>
					<td>${status.index+1 }</td>
					<td>${question.subject }</td>
					<td><fmt:formatDate value="${question.joinTime }" type="date" pattern="yyyy-MM-dd"/></td>
					<c:choose>
						<c:when test="${question.type==1 }">
							<td>单选题</td>
						</c:when>
						<c:otherwise>
							<td>多选题</td>
						</c:otherwise>
					</c:choose>
					<td>${question.paper.paperName }</td>
					<td><button class="btn-mini btn-info" type="button" onclick="javascript:window.location='getQuestionById?questionId=${question.qid}'">查看试题</button>&nbsp;&nbsp;<button class="btn-mini btn-info" type="button" onclick="javascript:window.location='preSave?questionId=${question.qid}'">修改</button>&nbsp;&nbsp;<button class="btn-mini btn-danger" type="button" onclick="studentDelete(${question.qid })">删除</button></td>
				</tr>
			</c:forEach>
	  </table>
	</div>
	<div class="pagination pagination-centered">
	 	 <ul>
	    	${pageCode }
	 	 </ul>
	</div>
</div>