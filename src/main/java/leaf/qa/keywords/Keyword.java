package leaf.qa.keywords;

import org.openqa.selenium.WebDriver;

/**
 * Created by serhatbolsu on 22/01/15.
 */
public abstract class Keyword {
    
    protected WebDriver webDriver;

    public Keyword(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
}
