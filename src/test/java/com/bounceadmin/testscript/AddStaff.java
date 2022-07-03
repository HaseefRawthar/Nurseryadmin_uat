package com.bounceadmin.testscript;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bounceadmin.baseclass.SetUp;
import com.bounceadmin.objectrepository.AddStaffObject;
import com.bounceadmin.objectrepository.DashboardObject;
import com.bounceadmin.objectrepository.LogInObject;
import com.bounceadmin.objectrepository.StaffListObject;
import com.bounceadmin.objectrepository.ViewStaffObject;
import com.bounceadmin.testdata.AddStaffData;
import com.bounceadmin.testdata.TestDataImport;

public class AddStaff extends SetUp {

	LogInObject loginObj;
	DashboardObject dashboardObj;
	TestDataImport tdImport;
	AddStaffObject addstaffObj;
	AddStaffData addstaffdataObj;
	StaffListObject stafflistObj;
	ViewStaffObject viewstaffObj;
	String[] staffData;
	String[] testData,roles;

	@BeforeClass
	public void initialize()
	{
		try
		{
			loginObj = new LogInObject(driver);
			dashboardObj = new DashboardObject(driver);
			tdImport = new TestDataImport();
			addstaffObj = new AddStaffObject(driver);
			addstaffdataObj = new AddStaffData();
			stafflistObj = new StaffListObject(driver);
			viewstaffObj = new ViewStaffObject(driver);

			tdImport.readSheet("AddStaff");

			loginObj.logIn(userEmail,userPassword);
			waitForElementToLoad(loginObj.selectBouncesuperAdmin);
			loginObj.selectBouncesuperAdmin.click();
			loginObj.selectButton.click();
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
	}

	@Test(priority=1)
	public void verifyMandatoryMsg() //verify mandatory msg is present when adding staff without filling mandatry fields
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();

			log.info("Entered verifyMandatoryMsg test");
			eTest = eReports.createTest("Verify Mandatory Message");
			eTest.assignCategory("Add Staff");

			waitIfElementClickIsIntercepted(dashboardObj.staff, "click", "");
			dashboardObj.addStaff.click();
			waitIfElementClickIsIntercepted(addstaffObj.addButton, "click", "");

			for(int i=0;i<5;i++)
			{
				expectedBoolArray.add(true);
			}
			waitForElementToLoad(addstaffObj.firstNameMandatoryMsg);
			actualBoolArray.add(isElementPresent(addstaffObj.firstNameMandatoryMsg));
			actualBoolArray.add(isElementPresent(addstaffObj.emailMandatoryMsg));
			actualBoolArray.add(isElementPresent(addstaffObj.countryCodeMandatoryMsg));
			actualBoolArray.add(isElementPresent(addstaffObj.phoneNumberMandatoryMsg));
			actualBoolArray.add(isElementPresent(addstaffObj.roleMandatoryMsg));
			waitIfElementClickIsIntercepted(dashboardObj.staff, "click", "");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedBoolArray);
		System.out.println(actualBoolArray);
		Assert.assertEquals(actualBoolArray, expectedBoolArray);
		log.info("Assertion passed");
	}

	@Test(priority=2)
	public void addStaffAsBounceAdmin() //add staff with role = bounce admin
	{
		try
		{
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();

			log.info("Entered addStaffAsBounceAdmin test");
			eTest = eReports.createTest("Add Staff As BounceAdmin");
			eTest.assignCategory("Add Staff");

			expectedArraylist.add("Dars Employee created successfully");
			waitIfElementClickIsIntercepted(dashboardObj.staff, "click", "");
			dashboardObj.addStaff.click();
			addstaffdataObj.generateFakeStaffData();
			staffData = addstaffdataObj.addStaffTestData();
			testData = addstaffdataObj.viewStaffData();
			for(int i=0;i<testData.length;i++)
			{
				System.out.println(testData[i]);
				expectedArraylist.add(testData[i]);
			}
			expectedArraylist.add("Bounce Admin");
			expectedArraylist.add("Active");
			System.out.println(expectedArraylist);
			for(int i=0;i<staffData.length;i++)
			{
				System.out.println(staffData[i]);
			}
			waitForElementToLoad(addstaffObj.bounceAdmin);
			addstaffObj.bounceAdmin.click();
			addstaffObj.addStaffDetails(staffData);
			addstaffObj.addButton.click();
			waitForElementToLoad(addstaffObj.staffSuccessMsg);
			actualArraylist.add(addstaffObj.staffSuccessMsg.getText());
			String name = staffData[0]+" "+staffData[1]+" "+staffData[2];
			stafflistObj.staffFilter(name,staffData[3],"","");
			Thread.sleep(2000);
			waitForElementToLoad(stafflistObj.viewButton);
			autoScrollandClick(stafflistObj.viewButton);
			waitForElementToLoad(viewstaffObj.viewName);
			actualArraylist.add(viewstaffObj.viewName.getText());
			actualArraylist.add(viewstaffObj.viewphoneNumber.getText());
			actualArraylist.add(viewstaffObj.viewEmail.getText());
			actualArraylist.add(viewstaffObj.viewRole.getText());
			actualArraylist.add(viewstaffObj.viewStatus.getText());
			waitIfElementClickIsIntercepted(dashboardObj.staff, "click", "");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(actualArraylist);
		Assert.assertEquals(actualArraylist, expectedArraylist);
		log.info("Assertion passed");
	}


	@Test(priority=3)
	public void addStaffAsCustomerSupportAgent() //add staff with role = CustomerSupportAgent
	{
		try
		{
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();

			log.info("Entered addStaffAsCustomerSupportAgent test");
			eTest = eReports.createTest("Add Staff As CustomerSupportAgent");
			eTest.assignCategory("Add Staff");

			expectedArraylist.add("Dars Employee created successfully");
			waitIfElementClickIsIntercepted(dashboardObj.staff, "click", "");
			dashboardObj.addStaff.click();
			addstaffdataObj.generateFakeStaffData();
			staffData = addstaffdataObj.addStaffTestData();
			testData = addstaffdataObj.viewStaffData();
			for(int i=0;i<testData.length;i++)
			{
				System.out.println(testData[i]);
				expectedArraylist.add(testData[i]);
			}
			expectedArraylist.add("Customer Support Agent");
			expectedArraylist.add("Active");
			System.out.println(expectedArraylist);
			for(int i=0;i<staffData.length;i++)
			{
				System.out.println(staffData[i]);
			}
			waitForElementToLoad(addstaffObj.customerSupportAgent);
			addstaffObj.customerSupportAgent.click();
			addstaffObj.addStaffDetails(staffData);
			addstaffObj.addButton.click();

			waitForElementToLoad(addstaffObj.staffSuccessMsg);
			actualArraylist.add(addstaffObj.staffSuccessMsg.getText());
			String name = staffData[0]+" "+staffData[1]+" "+staffData[2];
			stafflistObj.staffFilter(name,staffData[3],"","");
			Thread.sleep(2000);
			waitForElementToLoad(stafflistObj.viewButton);
			autoScrollandClick(stafflistObj.viewButton);
			waitForElementToLoad(viewstaffObj.viewName);
			actualArraylist.add(viewstaffObj.viewName.getText());
			actualArraylist.add(viewstaffObj.viewphoneNumber.getText());
			actualArraylist.add(viewstaffObj.viewEmail.getText());
			actualArraylist.add(viewstaffObj.viewRole.getText());
			actualArraylist.add(viewstaffObj.viewStatus.getText());
			waitIfElementClickIsIntercepted(dashboardObj.staff, "click", "");

		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(actualArraylist);
		Assert.assertEquals(actualArraylist, expectedArraylist);
		log.info("Assertion passed");
	}

	@Test(priority=4)
	public void addStaffAsSupportAgent() //add staff with role = CustomerSupportAgent
	{
		try
		{
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();

			log.info("Entered addStaffAsSupportAgent test");
			eTest = eReports.createTest("Add Staff As Support Agent");
			eTest.assignCategory("Add Staff");

			expectedArraylist.add("Dars Employee created successfully");
			waitIfElementClickIsIntercepted(dashboardObj.staff, "click", "");
			dashboardObj.addStaff.click();
			addstaffdataObj.generateFakeStaffData();
			staffData = addstaffdataObj.addStaffTestData();
			testData = addstaffdataObj.viewStaffData();
			for(int i=0;i<testData.length;i++)
			{
				System.out.println(testData[i]);
				expectedArraylist.add(testData[i]);
			}
			expectedArraylist.add("Support Agent");
			expectedArraylist.add("Active");
			System.out.println(expectedArraylist);
			for(int i=0;i<staffData.length;i++)
			{
				System.out.println(staffData[i]);
			}
			waitForElementToLoad(addstaffObj.supportAgent);
			addstaffObj.supportAgent.click();
			addstaffObj.addStaffDetails(staffData);
			addstaffObj.addButton.click();

			waitForElementToLoad(addstaffObj.staffSuccessMsg);
			actualArraylist.add(addstaffObj.staffSuccessMsg.getText());
			String name = staffData[0]+" "+staffData[1]+" "+staffData[2];
			stafflistObj.staffFilter(name,staffData[3],"","");
			Thread.sleep(2000);
			waitForElementToLoad(stafflistObj.viewButton);
			autoScrollandClick(stafflistObj.viewButton);
			waitForElementToLoad(viewstaffObj.viewName);
			actualArraylist.add(viewstaffObj.viewName.getText());
			actualArraylist.add(viewstaffObj.viewphoneNumber.getText());
			actualArraylist.add(viewstaffObj.viewEmail.getText());
			actualArraylist.add(viewstaffObj.viewRole.getText());
			actualArraylist.add(viewstaffObj.viewStatus.getText());
			waitIfElementClickIsIntercepted(dashboardObj.staff, "click", "");

		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(actualArraylist);
		Assert.assertEquals(actualArraylist, expectedArraylist);
		log.info("Assertion passed");
	}

	@Test(priority=5)
	public void addStaffAsTestAdmin() //add staff with role = CustomerSupportAgent
	{
		try
		{
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();

			log.info("Entered addStaffAsTestAdmin test");
			eTest = eReports.createTest("Add Staff As Test Admin");
			eTest.assignCategory("Add Staff");

			expectedArraylist.add("Dars Employee created successfully");
			waitIfElementClickIsIntercepted(dashboardObj.staff, "click", "");
			dashboardObj.addStaff.click();
			addstaffdataObj.generateFakeStaffData();
			staffData = addstaffdataObj.addStaffTestData();
			testData = addstaffdataObj.viewStaffData();
			for(int i=0;i<testData.length;i++)
			{
				System.out.println(testData[i]);
				expectedArraylist.add(testData[i]);
			}
			expectedArraylist.add("TestAdmin");
			expectedArraylist.add("Active");
			System.out.println(expectedArraylist);
			for(int i=0;i<staffData.length;i++)
			{
				System.out.println(staffData[i]);
			}
			waitForElementToLoad(addstaffObj.testAdmin);
			addstaffObj.testAdmin.click();
			addstaffObj.addStaffDetails(staffData);
			addstaffObj.addButton.click();

			waitForElementToLoad(addstaffObj.staffSuccessMsg);
			actualArraylist.add(addstaffObj.staffSuccessMsg.getText());
			String name = staffData[0]+" "+staffData[1]+" "+staffData[2];
			stafflistObj.staffFilter(name,staffData[3],"","");
			Thread.sleep(2000);
			waitForElementToLoad(stafflistObj.viewButton);
			autoScrollandClick(stafflistObj.viewButton);
			waitForElementToLoad(viewstaffObj.viewName);
			actualArraylist.add(viewstaffObj.viewName.getText());
			actualArraylist.add(viewstaffObj.viewphoneNumber.getText());
			actualArraylist.add(viewstaffObj.viewEmail.getText());
			actualArraylist.add(viewstaffObj.viewRole.getText());
			actualArraylist.add(viewstaffObj.viewStatus.getText());
			waitIfElementClickIsIntercepted(dashboardObj.staff, "click", "");

		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(actualArraylist);
		Assert.assertEquals(actualArraylist, expectedArraylist);
		log.info("Assertion passed");
	}

	@Test(priority=6)
	public void addStaffAsSupportAdmin() //add staff with role = CustomerSupportAgent
	{
		try
		{
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();

			log.info("Entered addStaffAsSupportAdmin test");
			eTest = eReports.createTest("Add Staff As Support Admin");
			eTest.assignCategory("Add Staff");

			expectedArraylist.add("Dars Employee created successfully");
			waitIfElementClickIsIntercepted(dashboardObj.staff, "click", "");
			dashboardObj.addStaff.click();
			addstaffdataObj.generateFakeStaffData();
			staffData = addstaffdataObj.addStaffTestData();
			testData = addstaffdataObj.viewStaffData();
			for(int i=0;i<testData.length;i++)
			{
				System.out.println(testData[i]);
				expectedArraylist.add(testData[i]);
			}
			expectedArraylist.add("SupportAdmin");
			expectedArraylist.add("Active");
			System.out.println(expectedArraylist);
			for(int i=0;i<staffData.length;i++)
			{
				System.out.println(staffData[i]);
			}
			waitForElementToLoad(addstaffObj.supportAdmin);
			addstaffObj.supportAdmin.click();
			addstaffObj.addStaffDetails(staffData);
			addstaffObj.addButton.click();

			waitForElementToLoad(addstaffObj.staffSuccessMsg);
			actualArraylist.add(addstaffObj.staffSuccessMsg.getText());
			String name = staffData[0]+" "+staffData[1]+" "+staffData[2];
			stafflistObj.staffFilter(name,staffData[3],"","");
			Thread.sleep(2000);
			waitForElementToLoad(stafflistObj.viewButton);
			autoScrollandClick(stafflistObj.viewButton);
			waitForElementToLoad(viewstaffObj.viewName);
			actualArraylist.add(viewstaffObj.viewName.getText());
			actualArraylist.add(viewstaffObj.viewphoneNumber.getText());
			actualArraylist.add(viewstaffObj.viewEmail.getText());
			actualArraylist.add(viewstaffObj.viewRole.getText());
			actualArraylist.add(viewstaffObj.viewStatus.getText());
			waitIfElementClickIsIntercepted(dashboardObj.staff, "click", "");

		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(actualArraylist);
		Assert.assertEquals(actualArraylist, expectedArraylist);
		log.info("Assertion passed");
	}

	@Test(priority=7)
	public void addStaffAsSupportAgentAssistant() //add staff with role = CustomerSupportAgent
	{
		try
		{
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();

			log.info("Entered addStaffAsSupportAgentAssistant test");
			eTest = eReports.createTest("Add Staff As Support Agent Assistant");
			eTest.assignCategory("Add Staff");

			expectedArraylist.add("Dars Employee created successfully");
			waitIfElementClickIsIntercepted(dashboardObj.staff, "click", "");
			dashboardObj.addStaff.click();
			addstaffdataObj.generateFakeStaffData();
			staffData = addstaffdataObj.addStaffTestData();
			testData = addstaffdataObj.viewStaffData();
			for(int i=0;i<testData.length;i++)
			{
				System.out.println(testData[i]);
				expectedArraylist.add(testData[i]);
			}
			expectedArraylist.add("Support Agent Assistant");
			expectedArraylist.add("Active");
			System.out.println(expectedArraylist);
			for(int i=0;i<staffData.length;i++)
			{
				System.out.println(staffData[i]);
			}
			waitForElementToLoad(addstaffObj.supportAgentAssistant);
			addstaffObj.supportAgentAssistant.click();
			addstaffObj.addStaffDetails(staffData);
			addstaffObj.addButton.click();

			waitForElementToLoad(addstaffObj.staffSuccessMsg);
			actualArraylist.add(addstaffObj.staffSuccessMsg.getText());
			String name = staffData[0]+" "+staffData[1]+" "+staffData[2];
			stafflistObj.staffFilter(name,staffData[3],"","");
			Thread.sleep(2000);
			waitForElementToLoad(stafflistObj.viewButton);
			autoScrollandClick(stafflistObj.viewButton);
			waitForElementToLoad(viewstaffObj.viewName);
			actualArraylist.add(viewstaffObj.viewName.getText());
			actualArraylist.add(viewstaffObj.viewphoneNumber.getText());
			actualArraylist.add(viewstaffObj.viewEmail.getText());
			actualArraylist.add(viewstaffObj.viewRole.getText());
			actualArraylist.add(viewstaffObj.viewStatus.getText());
			waitIfElementClickIsIntercepted(dashboardObj.staff, "click", "");

		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(actualArraylist);
		Assert.assertEquals(actualArraylist, expectedArraylist);
		log.info("Assertion passed");
	}

	@Test(priority=8)
	public void addStaffAsFinance() //add staff with role = CustomerSupportAgent
	{
		try
		{
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();

			log.info("Entered addStaffAsFinance test");
			eTest = eReports.createTest("Add Staff As Finance");
			eTest.assignCategory("Add Staff");

			expectedArraylist.add("Dars Employee created successfully");
			waitIfElementClickIsIntercepted(dashboardObj.staff, "click", "");
			dashboardObj.addStaff.click();
			addstaffdataObj.generateFakeStaffData();
			staffData = addstaffdataObj.addStaffTestData();
			testData = addstaffdataObj.viewStaffData();
			for(int i=0;i<testData.length;i++)
			{
				System.out.println(testData[i]);
				expectedArraylist.add(testData[i]);
			}
			expectedArraylist.add("Finance");
			expectedArraylist.add("Active");
			System.out.println(expectedArraylist);
			for(int i=0;i<staffData.length;i++)
			{
				System.out.println(staffData[i]);
			}
			waitForElementToLoad(addstaffObj.finance);
			addstaffObj.finance.click();
			addstaffObj.addStaffDetails(staffData);
			addstaffObj.addButton.click();

			waitForElementToLoad(addstaffObj.staffSuccessMsg);
			actualArraylist.add(addstaffObj.staffSuccessMsg.getText());
			String name = staffData[0]+" "+staffData[1]+" "+staffData[2];
			stafflistObj.staffFilter(name,staffData[3],"","");
			Thread.sleep(2000);
			waitForElementToLoad(stafflistObj.viewButton);
			autoScrollandClick(stafflistObj.viewButton);
			waitForElementToLoad(viewstaffObj.viewName);
			actualArraylist.add(viewstaffObj.viewName.getText());
			actualArraylist.add(viewstaffObj.viewphoneNumber.getText());
			actualArraylist.add(viewstaffObj.viewEmail.getText());
			actualArraylist.add(viewstaffObj.viewRole.getText());
			actualArraylist.add(viewstaffObj.viewStatus.getText());
			waitIfElementClickIsIntercepted(dashboardObj.staff, "click", "");

		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(actualArraylist);
		Assert.assertEquals(actualArraylist, expectedArraylist);
		log.info("Assertion passed");
	}


	@Test(priority=9)
	public void addStaffWithMultiRoles() //add staff with both roles
	{
		try
		{
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();

			log.info("Entered addStaffWithBothRoles test");
			eTest = eReports.createTest("Add Staff With Multiple Roles");
			eTest.assignCategory("Add Staff");

			expectedArraylist.add("Dars Employee created successfully");
			waitIfElementClickIsIntercepted(dashboardObj.staff, "click", "");
			dashboardObj.addStaff.click();
			addstaffdataObj.generateFakeStaffData();
			staffData = addstaffdataObj.addStaffTestData();
			testData = addstaffdataObj.viewStaffData();
			roles = addstaffdataObj.staffRoles();
			for(int i=0;i<testData.length;i++)
			{
				System.out.println(testData[i]);
				expectedArraylist.add(testData[i]);
			}
			expectedArraylist.add(roles[0]);
			expectedArraylist.add("Active");
			System.out.println(expectedArraylist);
			for(int i=0;i<staffData.length;i++)
			{
				System.out.println(staffData[i]);
			}
			waitForElementToLoad(addstaffObj.customerSupportAgent);
			addstaffObj.customerSupportAgent.click();
			autoScrollandClick(addstaffObj.bounceAdmin);
			autoScrollandClick(addstaffObj.supportAgent);
			autoScrollandClick(addstaffObj.testAdmin);
			autoScrollandClick(addstaffObj.supportAdmin);
			autoScrollandClick(addstaffObj.supportAgentAssistant);
			autoScrollandClick(addstaffObj.finance);

			addstaffObj.addStaffDetails(staffData);
			addstaffObj.addButton.click();
			waitForElementToLoad(addstaffObj.staffSuccessMsg);
			actualArraylist.add(addstaffObj.staffSuccessMsg.getText());
			String name = staffData[0]+" "+staffData[1]+" "+staffData[2];
			stafflistObj.staffFilter(name,staffData[3],"",roles[0]);
			Thread.sleep(2000);
			waitForElementToLoad(stafflistObj.viewButton);
			autoScrollandClick(stafflistObj.viewButton);
			waitForElementToLoad(viewstaffObj.viewName);
			actualArraylist.add(viewstaffObj.viewName.getText());
			actualArraylist.add(viewstaffObj.viewphoneNumber.getText());
			actualArraylist.add(viewstaffObj.viewEmail.getText());
			actualArraylist.add(viewstaffObj.viewRole.getText());
			actualArraylist.add(viewstaffObj.viewStatus.getText());
			waitIfElementClickIsIntercepted(dashboardObj.staff, "click", "");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(actualArraylist);
		Assert.assertEquals(actualArraylist, expectedArraylist);
		log.info("Assertion passed");
	}

	@AfterClass
	public void signOt()
	{
		dashboardObj.logout();
	}
}
