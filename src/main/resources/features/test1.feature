Feature: learn BDD
@all @Test1
  Scenario: Open website
  Given Open WebDriver
    When User open Website "https://otus.ru"
   Then User belived that website has open and title = "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям"
  Given Close WebDriver