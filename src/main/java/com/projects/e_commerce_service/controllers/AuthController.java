//package com.projects.e_commerce_service.controllers;
//
//import com.projects.e_commerce_service.dtos.AuthenticationRequestDto;
//import com.projects.e_commerce_service.dtos.AuthenticationResponseDto;
//import com.projects.e_commerce_service.dtos.UserRegistrationRequestDto;
//import com.projects.e_commerce_service.exceptions.InvalidPayloadException;
//import com.projects.e_commerce_service.exceptions.TokenGenerationException;
//import com.projects.e_commerce_service.services.AuthService;
//import com.projects.e_commerce_service.services.JwtTokenProvider;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/authenticate")
//@CrossOrigin(origins = {"http://localhost:8080"})
//@RequiredArgsConstructor
//public class AuthController {
//
//    private final JwtTokenProvider jwtTokenProvider;
//    private final AuthService authService;
//
//
//    @PostMapping()
//    public ResponseEntity<AuthenticationResponseDto> authenticateUser(@RequestBody AuthenticationRequestDto request) throws InvalidPayloadException, TokenGenerationException {
//
//        AuthenticationResponseDto token= authService.loginUser(request);
//
//        return ResponseEntity.ok((token));
//    }
//    @PostMapping(value = "/register-user")
//    public ResponseEntity<AuthenticationResponseDto> registerUser(@RequestBody UserRegistrationRequestDto request ) throws InvalidPayloadException, TokenGenerationException {
//        AuthenticationResponseDto token= authService.registerUser(request);
//
//        return ResponseEntity.ok((token));
//
//    }
//    @PostMapping(value = "/register-super-admin")
//    public ResponseEntity<AuthenticationResponseDto> registerSuperAdmin(@RequestBody UserRegistrationRequestDto request ) throws InvalidPayloadException, TokenGenerationException {
//        AuthenticationResponseDto token= authService.registerSuperAdmin(request);
//
//        return ResponseEntity.ok((token));
//
//    }
//
//}
//
