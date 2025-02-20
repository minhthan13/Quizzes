package com.quizz.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.quizz.dtos.UserDTO;
import com.quizz.entities.Users;
import com.quizz.repositories.UserRepository;
import com.quizz.services.WebUserService;

@Service("webUserService")

public class WebUserServiceImpl implements WebUserService {

  private final UserRepository userRepository;

  private final ModelMapper modelMapper;

  private final BCryptPasswordEncoder passwordEncoder;

  public WebUserServiceImpl(UserRepository _userRepository, ModelMapper _modelMapper,
      BCryptPasswordEncoder _passwordEncoder) {
    super();
    this.userRepository = _userRepository;
    this.modelMapper = _modelMapper;
    this.passwordEncoder = _passwordEncoder;
  }

  @Override
  public Users findUserByUsernameOrEmail(String usernameOrEmail) {
    return userRepository.findByUsername(usernameOrEmail) != null ? userRepository.findByUsername(usernameOrEmail)
        : userRepository.findByEmail(usernameOrEmail);
  }

  @Override
  public UserDTO getCurrentUser() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null || !auth.isAuthenticated()) {
      return null;
    }
    Users user = userRepository.findByUsername(auth.getName());
    return modelMapper.map(user, UserDTO.class);
  }

  @Override
  public List<UserDTO> getAllUsers() {
    List<Users> users = (List<Users>) userRepository.findAll();
    if (users.isEmpty()) {
      System.out.println("No users found");
    }
    return modelMapper.map(users, new TypeToken<List<UserDTO>>() {
    }.getType());
  }

  @Override
  public UserDTO getUserById(Long userId) {
    Users user = userRepository.findById(userId.intValue()).get();
    if (user == null) {
      System.out.println("No users found");
    }
    return modelMapper.map(user, UserDTO.class);
  }

  @Override
  public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
    if (usernameOrEmail == null || usernameOrEmail.trim().isEmpty()) {
      throw new UsernameNotFoundException("Username/email cannot be empty");
    }

    Users user = findUserByUsernameOrEmail(usernameOrEmail.trim());
    if (user == null || !Boolean.TRUE.equals(user.getIsActive())) {
      System.out.println("Failed login attempt for username/email: " + usernameOrEmail);
      throw new UsernameNotFoundException("Invalid username/email or account is inactive");
    }

    Collection<GrantedAuthority> authorities = new ArrayList<>();
    String roleWithPrefix = user.getRole().startsWith("ROLE_") ? user.getRole() : "ROLE_" + user.getRole();
    authorities.add(new SimpleGrantedAuthority(roleWithPrefix));

    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
        authorities);

  }

}
