package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TGuangzhoukaoqinMapper;
import com.changshun.countsystem.pojo.TGuangzhoukaoqin;
import com.changshun.countsystem.service.TGuangzhoukaoqinService;
import com.changshun.countsystem.util.TGuangzhoukaoqinExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TGuangzhoukaoqinServiceImpl extends CoreServiceImpl<TGuangzhoukaoqin> implements TGuangzhoukaoqinService{
	@Autowired
    private TGuangzhoukaoqinMapper tGuangzhoukaoqinMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TGuangzhoukaoqinExcelUtil tGuangzhoukaoqinExcelUtil = new TGuangzhoukaoqinExcelUtil();
        List<TGuangzhoukaoqin> ilist = tGuangzhoukaoqinExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TGuangzhoukaoqin tGuangzhoukaoqin : ilist) {
                tGuangzhoukaoqinMapper.insert(tGuangzhoukaoqin);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }
  @Override
    public void addInfo(TGuangzhoukaoqin tGuangzhoukaoqin){
	List<TGuangzhoukaoqin> tGuangzhoukaoqins = tGuangzhoukaoqinMapper.getList(tGuangzhoukaoqin);
        for (int i=0;i<tGuangzhoukaoqins.size();i++){
            caffeineCache.put(tGuangzhoukaoqins.get(i).getWorkerid(),tGuangzhoukaoqins.get(i));
        }
    }

    @Override
    public TGuangzhoukaoqin getInfo(String workerid) {
        caffeineCache.getIfPresent(workerid);
        TGuangzhoukaoqin tGuangzhoukaoqin = (TGuangzhoukaoqin) caffeineCache.asMap().get(workerid);
        return tGuangzhoukaoqin;
    }

}
