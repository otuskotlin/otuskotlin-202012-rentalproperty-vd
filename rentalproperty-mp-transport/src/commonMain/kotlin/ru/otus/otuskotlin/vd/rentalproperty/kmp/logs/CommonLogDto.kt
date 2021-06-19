package ru.otus.otuskotlin.vd.rentalproperty.kmp.logs

import ru.otus.otuskotlin.vd.rentalproperty.kmp.transport.models.common.ErrorDto

/**
 *  Общая модель лога для всех микросервисов системы
 */
data class CommonLogDto(
    val messageId: String? = null,
    val messageTime: String? = null,
    val logId: String? = null,
    val source: String? = null,
    val rentalproperty: LogDto? = null,
    // поля для других сервисов
    val errors: List<ErrorDto>? = null,
)
