<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head><jsp:include page="../inc.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="edge" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:if test="${fn:contains(sessionInfo.resourceList,'/user/edit' )}">
	<script type="text/javascript">
        $.canEdit = true;
	</script>

</c:if>
	<c:if test="${fn:contains(sessionInfo.resourceList,'/user/delete' )}">
		<script type="text/javascript">
            $.canDelete = true;
		</script>
	</c:if>
<title>用户管理</title>
	<script type="text/javascript">
	var dataGrid;
	var deptTree;
	$(function() {

		deptTree = $('#deptTree').tree({
			url : '${path}/dept/tree',

			lines : true,
			onClick : function(node) {
				dataGrid.datagrid('load', {
                    user_sex:1,
				});
			}
		});
	
		dataGrid = $('#dataGrid').datagrid({
			url : '${path}/user/dataGrid',
			fit : true,
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'user_id',
			sortName : 'user_createTime',
			sortOrder : 'asc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			columns : [ [ {
				width : '80',
				title : '登录名',
				field : 'login_name',
				sortable : true
			}, {
				width : '80',
				title : '姓名',
				field : 'user_name',
				sortable : true
			},{

				width : '80',
				title : '部门ID',
				field : 'dept_id',
				 hidden : true,
				formatter:function(value,row,index) {
				    return row.dept.dept_id;
				}
			},{
				width : '80',
				title : '所属部门',
				field : 'dept',
				formatter:function(value,row,index) {
				   return row.dept.dept_name;
				}
			},{
				width : '120',
				title : '创建时间',
				field : 'user_createTime',
				sortable : true,

			},  {
				width : '40',
				title : '性别',
				field : 'user_sex',
				sortable : true,
				formatter : function(value, row, index) {
					switch (value) {
					case 0:
						return '女';
					case 1:
						return '男';
					}
				}
			}, {
				width : '40',
				title : '年龄',
				field : 'user_age',
				sortable : true
			},{
				width : '60',
				title : '电话',
				field : 'user_phone',
				sortable : true
			}, {
				width : '60',
				title : '用户类型',
				field : 'user_type',
				sortable : true,
				formatter : function(value, row, index) {
					if(value==1){
						return "管理员";
					}else if(value==0){
						return "用户";
					}
					return "未知类型";
				}
			},{
				width : '60',
				title : '是否默认',
				field : 'user_isDefault',
				sortable : true,
				formatter : function(value, row, index) {
					switch (value) {
					case 1:
						return '默认';
					case 0:
						return '否';
					}
				}
			},{
				width : '60',
				title : '状态',
				field : 'user_state',
				sortable : true,
				formatter : function(value, row, index) {
					switch (value) {
					case 1:
						return '正常';
					case 0:
						return '停用';
					}
				}
			} , {
				field : 'action',
				title : '操作',
				width : 100,
				formatter : function(value, row, index) {
					var str = '';

					if($.canEdit)
					str += '<a href="javascript:void(0)" onclick="editFun(\''+row.user_id+'\');" >编辑</a>';

					str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
					if($.canDelete)
					str += '<a href="javascript:void(0)" onclick="deleteFun(\''+row.user_id+'\');" >删除</a>';


					return str;
				}
			}] ],
			toolbar : '#toolbar'
		});
	});
	
	function addFun() {
		parent.$.modalDialog({
			title : '添加',
			width : 500,
			height : 300,
			href : '${path}/user/addPage',
			buttons : [ {
				text : '添加',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#userAddForm');
					f.submit();
				}
			} ]
		});
	}
	
	function deleteFun(id) {
	    console.log(id);
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {//点击操作里面的删除图标会触发这个
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('询问', '您是否要删除当前用户？', function(b) {
			if (b) {
				var currentUserId = '${sessionInfo.user.user_id}';/*当前登录用户的ID*/
				if (currentUserId != id) {
					progressLoad();
					$.post('${path}/user/delete/'+id+'', {

					}, function(result) {

						parent.$.messager.alert('提示', result.error, 'info');
						dataGrid.datagrid('reload');

						progressClose();
					}, 'JSON');
				} else {
					parent.$.messager.show({
						title : '提示',
						msg : '不可以删除自己！'
					});
				}
			}
		});
	}
	
	function editFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}

		parent.$.modalDialog({
			title : '编辑',
			width : 500,
			height : 300,
			href : '${path}/user/editPage/'+id,
			buttons : [ {
				text : '编辑',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#userAddForm');
					f.submit();
				}
			} ]
		});
	}
	
	function searchFun() {
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}
	function cleanFun() {
		$('#searchForm input').val('');
		dataGrid.datagrid('load', {});
	}
	</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
		<form id="searchForm">
			<table>
				<tr>
					<th>姓名:</th>
					<td><input name="name" placeholder="请输入用户姓名"/></td>
					<th>创建时间:</th>
					<td><input name="createdatetimeStart" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />至<input  name="createdatetimeEnd" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="cleanFun();">清空</a></td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:true,title:'用户列表'" >
		<table id="dataGrid" data-options="fit:true,border:false"></table>
	</div>
	<div data-options="region:'west',border:true,split:false,title:'组织机构'"  style="width:180px;overflow: hidden; ">
		<ul id="deptTree"  style="width:160px;margin: 10px 10px 10px 10px">
		</ul>
	</div>
	<div id="toolbar" style="display: none;">
		<c:if test="${fn:contains(sessionInfo.resourceList, '/user/add')}">
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
		</c:if>
	</div>
</body>
</html>