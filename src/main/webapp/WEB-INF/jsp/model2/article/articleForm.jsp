<%@ page import="java.util.Optional" %>
<!DOCTYPE html>
<html>
<body>
<h3>글 등록</h3>
<form action="addArticle" method="post">
    <p><input type="text" name="title" placeholder="제목" required autofocus/></p>
    <p><input type="text" name="context" placeholder="내용" required/></p>
    <p><input type="" name="name" placeholder="유저" required/></p>
    <p><input type="text" name="name" placeholder="이름" required/></p>
    <p>
        <button type="submit">저장</button>
    </p>
</form>
<p style="color:red;"><%= Optional.ofNullable(request.getParameter("msg"))
        .orElse("")%>
</p>
</body>
</html>