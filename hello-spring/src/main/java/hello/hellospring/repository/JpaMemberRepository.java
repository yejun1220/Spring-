package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    // JPA Entity를 이용할 때는 EntityManager(JPA는 매니저로 모든걸 동작함)를 이용한다.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member as m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // 테이블 대상으로 쿼리를 날리는 것이 아닌 객체(Entity)를 대상으로 쿼리를 날린다.(Sql로 변역 된다.)
        // as는 alias로 지칭 해주는 용어다.
        return em.createQuery("select m from Member as m", Member.class).getResultList();
        // JdbvTemplate에서는 id, name 등을 select 하고 RowMapper로 객체에 맵핑을 해줘야 했으나 JPA는 알아서 생략하여 실행해준다.
    }
}
