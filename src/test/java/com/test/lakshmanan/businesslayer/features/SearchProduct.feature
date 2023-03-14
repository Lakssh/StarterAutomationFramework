Feature: Amazon - Search Product

  @Regression @SearchProduct @Web
  Scenario Outline: Search for a Product
    Given I launch the <application> and validate page title contains <pageTitle>
    When I search for the product <product>
    Then I validate the first result contains the text <productText>

    Examples:
      |application  | pageTitle  | product  |  productText   |
      |amazon       | Amazon.com | iphone   |  Apple iPhone  |
