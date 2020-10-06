package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TDianlankoufaMapper;
import com.changshun.countsystem.pojo.TDianlankoufa;
import com.changshun.countsystem.service.TDianlankoufaService;
import com.changshun.countsystem.util.TDianlankoufaExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TDianlankoufaServiceImpl extends CoreServiceImpl<TDianlankoufa> implements TDianlankoufaService{
	@Autowired
    private TDianlankoufaMapper tDianlankoufaMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TDianlankoufaExcelUtil tDianlankoufaExcelUtil = new TDianlankoufaExcelUtil();
        List<TDianlankoufa> ilist = tDianlankoufaExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TDianlankoufa tDianlankoufa : ilist) {
                tDianlankoufaMapper.insert(tDianlankoufa);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }


}
