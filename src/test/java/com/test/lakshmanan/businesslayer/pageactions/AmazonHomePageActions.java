package com.test.lakshmanan.businesslayer.pageactions;

import com.test.lakshmanan.businesslayer.pagelocators.AmazonHomePage;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AmazonHomePageActions extends AmazonHomePage {

    public AmazonHomePageActions enterSearchItem(String searchText) {
        browser().element(searchBox).enterValue(searchText);
        return this;
    }

    public AmazonHomePageActions clickSearchIcon() {
        browser().element(searchButton).click();
        browser().waitForPageLoad(20);
        return this;
    }

    public String getProductText(int itemNo){
        browser().element(productText).waitUntilElementVisible(30);
        List<WebElement> elements = getWebDriver().findElements(productText);
        WebElement e = elements.get(itemNo-1);
        return browser().element().getTextByElement(e);
    }


}
