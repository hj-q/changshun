package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.THouqin;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface THouqinService extends CoreService<THouqin> {
	String readExcelFile(MultipartFile file);

	public void addInfo(THouqin tHouqin);
    //从缓存中获取信息
    THouqin getInfo(String workerid);
}
