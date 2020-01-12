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
</HEAD>
<BODY>

<DIV id=header class=wrap>
    <DIV id=logo><IMG src="../images/logo.gif"></DIV>
    <DIV class=search>
    </DIV>
</DIV>
<DIV id=position class=wrap>当前位置：<a href="showHouse.do">青鸟租房网</a> - 浏览房源</DIV>
<DIV id=view class="main wrap">
    <DIV class=intro>
        <DIV class=lefter>
            <H1>${house.title}</H1>
            <DIV class=subinfo><fmt:formatDate value="${house.pubdate}" pattern="yyyy-MM-dd"></fmt:formatDate> </DIV>
            <DIV class=houseinfo>
                <P>户　　型：<SPAN>${house.tname}</SPAN></P>
                <P>面　　积：<SPAN>${house.floorage}<SUP>2</SUP></SPAN></P>
                <P>位　　置：<SPAN>${house.dname}-${house.sname}</SPAN></P>
                <P>联系方式：<SPAN>${house.utel}</SPAN></P>
            </DIV>
            <div align="right">
                <img border="1"
                            src="http://localhost:80/${house.path}"
                            width="100" height="75"
                            alt="">
            </div>
        </DIV>
        <DIV class=side>
            <P>北京青鸟房地产经纪公司</P>
            <P>资质证书：有</P>
            <P>内部编号:1000</P>
            <P>联 系 人：郭书倡</P>
            <P>联系电话：152-7192-2761<SPAN></SPAN></P>
        </DIV>
        <DIV class=clear></DIV>
        <DIV class=introduction>
            <H2><SPAN><STRONG>房源详细信息</STRONG></SPAN></H2>
            <DIV class=content>
                <P>${house.description}</P></DIV>
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
</HTML>
