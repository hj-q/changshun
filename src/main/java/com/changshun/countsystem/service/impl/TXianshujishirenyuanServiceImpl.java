package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TXianshujishirenyuanMapper;
import com.changshun.countsystem.pojo.TXianshujishirenyuan;
import com.changshun.countsystem.service.TXianshujishirenyuanService;
import com.changshun.countsystem.util.TXianshujishirenyuanExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TXianshujishirenyuanServiceImpl extends CoreServiceImpl<TXianshujishirenyuan> implements TXianshujishirenyuanService {
	@Autowired
    private TXianshujishirenyuanMapper tXianshujishirenyuanMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TXianshujishirenyuanExcelUtil tXianshujishirenyuanExcelUtil = new TXianshujishirenyuanExcelUtil();
        List<TXianshujishirenyuan> ilist = tXianshujishirenyuanExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TXianshujishirenyuan tXianshujishirenyuan : ilist) {
                tXianshujishirenyuanMapper.insert(tXianshujishirenyuan);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }
  @Override
    public void addInfo(TXianshujishirenyuan tXianshujishirenyuan){
	List<TXianshujishirenyuan> tXianshujishirenyuans = tXianshujishirenyuanMapper.getList(tXianshujishirenyuan);
        for (int i=0;i<tXianshujishirenyuans.size();i++){
            caffeineCache.put(tXianshujishirenyuans.get(i).getWorkerid(),tXianshujishirenyuans.get(i));
        }
    }

    @Override
    public TXianshujishirenyuan getInfo(String workerid) {
        caffeineCache.getIfPresent(workerid);
        TXianshujishirenyuan tXianshujishirenyuan = (TXianshujishirenyuan) caffeineCache.asMap().get(workerid);
        return tXianshujishirenyuan;
    }

}
