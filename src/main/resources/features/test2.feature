Feature: learn BDD
@all @Test2
  Scenario: Open website tele2
  Given Open WebDriver
    When User open Website "https://msk.tele2.ru/shop/number"
   Then User belived that website has open and title = "Красивые номера - купить красивый федеральный номер телефона Tele2 Москва и Московская область, продажа красивых мобильных номеров"
When User searches for available phones that contain numbers "97"
  When User checks that there are available phone numbers "97"
  Given Close WebDriver