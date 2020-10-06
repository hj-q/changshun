package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.THouqinwentoufu;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface THouqinwentoufuService extends CoreService<THouqinwentoufu> {
	String readExcelFile(MultipartFile file);

	public void addInfo(THouqinwentoufu tHouqinwentoufu);
    //从缓存中获取信息
    THouqinwentoufu getInfo(String workerid);
}
