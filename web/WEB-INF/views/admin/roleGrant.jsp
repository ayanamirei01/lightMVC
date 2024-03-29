<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
	var resourceTree;
	$(function() {
		resourceTree = $('#resourceTree').tree({
			url : '${ctx}/resource/tree',
			parentField : 'resource_pid',
			lines : true,
			checkbox : true,
			onCheck:function(node) {
			  var nodeParent = $(this).tree('getParent',node.target);
			  if(node.checked)
			  $("#resourceTree").tree('check',nodeParent.target);
            },
			onLoadSuccess : function(node, data) {
				progressLoad();
				$.post( '${ctx}/role/get', {
					role_id : '${role_id}'
				}, function(result) {
					var ids;
					if (result.role_id != undefined&&result.resources!= undefined) {
						ids = Array.from(result.resources);
					}

					if (ids.length > 0) {
						for (var i = 0; i < ids.length; i++) {
							if (resourceTree.tree('find', ids[i].resource_id)) {

								resourceTree.tree('check', resourceTree.tree('find', ids[i].resource_id).target);
							}
						}
					}
				}, 'json');
				progressClose();
			},
			cascadeCheck : false
		});

		$('#roleGrantForm').form({
			url : '${ctx}/role/grant',
			onSubmit : function() {
				progressLoad();
				var isValid = $(this).form('validate');
				if (!isValid) {
					progressClose();
				}
				var checknodes = $("#resourceTree").tree('getChecked');
				var parent = $("#resourceTree").tree('getParent');
				console.log(parent);
				var ids = [];
				if (checknodes && checknodes.length > 0) {
					for ( var i = 0; i < checknodes.length; i++) {
						ids.push(checknodes[i].id);
					}
				}

				$('#resources').val(ids);
				return isValid;
			},
			success : function(result) {
				progressClose();
				result = $.parseJSON(result);
				if (result.success) {
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				} else {
					//parent.$.messager.alert('错误', result.error, 'error');
				}
			}
		});
	});

	function checkAll() {
		var nodes = resourceTree.tree('getChecked', 'unchecked');
		if (nodes && nodes.length > 0) {
			for ( var i = 0; i < nodes.length; i++) {
				resourceTree.tree('check', nodes[i].target);
			}
		}
	}
	function uncheckAll() {
		var nodes = resourceTree.tree('getChecked');
		if (nodes && nodes.length > 0) {
			for ( var i = 0; i < nodes.length; i++) {
				resourceTree.tree('uncheck', nodes[i].target);
			}
		}
	}
	function checkInverse() {
		var unchecknodes = resourceTree.tree('getChecked', 'unchecked');
		var checknodes = resourceTree.tree('getChecked');
		if (unchecknodes && unchecknodes.length > 0) {
			for ( var i = 0; i < unchecknodes.length; i++) {
				resourceTree.tree('check', unchecknodes[i].target);
			}
		}
		if (checknodes && checknodes.length > 0) {
			for ( var i = 0; i < checknodes.length; i++) {
				resourceTree.tree('uncheck', checknodes[i].target);
			}
		}
	}
</script>
<div id="roleGrantLayout" class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'west'" title="系统资源" style="width: 300px; padding: 1px;">
		<div class="well well-small">
			<form id="roleGrantForm" method="post">
				<input name="role_id" type="hidden"  value="${role_id}" readonly="readonly">
				<ul id="resourceTree"></ul>
				<input id="resources" name="resources" type="hidden" />
			</form>
		</div>
	</div>
	<div data-options="region:'center'" title="" style="overflow: hidden; padding: 10px;">
		<div>
			<button class="btn btn-success" onclick="checkAll();">全选</button>
			<br /> <br />
			<button class="btn btn-warning" onclick="checkInverse();">反选</button>
			<br /> <br />
			<button class="btn btn-inverse" onclick="uncheckAll();">取消</button>
		</div>
	</div>
</div>