package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TFayungongzimingxiMapper;
import com.changshun.countsystem.pojo.TFayungongzimingxi;
import com.changshun.countsystem.service.TFayungongzimingxiService;
import com.changshun.countsystem.util.TFayungongzimingxiExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TFayungongzimingxiServiceImpl extends CoreServiceImpl<TFayungongzimingxi> implements TFayungongzimingxiService {
	@Autowired
    private TFayungongzimingxiMapper tFayungongzimingxiMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TFayungongzimingxiExcelUtil tFayungongzimingxiExcelUtil = new TFayungongzimingxiExcelUtil();
        List<TFayungongzimingxi> ilist = tFayungongzimingxiExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TFayungongzimingxi tFayungongzimingxi : ilist) {
                tFayungongzimingxiMapper.insert(tFayungongzimingxi);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }


}
