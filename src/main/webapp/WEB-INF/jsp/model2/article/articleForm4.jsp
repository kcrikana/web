<%@ page import="java.util.Optional" %>
<!DOCTYPE html>
<html>
<body>
<h3>글 한건 가져오기</h3>
<form action="getArticle" method="post">
    <p><input type="number" name="articleId" placeholder="글 번호" required/></p>
    <p>
        <button type="submit">검색</button>
    </p>
</form>
<p style="color:red;"><%= Optional.ofNullable(request.getParameter("msg"))
        .orElse("")%>
</p>
</body>
</html>