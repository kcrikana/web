<%@ page import="kr.mjc.minbeom.web.dao.Article" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<body>
<h3>글 목록</h3>
<p><a href="./articleForm">글 추가</a> <a href="./articleForm2">글 수정</a>
    <a href="./articleForm3">글 삭제</a> <a href="./articleForm4">글 하나 가져오기</a></p>
<%
    List<Article> articleList = (List<Article>) request.getAttribute("articleList");
    for (Article article : articleList) {%>
<p><%= article %>
</p>
<%
    }
%>
</body>
</html>