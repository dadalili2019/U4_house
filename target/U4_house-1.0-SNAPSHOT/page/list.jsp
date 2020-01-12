<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0030)http://localhost:8080/House-2/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD><TITLE>青鸟租房 - 首页</TITLE>
    <META content="text/html; charset=utf-8" http-equiv=Content-Type>
    <LINK rel=stylesheet type=text/css href="../css/style.css">
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
            }, "json");
            //发送异步请求查询区域信息
            $.post("findDistrictAll.do", null, function (data) {
                for (var i = 0; i < data.length; i++) {
                    var option = ("<option value=" + data[i].id + ">" + data[i].name + "</option>");
                    //追加到option拉列表
                    $("#district_id").append(option);
                }
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
                }, "json");
            })
        });
    </script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
    <DIV id=logo><IMG src="../images/logo.gif"></DIV>
</DIV>
<DIV id=navbar class=wrap>
    <DL class="search clearfix">
        <FORM id=sform method=post action="getAllHouseByPage.do">
            <tr>
                <td>房屋名称:</td>
                <td>
                    <input type="text" name="tname" size="7%" value="">
                </td>
            </tr>
            <TR>
                <TD class=field>户　　型：</TD>
                <TD><SELECT class=text name=typeId id="typeId">
                    <option value="">==请选择==</option>
                </SELECT></TD>
            </TR>
            <TR>
                <TD class=field>位置</TD>
                <TD>区：
                    <SELECT class=text id="district_id" name=district_id>
                        <option value="">请选择</option>
                    </SELECT>
                    街：<SELECT class=text
                              name=streetId id="street_id">
                        <option value="">请选择</option>
                    </SELECT></TD>
            </TR>
            <tr>
                <td>价格</td>
                <td>
                    <input type="text" name="startPrice" size="5%">--<input type="text" name="endPrice" size="5%">元/月
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="搜索">
                </td>
            </tr>
        </FORM>
    </DL>
</DIV>
<DIV class="main wrap">
    <TABLE class=house-list>
        <TBODY>
        <c:forEach items="${pageInfo.list}" var="house">
        <TR class=odd>
            <TD class=house-thumb><span><A href="details.htm" target="_blank"><img src="../images/thumb_house.gif"
                                                                                   width="100" height="75"
                                                                                   alt=""></a></span></TD>
            <TD>
                <DL>
                    <DT><A href="details.htm" target="_blank">${house.title}</A></DT>
                    <DD>${house.dname}-${house.sname}<BR>联系方式：${house.contact}</DD>
                </DL>
            </TD>
            <TD class=house-type>${house.tname}</TD>
            <TD class=house-price><SPAN>${house.price}</SPAN>元/月</TD>
        </TR>
        <TR>无租房信息</TR>
        </c:forEach>
        </TBODY>
    </TABLE>
    <DIV class=pager>
        <UL>
            <LI class=current><A href="#">首页</A></LI>
            <LI><A href="#">上一页</A></LI>
            <LI><A href="#">下一页</A></LI>
            <LI><A href="#">末页</A></LI>
        </UL>
        <SPAN
                class=total>1/2页</SPAN></DIV>
</DIV>
<DIV id=footer class=wrap>
    <DL>
        <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
        <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD>
    </DL>
</DIV>
</BODY>
</HTML>
