package ru.otus.otuskotlin.vd.rentalproperty.spring.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement
import ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity.person.User
import ru.otus.otuskotlin.vd.rentalproperty.spring.repository.UserRepository

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackageClasses = [UserRepository::class])
@EntityScan(basePackageClasses = [User::class])
@EnableTransactionManagement
class DbConfig {
}