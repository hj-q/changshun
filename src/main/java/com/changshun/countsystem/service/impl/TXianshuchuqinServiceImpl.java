package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TXianshuchuqinMapper;
import com.changshun.countsystem.pojo.TXianshuchuqin;
import com.changshun.countsystem.service.TXianshuchuqinService;
import com.changshun.countsystem.util.TXianshuchuqinExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TXianshuchuqinServiceImpl extends CoreServiceImpl<TXianshuchuqin> implements TXianshuchuqinService {
	@Autowired
    private TXianshuchuqinMapper tXianshuchuqinMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TXianshuchuqinExcelUtil tXianshuchuqinExcelUtil = new TXianshuchuqinExcelUtil();
        List<TXianshuchuqin> ilist = tXianshuchuqinExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TXianshuchuqin tXianshuchuqin : ilist) {
                tXianshuchuqinMapper.insert(tXianshuchuqin);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }
  @Override
    public void addInfo(TXianshuchuqin tXianshuchuqin){
	List<TXianshuchuqin> tXianshuchuqins = tXianshuchuqinMapper.getList(tXianshuchuqin);
        for (int i=0;i<tXianshuchuqins.size();i++){
            caffeineCache.put(tXianshuchuqins.get(i).getWorkerid(),tXianshuchuqins.get(i));
        }
    }

    @Override
    public TXianshuchuqin getInfo(String workerid) {
        caffeineCache.getIfPresent(workerid);
        TXianshuchuqin tXianshuchuqin = (TXianshuchuqin) caffeineCache.asMap().get(workerid);
        return tXianshuchuqin;
    }

}
