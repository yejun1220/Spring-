package hello.hellospring.controller;
import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
// @Controller가 있으면 Spring Container에 해당 class를 넣어두고 생성한(생성자 호출) 뒤 Controller가 관리한다. 이를 스프링 빈 등록이라고 한다.
// @Component(@Controller, @Service, @Repository)가 있으면 Spring이 Container에 해당 클래스의 객체를 하나씩 생성한다.
public class MemberController {
    // private final MemberService memberService = new MemberService();
    // Service는 하나만 있어도 되므로 new로 생성하는건 비효율적이다.

    private final MemberService memberService;

    @Autowired
    // @Autowired가 있으면 Spring이 Container 안에 있는 memberService 객체를 memberService 매개변수에 연결시켜준다.
    // memberService 클래스에 @Service 등을 붙여줘야 Spring이 Container에 해당 클래스를 넣어줄 수 있다.
    // @Autowired를 통해 MemberController에 MemberService를 의존관계 주입해준다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String CrateForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
