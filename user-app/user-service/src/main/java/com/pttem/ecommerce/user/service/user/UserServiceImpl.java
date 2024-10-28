/* SmartICT Bilisim A.S. (C) 2021 */
package com.pttem.ecommerce.user.service.user;

import com.pttem.ecommerce.user.dao.user.UserRepository;
import com.pttem.ecommerce.user.domain.UserEntity;
import com.pttem.ecommerce.user.dto.user.UserDto;
import com.pttem.ecommerce.user.service.base.BaseCrudServiceImpl;
import com.pttem.ecommerce.user.service.mapper.user.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseCrudServiceImpl<UserEntity, UserDto> implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository, UserMapper.INSTANCE);
        this.userRepository = userRepository;
    }

}
