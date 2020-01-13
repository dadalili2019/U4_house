<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0049)http://localhost:8080/HouseRent/page/register.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD><TITLE>青鸟租房 - 用户修改</TITLE>
    <META content="text/html; charset=utf-8" http-equiv=Content-Type>
    <LINK
            rel=stylesheet type=text/css href="../css/style.css">
    <META name=GENERATOR content="MSHTML 8.00.7601.17514">
    <script src="../scripts/jquery-1.8.3.js"></script>
    <script>
        //验证用户名是否正确
        $(function () {
            $("#inputName").blur(function () {
                //获取填写的name属性的值
                var username = $("#inputName").val();
                if (username != null) {
                    $.post("checkUser.do", {"username": username}, function (data) {
                        console.log(data);
                        if (data) {
                            // alert(222);
                            $("#inputPas").blur(function () {
                                //获取填写的旧密码的值
                                var oldPassword = $("#inputPas").val();
                                //验证旧密码是否正确
                                $.post("checkOldPassword.do", {
                                    "oldPassword": oldPassword,
                                    "username": username
                                }, function (data) {
                                    console.log(data);
                                    if (data.result == -1) {
                                        $("#notice1").html("密码输入错误").css("color", "red");
                                        $("#inputPas").focus();
                                    }
                                    //两次填的密码验证
                                    function beforeSubmit(form) {
                                        if (form.password.value = form.repassword.value) {
                                            alert('新密码与旧密码一致');
                                            form.password.focus();
                                            return false;
                                        }
                                    }
                                }, "json");
                            })
                        } else {
                            $("#notice").html("用户名不存在").css("color", "red");
                            $("#inputName").focus();
                        }
                    }, "json");
                }
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
            <DT>用户修改</DT>
            <DD class=past>修改个人信息</DD>
        </DL>
        <DIV class=box>
            <FORM action="modifyUser.do" method="post" onsubmit="return beforeSubmit(this);">
                <DIV class=infos>
                    <TABLE class=field>
                        <TBODY>
                        <TR>
                            <TD class=field>用 户 名：</TD>
                            <TD><INPUT class=text type=text name=name id="inputName">
                            </TD>
                        </TR>
                        <span id="notice"></span>
                        <TR>
                            <TD class=field>旧密码：</TD>
                            <TD><INPUT class=text type=password name=repassword id="inputPas"></TD>
                        </TR>
                        <span id="notice1"></span>
                        <TR>
                            <TD class=field>新密码：</TD>
                            <TD><INPUT class=text type=password name=password id="inputPas2"></TD>
                        </TR>
                        <TR>
                            <TD class=field>电　话：</TD>
                            <TD><INPUT class=text type=text name=telephone></TD>
                        </TR>
                        </TBODY>
                    </TABLE>
                    <DIV class=buttons>
                        <INPUT value=立即修改 type=submit>
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
</HTML>
