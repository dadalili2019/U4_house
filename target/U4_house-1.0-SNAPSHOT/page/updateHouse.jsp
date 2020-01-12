<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0044)http://localhost:8080/HouseRent/page/add.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD><TITLE>青鸟租房 -发布房屋信息</TITLE>
    <META content="text/html; charset=utf-8" http-equiv=Content-Type>
    <LINK
            rel=stylesheet type=text/css href="../css/style.css">
    <META name=GENERATOR content="MSHTML 8.00.7601.17514">
    <script src="../scripts/jquery-1.8.3.js"></script>
    <script>
        $(function () {
            //异步加载户型
            $.post("findTypeAll.do", null, function (data) {
                for (var i = 0; i < data.length; i++) {
                    var option = ("<option value=" + data[i].id + ">" + data[i].name + "</option>");
                    //追加到option拉列表
                    $("#typeId").append(option);
                }
                //加载类型选中项
                $("#typeId").val(${house.typeId})
            }, "json");
            //发送异步请求查询区域信息
            $.post("findDistrictAll.do", null, function (data) {
                for (var i = 0; i < data.length; i++) {
                    var option = ("<option value=" + data[i].id + ">" + data[i].name + "</option>");
                    //追加到option拉列表
                    $("#district_id").append(option);
                }
                //加载回显区域选中项
                $("#district_id").val(${house.did});
            }, "json");

            //二级级联查询街道信息
            $("#district_id").change(function () {
                var did = this.value;
                //发送异步请求查询街道
                //清空原有选项
                $("#street_id >option :gt(0)").remove();
                $.post("findStreetById.do", {"did": did}, function (data) {
                    for (var i = 0; i < data.length; i++) {
                        var option = ("<option value=" + data[i].id + ">" + data[i].name + "</option>");
                        //将option添加到下拉列表
                        $("#street_id").append(option);
                    }
                    //加载二级级联街道回显信息
                    $("#street_id").val(${house.streetId})
                }, "json");
            })
        });
    </script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
    <DIV id=logo><IMG src="../images/logo.gif"></DIV>
</DIV>
<DIV id=regLogin class=wrap>
    <DIV class=dialog>
        <DL class=clearfix>
            <DT>房屋信息修改</DT>
            <DD class=past>修改房屋信息</DD>
        </DL>
        <input type="hidden" name="oldPicPath" value="${house.path}">

        <DIV class=box>
            <FORM id=add_action method=post action="updateHouseById.do?id=${house.id}" enctype="multipart/form-data"
                  onsubmit="return beforeSubmit(this);">
                <DIV class=infos>
                    <TABLE class=field>
                        <TBODY>
                        <TR>
                            <TD class=field>标　　题：</TD>
                            <TD><INPUT id=add_action_title class=text type=text name=title value="${house.title}"></TD>
                        </TR>
                        <TR>
                            <TD class=field>户　　型：</TD>
                            <TD><SELECT class=text name=typeId id="typeId">
                                <option value="">==请选择==</option>
                            </SELECT></TD>
                        </TR>
                        <TR>
                            <TD class=field>面　　积：</TD>
                            <TD><INPUT id=add_action_floorage class=text type=text
                                       name=floorage value="${house.floorage}"></TD>
                        </TR>
                        <TR>
                            <TD class=field>价　　格：</TD>
                            <TD><INPUT id=add_action_price class=text type=text name=price value="${house.price}"></TD>
                        </TR>
                        <TR>
                            <TD class=field>发布日期：</TD>
                            <TD><INPUT class=text type=date name=pubdate
                                       value="<fmt:formatDate value="${house.pubdate}" pattern="yyyy-MM-dd"></fmt:formatDate>">
                            </TD>
                        </TR>
                        <TR>
                            <TD class=field>位　　置：</TD>
                            <TD>区：
                                <SELECT class=text id="district_id" name=did>
                                    <option value="">请选择</option>
                                </SELECT>
                                街：<SELECT class=text
                                          name=streetId id="street_id">
                                    <option value="">请选择</option>
                                </SELECT></TD>
                        </TR>
                        <TR>
                            <TD class=field>联系方式：</TD>
                            <TD><INPUT id=add_action_contact class=text type=text name=contact value="${house.contact}">
                            </TD>
                        </TR>
                        <TR>
                            <TD class=field>详细信息：</TD>
                            <TD><TEXTAREA name=description>${house.description}</TEXTAREA></TD>
                        </TR>
                        <TR>
                            <TD class=field>图片：</TD>
                            <TD><img src="http://localhost:80/${house.path}" width="100" height="75"><input type="file" name="pfile" ></TD>
                        </TR>
                        </TBODY>
                    </TABLE>
                    <DIV class=buttons>
                        <INPUT value=修改 type=submit>
                    </DIV>
                </DIV>
            </FORM>
        </DIV>
    </DIV>
</DIV>
<DIV id=footer class=wrap>
    <DL>
        <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
        <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD>
    </DL>
</DIV>
</BODY>
<script type="text/javascript">
    function beforeSubmit(form) {
        if (form.title.value == '') {
            alert('标题不能为空');
            form.title.focus();
            return false;
        }
        if (form.typeId.value == '') {
            alert('户型不能为空');
            form.typeId.focus();
            return false;
        }
        if (form.floorage.value == '') {
            alert('面积不能为空');
            form.floorage.focus();
            return false;
        }
        if (form.price.value == '') {
            alert('价格不能为空');
            form.price.focus();
            return false;
        }
        if (form.pubdate.value == '') {
            alert('发布日期不能为空');
            form.pubdate.focus();
            return false;
        }
        if (form.district_id.value == '') {
            alert('区域不能为空');
            form.district_id.focus();
            return false;
        }
        if (form.contact.value == '') {
            alert('联系方式不能为空');
            form.contact.focus();
            return false;
        }
        if (form.description.value == '') {
            alert('详细信息不能为空');
            form.description.focus();
            return false;
        }
    }
</script>
</HTML>
