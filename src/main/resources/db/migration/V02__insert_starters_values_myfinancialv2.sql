USE d6l5tr0on84g0a;


insert INTO public.users (id, created_at,update_at,email,"name","password") VALUES
(1,NOW(),NOW(),'rafamola@gmail.com','Rafael Marinho','$2a$10$e2g5dw0.aBtx5EvxbrImeePyHk3BVqwUIlovOo6RORXKbHWO0zq5i');


INSERT INTO public.user_profile_list (user_id,profile_list) VALUES
(1,1)
,(1,2);


insert INTO public.category (id,created_at,update_at,"name",user_id) VALUES
(1,'2020-03-23 16:13:46.794','2020-03-23 16:13:46.794','Educação',1)
,(2,'2020-03-23 16:13:46.828','2020-03-23 16:13:46.828','Diversão',1)
