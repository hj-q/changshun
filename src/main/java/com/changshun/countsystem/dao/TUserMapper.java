package com.changshun.countsystem.dao;

import com.changshun.countsystem.pojo.TUser;
import core.dao.CoreDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TUserMapper extends CoreDao<TUser> {
}
