package belle.com.springstudy.repository

import belle.com.springstudy.domain.Member
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import java.sql.ResultSet
import javax.sql.DataSource

class JdbcTempleteMemberRepository(@Autowired val dataSource: DataSource): MemberRepository{
    val jdbcTemplate = JdbcTemplate(dataSource)
    override fun save(member: Member): Member {
        val jdbcInsert = SimpleJdbcInsert(jdbcTemplate)
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id")

        val parameters = HashMap<String, Any>();
        parameters.put("name", member.name)

        val key = jdbcInsert.executeAndReturnKey(MapSqlParameterSource(parameters))
        member.id = key.toLong()
        return member

    }

    override fun findById(id: Long): Member? {
        val result: List<Member> = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id)
        return  result.firstOrNull();
    }

    override fun findByName(name: String): Member? {
        val result: List<Member> = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name)
        return result.firstOrNull();
    }

    override fun findAll(): List<Member> {
        return jdbcTemplate.query("select * from member", memberRowMapper())
    }

    private fun memberRowMapper(): RowMapper<Member> {
        return RowMapper<Member> { rs: ResultSet, rowNum: Int ->
            val member = Member(rs.getString("name"))
            member.id = rs.getLong("id")
            member
        }
    }
}