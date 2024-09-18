create database CDStation;

use CDStation

create table [user] (
	id int IDENTITY(1,1) PRIMARY KEY,
	[name] nvarchar(100),
	email varchar(100),
	phone varchar(45),
	[password] varchar(45),
	[role] int
)

create table cd_details (
	cdId int IDENTITY(1,1) PRIMARY KEY,
	cdName nvarchar(500),
	artist nvarchar(500),
	price varchar(45),
	cdCategory varchar(45),
	[status] varchar(45), 
	photo varchar(45),
	quantity int,
	sold int
)

create table cart (
	cId int IDENTITY(1,1) PRIMARY KEY,
	cdId int FOREIGN KEY REFERENCES cd_details(cdId),
	[uId] int FOREIGN KEY REFERENCES [user](id),
	total_price float,
	quantity int
)

create table [order] (
	id int IDENTITY(1,1) PRIMARY KEY,
	order_id varchar(45),
	[uId] int FOREIGN KEY REFERENCES [user](id),
	[address] nvarchar(500),
	cd_name nvarchar(500),
	artist nvarchar(500),
	quantity int,
	price varchar(45),
	payment varchar(45),
	[date] date
)

insert into [user](name, email, phone, password, role) values
('Admin', 'admin@gmail.com', '0947387389', 'admin', 1),
('Nhat Thang', 't@gmail.com', '0123456789', '020804', 2)

insert into cd_details(cdName, artist, price, cdCategory, status, photo, quantity, sold) values
('The Tortured Poets Department (The Manuscript)', 'Taylor Swift', '450000', 'New', 'Active', 'new1.jpg', 5, 0),
('Hit Me Hard And Soft', 'Billie Eilish', '400000', 'New', 'Active', 'new2.jpg', 3, 0),
('Cowboy Carter', 'Beyonce', '420000', 'New', 'Active', 'new3.jpg', 4, 0),
('Eternal Sunshine', 'Ariana Grande', '400000', 'New', 'Active', 'new4.jpg', 3, 0),
('Neon Pill', 'Cage The Elephant', '420000', 'New', 'Active', 'new5.jpg', 5, 0),
('Everything I Thought It Was', 'Justin Timberlake', '450000', 'New', 'Active', 'new6.jpg', 5, 0),
('Reasonable Woman', 'Sia', '420000', 'New', 'Active', 'new7.jpg', 5, 0),
('Music Box: 30th Anniversary Expanded Edition', 'Mariah Carey', '950000', 'New', 'Active', 'new8.jpg', 2, 0),
('Autumn Variations', 'Ed Sheeran', '450000', 'New', 'Active', 'new9.jpg', 3, 0),
('Chemistry', 'Kelly Clarkson', '390000', 'New', 'Active', 'new10.jpg', 5, 0),
('Colours (Frosted Ice Tape)', N'Hứa Kim Tuyền', '350000', 'Trend', 'Active', 'trend1.jpg', 5, 0),
(N'Nữ Thần Mặt Trăng (MÔNANGEL)', N'Bùi Lan Hương', '350000', 'Trend', 'Active', 'trend2.jpg', 3, 0),
('Minh Tinh', N'Văn Mai Hương, Hứa Kim Tuyền', '350000', 'Trend', 'Active', 'trend3.jpg', 4, 0),
(N'Nhiên', N'Lân Nhã', '350000', 'Trend', 'Active', 'trend4.jpg', 3, 0),
('LINK (Special Gold Version)', N'Hoàng Thuỳ Linh', '450000', 'Trend', 'Active', 'trend5.jpg', 5, 0),
('CITOPIA - The 2nd Studio Album (Official Cover Ver.)', N'Phùng Khánh Linh', '350000', 'Trend', 'Active', 'trend6.jpg', 5, 0),
(N'Điều Vô Lý Thứ Nhất [Deluxe Edition] (Những Bài Hát Của Hồ Tiến Đạt)', N'Nguyên Hà', '300000', 'Trend', 'Active', 'trend7.jpg', 5, 0),
(N'Con Đường Không Tên (Cassette Tape)', N'Bức Tường', '350000', 'Trend', 'Active', 'trend8.jpg', 2, 0),
('CONG', N'Tóc Tiên', '350000', 'Trend', 'Active', 'trend9.jpg', 3, 0),
(N'Chỉ có thể là anh (Official Cover)', 'TRANG', '350000', 'Trend', 'Active', 'trend10.jpg', 5, 0),
('Listen', 'David Guetta', '400000', 'Sale', 'Active', 'sale1.jpg', 5, 0),
('Lotus (Standard)', 'Christina Aguilera', '330000', 'Sale', 'Active', 'sale2.jpg', 3, 0),
('This House Is Not For Sale', 'Bon Jovi', '200000', 'Sale', 'Active', 'sale3.jpg', 4, 0),
('The 20/20 Experience Part 2 (Deluxe Ver.)', 'Justin Timberlake', '250000', 'Sale', 'Active', 'sale4.jpg', 3, 0),
('Unbreakable (Limited Cover)', 'Janet Jackson', '350000', 'Sale', 'Active', 'sale5.jpg', 5, 0),
('Tell Me You Love Me (Standard)', 'Demi Lovato', '300000', 'Sale', 'Active', 'sale6.jpg', 5, 0),
('Liberation', 'Christina Aguilera', '320000', 'Sale', 'Active', 'sale7.jpg', 5, 0),
('Witness (Standard)', 'Katy Perry', '250000', 'Sale', 'Active', 'sale8.jpg', 2, 0),
('Love Sux', 'Avril Lavigne', '425000', 'Sale', 'Active', 'sale9.jpg', 3, 0),
(N'Tình Ca Cho Em (Có Chữ Ký)', N'Elvis Phương', '250000', 'Sale', 'Active', 'sale10.jpg', 5, 0)

select * from [user]
select * from cd_details
select * from [order]
select * from cart