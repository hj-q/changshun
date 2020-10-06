package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TFayunhoursMapper;
import com.changshun.countsystem.pojo.TFayunhours;
import com.changshun.countsystem.service.TFayunhoursService;
import com.changshun.countsystem.util.TFayunhoursExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TFayunhoursServiceImpl extends CoreServiceImpl<TFayunhours> implements TFayunhoursService{
	@Autowired
    private TFayunhoursMapper tFayunhoursMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TFayunhoursExcelUtil tFayunhoursExcelUtil = new TFayunhoursExcelUtil();
        List<TFayunhours> ilist = tFayunhoursExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TFayunhours tFayunhours : ilist) {
                tFayunhoursMapper.insert(tFayunhours);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }


}
