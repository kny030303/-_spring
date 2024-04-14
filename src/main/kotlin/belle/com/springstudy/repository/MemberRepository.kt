package belle.com.springstudy.repository

import belle.com.springstudy.domain.Member

interface MemberRepository {
    fun save(member: Member): Member;
    fun findById(id: Long): Member?;
    fun findByName(name: String): Member?;
    fun findAll(): List<Member>;
}