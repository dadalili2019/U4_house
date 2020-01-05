<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0050)http://localhost:8080/HouseRent/manage!list.action -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
    <TITLE>青鸟租房 - 用户管理</TITLE>
    <META content="text/html; charset=utf-8" http-equiv=Content-Type>
    <LINK
            rel=stylesheet type=text/css href="../css/style.css">
    <META name=GENERATOR>
    <script src="../scripts/jquery-1.8.3.js"></script>
    <script>
        $(function () {
            $("#out").click(function () {
                if (confirm("确定退出吗？")) {
                    location.href = "UserGoOut.do?userName=${user.name}";
                }
            });
        });
        //根据id删除
        function isDel(data) {
           if (confirm("确认删除吗？")){
               location.href="DelHouseById.do?id="+data+"&isDel=1";
           }
        }

    </script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
    <DIV id=logo><IMG src="../images/logo.gif"></DIV>

    <DIV class=search>
        <h2>欢迎你${user.name}</h2>
        <LABEL class="ui-green searchs">
            <a href="fabu.jsp" title="">发布房屋信息</a></LABEL>
        <LABEL class=ui-green><INPUT value="退       出" type=button name=search id="out"></LABEL>
    </DIV>
</DIV>
<DIV class="main wrap">
    <DIV id=houseArea>
        <TABLE class=house-list>
            <TBODY>
            <c:forEach items="${pageInfo.list}" var="house">
                <TR>
                    <TD class=house-thumb><SPAN><A href="details.htm" target="_blank"><img
                            src="http://localhost:80/${house.path}"
                            width="100" height="75"
                            alt=""></A></SPAN></TD>
                    <TD>
                        <DL>
                            <DT><A href="details.htm" target="_blank">${house.title}</A></DT>
                            <DD>${house.dname}${house.sname},${house.floorage}平米<BR>联系方式：${house.contact}</DD>
                        </DL>
                    </TD>
                    <TD>
                        <c:if test="${house.ispass==0}">未审核</c:if>
                        <c:if test="${house.ispass==1}">已审核</c:if>
                    </TD>
                    <TD class=house-type><LABEL class=ui-green><INPUT
                            onclick="location.href='findById.do?id=${house.id}'" value="修    改" type=button
                            name=search></LABEL></TD>
                    <TD class=house-price><LABEL class=ui-green><INPUT value="删    除" type=button name=search onclick="isDel(${house.id})"></LABEL>
                    </TD>
                </TR>
            </c:forEach>
            </TBODY>
        </TABLE>
    </DIV>
    <DIV class=pager>
        <c:forEach begin="1" end="${pageInfo.pages}" var="p">
            <UL>
                <LI class=current><A id=widget_338868862
                                     href="http://localhost:8080/HouseRent/manage!ajaxList.action?number=1"
                                     parseContent="true" showError="true" targets="houseArea"
                                     ajaxAfterValidation="false" validate="false"
                                     dojoType="struts:BindAnchor">${p}</A>
                </LI>
            </UL>
        </c:forEach>
        <SPAN class=total>${pageInfo.pageNum}/${pageInfo.pages}页</SPAN></DIV>
</DIV>
<DIV id=footer class=wrap>
    <DL>
        <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
        <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD>
    </DL>
</DIV>
</BODY>
</HTML>
