package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TJihuachuqin;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TJihuachuqinService extends CoreService<TJihuachuqin> {
	String readExcelFile(MultipartFile file);

	public void addInfo(TJihuachuqin tJihuachuqin);
    //从缓存中获取信息
    TJihuachuqin getInfo(String workerid);
}
