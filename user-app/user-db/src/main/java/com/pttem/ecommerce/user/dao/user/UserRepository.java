/* SmartICT Bilisim A.S. (C) 2021 */
package com.pttem.ecommerce.user.dao.user;

import com.pttem.ecommerce.user.dao.base.BaseRepository;
import com.pttem.ecommerce.user.domain.UserEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface UserRepository extends BaseRepository<UserEntity, Long> {
}
