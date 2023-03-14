package com.test.lakshmanan.businesslayer.pageactions;

import com.test.lakshmanan.businesslayer.pagelocators.AmazonHomePage;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AmazonHomePageActions extends AmazonHomePage {

    /* Function to enter search text in the Amazon home page
       @param  : search text as string
       @return : class
       @Author : Lakshmanan Chellappan
    */
    public AmazonHomePageActions enterSearchItem(String searchText) {
        browser().element(searchBox).enterValue(searchText);
        return this;
    }

    /*  Function to click on search icon in the Amazon home page
        @param  : n/a
        @return : class
        @Author : Lakshmanan Chellappan
    */
    public AmazonHomePageActions clickSearchIcon() {
        browser().element(searchButton).click();
        browser().waitForPageLoad(20);
        return this;
    }

    /*  Function to get product text
        @param  : product item from the list
        @return : product text as string
        @Author : Lakshmanan Chellappan
    */
    public String getProductText(int itemNo){
        browser().element(productText).waitUntilElementVisible(30);
        List<WebElement> elements = getWebDriver().findElements(productText); // web driver methods can also be used directly
        WebElement e = elements.get(itemNo-1);
        return browser().element().getTextByElement(e);
    }


}
