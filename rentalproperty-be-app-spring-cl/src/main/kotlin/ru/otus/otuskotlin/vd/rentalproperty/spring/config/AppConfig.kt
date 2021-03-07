package ru.otus.otuskotlin.vd.rentalproperty.spring.config

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.context.annotation.PropertySources
import ru.otus.otuskotlin.vd.rentalproperty.spring.config.properties.AppProperties

@Configuration
@ConfigurationPropertiesScan(basePackageClasses = [AppProperties::class])
@PropertySources(PropertySource("/config/application.properties"))
class AppConfig {
}