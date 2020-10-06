package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TCompanys;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TCompanysService extends CoreService<TCompanys> {
	String readExcelFile(MultipartFile file);

}
