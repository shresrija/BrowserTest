@WithIE, @WithFF
  Feature: As a user
  I want to nagivate to gmail.com
  to login to the account

    Background:
      Given I navigate to Gmail site


    Scenario: Verify User is  not logged in
      Then I should see the message 'Sign in to continue to Gmail'



    Scenario: Verify User is  not logged in
      Given  I navigate to Gmail site
      When I enter username as 'shresrija'
      And I click the Next button
      And I enter password as 'cukes1245'
      And I click on Sign In button
      Then I should be see 'shresrija@gmail.com'
      Then I should be able to SignOut

    Scenario: Attempt login without credentials
      When I click the Next button
      Then I should see an error message 'Please enter your email.'


