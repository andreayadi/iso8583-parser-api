package com.personal.utility.config;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;

@Configuration
public class MultipleMongoConfig {
    
    @Primary
    @Bean(name = "newdb1Properties")
    @ConfigurationProperties(prefix = "spring.data.mongodb.newdb1")
    public MongoProperties getNewDb1Props() throws Exception{
        return new MongoProperties();
    }

    @Bean(name = "newdb2Properties")
    @ConfigurationProperties(prefix = "spring.data.mongodb.newdb2")
    public MongoProperties getNewDb2Props() throws Exception{
        return new MongoProperties();
    }

    @Primary
    @Bean(name = "newDb1MongoTemplate")
    public MongoTemplate newDb1MongoTemplate() throws Exception{
        return new MongoTemplate(newdb1MongoDatabaseFactory(getNewDb1Props()));
    }

    @Bean(name = "newDb2MongoTemplate")
    public MongoTemplate newDb2MongoTemplate() throws Exception{
        return new MongoTemplate(newdb2MongoDatabaseFactory(getNewDb2Props()));
    }

    @Primary
    @Bean
    public MongoDbFactory newdb1MongoDatabaseFactory(MongoProperties mongo) throws Exception{
        return new SimpleMongoClientDbFactory(mongo.getUri());
    }

    @Bean
    public MongoDbFactory newdb2MongoDatabaseFactory(MongoProperties mongo) throws Exception{
        return new SimpleMongoClientDbFactory(mongo.getUri());
    }

    
}
