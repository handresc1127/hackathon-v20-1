package com.handresc1127;

import com.handresc1127.utils.BaseTests;
import org.testng.annotations.Test;


public class TraditionalTestsV1 extends BaseTests {
    String urlApplitools="https://demo.applitools.com/gridHackathonV1.html";
    String urlApplitools2="https://demo.applitools.com/gridHackathonV2.html";

    @Test
    public void dataDrivenTest() {
        driver.get(urlApplitools);
    }
}
