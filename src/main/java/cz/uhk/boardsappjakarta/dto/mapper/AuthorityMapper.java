package cz.uhk.boardsappjakarta.dto.mapper;

import cz.uhk.boardsappjakarta.dto.AuthorityDTO;
import cz.uhk.boardsappjakarta.persistence.entity.Authority;
import org.mapstruct.Mapper;

@Mapper
public interface AuthorityMapper {
    AuthorityDTO authorityToAuthorityDTO(Authority authority);
    Authority authorityDTOToAuthority(AuthorityDTO authorityDTO);
}
