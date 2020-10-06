package com.changshun.countsystem.service.impl;

import com.changshun.countsystem.dao.TCangkukaoqinMapper;
import com.changshun.countsystem.dao.ToalMapper;
import com.changshun.countsystem.pojo.TCangkukaoqin;
import com.changshun.countsystem.pojo.Toal;
import com.changshun.countsystem.service.TCangkukaoqinService;
import com.changshun.countsystem.service.ToalService;
import com.changshun.countsystem.util.TCangkukaoqinExcelUtil;
import com.github.benmanes.caffeine.cache.Cache;
import core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class ToalServiceImpl  implements ToalService {
	@Autowired
    private ToalMapper toalMapper ;


    @Override
    public List<Toal> getList() {
        List<Toal> list = toalMapper.getList();
        return list;
    }

}
