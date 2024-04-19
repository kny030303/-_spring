package belle.com.springstudy.repository

import belle.com.springstudy.domain.Member
import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataJpaMemberRepository: JpaRepository<Member, Long>, MemberRepository {

    override fun findByName(name: String): Member?
}