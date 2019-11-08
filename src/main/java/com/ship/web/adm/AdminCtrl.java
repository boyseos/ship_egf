package com.ship.web.adm;

import java.util.Map;

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

import com.ship.web.cmm.IFunction;
import com.ship.web.usr.UserCtrl;
import com.ship.web.utl.Printer;

@RestController
@RequestMapping("/admins")
public class AdminCtrl {
	private static final Logger logger = LoggerFactory.getLogger(AdminCtrl.class);
	@Autowired Admin admin;
	@Autowired Map<String,Object> map;
	@Autowired AdminMapper adminMapper;
	@Autowired Printer printer;
	
	@PostMapping("/")
	public Map<?,?> register(@RequestBody Admin admin) {
		return null;
	}
	
	@PostMapping("/{eid}")
	public Map<?,?> access(@PathVariable String eid, @RequestBody Admin param){
		logger.info("관리자 로그인 : {}",param);
		//IFunction<Admin, Admin> f = x -> adminMapper.searchAdminByIdPw(param);
		map.clear();
		map.put("eid", "eid");
		map.put("epw", "epw");
		IFunction<Admin, Admin> f = x -> adminMapper.searchAdminByIdPw(param);
		map.put("msg", (f.apply(param) != null) ? "Success" : "Fail");
		return map;
	}
	
	@GetMapping("/list")
	public Map<?,?> eList(){
		logger.info("관리자 목록 : {}","sdf");
		return null;
	}
	
	@PutMapping("/{eid}")
	public Map<?,?> eUpdate(@PathVariable String eid, @RequestBody Admin param){
		logger.info("관리자 수정 : {}",param);
		return null;
	}
	
	@DeleteMapping("/{eid}")
	public Map<?,?> eDelete(@PathVariable String eid, @RequestBody Admin param){
		logger.info("관리자 삭제 : {}",param);
		return null;
	}
}
