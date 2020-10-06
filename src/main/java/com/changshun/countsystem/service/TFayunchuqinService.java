package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TFayunchuqin;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TFayunchuqinService extends CoreService<TFayunchuqin> {
	String readExcelFile(MultipartFile file);

	public void addInfo(TFayunchuqin tFayunchuqin);
    //从缓存中获取信息
    TFayunchuqin getInfo(String workerid);
}
