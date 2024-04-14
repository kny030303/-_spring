package belle.com.springstudy.controller

import belle.com.springstudy.service.MemberService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller

class MemberController(@Autowired val memberService: MemberService) {

}