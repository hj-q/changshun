package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TCangkukaoqin;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TCangkukaoqinService extends CoreService<TCangkukaoqin> {
	String readExcelFile(MultipartFile file);

	public void addInfo(TCangkukaoqin tCangkukaoqin);
    //从缓存中获取信息
    TCangkukaoqin getInfo(String workerid);
}
