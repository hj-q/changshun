package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TFayunrenyuanMapper;
import com.changshun.countsystem.pojo.TFayunrenyuan;
import com.changshun.countsystem.service.TFayunrenyuanService;
import com.changshun.countsystem.util.TFayunrenyuanExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TFayunrenyuanServiceImpl extends CoreServiceImpl<TFayunrenyuan> implements TFayunrenyuanService {
	@Autowired
    private TFayunrenyuanMapper tFayunrenyuanMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TFayunrenyuanExcelUtil tFayunrenyuanExcelUtil = new TFayunrenyuanExcelUtil();
        List<TFayunrenyuan> ilist = tFayunrenyuanExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TFayunrenyuan tFayunrenyuan : ilist) {
                tFayunrenyuanMapper.insert(tFayunrenyuan);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }
  @Override
    public void addInfo(TFayunrenyuan tFayunrenyuan){
	List<TFayunrenyuan> tFayunrenyuans = tFayunrenyuanMapper.getList(tFayunrenyuan);
        for (int i=0;i<tFayunrenyuans.size();i++){
            caffeineCache.put(tFayunrenyuans.get(i).getWorkerid(),tFayunrenyuans.get(i));
        }
    }

    @Override
    public TFayunrenyuan getInfo(String workerid) {
        caffeineCache.getIfPresent(workerid);
        TFayunrenyuan tFayunrenyuan = (TFayunrenyuan) caffeineCache.asMap().get(workerid);
        return tFayunrenyuan;
    }

}
