package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    // @Autowired가 있으면 Spring이 Container 안에 있는 memberRepository 객체를 memberRepository 매개변수에 연결시켜준다.
    // memberRepository 클래스에 @Repository 등을 붙여줘야 Spring이 Container에 해당 클래스를 넣어줄 수 있다.
    // @Autowired를 통해 MemberService에 memberRepository를 의존관계 주입해준다.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {

        // 중복 회원 검증
        validateDuplicateMember(member);
        memberRepository.save(member);

        return member.getId();
    }

    /**
     * 같은 이름이 존재하는 경우 join이 불가능하게 만든 함수
     */
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
            // 만약 isPresent()한 경우, IllegalStateException 예외를 던진다.(발생시킨다.)
            // IllegalStateException은 객체의 상태가 메소드 호출에는 부적절한 경우
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
