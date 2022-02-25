package kr.mashup.attendance.domain.attendance;

import java.time.LocalDateTime;

import javax.persistence.*;

import kr.mashup.attendance.domain.member.Member;
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
@ToString(of = {"attendanceId"})
@EqualsAndHashCode(of = "attendanceId")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Attendance {
    @Id
    @GeneratedValue
    private Long attendanceId;

    @ManyToOne
    @JoinColumn(name = "seminar_id")
    private Seminar seminar;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private AttendanceType type;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public static Attendance of(Seminar seminar, Member member, AttendanceType type) {
        Attendance attendance = new Attendance();
        attendance.seminar = seminar;
        attendance.member = member;
        attendance.type = type;
        return attendance;
    }
}
