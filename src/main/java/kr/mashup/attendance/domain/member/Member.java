package kr.mashup.attendance.domain.member;

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

import kr.mashup.attendance.domain.team.Team;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString(of = {"memberId", "createdAt", "updatedAt"})
@EqualsAndHashCode(of = "memberId")
@EntityListeners(AuditingEntityListener.class)
public class Member {
    @Id
    @GeneratedValue
    private Long memberId;

    private String username;

    private String password;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public static Member of(String username, String password, Team team) {
        Member member = new Member();
        member.username = username;
        member.password = password;
        member.team = team;
        return member;
    }
}
