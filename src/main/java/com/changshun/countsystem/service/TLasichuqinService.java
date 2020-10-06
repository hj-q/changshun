package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TLasichuqin;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TLasichuqinService extends CoreService<TLasichuqin> {
	String readExcelFile(MultipartFile file);

	public void addInfo(TLasichuqin tLasichuqin);
    //从缓存中获取信息
    TLasichuqin getInfo(String workerid);
}
