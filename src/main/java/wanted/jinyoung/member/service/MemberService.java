package wanted.jinyoung.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import wanted.jinyoung.auth.utills.CustomAuthorityUtils;
import wanted.jinyoung.exception.BusinessLogicException;
import wanted.jinyoung.exception.ExceptionCode;
import wanted.jinyoung.member.entity.Member;
import wanted.jinyoung.member.repository.MemberRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils authorityUtils;
    public void createMember(Member member){
        verifyExistsEmail(member.getEmail());

        List<String> roles = authorityUtils.createRoles(member.getEmail());
        member.updateRoles(roles);

        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        member.updatePassword(encryptedPassword);

        memberRepository.save(member);
    }

    private void verifyExistsEmail(String email){
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        if(optionalMember.isPresent()) throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    }
}
