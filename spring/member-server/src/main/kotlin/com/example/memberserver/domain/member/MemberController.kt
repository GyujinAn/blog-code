package com.example.memberserver.domain.member

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
    private val memberCommandService: MemberCommandService,
    private val memberQueryService: MemberQueryService,
) {

    @GetMapping("/members/{memberId}")
    fun find(@PathVariable memberId: String): MemberDto {
        return memberQueryService.find(memberId.toLong())
    }

    @PostMapping("/members")
    fun create(@RequestBody memberDto: MemberDto): String {
        return memberCommandService.create(memberDto).toString()
    }

    @PutMapping("/members/{memberId}")
    fun update(@PathVariable memberId: String, @RequestBody memberDto: MemberDto): String {
        return memberCommandService.update(memberId.toLong(), memberDto).toString()
    }

    @DeleteMapping("/members/{memberId}")
    fun delete(@PathVariable memberId: String) {
        memberCommandService.delete(memberId.toLong())
    }

}
