package kr.mashup.attendance.domain.seminar;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import kr.mashup.attendance.domain.season.Season;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString(of = {"seminarId", "title", "seminarStartedAt"})
@EqualsAndHashCode(of = "seminarId")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Seminar {
    @Id
    @GeneratedValue
    private Long seminarId;

    @ManyToOne
    @JoinColumn(name = "season_id")
    private Season season;

    private String title;

    private String theme;

    private LocalDateTime seminarStartedAt;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public static Seminar of(Season season, String title, String theme, LocalDateTime seminarStartedAt) {
        Seminar seminar = new Seminar();
        seminar.season = season;
        seminar.title = title;
        seminar.theme = theme;
        seminar.seminarStartedAt = seminarStartedAt;
        return seminar;
    }
}
