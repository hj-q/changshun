package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TDianlanchuqinMapper;
import com.changshun.countsystem.pojo.TDianlanchuqin;
import com.changshun.countsystem.service.TDianlanchuqinService;
import com.changshun.countsystem.util.TDianlanchuqinExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TDianlanchuqinServiceImpl extends CoreServiceImpl<TDianlanchuqin> implements TDianlanchuqinService {
	@Autowired
    private TDianlanchuqinMapper tDianlanchuqinMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TDianlanchuqinExcelUtil tDianlanchuqinExcelUtil = new TDianlanchuqinExcelUtil();
        List<TDianlanchuqin> ilist = tDianlanchuqinExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TDianlanchuqin tDianlanchuqin : ilist) {
                tDianlanchuqinMapper.insert(tDianlanchuqin);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }
  @Override
    public void addInfo(TDianlanchuqin tDianlanchuqin){
	List<TDianlanchuqin> tDianlanchuqins = tDianlanchuqinMapper.getList(tDianlanchuqin);
        for (int i=0;i<tDianlanchuqins.size();i++){
            caffeineCache.put(tDianlanchuqins.get(i).getWorkerid(),tDianlanchuqins.get(i));
        }
    }

    @Override
    public TDianlanchuqin getInfo(String workerid) {
        caffeineCache.getIfPresent(workerid);
        TDianlanchuqin tDianlanchuqin = (TDianlanchuqin) caffeineCache.asMap().get(workerid);
        return tDianlanchuqin;
    }

}
