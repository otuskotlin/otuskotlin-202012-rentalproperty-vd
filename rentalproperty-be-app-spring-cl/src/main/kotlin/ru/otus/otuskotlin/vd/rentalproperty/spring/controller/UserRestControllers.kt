package ru.otus.otuskotlin.vd.rentalproperty.spring.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.otus.otuskotlin.vd.rentalproperty.spring.service.UserService

@RestController
@RequestMapping("/users")
class UserRestController(private val userService: UserService) {

  @GetMapping
  fun findAll() = userService.getAll()

  @GetMapping("/{email}")
  fun findOne(@PathVariable email: String) = userService.getByEmail(email)
}
