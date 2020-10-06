package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TGuangzhouxuexiMapper;
import com.changshun.countsystem.pojo.TGuangzhouxuexi;
import com.changshun.countsystem.service.TGuangzhouxuexiService;
import com.changshun.countsystem.util.TGuangzhouxuexiExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TGuangzhouxuexiServiceImpl extends CoreServiceImpl<TGuangzhouxuexi> implements TGuangzhouxuexiService {
	@Autowired
    private TGuangzhouxuexiMapper tGuangzhouxuexiMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TGuangzhouxuexiExcelUtil tGuangzhouxuexiExcelUtil = new TGuangzhouxuexiExcelUtil();
        List<TGuangzhouxuexi> ilist = tGuangzhouxuexiExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TGuangzhouxuexi tGuangzhouxuexi : ilist) {
                tGuangzhouxuexiMapper.insert(tGuangzhouxuexi);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }
  @Override
    public void addInfo(TGuangzhouxuexi tGuangzhouxuexi){
	List<TGuangzhouxuexi> tGuangzhouxuexis = tGuangzhouxuexiMapper.getList(tGuangzhouxuexi);
        for (int i=0;i<tGuangzhouxuexis.size();i++){
            caffeineCache.put(tGuangzhouxuexis.get(i).getWorkerid(),tGuangzhouxuexis.get(i));
        }
    }

    @Override
    public TGuangzhouxuexi getInfo(String workerid) {
        caffeineCache.getIfPresent(workerid);
        TGuangzhouxuexi tGuangzhouxuexi = (TGuangzhouxuexi) caffeineCache.asMap().get(workerid);
        return tGuangzhouxuexi;
    }

}
