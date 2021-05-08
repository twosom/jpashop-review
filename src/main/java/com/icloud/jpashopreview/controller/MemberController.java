package com.icloud.jpashopreview.controller;


import com.icloud.jpashopreview.controller.dto.MemberForm;
import com.icloud.jpashopreview.controller.dto.MemberListDto;
import com.icloud.jpashopreview.domain.Member;
import com.icloud.jpashopreview.repository.MemberRepository;
import com.icloud.jpashopreview.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;


    @GetMapping
    public String list(@PageableDefault(size = 12, sort = "name", direction = Sort.Direction.DESC) Pageable pageable, Model model) {

        Page<Member> page = memberService.findMembers(pageable);
        Page<MemberListDto> map = page.map(MemberListDto::new);

        model.addAttribute("memberFormList", map.getContent());
        return "members/memberList";
    }






//    @GetMapping
//    public String memberList(Model model) {
//        List<Member> members = memberService.findMembers();
//
//        List<MemberListDto> memberFormList = members.stream().map(member -> new MemberListDto(member))
//                .collect(Collectors.toList());
//
//        model.addAttribute("memberFormList", memberFormList);
//
//        return "members/memberList";
//    }


    @GetMapping("/new")
    public String saveMemberForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/new")
    public String saveMember(MemberForm memberForm) {
        Member member = memberForm.toEntity();
        memberService.join(member);

        return "redirect:/members";
    }
}
