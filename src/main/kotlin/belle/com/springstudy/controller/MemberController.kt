package belle.com.springstudy.controller

import belle.com.springstudy.domain.Member
import belle.com.springstudy.service.MemberService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class MemberController(@Autowired val memberService: MemberService) {
    @GetMapping("/members/new")
    fun createFrom(): String{
        return "members/createMemberForm"
    }
    @GetMapping("/members")
    fun list(model: Model): String {
        val members = memberService.findMembers()
        model.addAttribute("members", members)

        return "members/memberlist"
    }

    @PostMapping("/members/new")
    fun create(form: MemberForm): String {
        val name = form.name
        val member = Member(name)

        memberService.join(member)
        return "redirect:/"
    }
}