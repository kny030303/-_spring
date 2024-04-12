package belle.com.springstudy.repository

import belle.com.springstudy.domain.Member
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import repository.MemoryMemberRepository

class MemoryMemberRepositoryTest {
    val repository = MemoryMemberRepository()

    @AfterEach
    fun afterEach(){
        repository.clearStore()
    }
    @Test
    fun save(){
        val member = Member("spring")
        repository.save(member)
        val result = repository.findById(member.id)

        Assertions.assertThat(member).isEqualTo(result)
     }

    @Test
    fun findByName(){
        val member1 = Member("spring1")
        repository.save(member1)

        val member2 = Member("spring2")
        repository.save(member2)

        val result = repository.findByName("spring1")
        Assertions.assertThat(member1).isEqualTo(result)
    }

    @Test
    fun findAll() {
        val member1 = Member("spring1")
        repository.save(member1)

        val member2 = Member("spring1")
        repository.save(member2)

        var result = repository.findAll()
        Assertions.assertThat(result.size).isEqualTo(2)
    }
}