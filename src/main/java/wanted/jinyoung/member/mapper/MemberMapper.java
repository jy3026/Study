package wanted.jinyoung.member.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import wanted.jinyoung.member.dto.MemberDto;
import wanted.jinyoung.member.entity.Member;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {
    default Member memberPostToMember(MemberDto.Post requestBody){
        if ( requestBody == null ) {
            return null;
        }
        Member member = new Member();
        member.updateEmail(requestBody.getEmail());
        member.updatePassword(requestBody.getPassword());

        return member;
    }
}
