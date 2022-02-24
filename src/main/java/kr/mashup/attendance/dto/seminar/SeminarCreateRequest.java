package kr.mashup.attendance.dto.seminar;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SeminarCreateRequest {
    private Integer season;
    private String title;
    private String theme;
    private LocalDateTime seminarStartedAt;
}
