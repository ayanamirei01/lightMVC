<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="edge" />
<c:if test="${fn:contains(sessionInfo.resourceList, '/resource/edit')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/resource/delete')}">
	<script type="text/javascript">
	</script>
</c:if>
<title>资源管理</title>
<script type="text/javascript">
	var treeGrid;
	$(function() {

	    treeGrid = $("#treeGrid").treegrid({
			url:'${ctx}/resource/tree',
			idField:'id',
			treeField:'text',
			parentField:'pid',
			fit:true,
			fitColumns:false,
			border:false,
			frozenColumns:[[{title:'编号',
				field:'id',
				width:40

			}]],
			columns: [[{
			    field:'text',
				title:'资源名称',
				width:150,
				// formatter:function(val,row,index){
			    //     return row.attributes.resource_name;
				// }
			},{
			    field:'attributes.resource_url',
				title:'资源路径',
				width:200,
				formatter:function(val,row,index) {
			        return row.attributes.resource_url;
				}
			},
				{
                field: 'attributes.resource_seq',
                title: '排序',
                width: 40,
				formatter:function(val,row,index) {

                    return row.attributes.resource_seq;
				}
              },
				{
			    field:'attributes.resource_icon',
				title:'图标',
				width:100,
				formatter:function(val,row,index) {
			        return row.attributes.resource_icon;
				}
			},{
			    field:'attributes.resource_type',
				title:'资源类型',
				width:100,
				formatter:function(val,row,index) {
			        if(row.attributes.resource_type == '1') {
			            return '按钮';
					} else {
			            return '菜单';
					}
				}
			},{
			    field:'attributes.resource_pid',
				title:'上级资源ID',
				width:40,
				hidden:true,
				formatter:function(val,row,index) {
			        return row.attributes.resource_pid;
				}
			},{
			    field:'attributes.resource_state',
				title:'使用状态',
				width:100,
				formatter:function(val,row,index) {
			        if(row.attributes.resource_state == 1) {
			            return "停用";
					} else {
			            return "正常";
					}
				}
			},
				{

				title:'操作',
				field:'action',
				width:80,
				formatter:function(val,row,index) {
				    var s = "";
				    s += '<a href="javascript:void(0)" onclick="editFun(\''+row.id+'\')">编辑</a>';
				    s+='&nbsp;&nbsp;|&nbsp;&nbsp;';
				    s += '<a href="javascript:void(0)" onclick="deleteFun(\''+row.id+'\')">删除</a>';
				    return s;
				}
			}
			]],
			toolbar: '#toolbar',
		})


	});
	
	function editFun(id) {
		if (id != undefined) {
			treeGrid.treegrid('select', id);
		}
		var node = treeGrid.treegrid('getSelected');
		if (node) {
			parent.$.modalDialog({
				title : '编辑',
				width : 500,
				height : 350,
				href : '${ctx}/resource/editPage?query_id=' + node.id,
				buttons : [ {
					text : '编辑',
					handler : function() {
						parent.$.modalDialog.openner_treeGrid = treeGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
						var f = parent.$.modalDialog.handler.find('#resourceAddForm');
						f.submit();
					}
				} ]
			});
		}
	}




	function deleteFun(id) {
		if (id != undefined) {
			treeGrid.treegrid('select', id);
		}
		var node = treeGrid.treegrid('getSelected');
		if (node) {
			parent.$.messager.confirm('询问', '您是否要删除当前资源？删除当前资源会连同子资源一起删除!', function(b) {
				if (b) {
					progressLoad();
					$.post('${pageContext.request.contextPath}/resource/delete', {
						id : node.id
					}, function(result) {
						if (result.success) {
							parent.$.messager.alert('提示', result.msg, 'info');
							treeGrid.treegrid('reload');
							parent.layout_west_tree.tree('reload');
						}
						progressClose();
					}, 'JSON');
				}
			});
		}
	}
	
	function addFun() {
		parent.$.modalDialog({
			title : '添加',
			width : 500,
			height : 350,
			href : '${ctx}/resource/addPage',
			buttons : [ {
				text : '添加',
				handler : function() {
					parent.$.modalDialog.openner_treeGrid = treeGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#resourceAddForm');
					f.submit();
				}
			} ]
		});
	}
	</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center',border:false"  style="overflow: hidden;">
			<table id="treeGrid"></table>
		</div>
	</div>
	
	<div id="toolbar" style="display: none;">
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>

	</div>
</body>
</html>