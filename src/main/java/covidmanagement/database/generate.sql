CREATE TABLE HoKhau(
	MaHoKhau serial NOT NULL,
	SoNha varchar(20),
	Ngach varchar(20),
	Ngo varchar(20),
	Duong varchar(50) NOT NULL,
	Phuong varchar(20) NOT NULL,
	Quan varchar(20) NOT NULL,
	ThanhPho varchar(20) NOT NULL,
 	CONSTRAINT PK_HoKhau PRIMARY KEY (MaHoKhau),
 	CONSTRAINT DiaChi UNIQUE(SoNha, Ngach, Ngo, Duong, Phuong, Quan, ThanhPho)
);

CREATE TABLE NhanKhau(
	MaNhanKhau serial NOT NULL,
	HoTen varchar(50) NOT NULL,
	NgaySinh date NOT NULL,
	GioiTinh varchar(10) NOT NULL,
	CMND_CCCD varchar(20) UNIQUE,
	SDT varchar(15),
	QuocTich varchar(20) NOT NULL,
	TonGiao varchar(20) DEFAULT 'Không',
	NguyenQuan varchar(100),
	MaHoKhau int NOT NULL,
	LaChuHo boolean NOT NULL,
	QuanHeVoiChuHo varchar(20) DEFAULT 'Chủ hộ',
	NgheNghiep varchar(20) NULL,
 	CONSTRAINT PK_NhanKhau PRIMARY KEY (MaNhanKhau)
);

CREATE TABLE CachLy(
	MaCachLy serial NOT NULL,
	MaNhanKhau int NOT NULL,
	BatDau date NOT NULL,
	KetThuc date NOT NULL,
	DiaDiem varchar(100) NOT NULL,
 	CONSTRAINT PK_CachLy PRIMARY KEY (MaCachLy)
);



CREATE TABLE KhaiBao(
	MaKhaiBao serial NOT NULL,
	MaNhanKhau int NOT NULL,
	Diemkhaibao varchar(50) NOT NULL,
	NgayKhaiBao date NOT NULL,
	BHYT boolean NOT NULL,
	LichTrinh varchar(500),
	Trieuchung boolean NOT NULL,
	TiepXucNguoiBenh boolean NOT NULL,
	TiepXucNguoiTuVungDich boolean NOT NULL,
	TiepXucNguoiCoBieuHien boolean NOT NULL,
	BenhNen varchar(200) NULL,
 	CONSTRAINT PK_KhaiBao PRIMARY KEY (MaKhaiBao)
);



CREATE TABLE XetNghiem(
	MaXetNghiem serial NOT NULL,
	MaNhanKhau int NOT NULL,
	ThoiGian date NOT NULL,
	DiaDiem varchar(100) NOT NULL,
	KetQua varchar(20) NOT NULL,
 	CONSTRAINT PK_XetNghiem PRIMARY KEY (MaXetNghiem)
);

CREATE TABLE TaiKhoan(
    username varchar(100) NOT NULL UNIQUE,
    password varchar(100) NOT NULL,
    CONSTRAINT PK_TaiKhoan PRIMARY KEY(username)
);

ALTER TABLE CachLy   ADD  CONSTRAINT FK_CachLy_NhanKhau FOREIGN KEY(MaNhanKhau)
REFERENCES NhanKhau (MaNhanKhau);

--ALTER TABLE KhaiBao   ADD  CONSTRAINT FK_KhaiBao_NhanKhau FOREIGN KEY(MaNhanKhau)
--REFERENCES NhanKhau (MaNhanKhau);

ALTER TABLE NhanKhau   ADD  CONSTRAINT FK_NhanKhau_HoKhau FOREIGN KEY(MaHoKhau)
REFERENCES HoKhau (MaHoKhau);

ALTER TABLE XetNghiem   ADD  CONSTRAINT FK_XetNghiem_NhanKhau FOREIGN KEY(MaNhanKhau)
REFERENCES NhanKhau (MaNhanKhau);

--INSERT INTO HoKhau (SoNha, Ngach, Ngo, Duong, Phuong, Quan, ThanhPho)
--VALUES (2, NULL, NULL, 'Tạ Quang Bửu', 'Bách Khoa', 'Hai Bà Trưng', 'Hà Nội');
--INSERT INTO HoKhau (SoNha, Ngach, Ngo, Duong, Phuong, Quan, ThanhPho)
--VALUES (4, NULL, 14, 'Tạ Quang Bửu', 'Bách Khoa', 'Hai Bà Trưng', 'Hà Nội');
--INSERT INTO HoKhau (SoNha, Ngach, Ngo, Duong, Phuong, Quan, ThanhPho)
--VALUES (7, NULL, NULL, 'Tạ Quang Bửu', 'Bách Khoa', 'Hai Bà Trưng', 'Hà Nội');
--INSERT INTO HoKhau (SoNha, Ngach, Ngo, Duong, Phuong, Quan, ThanhPho)
--VALUES (12, NULL, 7, 'Tạ Quang Bửu', 'Bách Khoa', 'Hai Bà Trưng', 'Hà Nội');
--INSERT INTO HoKhau (SoNha, Ngach, Ngo, Duong, Phuong, Quan, ThanhPho)
--VALUES (23, 2, 32, 'Tạ Quang Bửu', 'Bách Khoa', 'Hai Bà Trưng', 'Hà Nội');
--INSERT INTO HoKhau (SoNha, Ngach, Ngo, Duong, Phuong, Quan, ThanhPho)
--VALUES (6, NULL, NULL, 'Tạ Quang Bửu', 'Bách Khoa', 'Hai Bà Trưng', 'Hà Nội');

INSERT INTO TaiKhoan (username, password)
VALUES ('admin', '123456');