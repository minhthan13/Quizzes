package com.quizz.configurations;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.AbstractConverter;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.quizz.dtos.*;
import com.quizz.entities.*;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
            .setMatchingStrategy(MatchingStrategies.STRICT)
            .setSkipNullEnabled(true)
            .setFieldMatchingEnabled(true)
            .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        // Thêm converter cho Date
        AbstractConverter<Date, String> dateToString = new AbstractConverter<Date, String>() {
            @Override
            protected String convert(Date source) {
                if (source == null) return null;
                return new SimpleDateFormat("dd/MM/yyyy").format(source);
            }
        };

        AbstractConverter<String, Date> stringToDate = new AbstractConverter<String, Date>() {
            @Override
            protected Date convert(String source) {
                try {
                    if (source == null) return null;
                    return new SimpleDateFormat("dd/MM/yyyy").parse(source);
                } catch (Exception e) {
                    return null;
                }
            }
        };

        mapper.addConverter(dateToString);
        mapper.addConverter(stringToDate);

        configureUserMapping(mapper);

        return mapper;
    }

    private void configureUserMapping(ModelMapper mapper) {
        // Users -> UserDTO
        mapper.createTypeMap(Users.class, UserDTO.class).addMapping(Users::getUserId, UserDTO::setUserId)
            .addMapping(Users::getUsername, UserDTO::setUsername).addMapping(Users::getEmail, UserDTO::setEmail)
            .addMapping(Users::getRole, UserDTO::setRole)
            .addMapping(Users::getProfileImage, UserDTO::setProfileImage)
            .addMapping(Users::getIsActive, UserDTO::setIsActive)
            .addMapping(Users::getCreatedAt, UserDTO::setCreatedAt)
            // Không map password khi chuyển từ entity sang DTO
            .addMappings(mapping -> mapping.skip(UserDTO::setPassword));

        // UserDTO -> Users
        mapper.createTypeMap(UserDTO.class, Users.class).addMapping(UserDTO::getUserId, Users::setUserId)
            .addMapping(UserDTO::getUsername, Users::setUsername).addMapping(UserDTO::getEmail, Users::setEmail)
            .addMapping(UserDTO::getRole, Users::setRole)
            .addMapping(UserDTO::getProfileImage, Users::setProfileImage)
            .addMapping(UserDTO::getIsActive, Users::setIsActive)
            .addMapping(UserDTO::getCreatedAt, Users::setCreatedAt)
            // Password sẽ được mã hóa riêng trong service layer
            .addMappings(mapping -> mapping.skip(Users::setPassword));
    }


  }
