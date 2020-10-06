package com.changshun.countsystem.dao;

import com.changshun.countsystem.pojo.TCangkukaoqin;
import com.changshun.countsystem.pojo.Toal;
import core.dao.CoreDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ToalMapper  {
    List<Toal> getList( );
}
