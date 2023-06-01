package com.alpaco.dev.mapper;

import com.alpaco.dev.dto.review.ReviewListResponseDto;
import com.alpaco.dev.entity.Review;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ReviewMapper {
    List<ReviewListResponseDto> findAllDesc(int roomId, int limit, int offset);

    Optional<ReviewListResponseDto> findById(Long reviewId);

    void save(@Param("review") Review review);

}
