CREATE TABLE HoKhau(
	MaHoKhau serial NOT NULL,
	SoNha int NOT NULL,
	Ngach int,
	Ngo int,
	Duong varchar(50) NOT NULL,
	Phuong varchar(20) NOT NULL,
	Quan varchar(20) NOT NULL,
	ThanhPho varchar(20) NOT NULL,
 	CONSTRAINT PK_HoKhau PRIMARY KEY (MaHoKhau)
);

CREATE TABLE NhanKhau(
	MaNhanKhau serial NOT NULL,
	HoTen varchar(50) NOT NULL,
	NgaySinh date NOT NULL,
	GioiTinh varchar(10) NOT NULL,
	CMND_CCCD varchar(20) NULL,
	SDT varchar(15) NULL,
	QuocTich varchar(20) NOT NULL,
	TonGiao varchar(20) NOT NULL,
	NguyenQuan varchar(100) NOT NULL,
	MaHoKhau int NOT NULL,
	LaChuHo boolean NOT NULL,
	QuanHeVoiChuHo varchar(20),
	NgheNghiep varchar(20) NULL,
 	CONSTRAINT PK_NhanKhau PRIMARY KEY (MaNhanKhau)
);

CREATE TABLE ChuHo(
	MaHoKhau int NOT NULL,
	MaChuHo int NOT NULL,
	CONSTRAINT PK_Chu_Ho PRIMARY KEY (MaHoKhau, MaChuHo)
);


CREATE TABLE CachLy(
	MaCachLy serial NOT NULL,
	MaNhanKhau int NOT NULL,
	TenNguoiCachLy varchar(50) NOT NULL,
	BatDau date NOT NULL,
	KetThuc date NOT NULL,
	DiaDiem varchar(100) NOT NULL,
 	CONSTRAINT PK_CachLy PRIMARY KEY (MaCachLy)
);



CREATE TABLE KhaiBao(
	MaKhaiBao serial NOT NULL,
	MaNhanKhau int NOT NULL,
	TenNguoiKhaiBao varchar(50) NOT NULL,
	NgayKhaiBao date NOT NULL,
	CMND_CCCD varchar(20) NOT NULL,
	BHYT boolean NOT NULL,
	LichTrinh varchar(500) NULL,
	Sot boolean NOT NULL,
	Ho boolean NOT NULL,
	KhoTho boolean NOT NULL,
	ViemPhoi boolean NOT NULL,
	DauHong boolean NOT NULL,
	MetMoi boolean NOT NULL,
	TiepXucNguoiBenh boolean NOT NULL,
	TiepXucNguoiTuVungDich boolean NOT NULL,
	TiepXucNguoiCoBieuHien boolean NOT NULL,
	BenhNen varchar(200) NULL,
 	CONSTRAINT PK_KhaiBao PRIMARY KEY (MaKhaiBao)
);



CREATE TABLE XetNghiem(
	MaXetNghiem serial NOT NULL,
	MaNhanKhau int NOT NULL,
	HoTen varchar(50) NOT NULL,
	ThoiGian date NOT NULL,
	DiaDiem varchar(100) NOT NULL,
	KetQua varchar(20) NOT NULL,
 	CONSTRAINT PK_XetNghiem PRIMARY KEY (MaXetNghiem)
);

ALTER TABLE CachLy   ADD  CONSTRAINT FK_CachLy_NhanKhau FOREIGN KEY(MaNhanKhau)
REFERENCES NhanKhau (MaNhanKhau);

ALTER TABLE KhaiBao   ADD  CONSTRAINT FK_KhaiBao_NhanKhau FOREIGN KEY(MaNhanKhau)
REFERENCES NhanKhau (MaNhanKhau);

ALTER TABLE NhanKhau   ADD  CONSTRAINT FK_NhanKhau_HoKhau FOREIGN KEY(MaHoKhau)
REFERENCES HoKhau (MaHoKhau);

ALTER TABLE XetNghiem   ADD  CONSTRAINT FK_XetNghiem_NhanKhau FOREIGN KEY(MaNhanKhau)
REFERENCES NhanKhau (MaNhanKhau);

ALTER TABLE ChuHo ADD CONSTRAINT FK_ChuHo_HoKhau FOREIGN KEY(MaHoKhau)
REFERENCES HoKhau (MaHoKhau);

ALTER TABLE ChuHo ADD CONSTRAINT FK_ChuHo_NhanKhau FOREIGN KEY(MaChuHo)
REFERENCES NhanKhau (MaNhanKhau);


