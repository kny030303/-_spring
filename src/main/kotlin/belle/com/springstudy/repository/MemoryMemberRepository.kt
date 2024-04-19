package belle.com.springstudy.repository

import belle.com.springstudy.domain.Member
import org.springframework.stereotype.Repository

class MemoryMemberRepository: MemberRepository {
    companion object {
        var store = mutableMapOf<Long, Member>()
        var sequence: Long = 0L
    }
    override fun save(member: Member): Member {
        member.id = ++sequence
        store.put(member.id, member)
        return member
    }

    override fun findById(id: Long): Member? {
        return store.get(id)
    }

    override fun findByName(name: String): Member? {
        return store.values.find { it.name == name }
    }

    override fun findAll(): List<Member> {
        return store.values.toList()
    }

    fun clearStore() {
        store.clear()
    }
}