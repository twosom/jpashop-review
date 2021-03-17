package com.icloud.jpashopreview.controller;


import com.icloud.jpashopreview.controller.dto.MemberForm;
import com.icloud.jpashopreview.controller.dto.MemberListDto;
import com.icloud.jpashopreview.domain.Member;
import com.icloud.jpashopreview.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public String memberList(Model model) {
        List<Member> members = memberService.findMembers();

        List<MemberListDto> memberFormList = members.stream().map(member -> new MemberListDto(member))
                .collect(Collectors.toList());

        model.addAttribute("memberFormList", memberFormList);

        return "members/memberList";
    }


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
