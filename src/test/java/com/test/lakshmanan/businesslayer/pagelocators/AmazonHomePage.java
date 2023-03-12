package com.test.lakshmanan.businesslayer.pagelocators;

import com.test.lakshmanan.corelayer.FrameworkWrapper;
import org.openqa.selenium.By;

public class AmazonHomePage extends FrameworkWrapper {

    public final By logo = By.id("nav-logo-sprites");
    public final By searchBox = By.id("twotabsearchtextbox");
    public final By searchButton = By.id("nav-search-submit-button");
    public final By productText = By.xpath("//span[@class ='a-size-medium a-color-base a-text-normal']");

}
