package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.THouqincanjimoney;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface THouqincanjimoneyService extends CoreService<THouqincanjimoney> {
	String readExcelFile(MultipartFile file);

}
