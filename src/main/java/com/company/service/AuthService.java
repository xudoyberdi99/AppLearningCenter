package com.company.service;

import com.company.entity.User;
import com.company.exp.ResourseNotFoundException;
import com.company.payload.ApiResponse;
import com.company.payload.RegisterDto;
import com.company.repository.RoleRepository;
import com.company.repository.UserRepository;
import com.company.security.JwtProvider;
import com.company.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    JavaMailSender javaMailSender;

    public ApiResponse registerUser(RegisterDto registerDto) {
        if (!registerDto.getPassword().equals(registerDto.getPrePassword()))
            return new ApiResponse("parollar bir biriga mos emas",false);
        if (userRepository.existsByEmail(registerDto.getEmail()))
            return new ApiResponse("bunday user avval ruyxatdan utgan",false);
        User user=new User(
                registerDto.getFullName(),
                registerDto.getEmail(),
                registerDto.getUsername(),
                passwordEncoder.encode(registerDto.getPassword()),
                roleRepository.findByName(AppConstants.USER).orElseThrow(()->new ResourseNotFoundException("role","name",AppConstants.USER))
        );
        int code = new Random().nextInt(999999);
        user.setEmailCode(String.valueOf(code).substring(0,4));
        userRepository.save(user);
        sendEmail(registerDto.getEmail(),user.getEmailCode());
        return new ApiResponse("Muvaffaqiyatli ruyxatdan utdingiz",true);
    }

    public Boolean sendEmail(String sendingEmail,String emailCode){
        try {
            SimpleMailMessage mailMessage=new SimpleMailMessage();
            mailMessage.setFrom("from@gmail.com");
            mailMessage.setTo(sendingEmail);
            mailMessage.setSubject("Accountni tasdiqlang");
            mailMessage.setText(emailCode);
            javaMailSender.send(mailMessage);
            return true;
        }catch (Exception e){
            return false;
        }
    }
//    public ApiResponse login(LoginDto loginDto) {
//        try {
//            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
//            User user=(User) authenticate.getPrincipal();
//            String token = jwtProvider.generateToken(loginDto.getUsername(), user.getLavozim());
//            return new ApiResponse("succes",true);
//        }catch (BadCredentialsException badCredentialsException){
//            return new ApiResponse("Parol yoki login xato",false);
//        }
//    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> optionalUser = userRepository.findByUsername(username);
//        if (optionalUser.isPresent())
//            return optionalUser.get();
//
//         throw new UsernameNotFoundException(username+" topilmadi");
       return userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException(username+" topilmadi"));
    }

    public ApiResponse verifyEmail(String email, String emailCode) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            if (user.getEmailCode().equals(emailCode)){
                user.setEnabled(true);
                userRepository.save(user);
                return new ApiResponse("Account tasdiqlandi", true);
            }
            return new ApiResponse("kod xato",false);
        }
        return new ApiResponse("bunday user mavjud emas",false);
    }
}
