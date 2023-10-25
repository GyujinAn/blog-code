package com.example.memberserver.member;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @GetMapping("/members/{memberId}")
    public MemberDto find(@PathVariable String memberId) {
        return memberQueryService.find(Long.parseLong(memberId));
    }

    @PostMapping("/members")
    public String create(@RequestBody MemberDto memberDto) {
        return memberCommandService.create(memberDto).toString();
    }

    @PutMapping("/members/{memberId}")
    public String update(@PathVariable String memberId, @RequestBody MemberDto memberDto) {
        return memberCommandService.update(Long.parseLong(memberId), memberDto).toString();
    }

    @DeleteMapping("/members/{memberId}")
    public void delete(@PathVariable String memberId) {
        memberCommandService.delete(Long.parseLong(memberId));
    }
}
