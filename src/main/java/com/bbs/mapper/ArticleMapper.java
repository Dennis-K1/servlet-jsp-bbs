package com.bbs.mapper;

import com.bbs.domain.Article;
import com.bbs.domain.PageParameters;
import java.util.List;

public interface ArticleMapper {

	public int getNumberOfArticlesBySearch(PageParameters pageParameters);
	public List<Article> getArticleList(PageParameters pageParameters);
	public int deleteArticleById(Long id);
	public int updateDateDeleted(Long id);
	public int recoverArticleById(Long id);
	public int recoverDateDeleted(Long id);
	public int inputArticle(Article article);
	public Article getArticleById(Long id);
	public int increaseArticleViewsById(Long id);
	public int updateFileAttachedByArticleId(Long id);
}
