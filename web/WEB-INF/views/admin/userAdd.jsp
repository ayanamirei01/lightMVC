<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">


	$(function() {

		$('#dept_id').combobox({
			url : '${ctx}/dept/showAll',
			textField:"dept_name",
			valueField:"dept_id",

		});
        $('#dept_id').combobox('select',${u.dept.dept_id});
		$('#role_id').combobox({
		    url: '${ctx}/role/showAll',
            textField:"role_name",
            valueField:"role_id",
		});
		$('#role_id').combobox('select',${u.role.role_id});
		$('#userAddForm').form({

			url : '${ctx}/user/${action}',
			dataType:'json',
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
				//result = $.parseJSON(result);
				result = eval('(' + result + ')');
				if (result.success) {
				    console.log(result.error);
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('提示', result.error, 'warning');
				}
			}
		});
		
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
		<form id="userAddForm" method="post">
            <c:if test="${action == 'edit'}">
                <input type="hidden" name="_method" value="PUT">
                <input type="hidden" name="user.user_id" value="${u.user_id}">
            </c:if>
			<table class="grid">
				<tr>
					<td>登录名</td>
					<td><input name="user.login_name" type="text" placeholder="请输入登录名称" class="easyui-validatebox" data-options="required:true" value="${u.login_name}"></td>
					<td>姓名</td>
					<td><input name="user.user_name" type="text" placeholder="请输入姓名" class="easyui-validatebox" data-options="required:true" value="${u.user_name}"></td>
				</tr>
				<tr>
					<td>密码</td>
					<td><input name="user.password" type="password" placeholder="请输入密码" class="easyui-validatebox" data-options="required:true" value=""></td>
					<td>性别</td>
					<td><select name="user.user_sex" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<option value="1" selected="selected">男</option>
                        <option value="0" <c:if test="${u.user_sex == 0}">selected="selected"</c:if>>女</option>
					</select></td>
				</tr>
				<tr>
					<td>年龄</td>
					<td><input type="text" name="user.user_age" class="easyui-numberbox" value="${u.user_age}"/></td>
					<td>用户类型</td>
					<td><select name="user.user_type" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                        <option value="1" >管理员</option>
							<option value="0" <c:if test="${u.user_type ==0}">selected="selected"</c:if>>用户</option>
					</select></td>
				</tr>
				<tr>
					<td>部门</td>
					<td><select id="dept_id" name="user.dept.dept_id" style="width: 140px; height: 29px;" class="easyui-validatebox" data-options="required:true"></select></td>
					<td>角色</td>
					<td><select id="role_id"  name="user.role.role_id" data-options="required:true"  style="width: 140px; height: 29px;"></select></td>
				</tr>
				<tr>
					<td>电话</td>
					<td colspan="3">
						<input type="text" name="user.user_phone" class="easyui-numberbox"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>