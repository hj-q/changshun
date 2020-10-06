package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TFayuntiaozhengMapper;
import com.changshun.countsystem.pojo.TFayuntiaozheng;
import com.changshun.countsystem.service.TFayuntiaozhengService;
import com.changshun.countsystem.util.TFayuntiaozhengExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TFayuntiaozhengServiceImpl extends CoreServiceImpl<TFayuntiaozheng> implements TFayuntiaozhengService {
	@Autowired
    private TFayuntiaozhengMapper tFayuntiaozhengMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TFayuntiaozhengExcelUtil tFayuntiaozhengExcelUtil = new TFayuntiaozhengExcelUtil();
        List<TFayuntiaozheng> ilist = tFayuntiaozhengExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TFayuntiaozheng tFayuntiaozheng : ilist) {
                tFayuntiaozhengMapper.insert(tFayuntiaozheng);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }


}
