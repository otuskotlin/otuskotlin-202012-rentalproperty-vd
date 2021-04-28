package ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.common

import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.type.DataTypes
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.common.dto.DirectoryCassandraDto
import ru.otus.otuskotlin.vd.rentalproperty.be.repository.cassandra.common.dto.MediaFileCassandraDto
import java.io.Closeable
import java.net.InetAddress
import java.net.InetSocketAddress
import kotlin.coroutines.CoroutineContext

abstract class RepositoryCassandra(
  protected open val keyspaceName: String,
  protected open val hosts: String = "",
  protected open val port: Int = 9042,
  protected open val user: String = "cassandra",
  protected open val pass: String = "cassandra",
  protected open val replicationFactor: Int = 1,
  protected open val testing: Boolean = false,
) : CoroutineScope, Closeable {
  private val job = Job()

  /**
   * Создание сессии, кейспейса, регистрация типов и создание таблицы
   */
  protected val session by lazy {
    val builder = CqlSession.builder()
      .addContactPoints(parseAddresses(hosts, port))
      .withLocalDatacenter("datacenter1")
      .withAuthCredentials(user, pass)
    builder.build().apply {
      if (testing)
        createKeyspace() // создание кейспейса
    }
    builder.withKeyspace(keyspaceName).build().apply {
      if (testing) {
        //createKeyspace()
        createTypeProducer() // регистрация udt
        createTables()
        createIndexes()
      }
    }
  }

  override val coroutineContext: CoroutineContext
    get() = Dispatchers.Main + job

  override fun close() {
    job.cancel()
  }

  protected open fun CqlSession.createKeyspace() =
    execute(
      SchemaBuilder.createKeyspace(keyspaceName)
        .ifNotExists()
        .withSimpleStrategy(replicationFactor)
        .build()
    )

  protected open fun CqlSession.createTypeProducer() {
    execute(
      SchemaBuilder.createType(DirectoryCassandraDto.TYPE_NAME)
        .ifNotExists()
        .withField(DirectoryCassandraDto.ID, DataTypes.TEXT)
        .withField(DirectoryCassandraDto.NAME, DataTypes.TEXT)
        .withField(DirectoryCassandraDto.TYPE, DataTypes.TEXT)
        .build()
    )
    execute(
      SchemaBuilder.createType(MediaFileCassandraDto.TYPE_NAME)
        .ifNotExists()
        .withField(MediaFileCassandraDto.ID, DataTypes.TEXT)
        .withField(MediaFileCassandraDto.TITLE, DataTypes.TEXT)
        .withField(MediaFileCassandraDto.URL, DataTypes.TEXT)
        .withField(MediaFileCassandraDto.FILENAME, DataTypes.DOUBLE)
        .build()
    )
  }

  private fun parseAddresses(hosts: String, port: Int): Collection<InetSocketAddress> = hosts
    .split(Regex("""\s*,\s*"""))
    .map { InetSocketAddress(InetAddress.getByName(it), port) }

  abstract fun CqlSession.createTables()

  abstract fun CqlSession.createIndexes()

  abstract fun init(): RepositoryCassandra
}
