<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<form>
    <div class="register_con">
        <a id="x_btn">X</a>
        <div class="regi_con_id">
            <span>ID</span><br><input type="text" name="id" id="regi_id" placeholder="8~20 영문or숫자">
        </div>
        <div class="regi_con_nickname">
            <span>NICKNAME</span><br><input type="text" name="nickname" id="regi_nickname" placeholder="8~20 영문or숫자">
        </div>
        <div class="regi_con_pw">
            <span>PW</span><br><input type="password" name="pw" id="regi_pw" placeholder="8~20 영문,숫자,특수문자 조합" autocomplete="off">
        </div>
        <div class="regi_con_button">
            <button type="button" id="create_user">REGISTER</button>
        </div>
    </div>
</form>
</body>
</html>