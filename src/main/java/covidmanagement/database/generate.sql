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
	NgheNghiep varchar(50),
	GhiChu varchar(100),
 	CONSTRAINT PK_NhanKhau PRIMARY KEY (MaNhanKhau)
);

CREATE TABLE CachLy(
	MaCachLy serial NOT NULL,
	MaNhanKhau int NOT NULL,
	BatDau date NOT NULL,
	KetThuc date NOT NULL,
	DiaDiem varchar(100) NOT NULL,
	MucDo varchar(20) NOT NULL,
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

CREATE TABLE LichSu(
    ThoiGian timestamp DEFAULT NOW() PRIMARY KEY,
    ThongTin varchar(200) NOT NULL
);

ALTER TABLE CachLy   ADD  CONSTRAINT FK_CachLy_NhanKhau FOREIGN KEY(MaNhanKhau)
REFERENCES NhanKhau (MaNhanKhau);

ALTER TABLE KhaiBao   ADD  CONSTRAINT FK_KhaiBao_NhanKhau FOREIGN KEY(MaNhanKhau)
REFERENCES NhanKhau (MaNhanKhau);

ALTER TABLE NhanKhau   ADD  CONSTRAINT FK_NhanKhau_HoKhau FOREIGN KEY(MaHoKhau)
REFERENCES HoKhau (MaHoKhau);

ALTER TABLE XetNghiem   ADD  CONSTRAINT FK_XetNghiem_NhanKhau FOREIGN KEY(MaNhanKhau)
REFERENCES NhanKhau (MaNhanKhau);

INSERT INTO TaiKhoan (username, password)
VALUES ('admin', '123456');
--Hộ khẩu mẫu:
INSERT INTO HoKhau (SoNha, Ngach, Ngo, Duong, Phuong, Quan, ThanhPho)
VALUES ('2', NULL, NULL, 'Tạ Quang Bửu', 'Bách Khoa', 'Hai Bà Trưng', 'Hà Nội');
INSERT INTO HoKhau (SoNha, Ngach, Ngo, Duong, Phuong, Quan, ThanhPho)
VALUES ('4A', NULL, '14', 'Tạ Quang Bửu', 'Bách Khoa', 'Hai Bà Trưng', 'Hà Nội');
INSERT INTO HoKhau (SoNha, Ngach, Ngo, Duong, Phuong, Quan, ThanhPho)
VALUES ('7', '25', '14', 'Tạ Quang Bửu', 'Bách Khoa', 'Hai Bà Trưng', 'Hà Nội');
--Hộ khẩu 1:
INSERT INTO NhanKhau(hoten, ngaysinh, gioitinh, cmnd_cccd, sdt, quoctich, tongiao, nguyenquan, mahokhau, lachuho, quanhevoichuho, nghenghiep, ghichu)
VALUES ('Phan Đức Hùng', '1988-03-14', 'Nam', '125687237', '0937926636', 'Việt Nam', 'Không', 'Thái Nguyên', 1, TRUE, 'Chủ hộ', 'Giáo viên', NULL);
INSERT INTO NhanKhau(hoten, ngaysinh, gioitinh, cmnd_cccd, sdt, quoctich, tongiao, nguyenquan, mahokhau, lachuho, quanhevoichuho, nghenghiep, ghichu)
VALUES ('Nguyễn Thị Hoàng Lan', '1990-09-24', 'Nữ', '143856925', '0973562864', 'Việt Nam', 'Không', 'Nghệ An', 1, FALSE, 'Vợ', 'Giáo viên', NULL);
INSERT INTO NhanKhau(hoten, ngaysinh, gioitinh, cmnd_cccd, sdt, quoctich, tongiao, nguyenquan, mahokhau, lachuho, quanhevoichuho, nghenghiep, ghichu)
VALUES ('Phan Đức Tài', '2014-01-06', 'Nam', NULL, NULL, 'Việt Nam', 'Không', 'Thái Nguyên', 1, FALSE, 'Con ruột', 'Học sinh', NULL);
INSERT INTO NhanKhau(hoten, ngaysinh, gioitinh, cmnd_cccd, sdt, quoctich, tongiao, nguyenquan, mahokhau, lachuho, quanhevoichuho, nghenghiep, ghichu)
VALUES ('Phan Thị Hoàng Mai', '2018-08-18', 'Nữ', NULL, NULL, 'Việt Nam', 'Không', 'Thái Nguyên', 1, FALSE, 'Con ruột', NULL, NULL);
--Hộ khẩu 2:
INSERT INTO NhanKhau(hoten, ngaysinh, gioitinh, cmnd_cccd, sdt, quoctich, tongiao, nguyenquan, mahokhau, lachuho, quanhevoichuho, nghenghiep, ghichu)
VALUES ('Nguyễn Tiến Dũng', '1970-07-09', 'Nam', '121476239', '0957314561', 'Việt Nam', 'Không', 'Hà Nội', 2, TRUE, 'Chủ hộ', 'Kỹ sư', NULL);
INSERT INTO NhanKhau(hoten, ngaysinh, gioitinh, cmnd_cccd, sdt, quoctich, tongiao, nguyenquan, mahokhau, lachuho, quanhevoichuho, nghenghiep, ghichu)
VALUES ('Nguyễn Thị Hà Giang', '1974-05-12', 'Nữ', '123875339', '0914768245', 'Việt Nam', 'Không', 'Quảng Ninh', 2, FALSE, 'Vợ', 'Nhân viên văn phòng', NULL);
INSERT INTO NhanKhau(hoten, ngaysinh, gioitinh, cmnd_cccd, sdt, quoctich, tongiao, nguyenquan, mahokhau, lachuho, quanhevoichuho, nghenghiep, ghichu)
VALUES ('Nguyễn Việt Hoàng', '1998-11-30', 'Nam', '186493995', '0914783928', 'Việt Nam', 'Không', 'Hà Nội', 2, FALSE, 'Con ruột', 'Nhân viên marketing', NULL);
INSERT INTO NhanKhau(hoten, ngaysinh, gioitinh, cmnd_cccd, sdt, quoctich, tongiao, nguyenquan, mahokhau, lachuho, quanhevoichuho, nghenghiep, ghichu)
VALUES ('Nguyễn Anh Đức', '2001-04-21', 'Nam', '198238421', '0957342718', 'Việt Nam', 'Không', 'Hà Nội', 2, FALSE, 'Con ruột', 'Sinh viên', NULL);
--Hộ khẩu 3:
INSERT INTO NhanKhau(hoten, ngaysinh, gioitinh, cmnd_cccd, sdt, quoctich, tongiao, nguyenquan, mahokhau, lachuho, quanhevoichuho, nghenghiep, ghichu)
VALUES ('Trần Văn Hưng', '1990-06-19', 'Nam', '135487914', '0978178918', 'Việt Nam', 'Không', 'Điện Biên', 3, TRUE, 'Chủ hộ', 'Công nhân', NULL);
INSERT INTO NhanKhau(hoten, ngaysinh, gioitinh, cmnd_cccd, sdt, quoctich, tongiao, nguyenquan, mahokhau, lachuho, quanhevoichuho, nghenghiep, ghichu)
VALUES ('Phạm Thị Bích', '1991-02-04', 'Nữ', '136237498', '0987266183', 'Việt Nam', 'Không', 'Ninh Bình', 3, FALSE, 'Vợ', 'Bán hàng', NULL);
INSERT INTO NhanKhau(hoten, ngaysinh, gioitinh, cmnd_cccd, sdt, quoctich, tongiao, nguyenquan, mahokhau, lachuho, quanhevoichuho, nghenghiep, ghichu)
VALUES ('Trần Thanh Huyền', '2021-01-06', 'Nữ', NULL, NULL, 'Việt Nam', 'Không', 'Điện Biên', 3, FALSE, 'Con ruột', NULL, NULL);
--Khai báo mẫu:
INSERT INTO KhaiBao(manhankhau, diemkhaibao, ngaykhaibao, bhyt, lichtrinh, trieuchung, tiepxucnguoibenh, tiepxucnguoituvungdich, tiepxucnguoicobieuhien, benhnen)
VALUES (5, 'Trung tâm y tế phường Bách Khoa', '2023-01-24', TRUE, 'Quận Hoàng Mai, Quận Hoàn Kiếm', TRUE, TRUE, TRUE, TRUE, 'Không có bệnh nền');
INSERT INTO KhaiBao(manhankhau, diemkhaibao, ngaykhaibao, bhyt, lichtrinh, trieuchung, tiepxucnguoibenh, tiepxucnguoituvungdich, tiepxucnguoicobieuhien, benhnen)
VALUES (8, 'Trung tâm y tế phường Bách Khoa', '2023-01-30', TRUE, 'Quận Hoàng Mai', TRUE, FALSE, TRUE, TRUE, 'Không có bệnh nền');
INSERT INTO KhaiBao(manhankhau, diemkhaibao, ngaykhaibao, bhyt, lichtrinh, trieuchung, tiepxucnguoibenh, tiepxucnguoituvungdich, tiepxucnguoicobieuhien, benhnen)
VALUES (6, 'Trung tâm y tế phường Bách Khoa', '2023-01-30', TRUE, 'Quận Hoàn Kiếm', FALSE, FALSE, TRUE, TRUE, 'Không có bệnh nền');
INSERT INTO KhaiBao(manhankhau, diemkhaibao, ngaykhaibao, bhyt, lichtrinh, trieuchung, tiepxucnguoibenh, tiepxucnguoituvungdich, tiepxucnguoicobieuhien, benhnen)
VALUES (7, 'Trung tâm y tế phường Bách Khoa', '2023-01-30', TRUE, 'Quận Đống Đa, Quận Hoàn Kiếm, Quận Thanh Xuân', FALSE, TRUE, TRUE, TRUE, 'Không có bệnh nền');
INSERT INTO KhaiBao(manhankhau, diemkhaibao, ngaykhaibao, bhyt, lichtrinh, trieuchung, tiepxucnguoibenh, tiepxucnguoituvungdich, tiepxucnguoicobieuhien, benhnen)
VALUES (2, 'Trung tâm y tế phường Bách Khoa', '2023-01-25', TRUE, 'Quận Hoàng Mai, Quận Đống Đa', FALSE, TRUE, FALSE, TRUE, 'Không có bệnh nền');
--Xét nghiệm mẫu:
INSERT INTO XetNghiem(manhankhau, thoigian, diadiem, ketqua)
VALUES (5, '2023-01-24', 'Trung tâm y tế phường Bách Khoa', 'DƯƠNG_TÍNH');
INSERT INTO XetNghiem(manhankhau, thoigian, diadiem, ketqua)
VALUES (8, '2023-01-31', 'Trung tâm y tế phường Bách Khoa', 'DƯƠNG_TÍNH');
INSERT INTO XetNghiem(manhankhau, thoigian, diadiem, ketqua)
VALUES (6, '2023-01-31', 'Trung tâm y tế phường Bách Khoa', 'ÂM_TÍNH');
INSERT INTO XetNghiem(manhankhau, thoigian, diadiem, ketqua)
VALUES (7, '2023-01-31', 'Trung tâm y tế phường Bách Khoa', 'ÂM_TÍNH');
INSERT INTO XetNghiem(manhankhau, thoigian, diadiem, ketqua)
VALUES (2, '2023-01-25', 'Trung tâm y tế phường Bách Khoa', 'DƯƠNG_TÍNH');
--Cách ly mẫu:
INSERT INTO CachLy(manhankhau, batdau, ketthuc, diadiem, mucdo)
VALUES (5, '2023-01-24', '2023-01-31', 'Tại nhà', 'F0');
INSERT INTO CachLy(manhankhau, batdau, ketthuc, diadiem, mucdo)
VALUES (8, '2023-01-31', '2023-02-07', 'Tại nhà', 'F0');
INSERT INTO CachLy(manhankhau, batdau, ketthuc, diadiem, mucdo)
VALUES (6, '2023-01-31', '2023-02-04', 'Tại nhà', 'F1');
INSERT INTO CachLy(manhankhau, batdau, ketthuc, diadiem, mucdo)
VALUES (7, '2023-01-31', '2023-02-04', 'Tại nhà', 'F1');
INSERT INTO CachLy(manhankhau, batdau, ketthuc, diadiem, mucdo)
VALUES (2, '2023-01-25', '2023-02-02', 'Tại nhà', 'F0');
