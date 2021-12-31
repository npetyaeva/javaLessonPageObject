[![Build status](https://ci.appveyor.com/api/projects/status/0lcelglwi7tms2nw?svg=true)](https://ci.appveyor.com/project/npetyaeva/javalessonpageobject)

### Модуль «Автоматизированное тестирование»

# Page Object's

Необходимо протестировать функцию перевода с карты на карту. Разработчики пока реализовали возможность перевода только между своими картами, но уже хотят, чтобы вы всё протестировали.

Для этого они не поленились и захардкодили вам целого одного пользователя:
```
* login: 'vasya'
* password: 'qwerty123'
* verification code (hardcoded): '12345'
* cards:
    * first:
        * number: '5559 0000 0000 0001'
        * balance: 10 000 RUB
    * second:
        * number: '5559 0000 0000 0002'
        * balance: 10 000 RUB
 ```
