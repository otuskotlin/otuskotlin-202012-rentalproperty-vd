package ru.otus.otuskotlin.vd.rentalproperty.spring.domain.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseAuditEntity<T> : BaseEntity<T>() {

  @CreatedDate
  @Column(updatable = false, nullable = false)
  lateinit var created: Instant

  @LastModifiedDate
  @Column(nullable = false)
  lateinit var modified: Instant
}