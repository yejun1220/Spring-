package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    /*
    기본형(int..)이 아닌 참조형(Integer)을 쓴 이유
    1. 매개변수로 객체가 요구 될 때
    2. 기본형 값이 아닌 객체로 저장해야 할 때
    3. 객체간의 비교가 필요할 때
    참조형은 Boxing, Unboxing을 통해 값이 저장되고 출력 된다.
    참조형은 객체(Object형)를 이용할 때 사용되며 기본형으로 접근할 수 없기 때문에 사용 된다.
    Optional은 generic 되는 래퍼 클래스다.
    */

    Optional<Member> findById(Long id);

    Optional<Member> findByName(String name);

    List<Member> findAll();
}
