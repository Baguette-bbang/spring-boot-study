package hello.hellspring.domain.repository;

import hello.hellspring.domain.Member;

import java.util.*;
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // 키 값을 생성하기 위함. 0,1,2,3....

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // 아이디 설정
        store.put(member.getId(), member); // 스토어(내 상점)에 저장한다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // 널 처리
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        // 실무에서는 리스트를 많이 사용 조회하기도 편함.
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

}
