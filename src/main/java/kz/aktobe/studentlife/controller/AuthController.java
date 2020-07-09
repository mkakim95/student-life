package kz.aktobe.studentlife.controller;

import kz.aktobe.studentlife.common.payload.request.LoginRequest;
import kz.aktobe.studentlife.common.payload.request.SignupRequest;
import kz.aktobe.studentlife.common.payload.response.JwtResponse;
import kz.aktobe.studentlife.common.payload.response.MessageResponse;
import kz.aktobe.studentlife.entity.Student;
import kz.aktobe.studentlife.repository.StudentRepository;
import kz.aktobe.studentlife.config.jwt.JwtUtils;
import kz.aktobe.studentlife.config.services.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final StudentRepository studentRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager,
                          StudentRepository studentRepository,
                          PasswordEncoder encoder,
                          JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.studentRepository = studentRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateJwtToken(authentication);

        User userDetails = (User) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(token,
                userDetails.getUserId(),
                userDetails.getUsername(),
                userDetails.getEmail()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignupRequest signUpRequest) {
        if (studentRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (studentRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        Student student = new Student();
        student.setUsername(signUpRequest.getUsername());
        student.setPassword(encoder.encode(signUpRequest.getPassword()));
        student.setEmail(signUpRequest.getEmail());

        studentRepository.save(student);
        log.info("Student successfully registered: {}", student);

        return ResponseEntity.ok(new MessageResponse("Student registered successfully!"));
    }
}
