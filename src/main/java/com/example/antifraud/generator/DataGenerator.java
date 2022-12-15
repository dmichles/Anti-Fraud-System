package com.example.antifraud.generator;

import com.example.antifraud.models.entities.Authority;
import com.example.antifraud.repository.AuthorityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DataGenerator {

    @Bean
    public CommandLineRunner loadData(AuthorityRepository authorityRepository) {
        return args -> {
            if (authorityRepository.count() > 1){
                return;
            }

            authorityRepository.save(new Authority("ADMINISTRATOR"));
            authorityRepository.save(new Authority("MERCHANT"));
            authorityRepository.save(new Authority("SUPPORT"));
        };
    }
}
