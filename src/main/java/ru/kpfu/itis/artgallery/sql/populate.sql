INSERT INTO public.users (id, avatar_path, name, password, role, login, sign_in_provider) VALUES
  (7, null, 'Vladislav', '$2a$10$FbGShEHKRkbZjkHTUavx9uF0/A9pbIw84cDF5iayr1kiljapQkLMG', 'ADMIN',
   'vladislavtoporov@gmail.com', null);
INSERT INTO public.users (id, avatar_path, name, password, role, login, sign_in_provider) VALUES
  (24, null, 'v', '$2a$10$5oR9BfPysi3y8IVd5FdnyOTLIgZuC8DITlC/HKo7YsHMdXoSwcOJ.', 'SUPERVISER',
   'vladislavtoporov2@gmail.com', null);


INSERT INTO public.exposition (id, description, finish, name, price, start, owner_id, picture) VALUES (9, '<p>Экспозиция художников импрессионистов</p>

<p>Экспозиция художников импрессионистов</p>
', '2018-05-31', 'Экспозиция художников импрессионистов', 0, '2018-05-01', 7, null);
INSERT INTO public.exposition (id, description, finish, name, price, start, owner_id, picture) VALUES (8, '<p>Экспозиция художников импрессионистов</p>

<p>Экспозиция художников импрессионистов</p>
', '2018-07-01', 'Экспозиция художников 2', 0, '2018-06-01', 7, null);

INSERT INTO public.exhibit (id, content, count_votes, name, sum_votes, ts, author_id, exposition_id, picture) VALUES (1, '<p>Описание экспоната 1</p>
', 0, 'Экспонат 1', 0, '2018-05-04 21:03:15.630266', 7, 8,
                                                                                                                      'image/upload/v1526671347/tu59e38m5yck2tepxkkd.jpg#22d0b3b01be8e120f7136312ce8bdbed48709958');
INSERT INTO public.exhibit (id, content, count_votes, name, sum_votes, ts, author_id, exposition_id, picture) VALUES (2, '<p>Описание экспоната 1</p>
', 0, 'Экспонат 2', 0, '2018-05-04 21:03:15.630266', 7, 9,
                                                                                                                      'image/upload/v1526671347/tu59e38m5yck2tepxkkd.jpg#22d0b3b01be8e120f7136312ce8bdbed48709958');


INSERT INTO public.news (id, content, header, preview, ts, author_id)
VALUES (1, '<p>text<p>', 'header', '<p>text<p>', '2018-05-11 11:18:47.651739', 7);
INSERT INTO public.news (id, content, header, preview, ts, author_id) VALUES (2, '<p>this is content&nbsp;</p>

<p><img alt="ffff" src="http://www.baeldung.com/wp-content/uploads/2018/03/201801_APM_ads_set_b_Java2_300.jpg" style="float:right; height:250px; width:300px" /></p>
', 'ssbsgbxb', '<p>this is preview</p>
', '2018-05-22 12:56:57.127881', 7);

INSERT INTO public.private_message (id, content, ts, recipient_id, sender_id, header, is_read)
VALUES (3, '<p>hello again<br></p>', '2018-06-07 12:37:11.629121', 7, 7, 'hello again', true);
INSERT INTO public.private_message (id, content, ts, recipient_id, sender_id, header, is_read)
VALUES (1, '<p>Text</p>', '2018-05-11 10:52:26.993576', 7, 7, 'Subject', true);
INSERT INTO public.private_message (id, content, ts, recipient_id, sender_id, header, is_read)
VALUES (4, '<p>hello</p>', '2018-06-07 13:18:56.919336', 24, 7, 'subject', false);
INSERT INTO public.private_message (id, content, ts, recipient_id, sender_id, header, is_read)
VALUES (2, '<p>hello it is me</p>', '2018-05-22 12:57:57.014979', 7, 7, 'hello', true);

INSERT INTO public.ticket (id, content, header, ticket_status, ts, recipient_id, answer, manager_id)
VALUES (7, 'hello', 'hello', 'FEEDBACK', '2018-05-26 10:13:49.800362', 7, null, 7);
INSERT INTO public.ticket (id, content, header, ticket_status, ts, recipient_id, answer, manager_id)
VALUES (8, 'hello2', 'hello2', 'FEEDBACK', '2018-05-26 10:27:24.123485', 7, null, 7);
INSERT INTO public.ticket (id, content, header, ticket_status, ts, recipient_id, answer, manager_id)
VALUES (9, 'content', 'hello', 'FEEDBACK', '2018-06-07 13:21:12.589454', 7, '<p>ffff</p>', 7);
INSERT INTO public.ticket (id, content, header, ticket_status, ts, recipient_id, answer, manager_id)
VALUES (6, 'hello', 'иоор', 'PROCESSING', '2018-05-26 10:05:50.879738', 7, '<p>answer</p>', 7);


INSERT INTO public.file (id, content_type, file, name, ts, exhibit_id, message_id, ticket_id, user_id, format) VALUES
  (20, 'image', 'image/upload/v1526747844/fv77mvespqz8eti01ipl.jpg', 'photo3', '2018-05-19 19:37:24.581231', 1, null,
   null, 7, 'jpg');
INSERT INTO public.file (id, content_type, file, name, ts, exhibit_id, message_id, ticket_id, user_id, format) VALUES
  (19, 'audio', 'video/upload/v1526747403/pimrtls81sumeusuchbg.mp3', 'audio2', '2018-05-19 19:30:05.190987', 1, null,
   null, 7, 'mp3');
INSERT INTO public.file (id, content_type, file, name, ts, exhibit_id, message_id, ticket_id, user_id, format) VALUES
  (17, 'video', 'video/upload/v1526743928/dy8fmiilzzxatfrncgey.mp4', 'video1', '2018-05-19 18:32:10.295190', 1, null,
   null, 7, 'mp4');
INSERT INTO public.file (id, content_type, file, name, ts, exhibit_id, message_id, ticket_id, user_id, format) VALUES
  (15, 'image', 'image/upload/v1526671342/bpybx9z6kmorik2vxoi0.jpg', 'photo1', '2018-05-18 22:22:23.046239', 1, null,
   null, 7, 'jpg');
INSERT INTO public.file (id, content_type, file, name, ts, exhibit_id, message_id, ticket_id, user_id, format) VALUES
  (18, 'audio', 'video/upload/v1526746869/k0chz6zw03cdmnamaule.mp3', 'audio1', '2018-05-19 19:21:11.706900', 1, null,
   null, 7, 'mp3');
INSERT INTO public.file (id, content_type, file, name, ts, exhibit_id, message_id, ticket_id, user_id, format) VALUES
  (16, 'image', 'image/upload/v1526671347/tu59e38m5yck2tepxkkd.jpg', 'photo2', '2018-05-18 22:22:28.392659', 1, null,
   null, 7, 'jpg');

