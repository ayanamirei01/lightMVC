<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {

		$('#roleAddForm').form({
			url : '${pageContext.request.contextPath}/role/${action}',
			onSubmit : function() {
				progressLoad();
				var isValid = $(this).form('validate');
				if (!isValid) {
					progressClose();
				}
				return isValid;
			},
			success : function(result) {
				progressClose();
				result = $.parseJSON(result);
				if (result.success) {
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.error, 'error');
				}
			}
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;" >
		<form id="roleAddForm" method="post">
			<table class="grid">
				<tr>
					<td>角色名称</td>
					<td>
						<c:if test="${role != null}">
							<input type="hidden" value="${role.role_id}" name="role_id">
						</c:if>
						<input name="role_name" type="text" placeholder="请输入角色名称" class="easyui-validatebox span2" data-options="required:true" value="${role.role_name}"></td>
				</tr>
				<tr>
					<td>排序</td>
					<td><input name="role_seq" value="${role.role_seq}" class="easyui-numberspinner" style="width: 140px; height: 29px;" required="required" data-options="editable:false"></td>
				</tr>
				<tr>
					<td>备注</td>
					<td colspan="3"><textarea name="role_remark"  rows="" cols="" >${role.role_remark}</textarea></td>
				</tr>
			</table>
		</form>
	</div>
</div>