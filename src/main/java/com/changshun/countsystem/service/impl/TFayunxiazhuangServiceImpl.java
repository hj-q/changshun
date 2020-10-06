package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TFayunxiazhuangMapper;
import com.changshun.countsystem.pojo.TFayunxiazhuang;
import com.changshun.countsystem.service.TFayunxiazhuangService;
import com.changshun.countsystem.util.TFayunxiazhuangExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TFayunxiazhuangServiceImpl extends CoreServiceImpl<TFayunxiazhuang> implements TFayunxiazhuangService {
	@Autowired
    private TFayunxiazhuangMapper tFayunxiazhuangMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TFayunxiazhuangExcelUtil tFayunxiazhuangExcelUtil = new TFayunxiazhuangExcelUtil();
        List<TFayunxiazhuang> ilist = tFayunxiazhuangExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TFayunxiazhuang tFayunxiazhuang : ilist) {
                tFayunxiazhuangMapper.insert(tFayunxiazhuang);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }


}
