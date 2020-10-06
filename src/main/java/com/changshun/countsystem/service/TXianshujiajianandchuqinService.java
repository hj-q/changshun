package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TXianshujiajianandchuqin;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TXianshujiajianandchuqinService extends CoreService<TXianshujiajianandchuqin> {
	String readExcelFile(MultipartFile file);

	public void addInfo(TXianshujiajianandchuqin tXianshujiajianandchuqin);
    //从缓存中获取信息
    TXianshujiajianandchuqin getInfo(String workerid);
}
