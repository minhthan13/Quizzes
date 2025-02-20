package com.quizz.services;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.quizz.dtos.UserDTO;
import com.quizz.entities.Users;

public interface WebUserService extends UserDetailsService {
  UserDTO getCurrentUser();
  List<UserDTO> getAllUsers();
  UserDTO getUserById(Long userId);
  Users findUserByUsernameOrEmail(String usernameOrEmail);

}
