package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.ServerEntity;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ServerMapper extends BaseMapper<ServerEntity>{
}

