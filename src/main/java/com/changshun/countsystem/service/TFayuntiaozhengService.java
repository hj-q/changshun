package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TFayuntiaozheng;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TFayuntiaozhengService extends CoreService<TFayuntiaozheng> {
	String readExcelFile(MultipartFile file);

}
