package ru.otus.otuskotlin.vd.rentalproperty.spring.controller

import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import ru.otus.otuskotlin.vd.rentalproperty.spring.service.UserService

@RestController
@RequestMapping("/users")
class UserRestController(private val userService: UserService) {

  @GetMapping
  fun findAll() = userService.getAll()

  @GetMapping("/{email}")
  fun findOne(@PathVariable email: String) = userService.getByEmail(email)
    ?: throw ResponseStatusException(NOT_FOUND, "This user does not exist")
}
