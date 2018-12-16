package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class Transformer {

    public static <T> List<T> wrap(List<WebElement> list, WebDriver driver, BiFunction<WebDriver, WebElement, T> constructor) {
        List<T> wrappedList = new ArrayList<>();
        if (list.isEmpty()) return wrappedList;

        for (int i = 0; i < list.size(); i++) {
            wrappedList.add(constructor.apply(driver, list.get(i)));
        }
        return wrappedList;
    }
}
