package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.THouqinzhuxundamoney;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface THouqinzhuxundamoneyService extends CoreService<THouqinzhuxundamoney> {
	String readExcelFile(MultipartFile file);

}
