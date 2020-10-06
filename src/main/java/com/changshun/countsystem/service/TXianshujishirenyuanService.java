package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TXianshujishirenyuan;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TXianshujishirenyuanService extends CoreService<TXianshujishirenyuan> {
	String readExcelFile(MultipartFile file);

	public void addInfo(TXianshujishirenyuan tXianshujishirenyuan);
    //从缓存中获取信息
    TXianshujishirenyuan getInfo(String workerid);
}
