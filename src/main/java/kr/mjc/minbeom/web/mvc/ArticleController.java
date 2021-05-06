package kr.mjc.minbeom.web.mvc;

import kr.mjc.minbeom.web.dao.Article;
import kr.mjc.minbeom.web.dao.ArticleDao;

import kr.mjc.minbeom.web.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class ArticleController {

    private final ArticleDao articleDao;

    @Autowired
    public ArticleController(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    /**
     * 글 목록 화면
     */
    public void articleList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("articleList", articleDao.listArticles(0, 100));

        request.getRequestDispatcher("/WEB-INF/jsp/model2/article/articleList.jsp")
                .forward(request, response);
    }

    /**
     * 글 등록 화면
     */
    public void articleForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/model2/article/articleForm.jsp")
                .forward(request, response);
    }

    /**
     * 글 정보 화면
     */
    public void articleInfo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/model2/article/articleInfo.jsp")
                .forward(request, response);
    }


    /**
     * 글 등록 액션
     */
    public void addArticle(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Article article = new Article();
        article.setTitle(request.getParameter("title"));
        article.setContent(request.getParameter("context"));
        article.setUserId(Integer.parseInt(request.getParameter("userId")));
        article.setName(request.getParameter("name"));


        try {
            articleDao.addArticle(article);
            HttpSession session = request.getSession();
            session.setAttribute("ARTICLE", article);
            response.sendRedirect(request.getContextPath() + "/mvc/article/articleInfo");
        } catch (DuplicateKeyException e) {
            response.sendRedirect(request.getContextPath() +
                    "/mvc/article/articleForm?msg=error userId or name");
        }
    }

    /**
     * 글 수정 화면
     */

    public void articleForm2(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/model2/article/articleForm2.jsp")
                .forward(request, response);
    }

    /**
     * 글 수정 액션
     */
    public void updateArticle(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Article article = new Article();
        article.setTitle(request.getParameter("title"));
        article.setContent(request.getParameter("context"));
        article.setUserId(Integer.parseInt(request.getParameter("userId")));
        article.setArticleId(Integer.parseInt(request.getParameter("articleId")));


        try {
            articleDao.updateArticle(article);
            HttpSession session = request.getSession();
            session.setAttribute("ARTICLE", article);
            response.sendRedirect(request.getContextPath() + "/mvc/article/articleInfo");
        } catch (EmptyResultDataAccessException e) {
            response.sendRedirect(request.getContextPath() +
                    "/mvc/article/articleForm2?msg=error userId or articleId");
        }


    }

    /**
     * 글 삭제 화면
     */

    public void articleForm3(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/model2/article/articleForm3.jsp")
                .forward(request, response);
    }

    /**
     * 글 삭제액션
     */
    public void deleteArticle(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int articleId = Integer.parseInt(request.getParameter("articleId"));
        int userId = Integer.parseInt(request.getParameter("userId"));

        try {

            HttpSession session = request.getSession();
            session.setAttribute("ARTICLE", String.valueOf(articleDao.deleteArticle(articleId,userId)));
            response.sendRedirect(request.getContextPath() + "/mvc/article/articleList");
        } catch (EmptyResultDataAccessException e) {
            response.sendRedirect(request.getContextPath() +
                    "/mvc/article/articleForm3?msg=error userId or articleId");
        }
    }

    /**
     * 글 하나 검색하기 화면
     */

    public void articleForm4(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/model2/article/articleForm4.jsp")
                .forward(request, response);
    }
    
    /**
     * 글 하나 검색하기 액션
     */
    public void getArticle(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int articleId = Integer.parseInt(request.getParameter("articleId"));

        try {

            HttpSession session = request.getSession();
            session.setAttribute("ARTICLE", String.valueOf(articleDao.getArticle(articleId)));
            response.sendRedirect(request.getContextPath() + "/mvc/article/articleInfo");
        } catch (EmptyResultDataAccessException e) {
            response.sendRedirect(request.getContextPath() +
                    "/mvc/article/articleForm4?msg=error articleId");
        }
    }
}
