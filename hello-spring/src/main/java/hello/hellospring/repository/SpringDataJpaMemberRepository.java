package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository를 상속 받으면 SpringDataJpa가 인터페이스 SpringDataJpaMemberRepository의 구현체를 만들어준 후
// String Bean에 자동으로 등록해준다.
// 우리는 그 구현체를 가져와서 쓰면 된다.(SpringConfig에서 확인 가능)

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{

    // JPQL "select m from Member m where m.name =? 와 같음
    @Override
    Optional<Member> findByName(String name);
//    Optional<Member> findByNameAndId(String name, Long id); 등 가능
}
