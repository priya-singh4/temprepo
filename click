public void click(WebDriver drivernew, WebElement element, String testData, PageDetails pageDetails) {
		 if (!(testData.equalsIgnoreCase("n/a")) && !(testData.equalsIgnoreCase("n\\a")) && !(testData.equalsIgnoreCase(TESTDATA_NOT_APPLICABLE))) {
		        String actionDescription = element.toString();
		        LocalDateTime startTime = LocalDateTime.now();
		        String action = CLICK_ACTION_PREFIX +  getLocatorFromWebElement(element, drivernew);
		        WebDriverWait wait = new WebDriverWait(drivernew, TIME_OUT);
		        int maxAttempts = TestRunSettings.getRetryAttempts();
 
		        int attempts = 0;
		        boolean clickSuccessful = false;
		        while (attempts < maxAttempts && !clickSuccessful) {
		            try {
		                Webkeywords.instance().fluentWait(drivernew, element);
		                Actions actionnew = new Actions(drivernew);
		                actionnew.moveToElement(element).build().perform();
		                element.click();
		                waitTillPageLoad(wait);
		                testStepDetails.logTestStepDetails(drivernew, action, actionDescription, pageDetails, startTime,STATUSDONE);     
		                clickSuccessful = true;
		            } catch (Exception e) {
		                if (attempts < maxAttempts) {
		                    try {
		                        pause();
		                        logger.info("Retry attempt " + attempts + " of " + maxAttempts);
		                        Webkeywords.instance().waitElementforelementclickable(drivernew, element, 1000);
		                        JavascriptExecutor executor = (JavascriptExecutor) drivernew;
		                        executor.executeScript("arguments[0].scrollIntoView(true);", element);
		                        pause();
		                        executor.executeScript("arguments[0].click();", element);
		                        waitTillPageLoad(wait);
		                        testStepDetails.logTestStepDetails(drivernew, action, actionDescription, pageDetails, startTime, STATUSDONE);
		                        clickSuccessful = true;
		                    } catch (InterruptedException f) {
		                        Thread.currentThread().interrupt();
		                        break;
		                    } catch (Exception f) {
		                        logger.error("Attempt " + attempts + " failed: " + f.getMessage());
		                       }
		                } 
		            }
		            attempts++;
		        }
		    }
		}
 
==============================
 
public void setText(WebDriver drivernew, WebElement element1, String text1, PageDetails pageDetails) {
		if (!(text1.equalsIgnoreCase("n/a")) && !(text1.equalsIgnoreCase("n\\a")) && !(text1.equalsIgnoreCase(TESTDATA_NOT_APPLICABLE))) {
			String action = ENTERED_TEXT + text1;
			String actionDescription = ENTERED_TEXT + text1;
			String password = "*".repeat(text1.length());
			LocalDateTime startTime = LocalDateTime.now();
 
			int maxRetries = TestRunSettings.getRetryAttempts();
			int retryCount = 0;
			boolean success = false;
 
			while (!success && retryCount < maxRetries) {
				try {
					if (!textCheck(text1)) {
						Webkeywords.instance().fluentWait(drivernew, element1);
						WebDriverWait wait1 = new WebDriverWait(drivernew, 1000);
						wait1.until(ExpectedConditions.elementToBeClickable(element1));
						element1.clear();
						element1.sendKeys(text1);
 
						boolean isDataFilled1 = false;
						while (!isDataFilled1) {
							String textBoxValue = element1.getAttribute(VALUE);
							String textBoxtest = element1.getText();
							isDataFilled1 = validateTextBoxValue(drivernew, element1, text1, isDataFilled1, textBoxValue,
									textBoxtest);
						}
						if (element1.getAttribute("type").equals("password")) {
							logger.info("Successfully entered Password : {}", password);
							testStepDetails.logTestStepDetails(drivernew, "Entered Password -> " + password,
									"Entered Password -> " + password, pageDetails, startTime, STATUSDONE);
						} else {
							logger.info(SUCCESSFULLY_ENTERED_TEXT, text1, element1);
							testStepDetails.logTestStepDetails(drivernew, action,
									getActionDescription(action, actionDescription), pageDetails, startTime, STATUSDONE);
						}
						success = true;
					}
 
				} catch (Exception e) {
 
					if (retryCount < maxRetries) {
						logger.warn("Attempt {} failed. Retrying... Error: {}", retryCount, e.getMessage());
						pause();
					}
 
				}
				retryCount++;
			}
		}
	}
has context menu
