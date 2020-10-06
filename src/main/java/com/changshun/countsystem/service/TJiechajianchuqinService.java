package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TJiechajianchuqin;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TJiechajianchuqinService extends CoreService<TJiechajianchuqin> {
	String readExcelFile(MultipartFile file);

	public void addInfo(TJiechajianchuqin tJiechajianchuqin);
    //从缓存中获取信息
    TJiechajianchuqin getInfo(String workerid);
}
