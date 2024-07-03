package com.polarbookshop.catalogservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

@Configuration
@EnableJdbcAuditing //In Spring Data JPA we would use @EnableJpaAuditing and entity class should be annotated with
                    //@EntityListener(AuditingEntityListener.class)
public class DataConfig {
}
