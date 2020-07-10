package com.vp.amazonreviewsapp.model.dto.request;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequestDto {
    private Long id;
    private String productId;
    private String profileName;
    private LocalDateTime time;
    private String summary;
    private String text;
}
