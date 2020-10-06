package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TOfficeMapper;
import com.changshun.countsystem.pojo.TOffice;
import com.changshun.countsystem.service.TOfficeService;
import com.changshun.countsystem.util.TOfficeExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TOfficeServiceImpl extends CoreServiceImpl<TOffice> implements TOfficeService{
	@Autowired
    private TOfficeMapper tOfficeMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TOfficeExcelUtil tOfficeExcelUtil = new TOfficeExcelUtil();
        List<TOffice> ilist = tOfficeExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TOffice tOffice : ilist) {
                tOfficeMapper.insert(tOffice);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }
  @Override
    public void addInfo(TOffice tOffice){
	List<TOffice> tOffices = tOfficeMapper.getList(tOffice);
        for (int i=0;i<tOffices.size();i++){
            caffeineCache.put(tOffices.get(i).getWorkerid(),tOffices.get(i));
        }
    }

    @Override
    public TOffice getInfo(String workerid) {
        caffeineCache.getIfPresent(workerid);
        TOffice tOffice = (TOffice) caffeineCache.asMap().get(workerid);
        return tOffice;
    }

}
