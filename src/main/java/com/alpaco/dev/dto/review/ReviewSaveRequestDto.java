package com.alpaco.dev.dto.review;

import com.alpaco.dev.entity.Reservation;
import com.alpaco.dev.entity.Review;
import com.alpaco.dev.entity.user.Status;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Slf4j
@Builder
public class ReviewSaveRequestDto {
    private long review_id;
    private List<MultipartFile> reviewImages;

    @NotBlank(message = "score은 필수 입력사항 입니다.")
    private Integer score;

    @NotBlank(message = "content는 필수 입력사항 입니다.")
    @Size(min = 10, message = "내용이 짧습니다.")
    private String content;

    public Review toEntity(Reservation reservation) {
        return Review.builder()
                .reservation(reservation)
                .status(Status.ACTIVE)
                .score(score)
                .content(content)
                .build();
    }

    public static ReviewSaveRequestDto of(List<MultipartFile> reviewImages, int score, String content) {
        return ReviewSaveRequestDto.builder()
                .content(content)
                .score(score)
                .reviewImages(reviewImages)
                .build();
    }
}
