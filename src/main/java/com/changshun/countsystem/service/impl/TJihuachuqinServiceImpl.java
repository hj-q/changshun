package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TJihuachuqinMapper;
import com.changshun.countsystem.pojo.TJihuachuqin;
import com.changshun.countsystem.service.TJihuachuqinService;
import com.changshun.countsystem.util.TJihuachuqinExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TJihuachuqinServiceImpl extends CoreServiceImpl<TJihuachuqin> implements TJihuachuqinService {
	@Autowired
    private TJihuachuqinMapper tJihuachuqinMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TJihuachuqinExcelUtil tJihuachuqinExcelUtil = new TJihuachuqinExcelUtil();
        List<TJihuachuqin> ilist = tJihuachuqinExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TJihuachuqin tJihuachuqin : ilist) {
                tJihuachuqinMapper.insert(tJihuachuqin);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }
  @Override
    public void addInfo(TJihuachuqin tJihuachuqin){
	List<TJihuachuqin> tJihuachuqins = tJihuachuqinMapper.getList(tJihuachuqin);
        for (int i=0;i<tJihuachuqins.size();i++){
            caffeineCache.put(tJihuachuqins.get(i).getWorkerid(),tJihuachuqins.get(i));
        }
    }

    @Override
    public TJihuachuqin getInfo(String workerid) {
        caffeineCache.getIfPresent(workerid);
        TJihuachuqin tJihuachuqin = (TJihuachuqin) caffeineCache.asMap().get(workerid);
        return tJihuachuqin;
    }

}
