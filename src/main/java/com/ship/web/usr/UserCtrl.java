package com.ship.web.usr;

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

import com.ship.web.cmm.IConsumer;
import com.ship.web.cmm.IFunction;
import com.ship.web.cmm.IPredicate;
import com.ship.web.usr.User;
import com.ship.web.utl.Printer;

@RestController
@RequestMapping("/users")

public class UserCtrl {
	private static final Logger logger = LoggerFactory.getLogger(UserCtrl.class);
	@Autowired User user;
	@Autowired Printer printer;
	@Autowired UserMapper userMapper;
	@Autowired Map<String, Object> map;
	
	@PostMapping("/")
	public Map<?,?> join(@RequestBody User user) {
		logger.info("조인 아이디{}",user.getUid() +", "+user.getUpw());
		IConsumer<User> xx = x -> userMapper.insertUser(x);
		xx.accept(user);
		map.clear();
		map.put("uid", "SUCCESS");
		return map;
	}
	
	@GetMapping("/{uid}/exist")
	public Map<?,?> existId(@PathVariable String uid){
		logger.info("중복체크아이디{}",uid);
		IFunction<String, Integer> f = x-> userMapper.existId(uid);
		map.clear();
		map.put("boolean", (f.apply(uid)!=0) ? "True" : "FALSE");
		logger.info("중복체크결과{}",map.get("boolean"));
		//map.put("boolean", "True");
		return map;	
	}
	
	@PostMapping("/{uid}")
	public User login(@PathVariable String uid, @RequestBody User param) {
		logger.info("로그인 아이디{}",param.getUid() +", "+param.getUpw());
		IFunction<User,User> xx = x -> userMapper.selectUserByIdPw(x);
		User user = xx.apply(param);
		logger.info("람다후 아이디{}",user);
		return user;
	}
	
	@GetMapping("/{uid}")
	public User searchUserByIdPw(@PathVariable String uid, @RequestBody User param) {
		logger.info("로그인....? 아이디{}",param.getUid() +", "+param.getUpw());
		IFunction<User,User> xx = x -> userMapper.selectUserByIdPw(x);
		param = xx.apply(param);
		
		return xx.apply(param);
	}
	/*@GetMapping("/{uid}")
	public User xxx(@PathVariable String uid, @RequestBody User param) {
		logger.info("로긴2 아이디{}",param.getUid() +", "+param.getUpw());
		IFunction<User,User> xx = x -> userMapper.selectUserByIdPw(x);
		param = xx.apply(param);
		
		return xx.apply(param);
	}*/
	@PutMapping("/{uid}")
	public Map<?,?> updateUser(@PathVariable String uid, @RequestBody User param) {
		logger.info("AJAX 가 보낸 아이디{}",param.getUid() +", "+param.getUpw());
		//IConsumer<User> xx = x -> userMapper.
		map.clear();
		map.put("uid", "SUCCESS");
		return map;
	}
	@DeleteMapping("/{uid}")
	public Map<?,?> removeUser(@PathVariable String uid, @RequestBody User param) {
		logger.info("AJAX 가 보낸 아이디{}",param.getUid() +", "+param.getUpw());
		//IConsumer<User> xx = x
		map.clear();
		map.put("uid", "SUCCESS");
		return map;
	}
}
