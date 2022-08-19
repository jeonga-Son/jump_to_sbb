package com.ll.exam.sbb.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
//implements UserDetailsService를 꼭 해줘야한다.
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    // 시큐리티가 특정 회원의 username을 받았을 때
    // 그 username에 해당하는 회원정보를 얻는 수단
    // loadUserByUsername 꼭 써야한다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SiteUser siteUser = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("사용자를 찾을수 없습니다.")
        );

        // 권한들을 담을 빈 리스트를 만든다.
        // List로 권한을 만든 이유는 권한이 1개가 아닐 수 있기 때문이다.
        // 예를들어서 일반권한 뿐만아니라 관리자 권한도 추가할 수있다.
        //  if ("admin".equals(username)) {
        //            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        // 이렇게 뿐만아니라
        // if ("admin".equals(username)) {
        //            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        //            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));

        //이렇게도 가능하다. 권한을 하나씩 주더라도 List로 한다.

        List<GrantedAuthority> authorities = new ArrayList<>();

        if ("admin".equals(username)) {
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue())); //UserRole.ADMIN.getValue()) 대신 ROLE_ADMIN 써도 되나 오타 있을 수 있으므로 이렇게 씀.
        } else {
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
        }

        return new User(siteUser.getUsername(), siteUser.getPassword(), authorities);
    }
}
