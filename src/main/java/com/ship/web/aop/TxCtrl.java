package com.ship.web.aop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@RequestMapping("/service")
public class TxCtrl {
	private static final Logger logger = LoggerFactory.getLogger(TxCtrl.class);
	@Autowired TxService txService;
//	@Autowired List<String> txist;
//	@Autowired Map<String,Object> map;
	
	@GetMapping("/search{search}/sitehttps://{site}/")
	public List<String> crawling(@PathVariable String search, @PathVariable String site){
		Map<String, Object> map = new HashMap();
		map.clear();
		//map.put("asdf", "asdg");
		List list = (List<String>) txService.crawling(site,search);
		logger.info("컨트롤러 도착{}",site+search+"\n"+list);
		return list;
	}
}
