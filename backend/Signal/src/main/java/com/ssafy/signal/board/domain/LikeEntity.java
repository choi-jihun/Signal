package com.ssafy.signal.board.domain;

import com.ssafy.signal.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "likes")  // 테이블 이름을 "likes"로 지정
@Getter  // 모든 필드에 대한 getter 메서드 생성
@Setter  // 모든 필드에 대한 setter 메서드 생성
public class LikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)  // 컬럼명과 일치하게 수정
    private Member user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private BoardEntity boardEntity;

    @Column(name = "liked", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean liked = false;

    // 기본 생성자
    public LikeEntity() {}

    public LikeEntity(Member user, BoardEntity boardEntity) {
        this.user = user;
        this.boardEntity = boardEntity;
        this.liked = true;
    }

    // liked 값을 true로 설정하는 메서드
    public void like() {
        this.liked = true;
    }

    // liked 값을 false로 설정하는 메서드
    public void unlike() {
        this.liked = false;
    }
}
