package kr.mashup.attendance.domain.attendance;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import kr.mashup.attendance.domain.seminar.Seminar;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString(of = {"attendanceEventId"})
@EqualsAndHashCode(of = "attendanceEventId")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class AttendanceEvent {
    @Id
    @GeneratedValue
    private Long attendanceEventId;

    @ManyToOne
    @JoinColumn(name = "seminar_id")
    private Seminar seminar;

    //TODO ManyToOne Member

    @Enumerated(EnumType.STRING)
    private AttendanceEventType type;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public static AttendanceEvent of(Seminar seminar, AttendanceEventType type) {
        AttendanceEvent attendanceEvent = new AttendanceEvent();
        attendanceEvent.seminar = seminar;
        attendanceEvent.type = type;
        return attendanceEvent;
    }
}