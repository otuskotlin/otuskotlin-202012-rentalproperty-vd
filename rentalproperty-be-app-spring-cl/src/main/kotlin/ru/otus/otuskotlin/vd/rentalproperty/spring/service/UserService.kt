package ru.otus.otuskotlin.vd.rentalproperty.spring.service

import org.springframework.stereotype.Service
import ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.person.User

@Service
interface UserService {

  fun getAll(): List<User>

  fun getByEmail(email: String): User
}