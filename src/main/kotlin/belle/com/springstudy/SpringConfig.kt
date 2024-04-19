package belle.com.springstudy

import belle.com.springstudy.repository.*
import belle.com.springstudy.service.MemberService
import jakarta.persistence.EntityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class SpringConfig(
    @Autowired val datasource: DataSource,
    @Autowired val em: EntityManager,
    @Autowired val memberRepository: MemberRepository
) {

    @Bean
    fun memberService(): MemberService {
        return MemberService(memberRepository)
    }

    // @Bean
    // fun memberRepository(): MemberRepository {
        // return MemoryMemberRepository()
        // return JdbcMemberRepository(datasource)
        // return JdbcTempleteMemberRepository(datasource)
        // return JpaMemberRepository(em)
    // }
}