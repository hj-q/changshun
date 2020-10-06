package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TJiechajianbanzuzhang;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TJiechajianbanzuzhangService extends CoreService<TJiechajianbanzuzhang> {
	String readExcelFile(MultipartFile file);

	public void addInfo(TJiechajianbanzuzhang tJiechajianbanzuzhang);
    //从缓存中获取信息
    TJiechajianbanzuzhang getInfo(String workerid);
}
