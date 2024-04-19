package belle.com.springstudy

import belle.com.springstudy.repository.JdbcMemberRepository
import belle.com.springstudy.repository.MemberRepository
import belle.com.springstudy.repository.MemoryMemberRepository
import belle.com.springstudy.service.MemberService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class SpringConfig(@Autowired val datasource: DataSource) {

    @Bean
    fun memberService(): MemberService {
        return MemberService(memberRepository())
    }

    @Bean
    fun memberRepository(): MemberRepository {
        // return MemoryMemberRepository()
        return JdbcMemberRepository(datasource)
    }
}