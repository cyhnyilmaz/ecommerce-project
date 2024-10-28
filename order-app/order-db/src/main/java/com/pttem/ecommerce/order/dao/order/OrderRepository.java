/* SmartICT Bilisim A.S. (C) 2021 */
package com.pttem.ecommerce.order.dao.order;

import com.pttem.ecommerce.order.dao.base.BaseRepository;
import com.pttem.ecommerce.order.domain.OrderEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface OrderRepository extends BaseRepository<OrderEntity, Long> {
}
