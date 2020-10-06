package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TCangkuMapper;
import com.changshun.countsystem.pojo.TCangku;
import com.changshun.countsystem.service.TCangkuService;
import com.changshun.countsystem.util.TCangkuExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TCangkuServiceImpl extends CoreServiceImpl<TCangku> implements TCangkuService{
	@Autowired
    private TCangkuMapper tCangkuMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TCangkuExcelUtil tCangkuExcelUtil = new TCangkuExcelUtil();
        List<TCangku> ilist = tCangkuExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TCangku tCangku : ilist) {
                tCangkuMapper.insert(tCangku);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }
  @Override
    public void addInfo(TCangku tCangku){
	List<TCangku> tCangkus = tCangkuMapper.getList(tCangku);
        for (int i=0;i<tCangkus.size();i++){
            caffeineCache.put(tCangkus.get(i).getWorkerid(),tCangkus.get(i));
        }
    }

    @Override
    public TCangku getInfo(String workerid) {
        caffeineCache.getIfPresent(workerid);
        TCangku tCangku = (TCangku) caffeineCache.asMap().get(workerid);
        return tCangku;
    }

}
