package top.doperj.product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.doperj.product.domain.SKU;
import top.doperj.product.service.SKUChoiceService;
import top.doperj.product.service.SKUService;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EShopProductSKUChoiceTests {
    // IPhone X
/*    String[] skuChoicesForColor = {"银色", "深空灰色"};
    String[] skuChoicesForVersion = {"公开版", "原厂延保版", "双网通版"};
    String[] skuChoicesForStorage = {"64GB", "256GB"};
    String[] skuChoicesForWay = {"官方标配", "移动-移动专享版"};*/
    // Macbook Pro
/*    String[] skuChoicesForColor = {"灰色"};
    String[] skuChoicesForProcessor = {"i5", "i7"};
    String[] skuChoicesForStorage = {"8+64GB", "8+256GB", "8+512GB", "16+256GB", "16+512GB"};
    String[] skuChoicesForInch = {"13.3寸", "15.4寸"};*/
    // IPad
    String[] skuChoicesForColor = {"金色"};
    String[] skuChoicesForVersion = {"WIFI版", "Cellular版"};
    String[] skuChoicesForStorage = {"32GB", "128GB"};

    @Autowired
    SKUChoiceService skuChoiceService;

    @Autowired
    SKUService skuService;

    // Read
    @Test
    public void findAllSKUChoices() throws Exception {
        System.out.println(skuChoiceService.findAllSKUChoices());
    }

    @Test
    public void findSKUChoiceByName() throws Exception {
        System.out.println(skuChoiceService.findSKUChoiceByAttributeAndName("颜色", "银色"));
    }

    @Test
    public void findSKUChoicesByProductName() throws Exception {
        System.out.println(skuChoiceService.findSKUChoicesByProductName("Apple iPhone X(A1865)"));
    }

    @Test
    public void addSKUChoiceForColorBatch() throws Exception {
        skuChoiceService.addSKUChoiceBatch(skuChoicesForColor);
        skuChoiceService.setSKUAttributeByNameBatch(skuChoicesForColor, "颜色");
        System.out.println(skuChoiceService.findAllSKUChoices());
    }

    @Test
    public void addSKUChoiceForVersionBatch() throws Exception {
        skuChoiceService.addSKUChoiceBatch(skuChoicesForVersion);
        skuChoiceService.setSKUAttributeByNameBatch(skuChoicesForVersion, "版本");
        System.out.println(skuChoiceService.findAllSKUChoices());
    }

    @Test
    public void addSKUChoiceForStorageBatch() throws Exception {
        skuChoiceService.addSKUChoiceBatch(skuChoicesForStorage);
        skuChoiceService.setSKUAttributeByNameBatch(skuChoicesForStorage, "容量");
        System.out.println(skuChoiceService.findAllSKUChoices());
    }

    @Test
    public void addSKUChoiceForWayBatch() throws Exception {
        //skuChoiceService.addSKUChoiceBatch(skuChoicesForWay);
        //skuChoiceService.setSKUAttributeByNameBatch(skuChoicesForWay, "购买方式");
        System.out.println(skuChoiceService.findAllSKUChoices());
    }

    @Test
    public void addSKUChoiceForProcessorBatch() throws Exception {
/*        skuChoiceService.addSKUChoiceBatch(skuChoicesForProcessor);
        skuChoiceService.setSKUAttributeByNameBatch(skuChoicesForProcessor, "处理器");*/
        System.out.println(skuChoiceService.findAllSKUChoices());
    }

    @Test
    public void addSKUChoiceForInchBatch() throws Exception {
/*        skuChoiceService.addSKUChoiceBatch(skuChoicesForInch);
        skuChoiceService.setSKUAttributeByNameBatch(skuChoicesForInch, "尺寸");*/
        System.out.println(skuChoiceService.findAllSKUChoices());
    }

    @Test
    public void setSKUChoiceBySKU() {
        SKU sku = skuService.findAllSKUs().get(0);
        System.out.println(skuChoiceService.findSKUChoiceBySKUId(sku.getSkuId()));
    }

    @Test
    public void removeSKUChoiceBatch() throws Exception {
    }
}
