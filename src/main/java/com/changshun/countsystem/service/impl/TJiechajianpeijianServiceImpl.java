package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TJiechajianpeijianMapper;
import com.changshun.countsystem.pojo.TJiechajianpeijian;
import com.changshun.countsystem.service.TJiechajianpeijianService;
import com.changshun.countsystem.util.TJiechajianpeijianExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TJiechajianpeijianServiceImpl extends CoreServiceImpl<TJiechajianpeijian> implements TJiechajianpeijianService{
	@Autowired
    private TJiechajianpeijianMapper tJiechajianpeijianMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TJiechajianpeijianExcelUtil tJiechajianpeijianExcelUtil = new TJiechajianpeijianExcelUtil();
        List<TJiechajianpeijian> ilist = tJiechajianpeijianExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TJiechajianpeijian tJiechajianpeijian : ilist) {
                tJiechajianpeijianMapper.insert(tJiechajianpeijian);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }
  @Override
    public void addInfo(TJiechajianpeijian tJiechajianpeijian){
	List<TJiechajianpeijian> tJiechajianpeijians = tJiechajianpeijianMapper.getList(tJiechajianpeijian);
        for (int i=0;i<tJiechajianpeijians.size();i++){
            caffeineCache.put(tJiechajianpeijians.get(i).getWorkerid(),tJiechajianpeijians.get(i));
        }
    }

    @Override
    public TJiechajianpeijian getInfo(String workerid) {
        caffeineCache.getIfPresent(workerid);
        TJiechajianpeijian tJiechajianpeijian = (TJiechajianpeijian) caffeineCache.asMap().get(workerid);
        return tJiechajianpeijian;
    }

}
