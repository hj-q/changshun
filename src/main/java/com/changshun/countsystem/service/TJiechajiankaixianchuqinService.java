package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TJiechajiankaixianchuqin;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TJiechajiankaixianchuqinService extends CoreService<TJiechajiankaixianchuqin> {
	String readExcelFile(MultipartFile file);

	public void addInfo(TJiechajiankaixianchuqin tJiechajiankaixianchuqin);
    //从缓存中获取信息
    TJiechajiankaixianchuqin getInfo(String workerid);
}
