package ru.otus.otuskotlin.vd.rentalproperty.spring.controller

import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import ru.otus.otuskotlin.vd.rentalproperty.spring.repository.UserRepository

@RestController
@RequestMapping("/user")
class UserController(private val repository: UserRepository) {

  @GetMapping
  fun findAll() = repository.findAll()

  @GetMapping("/{email}")
  fun findOne(@PathVariable email: String) = repository.findByEmail(email)
    ?: throw ResponseStatusException(NOT_FOUND, "This user does not exist")
}
