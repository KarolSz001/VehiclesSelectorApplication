package com.app.main;
import com.app.service.ControlAppService;

public class Main01 {

    public static void main(String[] args) {
        final String appName = "App01 v1.03 09.10.2019 _K.Szot";
        System.out.println(appName);

        final String jsonFilename = "TestJsonFilename.json";
        ControlAppService controlAppService = new ControlAppService(jsonFilename);
        controlAppService.runApp();
    }
}
