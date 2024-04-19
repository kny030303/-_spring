package belle.com.springstudy.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Member(){

    var name: String = ""
    constructor(name: String) : this() {
        this.name = name
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
}