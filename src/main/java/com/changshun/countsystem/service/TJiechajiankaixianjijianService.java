package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TJiechajiankaixianjijian;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TJiechajiankaixianjijianService extends CoreService<TJiechajiankaixianjijian> {
	String readExcelFile(MultipartFile file);

	public void addInfo(TJiechajiankaixianjijian tJiechajiankaixianjijian);
    //从缓存中获取信息
    TJiechajiankaixianjijian getInfo(String workerid);
}
