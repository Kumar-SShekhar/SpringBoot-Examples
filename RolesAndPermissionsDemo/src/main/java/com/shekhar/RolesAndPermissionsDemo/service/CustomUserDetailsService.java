//package com.shekhar.RolesAndPermissionsDemo.service;
//
//import com.shekhar.RolesAndPermissionsDemo.model.Role;
//import com.shekhar.RolesAndPermissionsDemo.model.User;
//import com.shekhar.RolesAndPermissionsDemo.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class CustomUserDetailsService implements UserDetailsService {
//
//
//    private final UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(email).orElse(null);
//        if (user==null){
//            throw new UsernameNotFoundException("User not found with: " + email);
//        }
//
//        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRole()));
//    }
//
//    private Collection<GrantedAuthority> mapRolesToAuthorities(Role role) {
//        return List.of(new SimpleGrantedAuthority(role.name()));
////        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//    }
//
////    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
////        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
////    }
//}
