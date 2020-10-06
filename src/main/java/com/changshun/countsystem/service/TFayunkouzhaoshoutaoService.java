package com.changshun.countsystem.service;

import com.changshun.countsystem.pojo.TFayunkouzhaoshoutao;
import core.service.CoreService;
import org.springframework.web.multipart.MultipartFile;

public interface TFayunkouzhaoshoutaoService extends CoreService<TFayunkouzhaoshoutao> {
	String readExcelFile(MultipartFile file);

}
