insert INTO public.users (id, created_at,update_at,email,"name","password") VALUES
(1,NOW(),NOW(),'rafamola@gmail.com','Rafael Marinho','$2a$10$e2g5dw0.aBtx5EvxbrImeePyHk3BVqwUIlovOo6RORXKbHWO0zq5i');


INSERT INTO public.user_profile_list (user_id,profile_list) VALUES
(1,1)
,(1,2);


insert INTO public.category (id,created_at,update_at,"name",user_id) VALUES
(1,NOW(),NOW(),'Educação',1)
,(2,NOW(),NOW(),'Diversão',1)
