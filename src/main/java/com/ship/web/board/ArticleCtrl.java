package com.ship.web.board;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ship.web.cmm.IConsumer;
import com.ship.web.cmm.IFunction;
import com.ship.web.cmm.ISupplier;
import com.ship.web.pxy.Proxy;
import com.ship.web.pxy.ProxyMap;

@RestController
@RequestMapping("/articles")
public class ArticleCtrl {
	private static final Logger logger = LoggerFactory.getLogger(ArticleCtrl.class);
	@Autowired Map<String, Object> map;
	@Autowired Article art;
	@Autowired ArticleMapper articleMapper;
	@Autowired List<Article> articleList;
	@Autowired Proxy pxy;
	@Autowired ProxyMap map3;
	
	@PostMapping("/")
	public Map<?,?> write(@RequestBody Article param) {
		logger.info("글쓰기 내용{}", param);
		IConsumer<Article> c = x -> articleMapper.insertArticle(param);
		c.accept(param);
		Supplier<Integer> s = () -> articleMapper.allBoardCount();
		//map3.accept(Arrays.asList("msg", "count");
		
		return map;
	}
	
	@GetMapping("/count")//패스배러블 설정을 해주면 바뀔수 있는 부분이라는 뜻이다.
	public Map<?,?> allBoardcount(){
		ISupplier<Integer> g = () -> articleMapper.allBoardCount();
		String result = String.valueOf(g.get());
		logger.info("글찾기 = {}", result);
		map.clear();
		map.put("count", result);
		return map;
	}

	@GetMapping("/list/{page}__{pageSize}")//패스배러블 설정을 해주면 바뀔수 있는 부분이라는 뜻이다.
	public Map<?,?> selectPage(@PathVariable String page,@PathVariable String pageSize){
		logger.info("페이지네이션 = {}", page + "____"+ pageSize);
		map.clear();
		IFunction<Proxy, List<Article>> f = x -> articleMapper.selectPage(x);
		pxy.setPageNum(pxy.parseInt(page));
		pxy.setPageSize(pxy.parseInt(pageSize));
		pxy.paging();
		pxy.setListArr(f.apply(pxy));
		Map<String,Object> map2 = new HashMap<String, Object>();
		map2.clear();
		map2.put("pxy", pxy);
		//map3.accept(Arrays.asList({"pxy"}),Arrays.asList({}));
		return map2;
	}
	
	@GetMapping("/{uid}/count")//패스배러블 설정을 해주면 바뀔수 있는 부분이라는 뜻이다.
	public Map<?,?> myBoardCount(@PathVariable String uid){
		IFunction<String, Integer> g = x -> articleMapper.countUserArticle(uid);
		int result = g.apply(uid);
		logger.info("글찾기 = {}", result);
		map.clear();
		map.put("count", result);
		return map;
	}
	
	@GetMapping("/{uid}/list")//패스배러블 설정을 해주면 바뀔수 있는 부분이라는 뜻이다.
	public Map<?,?> myBoardList(@PathVariable String uid){
		IFunction<String, Integer> g = x -> articleMapper.countUserArticle(uid);
		int result = g.apply(uid);
		logger.info("글찾기 = {}", result);
		map.clear();
		map.put("count", result);
		return map;
	}
	
	@PutMapping("/{artseq}")
	public Article modify(@PathVariable String artseq, @RequestBody Article param){
		logger.info("글수정{}", param);
		IConsumer<Article> u = x -> articleMapper.update(param);
		u.accept(param);
		return param;
	}
	
	@DeleteMapping("/{artseq}")
	public Article delete(@PathVariable String artseq, @RequestBody Article param){
		logger.info("글삭제 = {}", param);
		IConsumer<Article> d = (x -> articleMapper.delete(param));
		d.accept(param);
		return param;
	}
}
