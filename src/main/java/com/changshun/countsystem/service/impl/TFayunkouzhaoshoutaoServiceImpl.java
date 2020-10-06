package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TFayunkouzhaoshoutaoMapper;
import com.changshun.countsystem.pojo.TFayunkouzhaoshoutao;
import com.changshun.countsystem.service.TFayunkouzhaoshoutaoService;
import com.changshun.countsystem.util.TFayunkouzhaoshoutaoExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TFayunkouzhaoshoutaoServiceImpl extends CoreServiceImpl<TFayunkouzhaoshoutao> implements TFayunkouzhaoshoutaoService {
	@Autowired
    private TFayunkouzhaoshoutaoMapper tFayunkouzhaoshoutaoMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TFayunkouzhaoshoutaoExcelUtil tFayunkouzhaoshoutaoExcelUtil = new TFayunkouzhaoshoutaoExcelUtil();
        List<TFayunkouzhaoshoutao> ilist = tFayunkouzhaoshoutaoExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TFayunkouzhaoshoutao tFayunkouzhaoshoutao : ilist) {
                tFayunkouzhaoshoutaoMapper.insert(tFayunkouzhaoshoutao);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }


}
