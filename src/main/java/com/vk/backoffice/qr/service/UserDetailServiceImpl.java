package com.vk.backoffice.qr.service;

import com.vk.backoffice.qr.entity.User;
import com.vk.backoffice.qr.entity.UserRole;
import com.vk.backoffice.qr.repository.UserRepository;
import com.vk.backoffice.qr.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    @Transactional()
    public UserDetails loadUserByUsername(String mobileNumber) throws UsernameNotFoundException {
        User user = userRepository.findByUserMobileNumber(mobileNumber);
        if (user == null) throw new UsernameNotFoundException(mobileNumber);

        List<String> roleNames = userRoleRepository.getRolesByUserId(mobileNumber);
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if(roleNames!=null){
            for(String role:roleNames){
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }
        UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(user.getMobileNumber(),user.getPassword(),grantList);
        return userDetails;
    }
}
