package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemberRepository repository = new MemoryMemberRepository();

    //@Test가 끝날때마다 AfterEach가 실행된다.
//    @AfterEach
//    public void afterEach() {
//        repository.clearStore();
//    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("Spring");

        repository.save(member);
        /*
        Optional 자료형은 null값을 반환해주는 장점이 있기 때문에 사용한다.
        결과에 .get(결과 혹은 NULL 출력, Optional<T>일 경우 안 붙여도 됨)을 붙이몉 Optional<T>의 값을 꺼낸다.(Unboxing)
        */
        Member result = repository.findById(member.getId()).get();
        // org.junit의 assertEquals()
        assertEquals(member, result);
        // org.assertj의 assertion.assertThat()
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        // repository.findByName("Spring1") 의 타입은 Optional<Member>다. (findByName()함수의 리턴 값)
        Member result = repository.findByName("Spring1").get();

        //assertThat을 이용하려면 org.assertj를 선택해야 한다.(ALT + ENTER 단축키로 import를 선언하여 Assertions를 생략함. )
        assertThat(member1).isEqualTo(result);
        // Assertions.assertEquals(null, member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        // findAll()은 List<Member>를 반환한다.
        List<Member> result = repository.findAll();

        // result의 참조형은 List고 size()는 그 List()의 오버라이딩된 함수다.
        assertThat(result.size()).isEqualTo(2);
    }

}
