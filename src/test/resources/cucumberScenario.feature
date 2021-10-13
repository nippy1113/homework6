#language: ru

@all
Функционал: Покупка товаров

  @firstTest
  Сценарий: Покупка товара на Озоне
    Допустим Вводим товар "iphone" в поисковую строку
    И Жмем на кнопу 'Поиск' у поисковой строки
    Затем Вводим в фильтр "Цена" с параметром "до" значение "80000"
    И В разделе "Беспроводные интерфейсы" кликаем на чекбокс "NFC"
    Но Кликаем на чекбокс "Высокий рейтинг"
    Допустим Добавляем в корзину "8" нечетных продуктов
    И Кликаем на корзину
    Затем Закрываем окно рекламы
    И Проверяем наличие добавленных продуктов в корзине
    Затем Удалим все продукты из корзины

  @secondTest
  Сценарий: Покупка товара на Озоне
    Допустим Вводим товар "беспроводные наушники" в поисковую строку
    И Жмем на кнопу 'Поиск' у поисковой строки
    Затем Вводим в фильтр "Цена" с параметром "до" значение "500"
    Допустим В разделе "Бренды" кликаем на чекбокс "TWS"
    Но Кликаем на чекбокс "Высокий рейтинг"
    Допустим Добавляем в корзину все нечетные продукты на всех страницах
    И Кликаем на корзину
    Затем Закрываем окно рекламы
    И Проверяем наличие добавленных продуктов в корзине
    Затем Удалим все продукты из корзины






