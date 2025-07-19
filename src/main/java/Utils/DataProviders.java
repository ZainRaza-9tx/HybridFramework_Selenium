package Utils;

import org.testng.annotations.DataProvider;

import java.io.IOException;

import static Utils.GenRandomData.*;

public class DataProviders {

    @DataProvider(name = "loginData")
    public String[][] getDDTLoginData() throws IOException {
        String path= "/Users/wahyatmy/IdeaProjects/HybridFramework_Selenium/TestData/ExcelUtils.xlsx";

        ExcelReader utils= new ExcelReader(path);

        int totalRows = utils.getRowCount("Sheet1");
        int totalcolumns = utils.getCellCount("Sheet1",totalRows);

        String loginData[][] = new String[totalRows][totalcolumns];

        for (int r = 1; r<=totalRows;r++)
        {
            for (int c =0; c<totalcolumns;c++)
            {
                loginData[r-1][c] = utils.getCellData("Sheet1",r,c);
            }
        }
        return loginData;
    }

    @DataProvider(name="RegistrationData",indices = 0)
     Object[][] getDDTRegistrationData() {
        Object[][] data={
                {randomname(),randomname(), randomemail(),randomPhoneNumber(),randomPassword()},
                {randomname(),randomname(), randomemail(),randomPhoneNumber(),randomPassword()},
                {randomname(),randomname(), randomemail(),randomPhoneNumber(),randomPassword()}
        };
        return data;
    }
}
