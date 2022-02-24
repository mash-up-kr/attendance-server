package kr.mashup.attendance.domain.member;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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

    //TODO teamId

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
