/*--------------------------------------------------------------------------------------------------------------
        * Purpose  :   To read from the property file and return the instance to Framework wrapper
        * author   :   Lakshmanan Chellappan
--------------------------------------------------------------------------------------------------------------*/
package com.test.lakshmanan.corelayer.testdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertySettings {

    private static final Properties globalSettingsProp = readFromPropertyFile("GlobalSettings");

    // Singleton pattern to get only one instance of property file through the execution
    private PropertySettings() {
    }

    public static Properties getGlobalSettingsPropertyInstance() {
        return globalSettingsProp;
    }

    /* Function to read value from property file
       @param  : file name
       @return : n/a
       @Author : Lakshmanan Chellappan
    */
    private static Properties readFromPropertyFile(String fileName) {
        Properties properties = new Properties();
        String relativePath = new File(System.getProperty("user.dir")).getAbsolutePath() +
                System.getProperty("file.separator") + "src" + System.getProperty("file.separator") + "test"
                + System.getProperty("file.separator") + "resources";
        try {
            properties.load(new FileInputStream(relativePath + System.getProperty("file.separator") +
                    fileName + ".properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
