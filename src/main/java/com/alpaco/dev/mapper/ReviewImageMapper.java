package com.alpaco.dev.mapper;

import com.alpaco.dev.entity.ReviewImage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReviewImageMapper {
    void save(@Param("reviewImage") ReviewImage reviewImage);
}
