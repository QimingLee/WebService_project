package com.example.demo.mapper;

import com.example.demo.entity.Image;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ImageMapper {
    int deleteByPrimaryKey(String image);

    int insert(Image record);

    int insertSelective(Image record);

    Image selectByPrimaryKey(String image);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKey(Image record);

    String selectTextByPrimaryKey(String image);
}