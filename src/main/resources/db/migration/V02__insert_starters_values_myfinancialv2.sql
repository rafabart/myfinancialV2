insert INTO public.users (created_at,update_at,email,"name","password") VALUES
(NOW(),NOW(),'rafamola@gmail.com','Rafael Marinho','$2a$10$e2g5dw0.aBtx5EvxbrImeePyHk3BVqwUIlovOo6RORXKbHWO0zq5i');


INSERT INTO public.user_profile_list (user_id,profile_list) VALUES
(1,1)
,(1,2);


insert INTO public.category (created_at,update_at,"name",user_id) VALUES
(NOW(),NOW(),'Educação',1)
,(NOW(),NOW(),'Diversão',1)
