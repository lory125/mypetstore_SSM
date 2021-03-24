package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.persistence.CategoryMapper;
import org.csu.mypetstore.persistence.ItemMapper;
import org.csu.mypetstore.persistence.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service()
public class CatalogService {


    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ItemMapper itemMapper;

    //Category
    public List<Category> getCategoryList() {
        return this.categoryMapper.getCategoryList();
    }

    public Category getCategory(String categoryId) {
        return this.categoryMapper.getCategory(categoryId);
    }

    //Product
    public List<Product> getProductListByCategory(String categoryId) {
        return this.productMapper.getProductListByCategory(categoryId);
    }

    public Product getProduct(String productId) {
        return this.productMapper.getProduct(productId);
    }

    //搜索product
    public List<Product> searchProductList(String keyword) {
        return productMapper.searchProductList("%" + keyword.toLowerCase() + "%");
    }

    //Item的操作
    public List<Item> getItemListByProduct(String productId) {
        return this.itemMapper.getItemListByProduct(productId);
    }

    public Item getItem(String itemId) {
        return this.itemMapper.getItem(itemId);
    }

    public boolean isItemInStock(String itemId) {
        return this.itemMapper.getInventoryQuantity(itemId) > 0;
    }

}
