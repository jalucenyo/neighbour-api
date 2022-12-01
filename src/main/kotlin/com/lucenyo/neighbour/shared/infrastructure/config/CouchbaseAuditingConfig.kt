package com.lucenyo.neighbour.shared.infrastructure.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.couchbase.repository.auditing.EnableCouchbaseAuditing


@Configuration
@EnableCouchbaseAuditing
class CouchbaseAuditingConfig {

}
