package belle.com.springstudy.repository

import belle.com.springstudy.domain.Member
import jakarta.persistence.EntityManager

class JpaMemberRepository(val em: EntityManager) : MemberRepository {

    override fun save(member: Member): Member {
        em.persist(member)
        return member
    }

    override fun findById(id: Long): Member? {
        val result = em.createQuery("select m from Member m where m.id = :id", Member::class.java)
            .setParameter("id", id)
            .resultList

        return result.firstOrNull()
    }

    override fun findByName(name: String): Member? {
        val result = em.createQuery("select m from Member m where m.name = :name", Member::class.java)
            .setParameter("name", name)
            .resultList

        return result.firstOrNull()
    }

    override fun findAll(): List<Member> {
        return em.createQuery("select m from Member m", Member::class.java).resultList
    }

}