package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TXianshuchuqin;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TXianshuchuqinService extends CoreService<TXianshuchuqin> {
	String readExcelFile(MultipartFile file);

	public void addInfo(TXianshuchuqin tXianshuchuqin);
    //从缓存中获取信息
    TXianshuchuqin getInfo(String workerid);
}
