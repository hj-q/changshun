package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TZhiliangfakuanMapper;
import com.changshun.countsystem.pojo.TZhiliangfakuan;
import com.changshun.countsystem.service.TZhiliangfakuanService;
import com.changshun.countsystem.util.TZhiliangfakuanExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TZhiliangfakuanServiceImpl extends CoreServiceImpl<TZhiliangfakuan> implements TZhiliangfakuanService{
	@Autowired
    private TZhiliangfakuanMapper tZhiliangfakuanMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TZhiliangfakuanExcelUtil tZhiliangfakuanExcelUtil = new TZhiliangfakuanExcelUtil();
        List<TZhiliangfakuan> ilist = tZhiliangfakuanExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TZhiliangfakuan tZhiliangfakuan : ilist) {
                tZhiliangfakuanMapper.insert(tZhiliangfakuan);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }
  @Override
    public void addInfo(TZhiliangfakuan tZhiliangfakuan){
	List<TZhiliangfakuan> tZhiliangfakuans = tZhiliangfakuanMapper.getList(tZhiliangfakuan);
        for (int i=0;i<tZhiliangfakuans.size();i++){
            caffeineCache.put(tZhiliangfakuans.get(i).getWorkerid(),tZhiliangfakuans.get(i));
        }
    }

    @Override
    public TZhiliangfakuan getInfo(String workerid) {
        caffeineCache.getIfPresent(workerid);
        TZhiliangfakuan tZhiliangfakuan = (TZhiliangfakuan) caffeineCache.asMap().get(workerid);
        return tZhiliangfakuan;
    }

}
