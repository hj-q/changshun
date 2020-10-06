package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TIdentity;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TIdentityService extends CoreService<TIdentity> {
	String readExcelFile(MultipartFile file);

}
