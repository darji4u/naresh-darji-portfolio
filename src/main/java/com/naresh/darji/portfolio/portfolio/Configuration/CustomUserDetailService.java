package com.naresh.darji.portfolio.portfolio.Configuration;


import com.naresh.darji.portfolio.portfolio.Models.CustomResponse;
import com.naresh.darji.portfolio.portfolio.Models.UserModel;
import com.naresh.darji.portfolio.portfolio.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        if (token.equals("naresh-server-admin")) {
            return User.withUsername("naresh-server-admin")
                    .password(new BCryptPasswordEncoder().encode("Tiqz@TTT#89555")) // {noop} indicates plain text password (for demo purposes)
                    .roles("ADMIN")
                    .build();
        }else{
            CustomResponse<UserModel> userData = userService.getUserByUsername(token.substring(7));
            return new CustomUserDetail(userData.getData().getUserEmail(),"","NORMAL");
        }
    }
}
