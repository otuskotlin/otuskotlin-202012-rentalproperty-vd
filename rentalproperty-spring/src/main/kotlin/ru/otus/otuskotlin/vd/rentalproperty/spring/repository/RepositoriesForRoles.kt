package ru.otus.otuskotlin.vd.rentalproperty.spring.repository

import org.springframework.data.repository.CrudRepository
import ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.Privilege
import ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.Role

interface RoleRepository : CrudRepository<Role, Long> {
  fun findByName(name: String): Role?
}

interface PrivilegeRepository : CrudRepository<Privilege, Long> {
  fun findByName(name: String): Privilege?
}
