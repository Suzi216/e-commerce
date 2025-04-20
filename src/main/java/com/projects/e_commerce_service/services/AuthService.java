//package com.projects.e_commerce_service.services;
//
//import com.projects.e_commerce_service.dtos.AuthenticationRequestDto;
//import com.projects.e_commerce_service.dtos.AuthenticationResponseDto;
//import com.projects.e_commerce_service.dtos.UserRegistrationRequestDto;
//import com.projects.e_commerce_service.entities.RoleEnum;
//import com.projects.e_commerce_service.entities.User;
//import com.projects.e_commerce_service.exceptions.InvalidPayloadException;
//import com.projects.e_commerce_service.exceptions.TokenGenerationException;
//import com.projects.e_commerce_service.repositories.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestBody;
//
//@Service
//@RequiredArgsConstructor
//public class AuthService {
//
//    private final UserRepository userRepository;
//    private final JwtTokenProvider jwtTokenProvider;
//    private final PasswordEncoder passwordEncoder;
//    public AuthenticationResponseDto loginUser(@RequestBody AuthenticationRequestDto payload) throws InvalidPayloadException, TokenGenerationException {//        boolean isSamePassword = checkUserPassword(payload.getPassword(), user.getPassword());
//
//        User user = userRepository.findByUsername(payload.getUsername()).orElseThrow(() -> new InvalidPayloadException("User not found"));
//
//        boolean isSamePassword = checkUserPassword(payload.getPassword(), user.getPassword());
//        if (!isSamePassword) {
//            throw new InvalidPayloadException("Wrong password.");
//        }
//        AuthenticationResponseDto authResponseDto = new ModelMapper().map(user, AuthenticationResponseDto.class);
//        try {
//            String accessToken = jwtTokenProvider.generateToken(user);
//            authResponseDto.setJwt(accessToken);
//        } catch (Exception e) {
//            throw new TokenGenerationException();
//        }
//
//        return authResponseDto;
//    }
//
//    private boolean checkUserPassword(String plainPassword, String encodedPassword) {
////        return true;
//        return passwordEncoder.matches(plainPassword, encodedPassword);
//    }
//
//    public AuthenticationResponseDto registerUser(UserRegistrationRequestDto payload) throws InvalidPayloadException, TokenGenerationException {
//        if (existingUser(payload.getUsername())){
//            throw new InvalidPayloadException("User  found");
//        }
//        ModelMapper modelMapper = new ModelMapper();
//        User user = modelMapper.map(payload, User.class);
//        user.setRole(RoleEnum.USER);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//        AuthenticationResponseDto authResponseDto = new ModelMapper().map(user, AuthenticationResponseDto.class);
//
//        try {
//            String accessToken = jwtTokenProvider.generateToken(user);
//            authResponseDto.setJwt(accessToken);
//        } catch (Exception e) {
//            throw new TokenGenerationException();
//        }
//        return authResponseDto;
//    }
//
//    public boolean existingUser(String email) {
//        return userRepository.findByUsername(email).isPresent();
//    }
//
//    public AuthenticationResponseDto registerSuperAdmin(UserRegistrationRequestDto payload) throws InvalidPayloadException, TokenGenerationException {
//        if (existingUser(payload.getUsername())){
//            throw new InvalidPayloadException("User  found");
//        }
//        ModelMapper modelMapper = new ModelMapper();
//        User user = modelMapper.map(payload, User.class);
//        user.setRole(RoleEnum.SUPER_ADMIN);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//        AuthenticationResponseDto authResponseDto = new ModelMapper().map(user, AuthenticationResponseDto.class);
//
//        try {
//            String accessToken = jwtTokenProvider.generateToken(user);
//            authResponseDto.setJwt(accessToken);
//        } catch (Exception e) {
//            throw new TokenGenerationException();
//        }
//        return authResponseDto;
//    }
//}
