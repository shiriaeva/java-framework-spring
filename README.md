# java-framework-spring

ДЗ 1

Устранить баг при попытке удаления записи по несуществующему id - [новый redirect](https://github.com/shiriaeva/java-framework-spring/blob/d27747aae8afe8d1a7c23a2d0161d42bca8143b3/simple_mvc/src/main/java/org/example/web/controllers/BookShelfController.java#L52)   
Исключить возможность сохранения пустых записей - [добавлена функция проверки на валидность данных в класс Book](https://github.com/shiriaeva/java-framework-spring/blob/d27747aae8afe8d1a7c23a2d0161d42bca8143b3/simple_mvc/src/main/java/org/example/web/dto/Book.java#L51-L55)   
Интерфейс и логика удаления записей по полям author, title и size - [поиск на соответствие полей author, title, size "регулярному выражению"](https://github.com/shiriaeva/java-framework-spring/blob/d27747aae8afe8d1a7c23a2d0161d42bca8143b3/simple_mvc/src/main/java/org/example/app/services/BookRepository.java#L41-L58)   
