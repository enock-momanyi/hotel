package com.management.hotel.service;

import com.management.hotel.properties.UnifiedPropertiesService;
import com.management.hotel.util.HotelUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class HotelUserDetailsService implements UserDetailsService {
    @Autowired
    private UnifiedPropertiesService unifiedPropertiesService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(!username.equals(unifiedPropertiesService.getApiUsername())){
            throw new UsernameNotFoundException("Username not found.");
        }
        return new HotelUserDetails(unifiedPropertiesService);
    }
}
