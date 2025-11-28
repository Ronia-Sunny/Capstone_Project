Feature: Tablet Shopping and Cart Management

  Background:
    Given User is on the Advantage Online Shopping homepage

  Scenario: Add three tablets to cart
    When User clicks on the Tablets category
    Then User should see the tablets product page
    
   
    When User selects the tablet "HP ElitePad 1000 G2 Tablet"
    Then User should be on the product details page for "HP ElitePad 1000 G2 Tablet"
    And User adds the product to cart
    Then The product should be added to cart successfully
    And User navigates back to tablets page
    
   
    When User selects the tablet "HP Pro Tablet 608 G1"
    Then User should be on the product details page for "HP Pro Tablet 608 G1"
    And User adds the product to cart
    Then The product should be added to cart successfully
    And User navigates back to tablets page
    
   
    When User selects the tablet "HP Elite x2 1011 G1 Tablet"
    Then User should be on the product details page for "HP Elite x2 1011 G1 Tablet"
    And User adds the product to cart
    Then The product should be added to cart successfully
    
   
    When User opens the shopping cart
    Then User should see all 3 tablets in the cart

  Scenario: Navigate to tablets category
    When User clicks on the Tablets category
    Then User should see the tablets product page
    And The page should display available tablet products

  Scenario: View tablet product details
    When User clicks on the Tablets category
    And User selects the tablet "HP ElitePad 1000 G2 Tablet"
    Then User should be on the product details page for "HP ElitePad 1000 G2 Tablet"
    And Product details should include name and price