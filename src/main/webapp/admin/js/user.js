$(function () {
    //绑定数据
    $("#td").datagrid({
        url: 'getUserData.do',
        pagination: true,  //开启分页
        pageSize: 3,  //初始化页大小
        pageList: [3, 5, 8, 10, 20],  //页大小选项
        toolbar: '#ToolBar', //指定工具栏
        columns: [[
            {checkbox: true, width: 100, align: 'right'},
            {field: 'id', title: '编号', width: 100, align: 'right'},
            {field: 'name', title: '用户名', width: 100, align: 'right'},
            {field: 'telephone', title: '电话', width: 100, align: 'right'},
            {field: 'isadmin', title: '是否是管理员', width: 100, align: 'right'},
            {field: 'age', title: '年龄', width: 100, align: 'right'},
            {
                field: 'opt', title: '操作', width: 100, align: 'right',
                formatter: function (value, row, index) {
                    return "<a href='javascript:goUpdate(" + row.id + ");'>修改</a>||<a href='javascript:DeleteByFruitName(" + row.id + ")'>删除</a>";

                }
            }
        ]]
    });
});

//动态查询用户信息
function searchUser() {
    var inputName = $("#inputName").val();
    var inputTel = $("#inputTel").val();
    //查询条件设置
    $("#td").datagrid("load", {
        //设置查询条件
        name: inputName,
        tel: inputTel
    });
}


//打开添加窗口
function goAdd() {
    //打开对话框
    //$("#AddDialog").dialog("open");
    $("#AddDialog").dialog("open").dialog('setTitle', "添加区域");
}

//关闭窗口
function CloseDialog() {
    $("#AddDialog").dialog("close");
}

//保存添加的数据     异步添加
function SaveDialog() {
    //alert("多要保存信息，告诉我接口在哪，我去找他");
    //实现异步技术实现添加,借助ajax技术，
    //方法一:使用jquery发送异步请求
    //$.post("地址",参数，回调函数,"json")
    //将表单序列化参数数据
    /*      var param=$("#addDialogForm").serialize();
          //console.log(param);
           $.post("addDistrict",param,function(data){
               if(data.result>0){
                   //成功关闭窗口
                   $("#AddDialog").dialog("close");
               }else{
                   //alert("sss");
                   $.messager.alert('友情提示','添加失败，请联系管理员:13260601227!','info');

               }
           },"json");*/
    //借助easyui异步提交表单
    $('#addDialogForm').form('submit', {
        url: "addDistrict.do",
        success: function (data) {  //{"result":1}
            var obj = $.parseJSON(data);   //将json字符串转化为json对象
            if (obj.result > 0) {
                //成功关闭窗口
                $("#AddDialog").dialog("close");
            } else {
                //alert("sss");
                $.messager.alert('友情提示', '添加失败，请联系管理员', 'info');
            }
        }
    });
}

//去打开修改的窗口
function goUpdate(id) {
    //1.获取datagrid的选中行
    var selObjs = $("#td").datagrid("getSelections");
    // console.log(selObjs);
    //2.验证是否选中一行
    if (selObjs.length == 1) {
        $("#upDialog").dialog("open").dialog('setTitle', "编辑区域");
        var id = selObjs[0].id;
        $.post("findById.do", {"id": id}, function (data) {
            // console.log(data)
            $("#upDialogForm").form('load', data);
        }, "json");

    } else {
        $.messager.alert('友情提示', '真他妈笨，选一行都不会', 'info');
    }
}

//修改区域信息
function UpdateDialog() {
    $('#upDialogForm').form('submit', {
        url: "updateDistrict.do",
        success: function (data) {  //{"result":1}
            var obj = $.parseJSON(data);   //将json字符串转化为json对象
            if (obj.result > 0) {
                //成功关闭窗口
                $("#upDialog").dialog("close");
            } else {
                //alert("sss");
                $.messager.alert('友情提示', '修改失败，请联系管理员', 'info');
            }
        }
    });
}

//删除
function DeleteByFruitName() {
    //1.获取datagrid的选中行
    var selObjs = $("#td").datagrid("getSelections");
    //2.验证是否选中一行
    if (selObjs.length == 1) {
        if (confirm("是否确认删除")) {
            // console.log(selObjs);// {id: 1000, name: "丰台1"}
            var id = selObjs[0].id;
            $.post("delDistrictById.do", {"id": id}, function (data) {
                var obj = $.parseJSON(data);   //将json字符串转化为json对象
                if (obj.result > 0) {
                    $("#td").datagrid('reload');
                } else {
                    $.messager.alert('友情提示', '删除失败，请联系管理员', 'info');
                }
            }, "json")
        } else {
            $.messager.alert('友情提示', '真他妈笨，选一行都不会', 'info');

        }
    }
}

//批量删除
function deleteMoreDistrict() {
    //获取当前选中行
    var selObjs = $("#td").datagrid("getSelections");
    // console.log(selObjs);
    //判断是否有选中项
    if (selObjs.length > 0) {
        //友情提示框
        if (confirm("是否确认删除所选项")) {
            //发送异步调用接口实现批量删除
            //获取所选id,进行拼接
            var str = "";
            for (i = 0; i < selObjs.length; i++) {
                str = str + selObjs[i].id + ",";
            }
            str = str.substring(0, str.length - 1);
            //发送异步请求
            $.post("deleteMoreDistrict.do", {"ids": str}, function (data) {
                if (data.result > 0) {
                    $("#td").datagrid('reload');//刷新datagrid
                } else {
                    $.messager.alert('友情提示', '批量删除失败', 'info');
                }
            }, "json");
        } else {
            $.messager.alert('友情提示', '不删还特码瞎点', 'info');
        }
    } else {
        $.messager.alert('友情提示', '选择至少一行数据都不会？', 'info');
    }
}

