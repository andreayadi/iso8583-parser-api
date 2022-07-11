package com.personal.utility.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.personal.utility.model.utility"}, mongoTemplateRef = NewDb1Config.MONGO_TEMPLATE)
public class NewDb1Config {
    protected static final String MONGO_TEMPLATE = "newDb1MongoTemplate";
}
