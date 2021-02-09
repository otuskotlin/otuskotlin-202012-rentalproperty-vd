package ru.otus.otuskotlin.vd.rentalproperty.spring.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.Profile
import ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.User

interface UserRepository : JpaRepository<User, Long> {
  fun findByEmail(login: String): User?
}

interface ProfileRepository : JpaRepository<Profile, Long> {
}