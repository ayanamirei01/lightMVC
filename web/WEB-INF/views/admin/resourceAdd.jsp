<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
	

	$(function() {
		
		$('#pid').combotree({
			url : '${ctx}/resource/tree',
			type:'POST',
			parentField : 'pid',
			lines : true,
			panelHeight : 'auto',
			textField:'text',
			valueField:'id',
			value:'${resource.resource_pid}',
		});

		$('#resourceAddForm').form({
			url : '${ctx}/resource/${action}',
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

					parent.$.modalDialog.openner_treeGrid.treegrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_treeGrid这个对象，是因为resource.jsp页面预定义好了
					parent.layout_west_tree.tree('reload');
					parent.$.modalDialog.handler.dialog('close');
				}
			}
		});
		$("#resource_state").val('${resource.resource_state}');
	});	$("#resource_type").val('${resource.resource_type}');
</script>
<div style="padding: 3px;">
	<form id="resourceAddForm" method="post">
		<table class="grid">
			<tr>
				<td>资源名称</td>
				<td>
					<c:if test="${action== 'edit'}">
						<input type="hidden" name="resource_id" value="${resource.resource_id}">
					</c:if>
					<input name="resource_name" type="text" placeholder="请输入资源名称" class="easyui-validatebox span2" data-options="required:true" value="${resource.resource_name}" ></td>
				<td>资源类型</td>
				<td><select id="resource_type" name="resource_type" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<option value="0" selected="selected">菜单</option>
					<option value="1"  >按钮</option>
				</select></td>
			</tr>
			<tr>
				<td>资源备注</td>
				<td>
					<input type="text" name="resource_remark" class="easyui-validatebox" value="${resource.resource_remark}" data-options="width:200,height:29">
				</td>
			</tr>
			<tr>
				<td>资源路径</td>
				<td><input name="resource_url" type="text" placeholder="请输入资源路径" value="${resource.resource_url}" class="easyui-validatebox span2" data-options="width:140,height:29" ></td>
				<td>排序</td>
				<td><input name="resource_seq" value="0"  class="easyui-numberspinner" style="width: 140px; height: 29px;" required="required" data-options="editable:false" value="${resource.resource_seq}"></td>
			</tr>
			<tr>
				<td>菜单图标</td>
				<td ><input  name="resource_icon" value="${resource.resource_icon}" /></td>
				<td>状态</td>
				<td ><select id="resource_state" name="resource_state" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<option value="0" selected="selected">正常</option>
					<option value="1" >停用</option>
				</select></td>
			</tr>
			<tr>
				<td>上级资源</td>
				<td colspan="3"><select id="pid" name="resource_pid" style="width: 200px; height: 29px;"></select>
				<a class="easyui-linkbutton" href="javascript:void(0)" onclick="$('#pid').combotree('clear');" >清空</a></td>
			</tr>
		</table>
	</form>
</div>