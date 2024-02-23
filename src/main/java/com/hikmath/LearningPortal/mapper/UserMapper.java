package com.hikmath.LearningPortal.mapper;

import com.hikmath.LearningPortal.Dto.UserDTO;
import com.hikmath.LearningPortal.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDTO, User> {

}
