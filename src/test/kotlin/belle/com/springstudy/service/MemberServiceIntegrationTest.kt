package belle.com.springstudy.service

import belle.com.springstudy.domain.Member
import belle.com.springstudy.repository.MemberRepository
import belle.com.springstudy.repository.MemoryMemberRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest @Autowired constructor(
    private val memberService: MemberService,
    private val memberRepository: MemberRepository
) {
    @Test
    fun 회원가입(){
        //given
        val member = Member("hello")

        //when
        val saveId = memberService.join(member)

        //then
        val findMember = memberRepository.findById(saveId)

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
}