package com.apprecovery.AAViewerUI;

import android.test.ActivityInstrumentationTestCase2;

import com.apprecovery.export.AgentExportConfigurationCollection;
import com.apprecovery.export.ExportManagement;
import com.apprecovery.view.login.LoginActivity;
import com.apprecovery.agents.AgentInfoSummaryCollection;
import com.apprecovery.agents.AgentManagement;
import junit.framework.Assert;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class com.apprecovery.AAViewerUI.MainActivityTest \
 * com.apprecovery.AAViewerUI.tests/android.test.InstrumentationTestRunner
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {

    public MainActivityTest()
    {
        super("com.apprecovery.AAViewerUI", LoginActivity.class);
    }

    @Override
    public void runTest()
    {
        try
        {
            ExportManagement mgmt = new ExportManagement();
            AgentExportConfigurationCollection result = mgmt.getAllAgentsExportConfiguration();

            int a = 0;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            Assert.fail();
        }
    }
}
