/* SmartICT Bilisim A.S. (C) 2021 */
package com.pttem.ecommerce.user.dao.user;

import com.pttem.ecommerce.user.dao.base.BaseRepository;
import com.pttem.ecommerce.user.domain.UserAddressEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface UserAddressRepository extends BaseRepository<UserAddressEntity, Long> {
}
