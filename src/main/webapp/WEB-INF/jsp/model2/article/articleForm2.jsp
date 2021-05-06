<%@ page import="java.util.Optional" %>
<!DOCTYPE html>
<html>
<body>
<h3>글 수정</h3>
<form action="updateArticle" method="post">
    <p><input type="text" name="title" placeholder="제목" required autofocus/></p>
    <p><input type="text" name="context" placeholder="내용" required/></p>
    <p><input type="number" name="userId" placeholder="유저번호" required/></p>
    <p><input type="number" name="articleId" placeholder="글 번호" required/></p>
    <p>
        <button type="submit">수정</button>
    </p>
</form>
<p style="color:red;"><%= Optional.ofNullable(request.getParameter("msg"))
        .orElse("")%>
</p>
</body>
</html>