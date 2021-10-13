package utils;


import data.DataManager;
import io.cucumber.plugin.event.EventHandler;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.Status;
import io.cucumber.plugin.event.TestStepFinished;
import io.qameta.allure.Allure;
import io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm;
import managers.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class AllureListener extends AllureCucumber5Jvm {
    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepFinished.class, new EventHandler<TestStepFinished>() {
            @Override
            public void receive(TestStepFinished testStepFinished) {
                if (testStepFinished.getResult().getStatus().is(Status.FAILED)) {
                    Allure.getLifecycle().addAttachment("html page", "text/html", "HTML", DriverManager.getDriverManager().getDriver().getPageSource().getBytes());
                    Allure.getLifecycle().addAttachment("screenshot", "image/png", "png", saveScreenShootPNG());
                }
            }
        });
        super.setEventPublisher(publisher);
    }

    public byte[] saveScreenShootPNG() {
        return ((TakesScreenshot) DriverManager.getDriverManager().getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
