package com.codestates.pre_project.global.auth.userdetails;

import com.codestates.pre_project.global.auth.utils.CustomAuthorityUtils;
import com.codestates.pre_project.module.member.entity.Member;
import com.codestates.pre_project.module.member.exception.MemberNotFoundException;
import com.codestates.pre_project.module.member.repository.MemberRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final CustomAuthorityUtils authorityUtils;

    public MemberDetailsService(MemberRepository memberRepository, CustomAuthorityUtils authorityUtils) {
        this.memberRepository = memberRepository;
        this.authorityUtils = authorityUtils;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> optionalMember = memberRepository.findByEmail(username);
        Member findMember = optionalMember.orElseThrow(() -> new MemberNotFoundException());


        return new MemberDetails(findMember);
    }

   private final class MemberDetails extends Member implements UserDetails {
        MemberDetails(Member member) {
            super(
                    member.getId(),
                    member.getEmail(),
                    member.getPassword(),
                    member.getRoles()
            );
        }

       @Override
       public Collection<? extends GrantedAuthority> getAuthorities() {
           return authorityUtils.createAuthorities(this.getRoles());
       }

       @Override
       public String getUsername() {
           return getEmail();
       }

       @Override
       public boolean isAccountNonExpired() {
           return true;
       }

       @Override
       public boolean isAccountNonLocked() {
           return true;
       }

       @Override
       public boolean isCredentialsNonExpired() {
           return true;
       }

       @Override
       public boolean isEnabled() {
           return true;
       }
   }


}
