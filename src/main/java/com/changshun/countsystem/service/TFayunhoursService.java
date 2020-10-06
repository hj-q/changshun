package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TFayunhours;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TFayunhoursService extends CoreService<TFayunhours> {
	String readExcelFile(MultipartFile file);

}
