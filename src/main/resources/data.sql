insert into Project
(beginDate, createdAt, creatorEmail, creatorName, creatorPhone, description, dueDate, published, sponsoredAmount, sponsoredCount, status, targetAmount, title, updatedAt, id)
values
('2021-01-01 00:00:00', CURRENT_TIMESTAMP(), 'abc@naver.com',   '홍길동', '010-1234-5678', '프로젝트설명1', '2021-01-03 00:00:00', true, 2000000, 0, 'IN_PROGRESS', 1000000, '첫번째 프로젝트', CURRENT_TIMESTAMP(), random_uuid()),
('2021-01-11 00:00:00', CURRENT_TIMESTAMP(), 'abc@hanmail.net', '홍길순', '010-2345-6789', '프로젝트설명2', '2021-01-03 00:00:00', true, 3000000, 0, 'IN_PROGRESS', 30000000, '두번째 프로젝트', CURRENT_TIMESTAMP(), random_uuid()),
('2021-01-21 00:00:00', CURRENT_TIMESTAMP(), 'abc@daum.com',    '구기자', '010-2345-6789', '프로젝트설명3', '2021-02-11 00:00:00', true, 4000000, 0, 'IN_PROGRESS', 300000, '두번째 프로젝트', CURRENT_TIMESTAMP(), random_uuid()),
('2021-01-31 00:00:00', CURRENT_TIMESTAMP(), 'abc@gmail.com', '손예진', '010-1111-2222', '프로젝트설명4', '2021-02-11 00:00:00', true, 5000000, 0, 'IN_PROGRESS', 300000, '두번째 프로젝트', CURRENT_TIMESTAMP(), random_uuid()),
('2021-02-01 00:00:00', CURRENT_TIMESTAMP(), 'ddd@naver.com', '김태희', '010-1111-1111', '프로젝트설명5', '2021-02-11 00:00:00', true, 6000000, 0, 'IN_PROGRESS', 300000, '두번째 프로젝트', CURRENT_TIMESTAMP(), random_uuid()),
('2021-02-11 00:00:00', CURRENT_TIMESTAMP(), 'eee@naver.com', '한가인', '010-1234-9999', '프로젝트설명6', '2021-02-11 00:00:00', true, 7000000, 0, 'IN_PROGRESS', 300000, '두번째 프로젝트', CURRENT_TIMESTAMP(), random_uuid()),
('2021-02-21 00:00:00', CURRENT_TIMESTAMP(), 'fff@naver.com', '고수', '010-2345-6789', '프로젝트설명7', '2021-02-11 00:00:00', true, 8000000, 0, 'IN_PROGRESS', 300000, '두번째 프로젝트', CURRENT_TIMESTAMP(), random_uuid()),
('2021-02-28 00:00:00', CURRENT_TIMESTAMP(), 'ggg@naver.com', '원빈', '010-2345-6789', '프로젝트설명8', '2021-02-11 00:00:00', true, 9000000, 0, 'IN_PROGRESS', 300000, '두번째 프로젝트', CURRENT_TIMESTAMP(), random_uuid()),
('2021-03-01 00:00:00', CURRENT_TIMESTAMP(), 'hhh@naver.com', '이나영', '010-2345-6789', '프로젝트설명2', '2021-02-11 00:00:00', true, 10000000, 0, 'IN_PROGRESS', 300000, '두번째 프로젝트', CURRENT_TIMESTAMP(), random_uuid()),
('2021-03-11 00:00:00', CURRENT_TIMESTAMP(), 'iii@naver.com', '텀블벅', '010-2345-6789', '프로젝트설명2', '2021-02-11 00:00:00', true, 11000000, 0, 'IN_PROGRESS', 300000, '두번째 프로젝트', CURRENT_TIMESTAMP(), random_uuid()),
('2021-03-21 00:00:00', CURRENT_TIMESTAMP(), 'jjj@naver.com', 'john a', '010-2345-6789', '프로젝트설명2', '2021-02-11 00:00:00', true, 12000000, 0, 'IN_PROGRESS', 300000, '두번째 프로젝트', CURRENT_TIMESTAMP(), random_uuid()),
('2021-03-31 00:00:00', CURRENT_TIMESTAMP(), 'mmm@naver.com', 'tom b', '010-2345-6789', '프로젝트설명2', '2021-02-11 00:00:00', true, 13000000, 0, 'IN_PROGRESS', 300000, '두번째 프로젝트', CURRENT_TIMESTAMP(), random_uuid()),
('2021-04-11 00:00:00', CURRENT_TIMESTAMP(), 'nnn@naver.com', 'steve c', '010-2345-6789', '프로젝트설명2', '2021-02-11 00:00:00', true, 14000000, 0, 'IN_PROGRESS', 300000, '두번째 프로젝트', CURRENT_TIMESTAMP(), random_uuid());