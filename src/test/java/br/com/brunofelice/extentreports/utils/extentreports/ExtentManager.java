package br.com.brunofelice.extentreports.utils.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();
    public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter spark = new ExtentSparkReporter("extent-report.html");
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("Extent Report Implementation");
        spark.config().setReportName("Sample Extent Report");
        extentReports.attachReporter(spark);
        return extentReports;
    }
}
