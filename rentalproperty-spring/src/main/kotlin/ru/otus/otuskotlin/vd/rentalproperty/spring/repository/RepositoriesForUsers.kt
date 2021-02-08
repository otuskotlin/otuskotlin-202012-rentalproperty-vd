package ru.otus.otuskotlin.vd.rentalproperty.spring.repository

import org.springframework.data.repository.CrudRepository
import ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.Profile
import ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.User

interface UserRepository : CrudRepository<User, Long> {
  fun findByEmail(login: String): User?
}

interface ProfileRepository : CrudRepository<Profile, Long> {
}