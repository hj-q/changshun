package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TOffice;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TOfficeService extends CoreService<TOffice> {
	String readExcelFile(MultipartFile file);

	public void addInfo(TOffice tOffice);
    //从缓存中获取信息
    TOffice getInfo(String workerid);
}
