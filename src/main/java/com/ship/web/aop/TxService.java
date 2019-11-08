package com.ship.web.aop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ship.web.pxy.Proxy;

@Transactional
@Service
public class TxService {
	@Autowired TxMapper mapper;
	@Autowired Map<String, Object> map;
	@Autowired Proxy pxy;
	//@Autowired List<?> txServiceList;
	
	public List<?> crawling(String paramMap, String search){
		List<String> txServiceList = new ArrayList<String>();
		txServiceList.clear();
		System.out.println("service");
		txServiceList = (List<String>) pxy.crawl(paramMap,search);
		return txServiceList;
	}
}
