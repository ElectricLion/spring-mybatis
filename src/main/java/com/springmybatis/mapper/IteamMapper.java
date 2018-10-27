package com.springmybatis.mapper;


import com.springmybatis.pojo.Iteam;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author:tanghui
 * @since 1.0
 */

public interface IteamMapper  {
    List<Iteam> select();

    void add(Iteam iteam);
}
