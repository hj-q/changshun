package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TUserMapper;
import com.changshun.countsystem.pojo.TUser;
import com.changshun.countsystem.service.TUserService;
import com.changshun.countsystem.util.TUserExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TUserServiceImpl extends CoreServiceImpl<TUser> implements TUserService{
	@Autowired
    private TUserMapper tUserMapper ;
    @Autowired
    Cache<String, Object> caffeineCache;
@Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        TUserExcelUtil tUserExcelUtil = new TUserExcelUtil();
        List<TUser> ilist = tUserExcelUtil.getExcelInfo(file);
        if (ilist != null && !ilist.isEmpty()) {
            //不为空的话添加到数据库
            for (TUser tUser : ilist) {
                tUserMapper.insert(tUser);
            }
            result = "上传成功";
        } else {
            result = "上传失败";
        }
        return result;
    }


}
