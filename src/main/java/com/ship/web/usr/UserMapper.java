package com.ship.web.usr;

import java.util.List;

import com.ship.web.board.Article;
import com.ship.web.usr.User;

import org.springframework.stereotype.Repository;
@Repository
public interface UserMapper {
	public void insertUser(User user);
	public User selectUserByIdPw(User user);
	public List<User> findByDeptTable();
	public int countUser();
	public int existId(String uid);
	public void insertBoard(Article param);
	public Article searchBoard(Article param);
}
