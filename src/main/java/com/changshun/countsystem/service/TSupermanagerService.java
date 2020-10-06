package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TSupermanager;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TSupermanagerService extends CoreService<TSupermanager> {
	String readExcelFile(MultipartFile file);

}
