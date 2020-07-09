package kz.aktobe.studentlife.config.services;

import kz.aktobe.studentlife.entity.Student;
import kz.aktobe.studentlife.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service("userDetailsService")
public class JdbcUserDetailsService implements UserDetailsService {

    private final StudentRepository studentRepository;

    public JdbcUserDetailsService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        log.info("loadUserByUsername => {}", student);

        return new User(student.getUsername(), student.getPassword(), new ArrayList<>(), student.getId(), student.getEmail());
    }
}
