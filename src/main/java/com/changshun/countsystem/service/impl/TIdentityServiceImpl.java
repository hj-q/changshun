package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TIdentityMapper;
import com.changshun.countsystem.pojo.TIdentity;
import com.changshun.countsystem.service.TIdentityService;
import com.changshun.countsystem.util.TIdentityExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TIdentityServiceImpl extends CoreServiceImpl<TIdentity> implements TIdentityService {
	@Autowired
    private TIdentityMapper tIdentityMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TIdentityExcelUtil tIdentityExcelUtil = new TIdentityExcelUtil();
        List<TIdentity> ilist = tIdentityExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TIdentity tIdentity : ilist) {
                tIdentityMapper.insert(tIdentity);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }


}
