package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TJiechajianjishi;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TJiechajianjishiService extends CoreService<TJiechajianjishi> {
	String readExcelFile(MultipartFile file);

	public void addInfo(TJiechajianjishi tJiechajianjishi);
    //从缓存中获取信息
    TJiechajianjishi getInfo(String workerid);
}
