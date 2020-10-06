package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TJiaqi;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TJiaqiService extends CoreService<TJiaqi> {
	String readExcelFile(MultipartFile file);

	public void addInfo(TJiaqi tJiaqi);
    //从缓存中获取信息
    TJiaqi getInfo(String workerid);
}
