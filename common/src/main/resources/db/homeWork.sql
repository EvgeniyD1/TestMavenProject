-- 1) вывести id, имя, фамилию, вес всех пользователей в таблице m_users
-- 2) вывести пользователя с логином  'TestLogin'
-- 3) посчитать количество незаблокированных пользователей
-- 4) удалить пользователей с айдишками между 5 и 10 и с буквой 'а' в логине
-- 5) обновить фамитлии пользователей след образом 1 Kalevich = >  Kalevich + {id}
-- 6) создать колонку temp varchar тип данных на 200 символов
-- 7) обновить эту колонку у всех юзеров значением '123'
-- 8) очистить эту колонку + изменить ее тип данных на int

select id, username, surname, weight
from m_users;

select *
from m_users
where (login = 'TestLogin');

select count(is_blocked) as unblocked_users
from m_users
where (is_blocked = false);

delete
from m_users
where (id > 5 and id < 10) and (login like ('%a%'));

update m_users
set surname = concat (surname, id);

alter table m_users
add temp varchar (200);

update m_users
set temp = '123';

update m_users
set temp = null;

alter table m_users
alter column temp type int USING temp::integer;

-- 1)+ Вывести все уникальные имена ролей пользователей.
-- 2)+ Подсчитать число машин у каждого пользователя. Вывести в формате User full name (username + пробел + user surname) | Число машин у пользователя.
-- 3)+ Подсчитать для каждого диллера число машин, старше 2018 года производства с красным кузовом.
-- 4)+ Найти пользователей не из Беларуси и России, у которых есть машина 2010-2015 года выпуска из Германии и купленную в диллере не в Германии с объемом двигателя больше 3 литра.
-- 5)+ Определить логины пользователей, имеющих больше 3 машин.
-- 6)+ Вывести уникальных диллеров с подсчитанной суммой стоимостей машин, связанных с ними.
-- 7)+ Подсчитать количество уникальных пользователей, владеющих хотя бы одной машиной, стоимость которой превышает среднюю стоимость всех машин.

select distinct role_name
from m_users join m_roles on m_users.id = m_roles.user_id;

select concat(username,' ', surname) as User_full_name, count(m_cars.user_id)
from m_cars join m_users on m_cars.user_id = m_users.id
group by User_full_name;

select m_auto_dealer.name, count(m_cars.dealer_id)
from m_auto_dealer left join m_cars on m_auto_dealer.id = m_cars.dealer_id
left join m_body mb on m_cars.id = mb.car_id
where mb.color='red' and mb.created>to_timestamp('00-00-2018', 'dd-mm-yyyy')
group by m_auto_dealer.name;

-- select  username
-- from (select m_users.username
--     from m_users left join l_user_location on m_users.id=l_user_location.user_id
--     where l_user_location.location_id!=1 and l_user_location.location_id!=2)
-- as mu
-- where username in (select m_users.username
--     from m_users left join m_cars mc on m_users.id = mc.user_id
--     left join m_body mb on mc.id = mb.car_id left join
--     l_car_location lcl on mb.car_id = lcl.car_id left join
--     m_engine me on mc.id = me.car_id left join
--     m_auto_dealer mad on mc.dealer_id = mad.id left join
--     l_auto_dealer_location ladl on mad.id = ladl.auto_dealer_id
--     where mb.created>to_timestamp('00-00-2010', 'dd-mm-yyyy') and
--     mb.created<to_timestamp('00-00-2015', 'dd-mm-yyyy') and lcl.location_id=3
--     and ladl.location_id!=3 and me.volume>3);

select distinct username
from m_users left join l_user_location lul on m_users.id = lul.user_id
left join m_cars mc on m_users.id = mc.user_id
left join m_body mb on mc.id = mb.car_id
left join m_engine me on mc.id = me.car_id
left join l_car_location lcl on mb.car_id = lcl.car_id
left join m_auto_dealer mad on mc.dealer_id = mad.id
left join l_auto_dealer_location ladl on mad.id = ladl.auto_dealer_id
where lul.location_id!=1 and lul.location_id!=2
and mb.created>to_timestamp('00-00-2010', 'dd-mm-yyyy')
and mb.created<to_timestamp('00-00-2015', 'dd-mm-yyyy')
and lcl.location_id=3
and ladl.location_id!=3
and me.volume>3;

select login
from m_users join m_cars mc on m_users.id = mc.user_id
group by login
having count(mc.user_id)>3;

select distinct name, sum(price)
from m_auto_dealer join m_cars mc on m_auto_dealer.id = mc.dealer_id
group by name;

select distinct count(m_user.count_user) as count_user
from (select m_users.id, count(m_users.id) as count_user
      from m_users join m_cars mc on m_users.id = mc.user_id
      group by m_users.id
      having count(mc.user_id)>=1) as m_user
join m_cars on m_user.id=m_cars.user_id
where price>(select avg(price)
             from m_cars)
