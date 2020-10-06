package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TLasichuqinMapper;
import com.changshun.countsystem.pojo.TLasichuqin;
import com.changshun.countsystem.service.TLasichuqinService;
import com.changshun.countsystem.util.TLasichuqinExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TLasichuqinServiceImpl extends CoreServiceImpl<TLasichuqin> implements TLasichuqinService {
	@Autowired
    private TLasichuqinMapper tLasichuqinMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TLasichuqinExcelUtil tLasichuqinExcelUtil = new TLasichuqinExcelUtil();
        List<TLasichuqin> ilist = tLasichuqinExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TLasichuqin tLasichuqin : ilist) {
                tLasichuqinMapper.insert(tLasichuqin);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }
  @Override
    public void addInfo(TLasichuqin tLasichuqin){
	List<TLasichuqin> tLasichuqins = tLasichuqinMapper.getList(tLasichuqin);
        for (int i=0;i<tLasichuqins.size();i++){
            caffeineCache.put(tLasichuqins.get(i).getWorkerid(),tLasichuqins.get(i));
        }
    }

    @Override
    public TLasichuqin getInfo(String workerid) {
        caffeineCache.getIfPresent(workerid);
        TLasichuqin tLasichuqin = (TLasichuqin) caffeineCache.asMap().get(workerid);
        return tLasichuqin;
    }

}
