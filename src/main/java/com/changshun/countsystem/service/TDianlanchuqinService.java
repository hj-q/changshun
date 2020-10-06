package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TDianlanchuqin;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TDianlanchuqinService extends CoreService<TDianlanchuqin> {
	String readExcelFile(MultipartFile file);

	public void addInfo(TDianlanchuqin tDianlanchuqin);
    //从缓存中获取信息
    TDianlanchuqin getInfo(String workerid);
}
