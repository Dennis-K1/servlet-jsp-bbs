package com.bbs.mapper;

import com.bbs.domain.Article;
import com.bbs.domain.PageParameters;
import java.util.List;

public interface ArticleMapper {

	int getNumberOfArticlesBySearch(PageParameters pageParameters);
	List<Article> getArticleList(PageParameters pageParameters);
	int deleteArticleById(Long id);
	int updateDateDeleted(Long id);
	int recoverArticleById(Long id);
	int recoverDateDeleted(Long id);
	int inputArticle(Article article);
	Article getArticleById(Long id);
	int increaseArticleViewsById(Long id);
	int updateFileAttachedByArticleId(Long id);

	int editArticle(Article article);
}
