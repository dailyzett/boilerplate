package org.example.springjooq.config

import org.jooq.impl.DataSourceConnectionProvider
import org.jooq.impl.DefaultConfiguration
import org.jooq.impl.DefaultDSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy
import javax.sql.DataSource

@Configuration
class JooqConfig {

    @Autowired
    lateinit var dataSource: DataSource

    @Bean
    fun connectionProvider(): DataSourceConnectionProvider {
        return DataSourceConnectionProvider(TransactionAwareDataSourceProxy(dataSource))
    }

    @Bean
    fun dsl(): DefaultDSLContext {
        return DefaultDSLContext(configuration())
    }

    @Bean
    fun configuration(): DefaultConfiguration {
        val jooqConfig = DefaultConfiguration()
        jooqConfig.set(connectionProvider())
        return jooqConfig
    }
}