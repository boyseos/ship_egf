package com.ship.web.adm;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {

	public Admin searchAdminByIdPw(Admin param);
	public Admin selectAdmin(Map<?, ?> param);
	
}
