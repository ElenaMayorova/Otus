Feature: learn BDD

  @all @Test3
  Scenario Outline: Open website tele2

    When User open Website "https://msk.tele2.ru/shop/number"
    Then User belived that website has open and title = "Красивые номера - купить красивый федеральный номер телефона Tele2 Москва и Московская область, продажа красивых мобильных номеров"
    When User searches for available phones that contain numbers "<number>"
    When User checks that there are available phone numbers "<number>"

    Examples:
      | number |
      | 97     |
      | 2      |
      | 33     |