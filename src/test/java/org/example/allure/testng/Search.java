package org.example.allure.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Search {

    @Test(description = "Search check")
    void testSearch(){
        System.setProperty("webdriver.chrome.driver","chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.get("https://auto.ria.com/");

        //General search
        String markText = "BMW";
        String modelText = "320";
        String regionText = "Киевская";

        By mark = By.xpath("//div[@id='brandTooltipBrandAutocomplete-brand']/label[@class='text']");
        By markListed = By.xpath("//div[@id='brandTooltipBrandAutocomplete-brand']/ul/li/a[text()='BMW' and @class='item']");

        By model = By.xpath("//div[@id='brandTooltipBrandAutocomplete-model']/label[@class='text']");
        By modelListed = By.xpath("//div[@id='brandTooltipBrandAutocomplete-model']/ul/li/a[text()='320' and @class='item']");

        By region = By.xpath("//div[@id='brandTooltipBrandAutocomplete-region']/label[@class='text']");
        By regionListed = By.xpath("//*[@id='brandTooltipBrandAutocomplete-region']/ul/li[2]/a");


        By submit = By.xpath(".//button[@type='submit']");

        wait.until(ExpectedConditions.visibilityOfElementLocated(submit));

        WebElement markSearch = driver.findElement(mark);
        markSearch.click();


        WebElement bmwPop = driver.findElement(markListed);
        bmwPop.click();

        WebElement modelSearch = driver.findElement(model);
        modelSearch.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(modelListed));

        WebElement modelPop = driver.findElement(modelListed);
        modelPop.click();

        WebElement regionSearch = driver.findElement(region);
        regionSearch.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(regionListed));

        WebElement regionPop = driver.findElement(regionListed);
        regionPop.click();

        WebElement submitButton = driver.findElement(submit);
        submitButton.click();


        By adButton = By.cssSelector("#leftFilterAdvancedSearch");
        wait.until(ExpectedConditions.visibilityOfElementLocated(adButton));

        // Search result //TODO: resolve <script template> question
        // By mark0 = By.xpath(".//*[@id='brandTooltipBrandAutocomplete-0']/ul/li[1]");
        // By model0 = By.xpath(".//*[@id='brandTooltipModelAutocompleteInput-0']/ul/li[1]");
        // By region0 = By.cssSelector("#regionSelected");

        // Assert.assertEquals(driver.findElement(mark0).getText(), markText, "Mark not matched at sidebar");
        // Assert.assertEquals(driver.findElement(model0).getText(), modelText, "Model not matched at sidebar");
        // Assert.assertEquals(driver.findElement(region0).getText(), regionText, "Region not matched at sidebar");

        By markId = By.xpath(".//*[@data-name='brand.id[0]']/a[1]");
        By modelId = By.xpath(".//*[@data-name='model.id[0]']/a[1]");
        By regionId = By.xpath(".//*[@data-name='region.id[0]']/a[1]");


        Assert.assertEquals(driver.findElement(markId).getText(), markText, "Mark not matched at tag-search");
        Assert.assertEquals(driver.findElement(modelId).getText(), modelText, "Model not matched at tag-search");
        Assert.assertEquals(driver.findElement(regionId).getText(), regionText, "Region not matched at tag-search");

        By resultTitles = By.cssSelector("div.item.ticket-title a span");

        List<WebElement> postTitles = driver.findElements(resultTitles);
        for (WebElement title : postTitles) {
            Assert.assertTrue(title.getText().contains("BMW 320"), "Title not matched in search result");
        }

        driver.close();

    }

}
