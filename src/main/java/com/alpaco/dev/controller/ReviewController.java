package com.alpaco.dev.controller;


import com.alpaco.dev.dto.review.ReviewListResponseDto;
import com.alpaco.dev.dto.review.ReviewSaveRequestDto;
import com.alpaco.dev.dto.review.ReviewSaveResponseDto;
import com.alpaco.dev.exception.BaseResponse;
import com.alpaco.dev.exception.ReviewException;
import com.alpaco.dev.service.ReviewService;
import com.alpaco.dev.util.jwt.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.alpaco.dev.exception.BaseResponseStatus.INVALID_REQUEST;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/app")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/reviews")
    public BaseResponse<List<ReviewListResponseDto>> getList(@RequestParam int roomId,
                                                             @RequestParam(defaultValue = "4") int limit,
                                                             @RequestParam(defaultValue = "0") int page) {

        int offset = limit * page;
        List<ReviewListResponseDto> reviewList = reviewService.findAllDesc(roomId, limit, offset);

        if (reviewList.size() == 0) throw new ReviewException(INVALID_REQUEST);

        return new BaseResponse<>(reviewList);
    }

    @GetMapping("/reviews/{reviewId}")
    public BaseResponse<ReviewListResponseDto> get(@PathVariable Long reviewId) {

        ReviewListResponseDto review = reviewService.findById(reviewId);

        return new BaseResponse<>(review);
    }

    @PostMapping("/reviews")
    public BaseResponse<ReviewSaveResponseDto> createReview(@RequestParam("reservationId") Long reservationId,
                                                            @RequestParam(value = "reviewImages", required = false) List<MultipartFile> reviewImages,
                                                            @RequestParam(value = "score", required = false, defaultValue = "5") int score,
                                                            @RequestParam(value = "content", required = false) String content) {
        ReviewSaveRequestDto requestDto = ReviewSaveRequestDto.of(reviewImages, score, content);
        ReviewSaveResponseDto save = reviewService.save(Long.valueOf(1), reservationId, requestDto);
        return new BaseResponse<>(save);
    }

    @GetMapping("/reviews/jwttest")
    public BaseResponse<String> jwttest(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        String username = principalDetails.getUsername();

        return new BaseResponse<>(username);
    }
}