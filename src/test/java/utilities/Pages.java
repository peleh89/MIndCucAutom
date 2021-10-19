package utilities;

import pages.HRAppCreateEmployeePage;
import pages.HRAppHomePage;
import pages.HRAppLoginPage;

public interface Pages {
    HRAppHomePage hrAppHomePage = new HRAppHomePage();
    HRAppLoginPage hrAppLoginPage = new HRAppLoginPage();
    HRAppCreateEmployeePage hrAppCreateEmployeePage = new HRAppCreateEmployeePage();

}
