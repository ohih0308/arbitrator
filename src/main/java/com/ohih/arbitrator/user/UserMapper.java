package com.ohih.arbitrator.user;

import com.ohih.arbitrator.security.UserDetailsImpl;
import com.ohih.arbitrator.user.dto.RegisterDto;

@org.apache.ibatis.annotations.Mapper
public interface UserMapper {
    UserDetailsImpl getUserByEmail(String email);

    int isEmailDuplicate(String email);

    void register(RegisterDto registerDto);
}
