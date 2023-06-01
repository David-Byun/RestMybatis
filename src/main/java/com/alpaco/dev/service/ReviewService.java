package com.alpaco.dev.service;


import com.alpaco.dev.dto.review.ReviewListResponseDto;
import com.alpaco.dev.dto.review.ReviewSaveRequestDto;
import com.alpaco.dev.dto.review.ReviewSaveResponseDto;
import com.alpaco.dev.entity.Reservation;
import com.alpaco.dev.entity.Review;
import com.alpaco.dev.entity.ReviewImage;
import com.alpaco.dev.exception.ReservationException;
import com.alpaco.dev.exception.ReviewException;
import com.alpaco.dev.mapper.ReservationMapper;
import com.alpaco.dev.mapper.ReviewImageMapper;
import com.alpaco.dev.mapper.ReviewMapper;
import com.alpaco.dev.mapper.UserMapper;
import com.alpaco.dev.util.aws.AwsS3Uploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.alpaco.dev.exception.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class ReviewService {
    private final UserMapper userMapper;
    private final ReviewMapper reviewMapper;
    private final ReservationMapper reservationMapper;
    private final ReviewImageMapper reviewImageMapper;
    private final AwsS3Uploader awsS3Uploader;

    public List<ReviewListResponseDto> findAllDesc(int roomId, int limit, int offset) {
        List<ReviewListResponseDto> allDesc = reviewMapper.findAllDesc(roomId, limit, offset);

        return allDesc;
    }

    public ReviewListResponseDto findById(Long reviewId) {
        ReviewListResponseDto reviewResponse = reviewMapper.findById(reviewId)
                .orElseThrow(() -> new ReviewException(NONE_REVIEW));

        return reviewResponse;
    }

    // 리뷰 - 리뷰이미지 저장 로직
    public ReviewSaveResponseDto save(Long userNumber, Long reservationId, ReviewSaveRequestDto requestDto) {
        Long userId = userNumber;

        // Reservaton 객체 꺼내오기, 예외 - 리뷰작성 시 예약이 없는 경우
        Reservation reservation = reservationMapper.findByIdAndUserId(reservationId, userId)
                .orElseThrow(() -> new ReservationException(POST_REVIEW_NONE_RESERVATION));

        // 예외 - 리뷰가 이미 있는 경우
        if (reservation.isReviewCreated()) {
            throw new ReviewException(POST_REVIEW_ALREADY_CREATED);
        }
        // Dto -> Entity
        Review review = requestDto.toEntity(reservation);
        reviewMapper.save(review);

        // 클래스 분할
        uploadReviewImages(requestDto, review);

        // 리뷰 작성 시 예약테이블 isReviewCreated 컬럼 변환
        reservationMapper.updateReviewStatus(reservationId);

        // 응답 response build
        return ReviewSaveResponseDto.builder()
                .id(review.getReview_id())
                .build();
    }

    private void uploadReviewImages(ReviewSaveRequestDto requestDto, Review review) {
        List<MultipartFile> reviewImages = requestDto.getReviewImages();

        for (MultipartFile multipartFile : reviewImages) {
            //리뷰라는 디렉토리를 만들어서 저장함 AwsS3Uploader에서 확인 가능
            String reviewImageUrl = awsS3Uploader.upload(multipartFile, "review");
            reviewImageMapper.save(ReviewImage.builder()
                    .reviewImageUrl(reviewImageUrl)
                    .review(review)
                    .build());
        }
    }

}