package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TFayungongzimingxi;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TFayungongzimingxiService extends CoreService<TFayungongzimingxi> {
	String readExcelFile(MultipartFile file);

}
