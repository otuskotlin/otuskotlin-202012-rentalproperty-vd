package ru.otus.otuskotlin.vd.rentalproperty.spring.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.Privilege
import ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.Role

interface RoleRepository : JpaRepository<Role, Long> {
  fun findByName(name: String): Role?
}

interface PrivilegeRepository : JpaRepository<Privilege, Long> {
  fun findByName(name: String): Privilege?
}
