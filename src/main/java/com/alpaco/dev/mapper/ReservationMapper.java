package com.alpaco.dev.mapper;

import com.alpaco.dev.entity.Reservation;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface ReservationMapper {
    Optional<Reservation> findByIdAndUserId(Long reservationId, Long userId);
    void updateReviewStatus(Long reservationId);
}