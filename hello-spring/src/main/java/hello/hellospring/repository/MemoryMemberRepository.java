package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository {

    // HashMap<> = new HashMap<>()이 아닌 이유는?
    // 자바는 객체 지향 언어이고, 객체 지향 언어의 꽃은 다형성(Polymorphism)이기 때문이다.
    // 1. Map 인터페이스의 제약을 따르겠다는 의도를 명확하게 드러낸다.
    // 2. 사용하는 코드가 Map 인터페이스 제약을 따르기 때문에 향후 변경시에 사용코드를 변경하지 않아도 된다.
    // 3. HashMap을 다른 클래스로 변경이 필요하면 선언하는 코드만 변경하면 된ㄷ다. 사용하는 코드를 고민하지 않아도 된다.
    // 4. 다른 개발자들이 이 코드를 나중에 더 성능이 좋거나 동시성 처리가 가능한 종류의 구체적인 Map으로 변경해야 할 때 HashMap store = new HashMap()이라고 되어 있다면,
    // 변경 시점에 상당히 많은 고민을 해야 하지만 Map store = new HashMap()으로 선언이 되어 있다면 편안하게 선언부를 변경할 수 있다.
    // 5. 개발은 무의미한 자유도를 제공하는 것 보다, 제약을 부여하는 것이 혼란을 줄이고, 유지보수하기 쉽다.
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    // Optional은 null을 반환받을 수 있는 래퍼 클래스(wrapper class)다.
    // Optional 객체를 사용하면 예상치 못한 NullPointerException 예외를 제공되는 메소드로 간단히 회피할 수 있다.
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // values()는 해당 map의 value들만 모아 collection 타입으로 리턴한다.
        // stream()는 iterator()와 같이 collection 프레임워크에서 collection 에 저장되어 있는 요소들을 읽어오는 방법 중 하나다.
        // filter()는 stream()에서 filter를 할 수 있는 함수다.
        // findAny()는 하나라도 찾으면 반환하는 함수다.
        return store.values().stream()
                .filter(member -> member.getName().equals(name)).findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

}
