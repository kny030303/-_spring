package belle.com.springstudy

import belle.com.springstudy.repository.MemberRepository
import belle.com.springstudy.repository.MemoryMemberRepository
import belle.com.springstudy.service.MemberService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SpringConfig {

    @Bean
    fun memberService(): MemberService {
        return MemberService(memberRepository())
    }

    @Bean
    fun memberRepository(): MemberRepository {
        return MemoryMemberRepository()
    }
}