package belle.com.springstudy.service

import belle.com.springstudy.domain.Member
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import repository.MemberRepository
import repository.MemoryMemberRepository

class MemberServiceTest {

    val memberRepository = MemoryMemberRepository()
    lateinit var memberService: MemberService


    @BeforeEach
    fun beforeEach() {
        memberService = MemberService(memberRepository)
    }
    @AfterEach
    fun afterEach() {
        memberRepository.clearStore()
    }

    @Test
    fun join() {
        //given
        val member = Member("spring")

        //when
        val saveId = memberService.join(member)

        //then
        val findMember = memberService.findOne(saveId)
        Assertions.assertThat(member.name).isEqualTo(findMember?.name)

    }

    @Test
    fun 중복_회원_예외() {
        //given
        val member1 = Member("spring")
        val member2 = Member("spring")

        //when
        memberService.join(member1)

        //then
        val e = assertThrows<IllegalStateException> { memberService.join(member2) }
        Assertions.assertThat(e.message).isEqualTo("이미 존재하는 회원입니다.")
    }
    @Test
    fun findMembers() {
        //given
        val member1 = Member("spring1")
        val member2 = Member("spring2")

        memberService.join(member1)
        memberService.join(member2)

        //when
        val result = memberService.findMembers()

        //then
        Assertions.assertThat(result.size).isEqualTo(2)
    }
}