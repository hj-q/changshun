package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TFayunxiazhuang;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TFayunxiazhuangService extends CoreService<TFayunxiazhuang> {
	String readExcelFile(MultipartFile file);

}
