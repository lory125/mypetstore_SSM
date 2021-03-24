package org.csu.mypetstore.persistence;


import org.apache.ibatis.annotations.Param;
import org.csu.mypetstore.domain.LineItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineItemMapper {
    List<LineItem> getLineItemsByOrderId(@Param("orderId") int orderId);
    void insertLineItem(LineItem lineItem);
}
