package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TDianlankoufa;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TDianlankoufaService extends CoreService<TDianlankoufa> {
	String readExcelFile(MultipartFile file);

}
