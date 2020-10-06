package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TFayunchuqinMapper;
import com.changshun.countsystem.pojo.TFayunchuqin;
import com.changshun.countsystem.service.TFayunchuqinService;
import com.changshun.countsystem.util.TFayunchuqinExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TFayunchuqinServiceImpl extends CoreServiceImpl<TFayunchuqin> implements TFayunchuqinService{
	@Autowired
    private TFayunchuqinMapper tFayunchuqinMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TFayunchuqinExcelUtil tFayunchuqinExcelUtil = new TFayunchuqinExcelUtil();
        List<TFayunchuqin> ilist = tFayunchuqinExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TFayunchuqin tFayunchuqin : ilist) {
                tFayunchuqinMapper.insert(tFayunchuqin);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }
  @Override
    public void addInfo(TFayunchuqin tFayunchuqin){
	List<TFayunchuqin> tFayunchuqins = tFayunchuqinMapper.getList(tFayunchuqin);
        for (int i=0;i<tFayunchuqins.size();i++){
            caffeineCache.put(tFayunchuqins.get(i).getWorkerid(),tFayunchuqins.get(i));
        }
    }

    @Override
    public TFayunchuqin getInfo(String workerid) {
        caffeineCache.getIfPresent(workerid);
        TFayunchuqin tFayunchuqin = (TFayunchuqin) caffeineCache.asMap().get(workerid);
        return tFayunchuqin;
    }

}
