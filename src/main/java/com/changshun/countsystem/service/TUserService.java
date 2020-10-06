package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TUser;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TUserService extends CoreService<TUser> {
	String readExcelFile(MultipartFile file);

}
