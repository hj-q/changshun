package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TSupermanagerMapper;
import com.changshun.countsystem.pojo.TSupermanager;
import com.changshun.countsystem.service.TSupermanagerService;
import com.changshun.countsystem.util.TSupermanagerExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TSupermanagerServiceImpl extends CoreServiceImpl<TSupermanager> implements TSupermanagerService {
	@Autowired
    private TSupermanagerMapper tSupermanagerMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TSupermanagerExcelUtil tSupermanagerExcelUtil = new TSupermanagerExcelUtil();
        List<TSupermanager> ilist = tSupermanagerExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TSupermanager tSupermanager : ilist) {
                tSupermanagerMapper.insert(tSupermanager);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }


}
