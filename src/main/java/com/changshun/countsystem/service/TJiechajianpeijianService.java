package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TJiechajianpeijian;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TJiechajianpeijianService extends CoreService<TJiechajianpeijian> {
	String readExcelFile(MultipartFile file);

	public void addInfo(TJiechajianpeijian tJiechajianpeijian);
    //从缓存中获取信息
    TJiechajianpeijian getInfo(String workerid);
}
