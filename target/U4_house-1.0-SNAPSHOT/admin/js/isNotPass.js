$(function () {
    //绑定数据
    $("#td").datagrid({
        url: 'getAllHouseByPageIsNotPass.do',
        pagination: true,  //开启分页
        pageSize: 3,  //初始化页大小
        pageList: [3, 5, 8, 10, 20],  //页大小选项
        toolbar: '#ToolBar', //指定工具栏
        columns: [[
            {checkbox: true, width: 100, align: 'right'},
            {field: 'id', title: 'ID', width: 100, align: 'right'},
            {field: 'title', title: '房屋名称', width: 100, align: 'right'},
            {field: 'description', title: '房屋描述', width: 100, align: 'right'},
            {field: 'price', title: '出租价格', width: 100, align: 'right'},
            {field: 'pubdate', title: '出租时间', width: 100, align: 'right'},
            {
                field: 'opt', title: '操作', width: 50, align: 'right',
                formatter: function (value, row, index) {
                    return "<a href='javascript:getById(" + row.id + ")'>详情</a>";

                }
            }
        ]]
    });
});

//打开房屋详情窗口
function getById() {
    var selObjs = $("#td").datagrid("getSelections");
    //打开详情窗口
    $("#getByIdDialog").dialog("open").dialog('setTitle', "详细信息");
    //获取所属用户id
    var uid = selObjs[0].userId;
    // alert(uid);
    //获取房屋id
    var hid = selObjs[0].id;
    // alert(hid);
    //获取userId
    $.post("getHouseById.do", {"uid": uid, "hid": hid}, function (data) {
        // console.log(data);
        $("#getByIdDialogForm").form('load', data);
    }, "json");
}

