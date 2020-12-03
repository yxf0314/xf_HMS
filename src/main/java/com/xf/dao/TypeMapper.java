package com.xf.dao;

import com.xf.pojo.RoomType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TypeMapper {
    List<RoomType> SelectTypeList();
}
