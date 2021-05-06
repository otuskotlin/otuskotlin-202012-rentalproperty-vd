package ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra

import org.testcontainers.containers.GenericContainer

class CassandraContainer : GenericContainer<CassandraContainer>("cassandra")

