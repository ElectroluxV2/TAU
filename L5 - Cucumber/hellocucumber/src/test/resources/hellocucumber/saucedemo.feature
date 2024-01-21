Feature: Login & Hamburger menu logout
  Everybody wants to use hamburger menu

  Scenario: Login and use hamburger menu to logout
    Given Unsigned page
    When User signs in
    Then He should be able to open hamburger menu and logout
