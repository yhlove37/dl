package ssm.dao;

import org.apache.ibatis.annotations.Param;
import ssm.entity.Category;
import ssm.entity.Order;
import ssm.entity.OrderItem;
import ssm.entity.Product;
import ssm.vo.ProductVo;

import java.util.List;

public interface ProductMapper {
    List<Product> findAllProduct();

    int getTotalCount();

    List<Product> findProductListForPageBean(
            @Param("offset") int index,@Param("limit") int currentCount);

    List<ProductVo> findHotProductList();

    List<ProductVo> findNewProductList();

    List<Category> findAllCategory();

    int getTotalCountByCid(@Param("cid") String cid);

    List<Product> findProductListForPageBeanByCid(
            @Param("cid") String cid, 
            @Param("offset") int index, 
            @Param("limit") int currentCount);

    Product findProductByPid(@Param("pid") String pid);

    ProductVo findProductVoByPid(int pid);

    void addOrders(Order order);

    void addOrderItem(OrderItem orderItem);

    void updateOrderAddr(@Param("address") String address,@Param("name") String name,@Param("telephone") String telephone, @Param("oid") String oid);

    List<Order> findAllOrdersByUid(@Param("uid") int uid);

    List<OrderItem> findAllOrderItemByOid(String oid);
    //
    void deleteOrderByoid(@Param("oid1") String oid1);
}
