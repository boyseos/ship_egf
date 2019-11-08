package com.ship.web.board;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ship.web.pxy.Proxy;

@Repository
public interface ArticleMapper {
	public void insertArticle(Article param);
	public int countUserArticle(String param);
	public Article userArticleList(Article param);
	public int allBoardCount();
	public List<Article> selectAll();
	public int update(Article param);
	//public void delete(Article param);
	public int delete(Article param);
	//public List<Article> selectPage(Map<String, String> x);
	public List<Article> selectPage(Proxy x);
}
